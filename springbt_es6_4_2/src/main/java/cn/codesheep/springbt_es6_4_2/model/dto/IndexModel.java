package cn.codesheep.springbt_es6_4_2.model.dto;

import org.elasticsearch.common.xcontent.XContentBuilder;

public class IndexModel {

    private String indexName;
    private String typeName;
    private int shardNumber;
    private int replicaNumber;
    private XContentBuilder builder;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getShardNumber() {
        return shardNumber;
    }

    public void setShardNumber(int shardNumber) {
        this.shardNumber = shardNumber;
    }

    public int getReplicaNumber() {
        return replicaNumber;
    }

    public void setReplicaNumber(int replicaNumber) {
        this.replicaNumber = replicaNumber;
    }

    public XContentBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(XContentBuilder builder) {
        this.builder = builder;
    }
}
