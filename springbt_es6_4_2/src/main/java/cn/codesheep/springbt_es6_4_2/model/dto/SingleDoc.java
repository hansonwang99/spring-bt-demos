package cn.codesheep.springbt_es6_4_2.model.dto;

import java.util.Map;

public class SingleDoc extends DocTemplateBase {

    private String id;
    private Map<String,Object> updateDocMap;
    private Map<String,Object> docMap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getDocMap() {
        return docMap;
    }

    public void setDocMap(Map<String, Object> docMap) {
        this.docMap = docMap;
    }

    public Map<String, Object> getUpdateDocMap() {
        return updateDocMap;
    }

    public void setUpdateDocMap(Map<String, Object> updateDocMap) {
        this.updateDocMap = updateDocMap;
    }
}
