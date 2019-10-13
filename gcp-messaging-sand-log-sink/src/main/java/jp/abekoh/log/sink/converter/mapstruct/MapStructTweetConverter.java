package jp.abekoh.log.sink.converter.mapstruct;

import jp.abekoh.SourceTweet;
import jp.abekoh.SourceUser;
import jp.abekoh.Tweet;
import jp.abekoh.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MapStructConverterUtil.class)
public interface MapStructTweetConverter {
    @Mapping(target = "tweetId", source = "id")
//    @Mapping(target = "sourceUrl", source = "source", qualifiedBy = SourceTextToUrl.class)
//    @Mapping(target = "postedTime", source = "timestampMs", qualifiedBy = TimestampMsToPostedTime.class)
    Tweet convert(SourceTweet tweet);


    @Mapping(target = "tweetId", source = "id")
    @Mapping(target = "userId", source = "name")
//    @Mapping(target = "websiteUrl", source = "url", qualifiedBy = SourceTextToUrl.class)
    User convert(SourceUser user);
}
