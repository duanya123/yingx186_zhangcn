package com.baizhi.zcn;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.baizhi.zcn.dao.AdminDao;
import com.baizhi.zcn.dao.UserMapper;
import com.baizhi.zcn.entity.Admin;
import com.baizhi.zcn.entity.User;
import com.baizhi.zcn.entity.UserExample;
import com.baizhi.zcn.util.AliyunOssUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class AliyunTests {

    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    String accessKeyId = "LTAI4Fft2pfxzwE2P2qLez3N";
    String accessKeySecret = "vfpqevOMfwxGPEX7PRSJ9T5a7FoZof";

    //创建存储空间
    @Test
    public void createBucket(){

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4Fft2pfxzwE2P2qLez3N";
        String accessKeySecret = "vfpqevOMfwxGPEX7PRSJ9T5a7FoZof";
        String bucketName = "yingx-asasasa";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        ossClient.createBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void queryAllBucket(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        //String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
            //String accessKeyId = "<yourAccessKeyId>";
            //String accessKeySecret = "<yourAccessKeySecret>";

    // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    // 列举存储空间。
            List<Bucket> buckets = ossClient.listBuckets();
            for (Bucket bucket : buckets) {
                System.out.println(" - " + bucket.getName());
            }

    // 关闭OSSClient。
            ossClient.shutdown();
    }

    @Test
    public void uploadFile(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4Fft2pfxzwE2P2qLez3N";
        String accessKeySecret = "vfpqevOMfwxGPEX7PRSJ9T5a7FoZof";

        String bucket="yingx-186";   //存储空间名
        String fileName="video/2020宣传视频.mp4";  //指定上传文件名  可以指定上传目录
        String localFile="C:\\Users\\NANAN\\Desktop\\other\\video\\2020宣传视频.mp4";  //指定本地文件路径

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, new File(localFile));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    @Test
    public void downLoad(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        //String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        //String accessKeyId = "<yourAccessKeyId>";
        //String accessKeySecret = "<yourAccessKeySecret>";
        String bucketName = "yingx-186";
        String objectName = "aaa.jpg";
        String localFile="C:\\Users\\NANAN\\Pictures\\Saved Pictures\\"+objectName;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(localFile));

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void delete(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        //String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        //String accessKeyId = "<yourAccessKeyId>";
        //String accessKeySecret = "<yourAccessKeySecret>";
        String bucketName = "yingx-186";
        String objectName = "aaa.jpg";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, objectName);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void test() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        //String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        //String accessKeyId = "<yourAccessKeyId>";
        //String accessKeySecret = "<yourAccessKeySecret>";

        String bucket="yingx-186";   //存储空间名
        String fileName="宣传视频.mp4";  //指定上传文件名  可以指定上传目录
        String localFile="C:\\Users\\NANAN\\Desktop\\other\\video\\2020宣传视频.mp4";  //指定本地文件路径

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = new FileInputStream(localFile);
        ossClient.putObject(bucket, fileName, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void aa(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。

        String accessKeyId = "LTAI4Fft2pfxzwE2P2qLez3N";
        String accessKeySecret = "vfpqevOMfwxGPEX7PRSJ9T5a7FoZof";

        String bucketName="yingxs";   //存储空间名
        String objectName="2020宣传视频.mp4";  //指定上传文件名  可以指定上传目录

    // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    // 设置视频截帧操作。
        String style = "video/snapshot,t_50000,f_jpg,w_800,h_600";
    // 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println(signedUrl);
        String string = signedUrl.toString();
        System.out.println(string);
        //AliyunOssUtil.uploadFile("yingxs","aaa.jpg",string);

        //==

        // 上传网络流。
        InputStream inputStream = null;
        try {
            inputStream = new URL(string).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.putObject("yingxs", "aaa.jpg", inputStream);



        // 关闭OSSClient。
        ossClient.shutdown();//sClient.shutdown();
    }


    @Test
    public void testVideoIntercept(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        //String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        //String accessKeyId = "<yourAccessKeyId>";
        //String accessKeySecret = "<yourAccessKeySecret>";
        String bucketName = "yingx-186";
        String objectName = "video/1585705868131-野马.mp4";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 设置视频截帧操作。
        String style = "video/snapshot,t_1000,f_jpg,w_0,h_0";
        // 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);

        //输出图片网络路径
        System.out.println(signedUrl);

        //文件上传 阿里云

        // 上传网络流。
        InputStream inputStream = null;
        try {
            inputStream = new URL(signedUrl.toString()).openStream();
            ossClient.putObject(bucketName, "aaa.jpg", inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }



        // 关闭OSSClient。
        ossClient.shutdown();
        //sClient.shutdown();
    }


}
