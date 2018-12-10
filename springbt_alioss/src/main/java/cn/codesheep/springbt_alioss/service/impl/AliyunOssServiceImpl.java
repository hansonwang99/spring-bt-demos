package cn.codesheep.springbt_alioss.service.impl;

import cn.codesheep.springbt_alioss.service.IAliyunOssService;
import cn.codesheep.springbt_alioss.util.AliyunOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AliyunOssServiceImpl implements IAliyunOssService {

    @Autowired
    private AliyunOssUtil aliyunOssUtil;

    @Override
    public String uploadSingleFile(File file) {
        return aliyunOssUtil.uploadSingle( file );
    }

    @Override
    public void deleteSingleFile(String dir, String fileName) {
        aliyunOssUtil.deleteSingle( dir, fileName );
    }

    @Override
    public void downloadSingleFile(String fileName, String downLoadFileName) {
        aliyunOssUtil.downloadSingle( fileName, downLoadFileName );
    }
}
