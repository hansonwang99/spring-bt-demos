package cn.codesheep.springbt_alioss.service;

import java.io.File;

public interface IAliyunOssService {

    String uploadSingleFile(File file);
    void deleteSingleFile(String dir, String fileName);
    void downloadSingleFile(String fileName, String downLoadFileName);
}
