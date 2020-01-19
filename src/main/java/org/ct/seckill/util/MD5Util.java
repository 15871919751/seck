package org.ct.seckill.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author K
 * @Date 2020/1/17 23:48
 * @Version 1.0
 */
@Slf4j
public class MD5Util {

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    private static final String SALT = "1a2b3c4d";
    /**
     * 将用户输入的密码进行加密
     *
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String str =""+ SALT.charAt(0) + SALT.charAt(2) + inputPass + SALT.charAt(5) + SALT.charAt(4);
        return md5(str);
    }

    /**
     * 将客户端接收到的密码再一次进行加密
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String str = salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String password,String salt) {
        String formPass = inputPassToFormPass(password);
        return formPassToDBPass(formPass,salt);
    }

    public static void main(String[] args) {
        log.info(inputPassToFormPass("123456"));
    }

}
