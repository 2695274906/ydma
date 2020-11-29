package cn.itsqh.ydma.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileInputStreamTest {

    public static void main(String[] args) {

        try {
            // 1.构造FileInputStream类型的对象与c:/a.txt文件关联
            FileInputStream fis = new FileInputStream("D:\\Desktop\\2020-04\\mock.docx");

            // 2.从输入流中读取数据内容并打印出来
            // 表示从输入流中读取单个字节
			/*
			int res = fis.read();
			System.out.println("读取到的单个字节数据是：" + res
				+ "，对应的字符是：" + (char)res);
			res = fis.read();
			System.out.println("读取到的单个字节数据是：" + res
				+ "，对应的字符是：" + (char)res);
			*/
			/*  以字节为单位读取文件中的所有内容
			int res = 0;
			while((res = fis.read()) != -1) {
				System.out.println("读取到的单个字节数据是：" + res
						+ "，对应的字符是：" + (char)res);
			}
			*/

            // 以字节数组为缓冲区进行读取
            byte[] bArr = new byte[20];
			/*
			// 表示从输入流中读取8个字节放入数组bArr中下标从2开始的位置
			int res = fis.read(bArr, 2, 8);
			System.out.println("读取到的数据内容是：" + new String(bArr)
				+ "，实际读取到的数据大小是：" + res);  // 8
			*/
            // 表示从输入流中期望读满整个字节数组bArr
            int res = fis.read(bArr);
//            System.out.println("读取到的数据内容是：" + new String(bArr)
//                    + "，实际读取到的数据大小是：" + res);  // 8

            // 练习：实现c:/a.txt到c:/b.txt文件的拷贝
            // 3.关闭流对象并释放有关的资源

            FileOutputStream fos= new FileOutputStream("D:\\Desktop\\2020-04\\b.txt");
            while((res = fis.read()) != -1) {
                fos.write(res);

            }
            System.out.println("写入成功");
            fis.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
