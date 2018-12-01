package cn.codesheep.springbt_es6_4_2.service.impl;

import cn.codesheep.springbt_es6_4_2.model.dto.DocModel;
import cn.codesheep.springbt_es6_4_2.model.dto.BatchDoc;
import cn.codesheep.springbt_es6_4_2.model.dto.IndexModel;
import cn.codesheep.springbt_es6_4_2.model.dto.SearchModel;
import cn.codesheep.springbt_es6_4_2.model.dto.SingleDoc;
import cn.codesheep.springbt_es6_4_2.service.ISearchService;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;

@Service
public class SearchServiceImpl implements ISearchService {

	private static Logger logger = Logger.getLogger(SearchServiceImpl.class.getClass());

	@Autowired
	private RestHighLevelClient esRestHighLevelClient;

	@Autowired
	private DocModel docModel;

	@Override
	public Boolean createIndex(IndexModel indexModel) {

		// 创建索引
		CreateIndexRequest request = new CreateIndexRequest( indexModel.getIndexName() );

		// 设置分片和副本
		request.settings(Settings.builder()
				.put("index.number_of_shards", indexModel.getShardNumber())
				.put("index.number_of_replicas", indexModel.getReplicaNumber())
		);

		request.mapping( indexModel.getTypeName(), indexModel.getBuilder() );
		CreateIndexResponse createIndexResponse = null;
		try {
			createIndexResponse = esRestHighLevelClient.indices().create( request, RequestOptions.DEFAULT );
		} catch (IOException e) {
			e.printStackTrace();
		}

		return createIndexResponse.isAcknowledged();
	}

	@Override
	public Boolean existIndex(String indexName) {

		GetIndexRequest request = new GetIndexRequest();
		request.indices(indexName);
		try {
			 return esRestHighLevelClient.indices().exists( request, RequestOptions.DEFAULT );
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean deleteIndex(String indexName) {

		try {
			if ( existIndex(indexName) ) {
				DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
				DeleteIndexResponse deleteIndexResponse = esRestHighLevelClient.indices().delete( deleteIndexRequest, RequestOptions.DEFAULT );
				return deleteIndexResponse.isAcknowledged();
			} else {
				logger.info("要删除的索引不存在");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateIndex() {
		return null;
	}

	@Override
	public Boolean insertDoc( SingleDoc singleDoc ) {

		IndexRequest indexRequest = new IndexRequest( singleDoc.getIndexName(), singleDoc.getTypeName(), singleDoc.getId() )
				.source( singleDoc.getDocMap() );
		try {
			IndexResponse indexResponse = esRestHighLevelClient.index( indexRequest, RequestOptions.DEFAULT );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean insertDocBatch( BatchDoc batchDoc ) {

		BulkRequest bulkRequest = new BulkRequest();
		Iterator<Map<String,Object>> iter = batchDoc.getBatchDocMap().iterator();
		while( iter.hasNext() ) {
			Map<String,Object> docMap = iter.next();
			IndexRequest indexRequest = new IndexRequest( batchDoc.getIndexName(), batchDoc.getTypeName() )
					.source( docMap );
			bulkRequest.add(indexRequest);
		}
		try {
			BulkResponse bulkResponse = esRestHighLevelClient.bulk( bulkRequest, RequestOptions.DEFAULT );
			if (bulkResponse.hasFailures()) {
				logger.error("批量插入文档失败");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public ArrayList<Map<String, Object>> queryDocs( SearchModel searchModel ) {

		SearchRequest searchRequest = new SearchRequest( searchModel.getIndexName() );
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		MultiMatchQueryBuilder multiMatchQuery = QueryBuilders
				.multiMatchQuery( searchModel.getKeyword(), searchModel.getFields().toArray( new String[searchModel.getFields().size()] ) )
				.operator(Operator.AND);
		searchSourceBuilder.query( multiMatchQuery );
		searchSourceBuilder.from( (searchModel.getPageNum() - 1) * searchModel.getPageSize() );
		searchSourceBuilder.size( searchModel.getPageSize() );
		searchRequest.source( searchSourceBuilder );

		ArrayList<Map<String, Object>> resultList = new ArrayList<>();

		try {
			SearchResponse searchResponse = esRestHighLevelClient.search( searchRequest, RequestOptions.DEFAULT );
			SearchHits hits = searchResponse.getHits();
			SearchHit[] searchHits = hits.getHits();

			for ( SearchHit hit : searchHits ) {
				Map<String, Object> sourceMap = hit.getSourceAsMap();
				sourceMap.put( "id", hit.getId() );
				for( String field : docModel.DOC_FIELDS )
					sourceMap.put( field, hit.getSourceAsMap().get(field) );
				resultList.add( sourceMap );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}

		return resultList;
	}


	@Override
	public Boolean deleteDoc( SingleDoc singleDoc ) {

		DeleteRequest deleteRequest = new DeleteRequest( singleDoc.getIndexName(), singleDoc.getTypeName(), singleDoc.getId() );
		DeleteResponse deleteResponse = null;

		try {
			deleteResponse = esRestHighLevelClient.delete( deleteRequest, RequestOptions.DEFAULT );
		} catch (IOException e) {
			e.printStackTrace();
		}

		if( deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND ) {
			logger.info("要删除的文档并不存在");
			return false;
		}

		ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
		int total = shardInfo.getTotal();
		int successful = shardInfo.getSuccessful();
		if ( total != successful ) {
			logger.info(" number of successful shards is less than total shards");
		}

		if( 0 < shardInfo.getFailed() ) {
			for ( ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures() ) {
				logger.error( failure.reason() );
			}
			return false;
		}

		return true;
	}

	@Override
	public Boolean deleteDocBatch( BatchDoc batchDoc ) {

		BulkRequest bulkDeleteRequest = new BulkRequest();
		Iterator<String> iter = batchDoc.getDocIds().iterator();
		while( iter.hasNext() ) {
			String docId = iter.next();
			DeleteRequest deleteRequest = new DeleteRequest( batchDoc.getIndexName(), batchDoc.getTypeName(), docId );
			bulkDeleteRequest.add( deleteRequest );
		}

		try {
			BulkResponse bulkDeleteResponse = esRestHighLevelClient.bulk( bulkDeleteRequest, RequestOptions.DEFAULT );
			if ( bulkDeleteResponse.hasFailures() ) {  // 注意此处还可以对每个delete的response进行逐个判断：https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.4/java-rest-high-document-bulk.html
				logger.error("批量删除文档失败");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean updateDoc( SingleDoc singleDoc ) {

		UpdateRequest updateRequest = new UpdateRequest( singleDoc.getIndexName(), singleDoc.getTypeName(), singleDoc.getId() ).doc( singleDoc.getUpdateDocMap() );
		try {
			UpdateResponse updateResponse = esRestHighLevelClient.update( updateRequest, RequestOptions.DEFAULT );

			if( updateResponse.getResult() == DocWriteResponse.Result.UPDATED )
				return true;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
