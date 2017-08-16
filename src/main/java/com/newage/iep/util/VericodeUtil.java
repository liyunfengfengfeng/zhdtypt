package com.newage.iep.util;

/**
 * Created by a1996_000 on 2017/8/16.
 */

/**
 * 生成随机验证码
 */
public class VericodeUtil {
    /**
     * 生成六位随机验证码
     */
    public static int getRandNum() {
        int randNum = 1 + (int)(Math.random() * ((999999 - 1) + 1));
        return randNum;
    }
    public static void main(String[]args){
        System.out.println(VericodeUtil.getRandNum());
    }
}
