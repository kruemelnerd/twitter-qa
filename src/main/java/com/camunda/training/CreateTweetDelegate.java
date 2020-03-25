package com.camunda.training;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import javax.inject.Inject;


@Component
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
        AccessToken accessToken = new AccessToken("220324559-jet1dkzhSOeDWdaclI48z5txJRFLCnLOK45qStvo",
                "B28Ze8VDucBdiE38aVQqTxOyPc7eHunxBVv7XgGim4say");
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("lRhS80iIXXQtm6LM03awjvrvk", "gabtxwW8lnSL9yQUNdzAfgBOgIMSRqh7MegQs79GlKVWF36qLS");
        twitter.setOAuthAccessToken(accessToken);
        twitter.updateStatus(content);
    }
}

//@Component(value = "createTweetDelegate")
//public class CreateTweetDelegate implements JavaDelegate {
//    private final Logger LOGGER = LoggerFactory.getLogger(CreateTweetDelegate.class.getName());
//
//    private TwitterService twitterService;
//
//    @Autowired
//    public CreateTweetDelegate(TwitterService twitterService) {
//        this.twitterService = twitterService;
//    }
//
//    public void execute(DelegateExecution execution) throws TwitterException {
//        String content = (String) execution.getVariable("content");
//        LOGGER.info("Publishing tweet: " + content);
//        String status;
//        try {
//            status = twitterService.postTweet(content);
//        } catch (TwitterException e) {
//            if (e.getErrorCode() == 187)
//                throw new BpmnError("DoppelteNachrichtError");
//            else
//                throw e;
//        }
//        execution.setVariable("tweet-id", status);
//    }
//}