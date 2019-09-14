package jp.abekoh.log.sink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableBinding(Sink.class)
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @StreamListener(Sink.INPUT)
    public void log(Map<String, Object> tweet) {
        logger.info(tweet.toString());
    }
}
