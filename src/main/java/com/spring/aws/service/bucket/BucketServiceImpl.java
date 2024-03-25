package com.spring.aws.service.bucket;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService{

    private AmazonS3 awsS3;

    public BucketServiceImpl(AmazonS3 awsS3) {
        this.awsS3 = awsS3;
    }

    @Override
    public String createBucket(String bucketName) {
        awsS3.createBucket(bucketName);

        return String.format("Bucket created with name %s",bucketName);
    }

    @Override
    public List<String> getBuckets() {

        return awsS3.listBuckets().stream().map(Bucket::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getFilesInBucket(String bucketName) {
        ListObjectsV2Result listObjectsV2Result = awsS3.listObjectsV2(bucketName);
        return new ArrayList<>(listObjectsV2Result.getObjectSummaries().stream().map(S3ObjectSummary::getKey).toList());
    }

    @Override
    public void deleteBucket(String bucketName) {
        awsS3.deleteBucket(bucketName);

    }
}
