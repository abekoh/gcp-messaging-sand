package jp.abekoh;

import lombok.Data;

import java.net.URL;

@Data
public class User {
    private Long tweetId;

    private String userId;

    private String screenName;

    private URL websiteUrl;

    private String description;

    private String location;
}
