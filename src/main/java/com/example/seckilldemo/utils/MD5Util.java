package com.example.seckilldemo.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * @author ：qhh
 * @date ：Created in 2022/3/24 15:41
 */
@Component
public class MD5Util {
    private static final String salt = "1a2b3c4d";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static String inputPwdToFormPwd(String inputPwd){
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPwd + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPwdToDBPwd(String formPwd, String salt){
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPwd + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPwdToDBPwd(String inputPwd, String salt){
        String formPwd = inputPwdToFormPwd(inputPwd);
        String dbPwd = formPwdToDBPwd(formPwd, salt);
        return dbPwd;
    }

    public static void main(String[] args) {
        System.out.println(inputPwdToFormPwd("123456"));
        System.out.println(formPwdToDBPwd("d3b1294a61a07da9b49b6e22b2cbd7f9", "1a2b3c4d"));
        System.out.println(inputPwdToDBPwd("123456", "1a2b3c4d"));
    }

}
