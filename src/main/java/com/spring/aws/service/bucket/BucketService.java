package com.spring.aws.service.bucket;

import java.util.List;

public interface BucketService {

    String createBucket(String bucketName);

    List<String> getBuckets();

    List<String> getFilesInBucket(String bucketName);

    void  deleteBucket(String bucketName);
}
