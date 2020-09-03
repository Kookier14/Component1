package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;



import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.junit.Test;

import com.service.ComponentServiceImpl;
import com.util.Encryption;
import com.util.FileUtil;
import com.util.Zip;

public class CommTest {
	public static void main(String[] args) throws Exception {
//		StringBuffer sBuffer=new StringBuffer("interface");
//		System.out.println(sBuffer.toString().equals("interface"));
		
		String string="localhost:8080/Component/user/addUser";
		System.out.println(string.indexOf("8080"));
		System.out.println(string.substring(string.indexOf("8080")+5));
	}
	
	public static void getInterface() throws Exception{
		StringBuffer res=new StringBuffer();
		String string=new ComponentServiceImpl().readCDL1("E:\\gbug\\Today_CDL 1\\Today.CDL");
		//System.out.println(string);
		List<String> lists=new ArrayList<String>();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<string.length();i++){
			if(string.charAt(i)=='\t'||string.charAt(i)=='\r'||string.charAt(i)=='\n'||string.charAt(i)==' '){
				if(sb.length()>0){
					//System.out.println(sb+"  "+sb.length()+sb.equals("interface"));
				if(sb.toString().equals("interface")){
					
					int j=i;
					int t=0;
					while(j<string.length()){
						sb.append(string.charAt(j));
						if(string.charAt(j)=='}'){
							break;
						}
						j++;
					}
					sb.append(';');
					sb.append("\n");
					i=j++;
					res.append(sb);
				}
				
				sb=new StringBuffer();
				}
			}
			else{
				sb.append(string.charAt(i));
			}
		}
		System.out.println(lists.size());
		System.out.println(res);
		
	}
	
	@Test
	public void testUnzip() throws Exception{
		String str1="D:\\54\\eclipse\\015d59c4-bf16-451b-9aa8-ff1bca9b93ed\\comFile2.zip";
		String str2="D:\\54\\eclipse\\015d59c4-bf16-451b-9aa8-ff1bca9b93ed\\fghh";
		String str3="D:\\54\\eclipse\\015d59c4-bf16-451b-9aa8-ff1bca9b93ed\\fdfsd";
		FileUtil.copyFolder(str2, str3);
		//Zip.unzip(str1,str2);
		//unzip(str1, str2);
	}
	
	public void testZip() throws IOException{
		String str1="D:\\54\\eclipse\\015d59c4-bf16-451b-9aa8-ff1bca9b93ed\\aa.txt";
		String str2="D:\\54\\eclipse\\015d59c4-bf16-451b-9aa8-ff1bca9b93ed\\tta.zip";
		Zip.compress(str1,str2);
	}
	public static void unzip(String sourcefiles, String decompreDirectory) throws IOException {
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
		                      throw new IOException("½âÑ¹Ê§°Ü£º" + ex.toString());
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
		              throw new IOException("½âÑ¹Ê§°Ü£º" + ex.toString());
		          } finally {
		              if (readfile != null) {
		                  try {
		                      readfile.close();
		                  } catch (IOException ex) {
		                      ex.printStackTrace();
		                      throw new IOException("½âÑ¹Ê§°Ü£º" + ex.toString());
		                  }
		              }
		          }
		      }
	
}
