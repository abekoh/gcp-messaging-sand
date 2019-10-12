package jp.abekoh.log.sink;

import jp.abekoh.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableBinding(Sink.class)
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private PostClient postClient;

    @Autowired
    public Consumer(PostClient postClient) {
        this.postClient = postClient;
    }

    @StreamListener(Sink.INPUT)
    public void log(Tweet tweet) {
        postClient.dryPost(tweet.toString());
//        postClient.post((String) tweet.get("text"));
    }
}
