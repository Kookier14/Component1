package com.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

import com.util.Zip;

public class DecomFileTest {
	
	
	public static void main(String[] args) throws Exception {
		//decomFile();
		//unzip("E:\\gbug\\Today_CDL.zip");
		compress("E:\\gbug\\Today_CDL", "E:\\gbug\\Today_CDL.zip");
	}
	public static void decomFile(){
		 File file = new File("E:\\gbug\\Today_CDL.zip");//当前压缩文件
	        ZipInputStream zin;//创建ZipInputStream对象
	        try {
	            ZipFile zipFile = new ZipFile(file);//创建压缩文件对象
	            zin = new ZipInputStream(new FileInputStream(file));//实例化对象，指明要解压的文件
	            ZipEntry entry ;
	            while (((entry=zin.getNextEntry())!=null)){//如果entry不为空，并不在同一个目录下
	                System.out.println(entry.getName());
	                File tmp = new File(entry.getName());//解压出的文件路径
	                if (!tmp.exists()){//如果文件不存在
	                    tmp.getParentFile().mkdirs();//创建文件父类文件夹路径
	                    OutputStream os = new FileOutputStream(tmp);//将文件目录中的文件放入输出流
	                    //用输入流读取压缩文件中制定目录中的文件
	                    InputStream in = zipFile.getInputStream(entry);
	                    int count = 0;
	                    while ((count = in.read())!=-1){//如有输入流可以读取到数值
	                        os.write(count);//输出流写入
	                    }
	                    os.close();
	                    in.close();
	                }
	                zin.closeEntry();
	                System.out.println(entry.getName()+"解压成功");
	            }
	            zin.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		
	}
	public static void unzip(String zipFile) throws Exception {
        FileSystem fs = FileSystems.newFileSystem(Paths.get(zipFile), null);
        final String currentPath = System.getProperty("user.dir");
        System.out.println("current directory:" + currentPath);
 
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
	 public static void compress(String srcFilePath, String destFilePath) {
	        //
	        File src = new File(srcFilePath);
	 
	        if (!src.exists()) {
	            throw new RuntimeException(srcFilePath + "不存在");
	        }
	        File zipFile = new File(destFilePath);
	 
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
	 @Test
	 public void test() throws IOException{
		 Zip.compress("D:\\54\\eclipse\\b8ce8118-93d1-4896-a97c-19eb51b7d0b7", "D:\\54\\eclipse\\aac1.zip");
	 }
	
}
