package com.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.controller.ReturnMsg;
import com.dao.ComponentDao;
import com.dao.LogDao;
import com.dao.UserDao;
import com.domain.ComParent;
import com.domain.Component;
import com.domain.Log;
import com.domain.Term;
import com.domain.User;
import com.mapper.ComponentMapper;
import com.mapper.UserMapper;
import com.util.Encryption;
import com.util.FileUtil;
import com.util.FuzzySearch;
import com.util.Zip;
import com.util.Zip1;

@Service("componentService")
public class ComponentServiceImpl implements ComponentService {

	@Resource
	private ComponentMapper componentMapper;
	
	@Resource
	private ComponentDao componentDao;
	

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private LogDao LogDao;
	
	@Override
	public ReturnMsg uploadComponent(String comName,MultipartFile upfile,Component tempCom,String md5Value,String userId,String[] terms) throws  Exception{
		final String currentPath = System.getProperty("user.dir")+"\\"+UUID.randomUUID().toString();
		ReturnMsg returnMsg=new ReturnMsg();
		System.out.println(currentPath);
		System.out.println(comName);
		System.out.println(md5Value);
		String fileName = upfile.getOriginalFilename();
		//获取上传的文件名加后缀名（XXX.zip）
		//File file = new File(currentPath, fileName);
		File file = new File(currentPath+"\\"+fileName);
		//新建一个.zip文件夹，路径为currentPath+filename
		File filePath=new File(currentPath);
		//又新建了一个文件夹 filePath,路径为currentPath
		if(!filePath.exists()){
			filePath.mkdir();
		}
		if(!file.exists()){
			file.mkdir();
			file.createNewFile();
		}
		upfile.transferTo(file);
		//该方法是 springMVC 封装的方法，主要用于把内存中的内容写入到磁盘当中
		//这个时候file就是上传的压缩包
		System.out.println(file.getName());
		if(!isValid(md5Value, currentPath+"\\"+fileName)){ 
			System.out.println("文件传输失败！！！");
			file.delete();
			returnMsg.setState(-1);
			returnMsg.setMsg("md5");
			return returnMsg;
		}
		Zip.unzip(currentPath+"\\"+fileName,currentPath); 
		//BY CJL:第一个参数是需要解压的源路径；第二个参数是解压文件的目的路径，这边需要将解压的文件名字加上去
		File comFile=new File(currentPath+"\\"+"comFile");
		File parFile=new File(currentPath+"\\"+"parFile");
		if(!comFile.exists()){
			comFile.mkdirs();
		}
		if(!parFile.exists()){
			parFile.mkdirs();
		}
		File zipFile=new File(currentPath+"\\"+"zipDecom");
		File[] files=zipFile.listFiles()[0].listFiles();  
		//遍历获得大文件夹下的文件名称
		String oldPath=currentPath+"\\"+"zipDecom"+"\\"+zipFile.listFiles()[0].getName();
		//System.out.println(oldPath);
		String cdlPath="";
		//======================================================== BY CJL 
		String includePath="";
		String sourcesPath="";
		String batPath="";
		String idlPath="";
		String binPath="";
		String ComponentPath="";
		String IDLGeneratePath="";
		//boolean[] flag=new boolean[8];
		boolean[] flag=new boolean[7];
		//0 binPath
		//1 ComponentPath
		//2 IDLGeneratePath
		
		//========================================================
		if(files==null){
			filePath.delete();
			returnMsg.setState(-1);
			returnMsg.setMsg("压缩包为空");
			return returnMsg;
		}
		
		for(File file1:files){ //BY CJL:遍历循环文件夹里的文件
			//System.out.println(file1.getName());
			String file1Name=file1.getName();
			//System.out.println(file1Name);
			String suffix="";
			if (file1.isFile()) {
				
				//指的是文件类型
				if(file1Name.lastIndexOf(".")!=-1){  //BY CJL:如果文件名有后缀
					 suffix=file1Name.substring(file1Name.lastIndexOf(".")+1);
				}
				//得到后缀名suffix
				if("idl".equalsIgnoreCase(suffix)){
					idlPath=oldPath+"\\"+fileName;
					flag[6]=true;
				}else if ("cdl".equalsIgnoreCase(suffix)) {
					cdlPath=oldPath+"\\"+file1Name;
					flag[5]=true;
				}else if ("bat".equalsIgnoreCase(suffix)) {
					batPath=oldPath+"\\"+fileName;
					flag[4]=true;
				}
				else {
					
				}
				
			}else if(file1.isDirectory()){
				//目录类型
				if ("sources".equals(file1Name)) {
					sourcesPath=oldPath+"\\"+fileName;
					flag[3]=true;
				}
				else if ("include".equals(file1Name)) {
					includePath=oldPath+"\\"+fileName;
					flag[2]=true;
				}else if ("IDLGenerate".equals(file1Name)) {
					IDLGeneratePath=oldPath+"\\"+fileName;
					flag[1]=true;
				}else if ("Component".equals(file1Name)) {
					ComponentPath=oldPath+"\\"+fileName;
					flag[0]=true;
				}
				else if ("bin".equals(file1Name)) {
					binPath=oldPath+"\\"+fileName;
					//flag[0]=true;
				}
				else {
					
				}
				
			}
			
		}
		ArrayList<String> resultMsgs=new ArrayList<>();
		String[] msg=new String[]{"缺少component文件夹","缺少IDLGenerate文件夹","缺少include文件夹",
				                  "缺少sources文件夹","缺少bat文件","缺少cdl文件","缺少idl文件"};//少了哪一个文件
		for(int i=0 ; i<flag.length;i++){
			if(flag[i] == false){
				returnMsg.setState(-1);
				resultMsgs.add(msg[i]);
			}
		}
		returnMsg.setMsg(String.join(";", resultMsgs));
		
		if(returnMsg.getState() == -1){
			return returnMsg;
		}
		for(File file1:files){
			String file1Name=file1.getName();
			if ("bin".equals(file1Name)){
				FileUtil.copyFolder(oldPath+"\\"+file1Name, currentPath+"\\"+"comFile"+"\\"+file1Name);
			}else if ("Component".equals(file1Name)) {
				FileUtil.copyFolder(oldPath+"\\"+file1Name, currentPath+"\\"+"comFile"+"\\"+file1Name);
			}else if ("IDLGenerate".equals(file1Name)) {
				FileUtil.copyFolder(oldPath+"\\"+file1Name, currentPath+"\\"+"comFile"+"\\"+file1Name);
			}
		}
		/*
		if(cdlPath==null||cdlPath.length()==0){   //BY CJL:？？为什么只判断cdl
			System.out.println("cdl not found");
			returnMsg.setState(-1);
			returnMsg.setMsg("cdl不存在");
			return returnMsg;
			//接下来依次判断include、sources、bat、idl、bin、component、IDL
			
		}
		*/
		
		Zip.compress(currentPath+"\\"+"comFile", currentPath+"\\"+"comFile.zip");
		//生成一个comFile.zip文件夹
		Zip.compress(currentPath+"\\"+"parFile", currentPath+"\\"+"parFile.zip");
		//Component zipcomponent=new Component();  BY CJL
		Component component=new Component();
		//ComParent comParent=new ComParent();
		component.setComId(UUID.randomUUID().toString());
		//zipcomponent.setUrl(currentPath+"\\"+"comFile.zip");
		component.setUrl(currentPath+"\\"+upfile);
		Date date=new Date();
	    component.setCreateTime(new Timestamp(date.getTime()));
	    component.setKeyWord(tempCom.getKeyWord());
	    component.setDesInfo(tempCom.getDesInfo());
	    User user=new User();
	    user=userDao.getUserById(userId);
	    component.setUser(user);
	    String cdlHashValue=Encryption.md5Password(readCDL(cdlPath));
	    ComParent comParent2=componentDao.selectComParByCdl(cdlHashValue);
	    
	    
	    if(comParent2==null){
	    	//System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
	    	comParent2=new ComParent();
	    	comParent2.setCdlHashValue(cdlHashValue);
	    	comParent2.setLastestVersion(1);
	    	comParent2.setParName(comName);
	    	comParent2.setUrl(currentPath+"\\"+"parFile.zip");
	    	comParent2.setParId(UUID.randomUUID().toString());
	    	//comParent2.setParEntity(FileUtil.getBytesByFile(currentPath+"\\"+"parFile.zip"));
	    	comParent2.setCDL(readCDL1(cdlPath));
	    	comParent2.setLastestVersion(1);
	    	component.setVersion(1);
	    	comParent2.setInterfaceInfo(getInterface(cdlPath));
	    	componentDao.uploadComPar(comParent2);
	    }
	    else{
	    	 //System.out.println("fffffffffffffffffffffffff");
	    	component.setVersion(comParent2.getLastestVersion()+1);
	    	componentDao.updateParVersion(comParent2.getLastestVersion()+1);
	    	FileUtil.deleteFile(currentPath+"\\"+"parFile.zip");
	    }
	   // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	    //component.setComEntity(FileUtil.getBytesByFile(currentPath+"\\"+"comFile.zip"));
	    component.setComParent(comParent2);
	    //System.out.println(component.getKeyWord());
	    System.out.println(component.getVersion());
	    componentDao.uploadComponet(component);
	    insertLog(userId, 0, component.getComId());
	    FileUtil.delFolder(currentPath+"\\"+"comFile");
	    
	    FileUtil.delFolder(currentPath+"\\"+"parFile");
	    FileUtil.delFolder(currentPath+"\\"+"zipDecom");
	    FileUtil.deleteFile(currentPath+"\\"+fileName);
	    componentDao.addComTermMap(terms, component.getComId());
	    returnMsg.setState(1);//BY CJL
	    returnMsg.setMsg("上传成功"); //BY CJL
	    System.out.println("上上传成功！！！！");
		return returnMsg;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public static String readCDL(String cdlPath) throws Exception{
        String str = readCDL1(cdlPath);
        String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
        System.out.println(dest);
		return dest;
		
	}
	
	
	
	
	
	
	
	
	
	
	public static String readCDL1(String cdlPath) throws Exception{
		File file=new File(cdlPath);
		FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
            //System.out.println(s);
        }
        bReader.close();
        String str = sb.toString();
        return str;
	}
	public static boolean isValid(String md5Value,String filePath) throws Exception, IOException{
		String revMd5=Encryption.calMd5(filePath);
		return revMd5.equals(md5Value);
	}

