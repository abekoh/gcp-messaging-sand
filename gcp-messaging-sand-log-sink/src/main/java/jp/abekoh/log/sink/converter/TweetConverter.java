package jp.abekoh.log.sink.converter;

import jp.abekoh.SourceTweet;
import jp.abekoh.Tweet;

public interface TweetConverter {
    Tweet convert(SourceTweet sourceTweet);
    SourceTweet convert(Tweet tweet);
}
