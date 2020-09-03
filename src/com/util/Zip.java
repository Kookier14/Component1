package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.tools.zip.ZipFile;

public class Zip {
	public static void unzip(String zipFile,String path) throws Exception {
		System.out.println(zipFile);
        FileSystem fs = FileSystems.newFileSystem(Paths.get(zipFile), null);
         final String currentPath =path+ "\\"+"zipDecom";
        System.out.println("current directory:" + currentPath);
        System.out.println(fs.getPath("/"));
        Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path destPath = Paths.get(currentPath, file.toString());
                Files.deleteIfExists(destPath);
                Files.createDirectories(destPath.getParent());
                Files.move(file, destPath);
                return FileVisitResult.CONTINUE;
            }
        });
    }
	 public static void compress(String srcFilePath, String destFilePath) throws IOException {
	        //
	        File src = new File(srcFilePath);
	 
	        if (!src.exists()) {
	            throw new RuntimeException(srcFilePath + "不存在");
	        }
	        File zipFile = new File(destFilePath);
	        if(!zipFile.exists()){
	        	System.out.println("dfsd");
	        	zipFile.createNewFile();
	        }
	 
	        try {
	 
	            FileOutputStream fos = new FileOutputStream(zipFile);
	            ZipOutputStream zos = new ZipOutputStream(fos);
	            String baseDir = "";
	            compressbyType(src, zos, baseDir);
	            zos.close();
	 
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	 
	        }
	    }
	 private static void compressbyType(File src, ZipOutputStream zos,String baseDir) {
		 
      if (!src.exists())
          return;
      System.out.println("压缩路径" + baseDir + src.getName());
      //判断文件是否是文件，如果是文件调用compressFile方法,如果是路径，则调用compressDir方法；
      if (src.isFile()) {
          //src是文件，调用此方法
          compressFile(src, zos, baseDir);
           
      } else if (src.isDirectory()) {
          //src是文件夹，调用此方法
          compressDir(src, zos, baseDir);

      }

  }
	 private static void compressFile(File file, ZipOutputStream zos,String baseDir) {
      if (!file.exists())
          return;
      try {
          BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
          ZipEntry entry = new ZipEntry(baseDir + file.getName());
          zos.putNextEntry(entry);
          int count;
          byte[] buf = new byte[1024];
          while ((count = bis.read(buf)) != -1) {
              zos.write(buf, 0, count);
          }
          bis.close();

      } catch (Exception e) {
        // TODO: handle exception

      }
  }
	 private static void compressDir(File dir, ZipOutputStream zos,String baseDir) {
      if (!dir.exists())
          return;
      File[] files = dir.listFiles();
      if(files.length == 0){
          try {
              zos.putNextEntry(new ZipEntry(baseDir + dir.getName()+File.separator));
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      for (File file : files) {
          compressbyType(file, zos, baseDir + dir.getName() + File.separator);
      }
}  
	


}
