package com.spring.aws.service.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class FileServiceImpl implements FileService{

    private AmazonS3 awsS3;

    @Value("${aws-s3-bucket}")
    private String bucketName;


    public FileServiceImpl(AmazonS3 awsS3) {
        this.awsS3 = awsS3;
    }

    @Override
    public void uploadFile(String keyName, MultipartFile file) throws IOException {
        var putObjectResult = awsS3.putObject(bucketName, keyName, file.getInputStream(), null);
        log.info(String.valueOf(putObjectResult.getMetadata()));
    }

    @Override
    public S3Object getFile(String fileName) {
        return awsS3.getObject(bucketName, fileName);
    }

    @Override
    public void deleteFile(String fileName) {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, fileName);
        awsS3.deleteObject(deleteObjectRequest);

    }
}
