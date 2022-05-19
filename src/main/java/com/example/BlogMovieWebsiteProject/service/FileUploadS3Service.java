package com.example.BlogMovieWebsiteProject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;


import java.io.IOException;
import java.io.InputStream;


@Service
public class FileUploadS3Service {
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    public S3Client createS3Client(){
        final AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        final StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
        final Region REGION = Region.US_WEST_2;
        return S3Client.builder().credentialsProvider(credentialsProvider).region(REGION).build();
    }

    public void saveFileToS3(String fileName, InputStream inputStream) throws AwsServiceException, SdkClientException, IOException {
        S3Client client = this.createS3Client();
        PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(fileName).acl("public-read").build();

        client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));

        S3Waiter waiter = client.waiter();
        HeadObjectRequest waitRequest = HeadObjectRequest.builder().bucket(bucketName).key(fileName).build();

        WaiterResponse<HeadObjectResponse> waitResponse = waiter.waitUntilObjectExists(waitRequest);
        waitResponse.matched().response().ifPresent(x -> {
            System.out.println("The file " + fileName + " is now ready");
        });
    }

}
