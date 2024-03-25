package com.spring.aws.controller;

import com.spring.aws.service.bucket.BucketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aws/s3/bucket")
public class BucketController {

    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping("")
    public String createBucket(@RequestParam String bucketName){
        return bucketService.createBucket(bucketName);
    }

    @GetMapping("")
    public List<String> getBuckets(){
        return bucketService.getBuckets();
    }

    @GetMapping("/{bucketName}/files")
    public List<String> getFilesInBucket(@PathVariable String bucketName){
        return bucketService.getFilesInBucket(bucketName);
    }

    @DeleteMapping("/{bucketName}")
    public void deleteBucket(@PathVariable String bucketName){
        bucketService.deleteBucket(bucketName);
    }
}
