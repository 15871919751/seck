package org.ct.seckill.util;

import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author K
 * @Date 2020/1/19 0:22
 * @Version 1.0
 */
public class ValidatorUtil {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(str);
        return m.matches();
    }
}
