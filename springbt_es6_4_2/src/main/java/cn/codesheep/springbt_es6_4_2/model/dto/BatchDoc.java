package cn.codesheep.springbt_es6_4_2.model.dto;

import java.util.List;
import java.util.Map;

public class BatchDoc extends DocTemplateBase {

    private List<String> docIds;  // 仅批量删除文档时使用

    private List<Map<String,Object>> batchDocMap;

    public List<Map<String, Object>> getBatchDocMap() {
        return batchDocMap;
    }

    public void setBatchDocMap(List<Map<String, Object>> batchDocMap) {
        this.batchDocMap = batchDocMap;
    }

    public List<String> getDocIds() {
        return docIds;
    }

    public void setDocIds(List<String> docIds) {
        this.docIds = docIds;
    }
}
