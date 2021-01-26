package org.chail;

import java.util.*;

/**
 * @ClassName : TryCatch
 * @Description : trycatch用法
 * @Author : Chail
 * @Date: 2020-11-27 14:03
 */
public class TryCatch {
    public static void main(String[] args) {
        Set<String> set=new HashSet<>();
        String s = ReadFromFile.readFileByLines("D:\\a.txt");
        String[] split = s.split("\n");
        List<String> res=new ArrayList<>();
        for(String str:split){

            String[] s1 = str.split(" ");

            String key=s1[1]+s1[2];

            if(!set.contains(key)){
                set.add(key);
            }else{
                StringBuffer sb=new StringBuffer("delect * from mc_sensitive_column where id=").append(s1[0]).append(";");
                res.add(sb.toString());
                System.out.println(sb.toString());
            }
        }

        System.out.println();

    }
}
