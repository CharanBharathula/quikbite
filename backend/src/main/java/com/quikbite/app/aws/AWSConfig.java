package com.quikbite.app.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
// (Needs to remove after setting up AWS)Activate AWS beans only when aws.enabled=true otherwise if in application.proeprties if we didnt mention values for aws region etc then app will fail to start. so we use this annotation to conditionally load this configuration only if aws is enabled which needs to be mentioned in application.properties
@ConditionalOnProperty(prefix = "aws", name = "enabled", havingValue = "true", matchIfMissing = false)
public class AWSConfig {

    @Value("${aws.s3.region}")
    private String awsRegion;

    @Value("${aws.accessKeyId}")
    private String awsAccessKeyId;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Bean
    public StaticCredentialsProvider staticCredentialsProvider(){
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKeyId, awsSecretKey));
    }

    @Bean
    public S3Client s3Client( StaticCredentialsProvider credentialsProvider ){
        return S3Client.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(credentialsProvider)
                .build();
    }
}
