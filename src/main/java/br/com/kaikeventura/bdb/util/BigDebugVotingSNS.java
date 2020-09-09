package br.com.kaikeventura.bdb.util;

import br.com.kaikeventura.bdb.error.exception.ErrorMountingVoteMessageException;
import br.com.kaikeventura.bdb.model.Voting;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BigDebugVotingSNS {

    private final AmazonSNSClient amazonSNSClient;

    private final static String TOPIC_ARN = "arn:aws:sns:us-east-1:000000000000:bdb-topic";

    public SubscribeResult addSubscription() {
        return amazonSNSClient.subscribe(
                new SubscribeRequest(
                        TOPIC_ARN,
                        "sqs",
                        "http://localhost:4576/000000000000/bdb-queue"
                )
        );
    }

    public void publishMessage(Voting voting) {
        amazonSNSClient.publish(new PublishRequest(TOPIC_ARN, buildMessage(voting), "Vote OK"));
    }

    private String buildMessage(Voting voting) {
        try {
            return new ObjectMapper().writeValueAsString(voting);
        } catch (JsonProcessingException e) {
            throw new ErrorMountingVoteMessageException();
        }
    }
}
