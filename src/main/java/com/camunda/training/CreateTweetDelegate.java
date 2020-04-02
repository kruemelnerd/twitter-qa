package com.camunda.training;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Component("createTweetDelegate")
public class CreateTweetDelegate implements JavaDelegate {

    private TwitterService twitterService;

    private final Logger LOGGER = LoggerFactory.getLogger(CreateTweetDelegate.class.getName());

    @Inject
    public CreateTweetDelegate(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    public void execute(DelegateExecution execution) throws Exception {
        String content = (String) execution.getVariable("content");
        LOGGER.info("Publishing tweet: " + content);


        String tweetId = this.twitterService.postTweet(content);
        execution.setVariable("tweet-id", tweetId);
    }
}
