package jp.abekoh.twitter.source;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.app.twitter.TwitterCredentials;
import org.springframework.cloud.stream.app.twitterstream.source.TwitterStreamProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
@EnableBinding({TwitterSource.class})
@EnableConfigurationProperties({TwitterCredentials.class, TwitterStreamProperties.class})
public class TwitterSourceConfiguration {

    private TwitterCredentials credentials;

    private TwitterStreamProperties twitterStreamProperties;

    private TwitterSource source;

    @Autowired
    public TwitterSourceConfiguration(TwitterCredentials credentials, TwitterStreamProperties twitterStreamProperties, TwitterSource source) {
        this.credentials = credentials;
        this.twitterStreamProperties = twitterStreamProperties;
        this.source = source;
    }

    @Bean
    public MessageProducer twitterStream(TwitterTemplate twitterTemplate) {
        TwitterSourceMessageProducer messageProducer = new TwitterSourceMessageProducer(twitterTemplate, this.twitterStreamProperties);
        messageProducer.setOutputChannel(this.source.output());
        return messageProducer;
    }

    @Bean
    @ConditionalOnMissingBean
    public TwitterTemplate twitterTemplate() {
        return new TwitterTemplate(this.credentials.getConsumerKey(), this.credentials.getConsumerSecret(), this.credentials.getAccessToken(), this.credentials.getAccessTokenSecret());
    }
}
