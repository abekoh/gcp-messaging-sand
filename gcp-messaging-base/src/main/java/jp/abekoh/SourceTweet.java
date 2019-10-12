package jp.abekoh;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceTweet {
    private Long id;

    private String text;

    private String source;

    private String createdAt;

    private SourceUser sourceUser;

    private String timestampMs;
}
