package cn.itsqh.ydma.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class FileTest {

    public static void main(String[] args) throws Exception {

        File file=new File("D:\\Desktop\\2020-04\\转正申请表.doc");

        FileInputStream fis = new FileInputStream(file);

        BufferedInputStream bis=  new BufferedInputStream(fis);

        System.out.println(bis);
    }


}
