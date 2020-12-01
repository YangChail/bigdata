package org.chail.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.net.URI;
import java.net.URL;


/**
 * @ClassName : HwHdfs
 * @Description :
 * @Author : Chail
 * @Date: 2020-11-30 09:23
 */
public class HwHdfs {


    public static void main(String[] args) {

        String path = "hdfs://192.168.239.1:8020";
        path = path + "/user/hive/warehouse/autotest_in.db/data1w_txt/000000_0";
        Configuration configuration = new Configuration();
        URI uri=new URL(path);

        FileSystem fs=FileSystem.get(uri,configuration);






    }





}
