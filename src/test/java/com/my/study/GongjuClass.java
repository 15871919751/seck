package com.my.study;

import java.io.*;

/**
 * @Author K
 * @Date 2020/1/19 8:53
 * @Version 1.0
 */
public class GongjuClass {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\A555L\\Desktop\\aalf.dat");
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String stringBuffer = "";
        String line = null;
        while ((line = br.readLine()) != null) {
            stringBuffer += line;
            stringBuffer += "\r\n"; // 补上换行符   
        }
        System.out.println(stringBuffer);

    }
}
