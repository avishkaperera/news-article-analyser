package com.reprisk.analyser.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class LookupUtil {

    private static final String COMPANY_NAME_MATCHER_REGEX = ".*(?<!\\w)%s(?!\\w).*";

    public static boolean isCompanyPresentInArticle(String newsArticleText, String companyName) {
        if (!newsArticleText.contains(companyName)) {
            return false;
        }

        String sanitizedName = companyName.contains("(") ? companyName
                .replace("(", "\\(")
                .replace(")", "\\)") : companyName;
        String formattedRegex = String.format(COMPANY_NAME_MATCHER_REGEX, sanitizedName);
        Pattern companyNamePattern = Pattern.compile(formattedRegex, Pattern.DOTALL);
        Matcher companyNameMatcher = companyNamePattern.matcher(newsArticleText);
        return companyNameMatcher.find();
    }
}
