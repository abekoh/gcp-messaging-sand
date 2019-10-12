package jp.abekoh.twitter.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TwitterSource {
    String OUTPUT = "twitter";

    @Output("twitter")
    MessageChannel output();
}