	@Override
	public List<Component> selectComByUserId(String userId) {
		List<Component> components=componentDao.selectComByUserId(userId);
		for(int i=0;i<components.size();i++){
			components.get(i).setComName(components.get(i).getComParent().getParName());
			//System.out.println(components.get(i).getComParent().getParName());
		}
		return components;
	}

	@Override
	public Component selectComById(String comId) {
		Component component=componentDao.selectComById(comId);
		component.setComName(component.getComParent().getParName());
		return component;
	}

	@Override
	public Integer deleteComById(String userId,String comId) {
		insertLog(userId, 3, comId);
		String path=componentDao.selectComById(comId).getUrl();
		System.out.println(path);
		File file=new File(path);
		file.delete();
		componentDao.deleteMapByComId(comId);
		return componentDao.deleteComById(comId);
	}

	@Override
	public List<Component> showUnverifyCom() {
		List<Component> components=componentDao.showUnverifyCom();
		for(int i=0;i<components.size();i++){
			//System.out.println(components.get(i).getUser());
			components.get(i).setUser(userDao.getUserById(components.get(i).getUser().getUserId()));
		}
		for(int i=0;i<components.size();i++){
			components.get(i).setComName(components.get(i).getComParent().getParName());
			//System.out.println(components.get(i).getComParent().getParName());
		}
		
		return components;
	}
	@Override
	public Integer verifyCom(String userId,String comId, Integer state,String denyInfo) {
		insertLog(userId, 2, comId);
		return componentDao.verifyCom(comId, state,denyInfo);
	}

