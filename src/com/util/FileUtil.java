package com.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	public static void copyFolder(String strPatientImageOldPath, String strPatientImageNewPath) {
        File fOldFolder = new File(strPatientImageOldPath);//���ļ���
        try {
            File fNewFolder = new File(strPatientImageNewPath);//���ļ���
            if (!fNewFolder.exists()) {
                fNewFolder.mkdirs();//�����ھʹ���һ���ļ���
            }
            File[] arrFiles = fOldFolder.listFiles();//��ȡ���ļ����������е��ļ�
            for (int i = 0; i < arrFiles.length; i++) {
            	//System.out.println("aa");
                //��ԭ����·�����������ڵ�·��������һ���ļ�
                if (!arrFiles[i].isDirectory()) {
                    copyFile(strPatientImageOldPath + "/" + arrFiles[i].getName(), strPatientImageNewPath + "/" + arrFiles[i].getName());
                }
                else{
                	copyFolder(strPatientImageOldPath + "/" + arrFiles[i].getName(), strPatientImageNewPath + "/" + arrFiles[i].getName());
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

}
	 public static void copyFile(String strOldpath,String strNewPath)
	    {
	        try
	        {
	 
	            File fOldFile = new File(strOldpath);
	            if (fOldFile.exists())
	            {
	                int bytesum = 0;
	                int byteread = 0;
	                InputStream inputStream = new FileInputStream(fOldFile);
	                FileOutputStream fileOutputStream = new FileOutputStream(strNewPath);
	                byte[] buffer = new byte[1444];
	                while ( (byteread = inputStream.read(buffer)) != -1)
	                {
	                    bytesum += byteread; //��һ���Ǽ�¼�ļ���С�ģ�����ɾȥ
	                    fileOutputStream.write(buffer, 0, byteread);//������������һ��������д�����ݣ�
	                    //�ڶ��������Ǵ�ʲô�ط���ʼд����������������Ҫд�Ĵ�С
	                }
	                inputStream.close();
	                fileOutputStream.close();
	            }
	        }
	        catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            System.out.println("���Ƶ����ļ�����");
	            e.printStackTrace();
	        }
	        
	    }
	 public static byte[] getBytesByFile(String pathStr) {
	        File file = new File(pathStr);
	        try {
	            FileInputStream fis = new FileInputStream(file);
	            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
	            byte[] b = new byte[1000];
	            int n;
	            while ((n = fis.read(b)) != -1) {
	                bos.write(b, 0, n);
	            }
	            fis.close();
	            byte[] data = bos.toByteArray();
	            bos.close();
	            return data;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 //���ڽ��ֽ���ת�����ļ�
	 public static void saveFile(String filepath,String fileName,byte [] data)throws Exception{   
		    if(data != null){ 
		      File file1=new File(filepath);
		      if(!file1.exists()){
		    	  file1.mkdir();
		      }
		      filepath =filepath+"\\" + fileName;   
		      File file  = new File(filepath);   
		      if(file.exists()){   
		         file.delete();   
		      }
		      
		      FileOutputStream fos = new FileOutputStream(file);   
		      fos.write(data,0,data.length);   
		      fos.flush();   
		      fos.close();   
		    }   
		}
	 public static boolean deleteFile(String path) {
			
	        File file = new File(path);
	        
	      
	        if(file.isDirectory()) {
	        	File[] listfile = file.listFiles(); 
	            if (delAllFile(path)) {	                
	                return true;
	            } 
	        }
	        //���ļ�
	        if(!file.isDirectory()) {
	            if (file.delete()) {	            
	                return true;
	            }
	        }
			return false;
		}
	 private static boolean delAllFile(String path) {
	        boolean flag = false;
	        File file = new File(path);
	        if (!file.exists()) { return flag; }
	        String[] tempList = file.list();
	        File temp = null;
	        for (int i = 0; i < tempList.length; i++) {
	            if (path.endsWith(File.separator)) {
	                temp = new File(path + tempList[i]);
	            } else {
	                temp = new File(path + File.separator + tempList[i]);
	            }
	            //���ļ�
	            if (temp.isFile()) {
	                temp.delete();
	                flag = true;
	            }
	            //���ļ���
	            if (temp.isDirectory()) {
	            	//��ɾ���ļ���������ļ�
	                delAllFile(path + "/" + tempList[i]);
	                //��ɾ�����ļ���
	                delFolder(path + "/" + tempList[i]);
	                flag = true;
	            }
	        }
	        return flag;
	    }

	 public static void delFolder(String folderPath) {
	        try {
	        	//ɾ����������������
	            delAllFile(folderPath);
	            String filePath = folderPath;
	            filePath = filePath.toString();
	            java.io.File myFilePath = new java.io.File(filePath);
	            //ɾ�����ļ���
	            myFilePath.delete();
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }
	    }
	
	

	
}
