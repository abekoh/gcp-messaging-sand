package jp.abekoh;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceUser {
    private Long id;

    private String name;

    private String screenName;

    private String url;

    private String description;

    private String location;
}
