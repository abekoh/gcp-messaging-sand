package jp.abekoh.log.sink.converter.mapstruct;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapStructConverterUtil {

    private static final Pattern urlPattern = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    @SourceTextToUrl
    public URL sourceTextToUrl(String sourceText) {
        Matcher matcher = urlPattern.matcher(sourceText);
        if (matcher.find()) {
            try {
                return new URL(matcher.group(0));
            } catch (MalformedURLException e) {
                return null;
            }
        }
        return null;
    }

    @TimestampMsToPostedTime
    public ZonedDateTime timestampMsToPostedTime(String timestampMs) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(timestampMs)), TimeZone.getTimeZone("UTC").toZoneId());
    }
}
