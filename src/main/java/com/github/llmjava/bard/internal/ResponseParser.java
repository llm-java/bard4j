package com.github.llmjava.bard.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseParser {
    static Pattern SNlM0e_REGEX = Pattern.compile("\"SNlM0e\":\"(.*?)\"");

    public static String getSNlM0e(String body) {
        String result = null;
        Matcher matcher = SNlM0e_REGEX.matcher(body);
        if (matcher.find()) {
            result = matcher.group(1);
        } else {
            System.out.println("No match found");
        }
        return result;
    }

}