	@Override
	public String downloadComponent(String userId,String comId) throws Exception {
		final String currentPath = System.getProperty("user.dir")+"\\"+UUID.randomUUID();
		System.out.println(currentPath);
		File file=new File(currentPath);
		if(!file.exists()){
			file.mkdirs();
		}
		Component component=componentDao.selectComById(comId);
		ComParent comParent=componentDao.selectComParById(component.getComParent().getParId());
//		File comFile=new File(component.getUrl());
//		File parFile=new File(comParent.getUrl());
//		final String currentPath1 = System.getProperty("user.dir")+"\\"+comParent.getParId();
//		FileUtil.saveFile(currentPath,component.getComName()+".zip", component.getComEntity());
//		FileUtil.saveFile(currentPath1,comParent.getParName()+".zip", comParent.getParEntity());
		Zip1.unzip1(component.getUrl(), currentPath);
		Zip1.unzip1(comParent.getUrl(), currentPath);
//		FileUtil.copyFile(component.getUrl(), currentPath+"\\"+"comFile.zip");
//		FileUtil.copyFile(comParent.getUrl(), currentPath+"\\"+"parFile.zip");
//		FileUtil.copyFile(currentPath1+"\\"+comParent.getParName()+".zip", currentPath+"\\"+comParent.getParName()+".zip");
		String filePath=currentPath+"\\"+component.getComParent().getParName()+component.getVersion();
		File res=new File(filePath);
		FileUtil.copyFolder(currentPath+"\\comFile", filePath);
		FileUtil.copyFolder(currentPath+"\\parFile", filePath);
		Zip.compress(filePath, currentPath+"\\"+component.getComParent().getParName()+component.getVersion()+".zip");
		insertLog(userId, 1, comId);
		componentDao.updateDonwloadTimes(comId);
		return currentPath+"\\"+component.getComParent().getParName()+component.getVersion()+".zip";
	}

