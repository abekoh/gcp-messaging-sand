package jp.abekoh.twitter.source;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.abekoh.SourceTweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.app.twitterstream.source.AbstractTwitterInboundChannelAdapter;
import org.springframework.cloud.stream.app.twitterstream.source.TwitterStreamProperties;
import org.springframework.cloud.stream.app.twitterstream.source.TwitterStreamType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.social.support.URIBuilder;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;

public class TwitterSourceMessageProducer extends AbstractTwitterInboundChannelAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TwitterSourceMessageProducer.class);
    private static final String API_URL_BASE = "https://stream.twitter.com/1.1/statuses/";
    private final TwitterStreamProperties twitterStreamProperties;

    public TwitterSourceMessageProducer(TwitterTemplate twitterTemplate, TwitterStreamProperties twitterStreamProperties) {
        super(twitterTemplate);
        this.twitterStreamProperties = twitterStreamProperties;
    }

    protected URI buildUri() {
        TwitterStreamType streamType = this.twitterStreamProperties.getStreamType();
        URIBuilder b = URIBuilder.fromUri("https://stream.twitter.com/1.1/statuses/" + streamType.getPath());
        if (streamType == TwitterStreamType.FILTER) {
            if (StringUtils.hasText(this.twitterStreamProperties.getFollow())) {
                b.queryParam("follow", this.twitterStreamProperties.getFollow());
            }

            if (StringUtils.hasText(this.twitterStreamProperties.getTrack())) {
                b.queryParam("track", this.twitterStreamProperties.getTrack());
            }

            if (StringUtils.hasText(this.twitterStreamProperties.getLocations())) {
                b.queryParam("locations", this.twitterStreamProperties.getLocations());
            }
        }

        if (StringUtils.hasText(this.twitterStreamProperties.getLanguage())) {
            b.queryParam("language", this.twitterStreamProperties.getLanguage());
        }

        URI uri = b.build();
        this.logger.info("Using twitter stream url: " + uri);
        return uri;
    }

    protected void doSendLine(String line) {
        if (line.startsWith("{\"limit")) {
            this.logger.info("Twitter stream is being track limited.");
        } else if (!line.startsWith("{\"delete") && !line.startsWith("{\"warning")) {
            SourceTweet sourceTweet = convertStringToTweet(line);
            this.logger.info("Send message: " + sourceTweet);
            this.sendMessage(MessageBuilder.withPayload(sourceTweet).build());
        }
    }

    private SourceTweet convertStringToTweet(String line) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(line, SourceTweet.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new SourceTweet();
        }
    }
}
