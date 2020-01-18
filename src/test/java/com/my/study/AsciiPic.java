package com.my.study;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author K
 * @Date 2020/1/17 18:15
 * @Version 1.0
 * 图片进行灰度处理转化成ASCII.txt
 */
public class AsciiPic {

    /**
     * @param
     *
     */
    public  AsciiPic() throws IOException {

    }

    /**
     * test
     *
     * @param args
     */
    public static void main(final String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\abc.txt"));
        StringBuffer sb = new StringBuffer();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            System.out.println(sb.toString());
        } catch (IOException e) {
            System.out.println("读取失败!");
        }finally {
            if (br != null) {
                br.close();
            }
        }


    }


}