	@Override
	public List<Component> comSearch(String keyWord) {
		String[] keyWords=keyWord.split(" ");
		System.out.println(keyWords.length);
		for(int i=0;i<keyWords.length;i++){
			System.out.println(keyWords[i]);
		}
		List<Component> components=componentDao.showVerifiedCom();
		List<Component> res=new ArrayList<Component>();
		for(int i=0;i<components.size();i++){
			if(isMatch(keyWords, components.get(i)))
				res.add(components.get(i));
		}
		return res;
	}
	
	private boolean isMatch(String[] keyWords,Component component){
		String[] lists=component.getKeyWord().split(" ");
		for(int i=0;i<keyWords.length;i++){
			
			if(keyWords[i].equals(component.getComName()))
				return true;
			for(int j=0;j<lists.length;j++){
				System.out.println("K"+lists[j]+"K");
				//System.out.println(lists[j]+"  "+keyWords[i]);
				if(lists[j].equals(keyWords[i]))
				//if(FuzzySearch.search(lists[j], keyWords[i]))
					return true;
			}
		}
		return false;
	}
	//将用户对组件的操作插入 日志，operation为0表示上传，1表示下载，2表示审核，3表示删除
	private Integer insertLog(String userId,Integer operation,String comId){
		String userName=userDao.getUserById(userId).getUserName();
		Log log=new Log();
		log.setLogId(UUID.randomUUID().toString());
		String mes=userName;
		if(operation==0){
			mes+="上传";
			log.setType("上传");
		}
		else if(operation==1){
			mes+="下载";
			log.setType("下载");
		}
		else if(operation==2){
			mes+="审核";
			log.setType("审核");
		}
		else{
			mes+="删除";
			log.setType("删除");
		}
		int version=componentDao.selectComById(comId).getVersion();
		String comName=componentDao.selectComParById(componentDao.selectComById(comId).getComParent().getParId()).getParName();
		mes+=comName;
		log.setMessage(mes+version);
		Date date=new Date();
		log.setCreateTime(new Timestamp(date.getTime()));
		log.setOperator(userName);
		
		return LogDao.insertLog(log);
		
	}

