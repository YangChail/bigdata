package org.chail;

/**
 * @ClassName : TryCatch
 * @Description : trycatch用法
 * @Author : Chail
 * @Date: 2020-11-27 14:03
 */
public class TryCatch {
    public static void main(String[] args) {
        try {
            System.out.println("ssssssssss");
            throw  new RuntimeException("xxxxxxxx");
        } catch (Exception e) {
            System.out.println(111111);
        } finally {
            System.out.println(22222);
        }
    }
}
