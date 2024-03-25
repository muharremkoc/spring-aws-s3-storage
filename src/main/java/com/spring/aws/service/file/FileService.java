package com.spring.aws.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import com.amazonaws.services.s3.model.S3Object;

public interface FileService {

    void  uploadFile(String keyName, MultipartFile file) throws IOException;

    S3Object getFile(String fileName);

    void deleteFile(String fileName);

}
