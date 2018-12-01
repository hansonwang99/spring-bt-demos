package cn.codesheep.springbt_es6_4_2.controller;

import cn.codesheep.springbt_es6_4_2.model.dto.DocModel;
import cn.codesheep.springbt_es6_4_2.model.dto.BatchDoc;
import cn.codesheep.springbt_es6_4_2.model.dto.IndexModel;
import cn.codesheep.springbt_es6_4_2.model.dto.SearchModel;
import cn.codesheep.springbt_es6_4_2.model.dto.SingleDoc;
import cn.codesheep.springbt_es6_4_2.service.ISearchService;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private ISearchService searchService;

    @Autowired
    private DocModel docModel;

    @GetMapping("/createIndex")
    public String createIndex() throws IOException {

        IndexModel indexModel = new IndexModel();
        indexModel.setIndexName("testindex2"); // 注意索引名字必须小写，否则ES抛异常
        indexModel.setTypeName("testtype2");
        indexModel.setReplicaNumber( 2 );   // 两个节点，因此两个副本
        indexModel.setShardNumber( 3 );

        XContentBuilder builder = null;
        builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("title");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");
                }
                builder.endObject();
                builder.startObject("filecontent");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");
                    builder.field("term_vector", "with_positions_offsets");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();

        indexModel.setBuilder( builder );
        Boolean res = searchService.createIndex(indexModel);

        if( true==res )
            return "创建索引成功";
        else
            return "创建索引失败";
    }

    @GetMapping("/deleteIndex")
    public String deleteIndex() {
        return (searchService.deleteIndex("testindex2")==true) ? "删除索引成功":"删除索引失败";
    }

    @PostMapping("/insertSingleDoc")
    public String insertSingleDoc( @RequestBody SingleDoc singleDoc  ) {
        return ( true==searchService.insertDoc( singleDoc ) ) ? "插入单个文档成功" : "插入单个文档失败";
    }

    @GetMapping("/insertDocBatch")
    public String insertDocBatch() {

        BatchDoc batchDoc = new BatchDoc();
        batchDoc.setIndexName("testindex2");
        batchDoc.setTypeName("testtype2");

        Map<String,Object> doc1 = new HashMap<>();
        doc1.put("title","人工智能标题1");
        doc1.put("filecontent","人工智能内容1");
        Map<String,Object> doc2 = new HashMap<>();
        doc2.put("title","人工智能标题2");
        doc2.put("filecontent","人工智能内容2");
        Map<String,Object> doc3 = new HashMap<>();
        doc3.put("title","人工智能标题3");
        doc3.put("filecontent","人工智能内容3");
        Map<String,Object> doc4 = new HashMap<>();
        doc4.put("title","人工智能标题4");
        doc4.put("filecontent","人工智能内容4");

        List<Map<String,Object>> docList = new ArrayList<>();
        docList.add( doc1 );
        docList.add( doc2 );
        docList.add( doc3 );
        docList.add( doc4 );

        batchDoc.setBatchDocMap( docList );

        return ( true==searchService.insertDocBatch( batchDoc ) ) ? "批量插入文档成功" : "批量插入文档失败";
    }

    @GetMapping("/searchDoc")
    public List<Map<String,Object>> searchDoc() {

        SearchModel searchModel = new SearchModel();
        searchModel.setIndexName( "testindex2" );
        List<String> fields = new ArrayList<>();
        fields.add("title");
        fields.add("filecontent");
        fields.add("id");
        searchModel.setFields( fields );
        searchModel.setKeyword( "人工智能" );
        searchModel.setPageNum( 1 );
        searchModel.setPageSize( 5 );

        return searchService.queryDocs( searchModel );
    }

    @GetMapping("/deleteDoc")
    public String deleteDoc() {

        SingleDoc singleDoc = new SingleDoc();
        singleDoc.setIndexName("testindex2");
        singleDoc.setTypeName("testtype2");
        singleDoc.setId("vPHMY2cBcGZ3je_1EgIM");

        return (true==searchService.deleteDoc(singleDoc)) ? "删除文档成功" : "删除文档失败";
    }

    @GetMapping("/deleteDocBatch")
    public String deleteDocBatch() {

        BatchDoc batchDoc = new BatchDoc();
        batchDoc.setIndexName("testindex2");
        batchDoc.setTypeName("testtype2");
        List<String> ids = new ArrayList<>();
        ids.add("vfHMY2cBcGZ3je_1EgIM");
        ids.add("vvHMY2cBcGZ3je_1EgIM");
        batchDoc.setDocIds( ids );

        return ( true==searchService.deleteDocBatch(batchDoc) ) ? "批量删除文档成功" : "批量删除文档失败";
    }

    @PostMapping("/updateDoc")
    public String updateDoc( @RequestBody SingleDoc singleDoc ) {

        return (true==searchService.updateDoc(singleDoc)) ? "更新文档成功" : "更新文档失败";

    }

}
