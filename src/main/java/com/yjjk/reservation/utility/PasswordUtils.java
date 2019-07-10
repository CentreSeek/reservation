/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PasswordUtils
 * Author:   CentreS
 * Date:     2019/7/1 10:23
 * Description: 加密工具
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.utility;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @author CentreS
 * @Description: 加密工具
 * @create 2019/7/1
 */
public class PasswordUtils {


    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 自定义简单生成盐，是一个随机生成的长度为16的字符串，每一个字符是随机的十六进制字符
     */
    public static String salt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(hex[random.nextInt(16)]);
        }
        return sb.toString();
    }

    /**
     * 生成含随机盐的密码
     *
     * @param password
     * @return
     */
    public static String generate(String password) {
        return generate(password, salt());
    }

    /**
     * 生成含盐密码
     *
     * @param password
     * @param salt
     * @return
     */
    public static String generate(String password, String salt) {
        if (salt.length() < 16) {
            for (int i = 0; i < 16 - salt.length(); i++) {
                salt += "0";
            }
        }
        password = md5Hex(password + salt);
        System.out.println("加盐后的password=" + password);
        char[] cs1 = password.toCharArray();
        char[] cs2 = salt.toCharArray();
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = cs1[i / 3 * 2];
            cs[i + 1] = cs2[i / 3];
            cs[i + 2] = cs1[i / 3 * 2 + 1];
        }
        return new String(cs);
    }

    /**
     * 校验密码是否正确
     */
    public static boolean verify(String password, String md5) {
        char[] cs = md5.toCharArray();
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = cs[i];
            cs1[i / 3 * 2 + 1] = cs[i + 2];
            cs2[i / 3] = cs[i + 1];
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    public static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String md5 = "73b5A661fb1b797d6b1C1b5e6A7cC686b95d43abE0479584";
        String password = "19924210";
        System.out.println(verify(password,md5));

    }
}
