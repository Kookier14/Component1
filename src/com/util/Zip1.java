package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class Zip1 {
	 //unzip解压我自己的压缩文件会报错，而unzip1不会
	 public static void unzip1(String sourcefiles, String decompreDirectory) throws IOException {
        ZipFile readfile = null;
         try {
             readfile =new ZipFile(sourcefiles);
             Enumeration takeentrie = readfile.getEntries();
             ZipEntry zipEntry = null;
             File credirectory = new File(decompreDirectory);
             credirectory.mkdirs();
             while (takeentrie.hasMoreElements()) {
                 zipEntry = (ZipEntry) takeentrie.nextElement();
                 String entryName = zipEntry.getName();
                 InputStream in = null;
                 FileOutputStream out = null;
                 try {
                     if (zipEntry.isDirectory()) {
                         String name = zipEntry.getName();
                         name = name.substring(0, name.length() - 1);
                         File  createDirectory = new File(decompreDirectory+ File.separator + name);
                         createDirectory.mkdirs();
                     } else {
                         int index = entryName.lastIndexOf("\\");
                         if (index != -1) {
                             File createDirectory = new File(decompreDirectory+ File.separator+ entryName.substring(0, index));
                             createDirectory.mkdirs();
                         }
                         index = entryName.lastIndexOf("/");
                         if (index != -1) {
                             File createDirectory = new File(decompreDirectory + File.separator + entryName.substring(0, index));
                             createDirectory.mkdirs();
                         }
                         File unpackfile = new File(decompreDirectory + File.separator + zipEntry.getName());
                         in = readfile.getInputStream(zipEntry);
                         out = new FileOutputStream(unpackfile);
                         int c;
                         byte[] by = new byte[1024];
                         while ((c = in.read(by)) != -1) {
                             out.write(by, 0, c);
                         }
                         out.flush();
                     }
                 } catch (IOException ex) {
                     ex.printStackTrace();
                     throw new IOException("解压失败：" + ex.toString());
                 } finally {
                     if (in != null) {
                         try {
                             in.close();
                         } catch (IOException ex) {
 
                         }
                     }
                     if (out != null) {
                         try {
                             out.close();
                         } catch (IOException ex) {
                             ex.printStackTrace();
                         }
                     }
                     in=null;
                     out=null;
                 }
 
             }
         } catch (IOException ex) {
             ex.printStackTrace();
             throw new IOException("解压失败：" + ex.toString());
         } finally {
             if (readfile != null) {
                 try {
                     readfile.close();
                 } catch (IOException ex) {
                     ex.printStackTrace();
                     throw new IOException("解压失败：" + ex.toString());
                 }
             }
         }
     }

}
