package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;

//MD5加密
public class Encryption {
	public static String md5Password(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }
	public static String calMd5(String filePath) throws IOException, NoSuchAlgorithmException{
		
	            File file = new File(filePath);
	            FileInputStream fis = new FileInputStream(file);
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] buffer = new byte[1024];
	            int length = -1;
	            while ((length = fis.read(buffer, 0, 1024)) != -1) {
	                md.update(buffer, 0, length);
	            }
	             BigInteger bigInt = new BigInteger(1, md.digest());
	            System.out.println("文件md5值：" + bigInt.toString(16));
	            
	      
		 return bigInt.toString(16);
	    }
	}



