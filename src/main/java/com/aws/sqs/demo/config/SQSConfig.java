/**
 * 
 */
package com.aws.sqs.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

/**
 * @author Liang Faan <liang.faan@gmail.com>
 *
 */
@Configuration
public class SQSConfig {

	@Value("${cloud.aws.region.static}")
	private String region;
	@Value("${cloud.aws.credentials.access-key}")
	private String awsAccessKey;
	@Value("${cloud.aws.credentials.secret-key}")
	private String awsSecretKey;
	
	
	@Bean
	public QueueMessagingTemplate queueMessagingTemplate() {
		return new QueueMessagingTemplate(amazonSQSAsync());
	}
	
//	@Bean
	public AmazonSQSAsync amazonSQSAsync() {
		return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey))).build();
	}
}
