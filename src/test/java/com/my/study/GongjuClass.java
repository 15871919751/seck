package com.my.study;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author K
 * @Date 2020/1/19 8:53
 * @Version 1.0
 */
public class GongjuClass {
    public static void main(String[] args) throws ParseException {
        int n = -11;
        System.out.println((n^(n>>31))+ (n>>>31));
    }
}
