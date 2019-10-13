package jp.abekoh.log.sink;

import jp.abekoh.SourceTweet;
import jp.abekoh.Tweet;
import jp.abekoh.log.sink.converter.mapstruct.MapStructTweetConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private PostClient postClient;

    private MapStructTweetConverter mapStructTweetConverter;

    @Autowired
    public Consumer(PostClient postClient, MapStructTweetConverter mapStructTweetConverter) {
        this.postClient = postClient;
        this.mapStructTweetConverter = mapStructTweetConverter;
    }

    @StreamListener(Sink.INPUT)
    public void log(SourceTweet sourceTweet) {
        Tweet tweet = mapStructTweetConverter.convert(sourceTweet);
        postClient.dryPost(tweet.toString());
//        postClient.post((String) tweet.get("text"));
    }
}