	@Override
	public List<Term> showTerms() {
		List<Term> terms= componentDao.showTerms();
		Map<String, List<Term>> maps=new HashMap<>();
		for(int i=0;i<terms.size();i++)
			terms.get(i).setIsLeaf(true);
		for(int i=0;i<terms.size();i++){
			if(terms.get(i).getParId()!="-1"){
				List<Term> tmpList=maps.get(terms.get(i).getParId());
				if(tmpList==null)
					tmpList=new ArrayList<>();
				tmpList.add(terms.get(i));
				maps.put(terms.get(i).getParId(), tmpList);
			}
		}
		for(int i=0;i<terms.size();i++){
			if(maps.get(terms.get(i).getTermId())!=null){
				terms.get(i).setChildren(maps.get(terms.get(i).getTermId()));
				terms.get(i).setIsLeaf(false);
			}
			terms.get(i).setKey(terms.get(i).getTermId());
			terms.get(i).setValue(terms.get(i).getKey());
			terms.get(i).setTitle(terms.get(i).getTermName());
		}
		List<Term> res=new ArrayList<>();
		for(int i=0;i<terms.size();i++){
			if(terms.get(i).getParId().equals("-1")){
				terms.get(i).setParId(null);
				res.add(terms.get(i));
			}
		}

		return res;
		
	}

	@Override
	public List<Component> showComByTerm(String termId) {
		List<String> comIds=componentDao.showComIdByTerm(termId);
		List<Component> components=new ArrayList<>();
		for(int i=0;i<comIds.size();i++){
			//System.out.println(comIds.get(i));
			Component component=componentDao.selectComById(comIds.get(i));
			if(component.getState()==1){
			components.add(component);
			}
		}
		return components;
	}

	@Override
	public void deleteItem(String termId) {
		Queue<String> termIdQueue=new LinkedList<>();
		termIdQueue.add(termId);
		List<String> lists=new ArrayList<>();
		while(!termIdQueue.isEmpty()){
			String tmp=termIdQueue.poll();
			List<String> tmpLists=componentDao.getTermByParId(tmp);
			for(int i=0;i<tmpLists.size();i++){
				termIdQueue.add(tmpLists.get(i));
			}
			lists.add(tmp);
		}
		for(int i=0;i<lists.size();i++){
			componentDao.deleteMapByTermId(lists.get(i));
			componentDao.deleteTermById(lists.get(i));
		}
	}

	@Override
	public Integer addTerm(Term term) {
		if(componentDao.getTermByName(term.getTermName())!=null)
			return -1;
		term.setTermId(UUID.randomUUID().toString());
		if(term.getParId()==null)
			term.setParId("-1");
		return componentDao.addTerm(term);
	}

	@Override
	public List<Term> showTerms1() {
		
		return  componentDao.showTerms();
	}

	@Override
	public List<Component> showAllComs() {
		List<Component> lists=componentDao.showVerifiedCom();
		Collections.sort(lists, new Comparator<Component>() {

			@Override
			public int compare(Component o1, Component o2) {
				return o2.getDownTimes()-o1.getDownTimes();
			}
		});
		return lists;
	}
	
	
	
	public  String getInterface(String cdlPath) throws Exception{
		StringBuffer res=new StringBuffer();
		//String string=new ComponentServiceImpl().readCDL1("E:\\gbug\\Today_CDL 1\\Today.CDL");
		String string=new ComponentServiceImpl().readCDL1(cdlPath);
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
		return res.toString();
		
	}
	
	
	
	

}
