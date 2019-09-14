package jp.abekoh.twitter.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.app.twitterstream.source.TwitterstreamSourceConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TwitterstreamSourceConfiguration.class)
public class TwitterSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwitterSourceApplication.class, args);
    }
}
