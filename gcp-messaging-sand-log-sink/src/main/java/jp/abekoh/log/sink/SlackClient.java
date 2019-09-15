package jp.abekoh.log.sink;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SlackClient implements PostClient {

    private static final Logger logger = LoggerFactory.getLogger(SlackClient.class);


    private SlackSession slackSession;

    private SlackChannel slackChannel;

    public SlackClient(@Value("${slack.token}") String token,
                       @Value("${slack.channel}") String channel) {
        slackSession = SlackSessionFactory.createWebSocketSlackSession(token);
        try {
            slackSession.connect();
        } catch (IOException e) {
            logger.error("failed to connect to slack");
            e.printStackTrace();
        }
        slackChannel = slackSession.findChannelByName(channel);
    }

    @Override
    public void post(String body) {
        slackSession.sendMessage(slackChannel, body);
        logger.info("send to slack: " + body);
    }
}
