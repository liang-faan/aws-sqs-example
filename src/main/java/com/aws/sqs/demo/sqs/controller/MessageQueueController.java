/**
 * 
 */
package com.aws.sqs.demo.sqs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

/**
 * @author Liang Faan <liang.faan@gmail.com>
 *
 */
@RestController
@RequestMapping("/messaging")
public class MessageQueueController {

	public static final Logger logger=LoggerFactory.getLogger(MessageQueueController.class);
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	@Value("${cloud.aws.end-point}")
	private String sqsEndPoint;
	
	final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();  //to create AmazonSQS 
	
	
	@GetMapping
	public void sendMessage() {
		
		
//		System.out.println("Creating a new Amazon SQS FIFO queue called " +
//                "MyFifoQueue.fifo.\n");
//        final Map<String, String> attributes = new HashMap<>();
//
//        // A FIFO queue must have the FifoQueue attribute set to true.
//        attributes.put("FifoQueue", "true");
//
//        /*
//         * If the user doesn't provide a MessageDeduplicationId, generate a
//         * MessageDeduplicationId based on the content.
//         */
//        attributes.put("ContentBasedDeduplication", "true");
//
//        // The FIFO queue name must end with the .fifo suffix.
//        final CreateQueueRequest createQueueRequest =new CreateQueueRequest("RedemptionSystemMsgQueue.fifo").withAttributes(attributes);
//        final String myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
//
//        // List all queues.
//        System.out.println("Listing all queues in your account.\n");
//        for (final String queueUrl : sqs.listQueues().getQueueUrls()) {O
//            System.out.println("  QueueUrl: " + queueUrl);
//        }
//        System.out.println();

		
//		queueMessagingTemplate.send(sqsEndPoint, new SendMessageRequest(sqsEndPoint, "Hello My First AWS SQS"));
		SendMessageRequest sendMessageRequest = new SendMessageRequest(sqsEndPoint, "This is my 2nd message to SQS");
		sendMessageRequest.setMessageGroupId("TestGroup1");
//		this.sqs.sendMessage(sendMessageRequest);
		SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
        final String sequenceNumber = sendMessageResult.getSequenceNumber();
        final String messageId = sendMessageResult.getMessageId();
        System.out.println("SendMessage succeed with messageId "
                + messageId + ", sequence number " + sequenceNumber + "\n");
	}
	
	@SqsListener("RedemptionSystemQueue.fifo")
	public void messageListener(String msg) {
		logger.info("Message From AWS"+msg);
	}
}
