package jp.abekoh;

import lombok.Data;

import java.net.URL;
import java.time.ZonedDateTime;

@Data
public class Tweet {
    private Long tweetId;

    private String text;

    private URL sourceUrl;

    private ZonedDateTime postedTime;

    private SourceUser user;
}
