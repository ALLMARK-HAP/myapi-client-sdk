package com.my.project.utils;

import cn.hutool.crypto.digest.DigestUtil;


/**
 * @Description 签名工具
 * @Author
 * @Create 14:11
 */
public class SignUtil {

    public static String getSign(String headers,String secretKey){
        String md5Hex1 = DigestUtil.md5Hex(headers +"."+ secretKey);
        return md5Hex1;
    }
}
