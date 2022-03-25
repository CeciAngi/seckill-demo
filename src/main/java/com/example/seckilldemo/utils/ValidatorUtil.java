package com.example.seckilldemo.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：qhh
 * @date ：Created in 2022/3/25 14:49
 */
public class ValidatorUtil {
    private static final Pattern mobilePattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    public static boolean isMobile(String mobile){
        if (StringUtils.isEmpty(mobile))
            return false;
        Matcher matcher = mobilePattern.matcher(mobile);
        return matcher.matches();
    }
}
