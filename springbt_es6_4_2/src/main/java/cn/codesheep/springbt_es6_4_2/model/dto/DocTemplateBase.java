package cn.codesheep.springbt_es6_4_2.model.dto;

public class DocTemplateBase {

    protected String indexName;
    protected String typeName;

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
}
