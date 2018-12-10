package cn.codesheep.springbt_alioss.controller;

import cn.codesheep.springbt_alioss.model.result.ResponseData;
import cn.codesheep.springbt_alioss.model.result.ResponseUtil;
import cn.codesheep.springbt_alioss.service.IAliyunOssService;
import cn.codesheep.springbt_alioss.util.AliyunOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@RestController
public class AliOssOperationController {

    @Autowired
    private IAliyunOssService aliyunOssService;

    @Autowired
    private AliyunOssUtil aliyunOssUtil;

    @PostMapping(value = "/uploadPic")
    public ResponseData uploadFile( @RequestParam("file") MultipartFile file ) {

        Long id = 1l;  // 此处可结合全局唯一ID流水号生成器来生成 ID
        String fileName = file.getOriginalFilename();

        try {
            if ( file != null ) {
                String prefix = fileName.substring(fileName.lastIndexOf("."));
                File newFile = File.createTempFile( String.valueOf(id), prefix );
                FileOutputStream os = new FileOutputStream(newFile);
                os.write( file.getBytes() );
                os.close();
                file.transferTo( newFile );
                String uploadUrl = aliyunOssService.uploadSingleFile(newFile);
                if( uploadUrl.equals("") ){
                    return ResponseUtil.getFailed("上传图片失败");
                }
                aliyunOssUtil.deleteFile( newFile );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ResponseUtil.getSucceed();
    }

    @PostMapping(value = "/deleteFile")
    public ResponseData deleteFile( @RequestParam("dir") String dir, @RequestParam("fileName") String fileName ) {
        aliyunOssService.deleteSingleFile(dir, fileName);
        return ResponseUtil.getSucceed();
    }

}
