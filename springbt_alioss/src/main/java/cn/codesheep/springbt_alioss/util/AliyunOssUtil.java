package cn.codesheep.springbt_alioss.util;

import cn.codesheep.springbt_alioss.model.dto.AliyunOssPropertiesDTO;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AliyunOssUtil {

    private Logger logger = LoggerFactory.getLogger( AliyunOssUtil.class );

    @Autowired
    private AliyunOssPropertiesDTO aliyunOssProperties;

    public String uploadSingle( File file ) {

        if( null==file )
            return null;

        String bucketName = aliyunOssProperties.getBucket();
        String fileUrl = "";

        OSSClient client = new OSSClient( aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessId(), aliyunOssProperties.getAccessKey() );

        try {
            if ( !client.doesBucketExist( bucketName ) ) {  // 判断Bucket是否存在,不存在则创建之
                client.createBucket( bucketName );
                CreateBucketRequest createBucketRequest = new CreateBucketRequest( bucketName );
                createBucketRequest.setCannedACL( CannedAccessControlList.PublicRead );
                client.createBucket( createBucketRequest );
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            fileUrl = aliyunOssProperties.getProjectName() + "/" + simpleDateFormat.format( new Date()) + "/" + file.getName();  // 按照 proj/y/m/d 设置OSS上的文件路径和名称
            PutObjectResult result = client.putObject( new PutObjectRequest(bucketName, fileUrl, file) );  // 正式上传文件动作
            client.setBucketAcl( bucketName, CannedAccessControlList.PublicRead );                         // 设置权限(公开读)
            if ( null!=result )
                logger.info( "--------- OSS文件上传成功，文件在OSS上的目录为：" + fileUrl + " ---------" );

        } catch ( OSSException oe ) {
            logger.error( oe.getMessage() );
            return null;
        } catch ( ClientException ce ) {
            logger.error( ce.getErrorMessage() );
            return fileUrl;
        } finally {
            if( null!=client )
                client.shutdown();
        }
        return fileUrl; // 返回OSS上的文件路径和名称
    }

    public void deleteSingle( String dir, String fileName ) {
        OSSClient client = new OSSClient( aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessId(), aliyunOssProperties.getAccessKey() );
        client.deleteObject( aliyunOssProperties.getBucket(), dir+"/"+fileName );
    }

    public void downloadSingle( String  fileName, String downLoadFileName ) {
        OSSClient client = new OSSClient( aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessId(), aliyunOssProperties.getAccessKey() );
        client.getObject( new GetObjectRequest( aliyunOssProperties.getBucket(), fileName ), new File(downLoadFileName) );
    }

    public  void deleteFile(File file){
        if( file.exists() ){
            file.delete();
        }
    }

}
