package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;

//MD5����
public class Encryption {
	public static String md5Password(String password) {

        try {
            // �õ�һ����ϢժҪ��
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // ��ÿһ��byte ��һ�������� 0xff;
            for (byte b : result) {
                // ������
                int number = b & 0xff;// ����
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // ��׼��md5���ܺ�Ľ��
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
	            System.out.println("�ļ�md5ֵ��" + bigInt.toString(16));
	            
	      
		 return bigInt.toString(16);
	    }
	}



