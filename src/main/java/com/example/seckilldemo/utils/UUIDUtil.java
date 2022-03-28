package com.example.seckilldemo.utils;

import java.util.UUID;

/**
 * @author ：qhh
 * @date ：Created in 2022/3/28 13:58
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
