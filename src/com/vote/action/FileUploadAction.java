package com.vote.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vote.common.CircleImage;
import com.vote.common.VoteImage;
import com.vote.common.commonUtil;

@Component("fileUploadAction")
@Scope("prototype")
public class FileUploadAction extends CommonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String IMGPATH = "/upload/image";
	private static final String IMGCIRCLEPATH = "/upload/circle/image";
	private File file;            //文件
	private String fileFileName;  //文件名 
	private String filePath;      //文件路径
	private InputStream inputStream;
	private String msg;
	
	@SuppressWarnings("resource")
	public String imgUpload(){
	    
		try {
			String path = ServletActionContext.getServletContext().getRealPath(IMGPATH);
			File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
			if (!file.exists()) {
			    file.mkdir();
			}
			File f = this.getFile();
			String fileTypeString=getFileType(fileFileName);
			if (fileTypeString ==null) {
				msg="图片获取错误";
				return ERROR;
			}
			commonUtil.p("文件类型为"+fileTypeString);
			String[] accessType= {"jpg","jpeg","gif","png","bmp"};
			if(!commonUtil.contain(accessType, fileTypeString))
				return "error";
			//新名字
			SimpleDateFormat format=new SimpleDateFormat("YYYYMMddHHmm");
			String dateString=format.format(new Date());
			int ro=(int)(Math.random()*10000);
			String fileNewName=dateString+ro+"."+fileTypeString;
			
			commonUtil.p("新文件名"+fileNewName);
			this.fileFileName=fileNewName;
      FileInputStream inputStream1 = new FileInputStream(f);
      FileOutputStream outputStream = new FileOutputStream(path + "/"
			       + fileNewName);
      byte[] buf = new byte[1024];
      int length = 0;
      while ((length = inputStream1.read(buf)) != -1) {
			   outputStream.write(buf, 0, length);
      }
      inputStream1.close();
      outputStream.flush();
      new Thread(new VoteImage(path + "/"
			       + fileNewName,fileTypeString)).start();
      msg="图片上传成功";
      return SUCCESS;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg="文件夹创建失败";
			return "error";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg="图片上传失败";
			return "error";
		}
	}

	@SuppressWarnings("resource")
	public String imgCircleUpload(){
	    
		try {
			String path = ServletActionContext.getServletContext().getRealPath(IMGCIRCLEPATH);
			File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
			if (!file.exists()) {
			    file.mkdir();
			}
			File f = this.getFile();
			String fileTypeString=getFileType(fileFileName);
			if (fileTypeString ==null) {
				msg="图片获取错误";
				return ERROR;
			}
			commonUtil.p("文件类型为"+fileTypeString);
			String[] accessType= {"jpg","jpeg","gif","png","bmp"};
			if(!commonUtil.contain(accessType, fileTypeString))
				return "error";
			//新名字
			SimpleDateFormat format=new SimpleDateFormat("YYYYMMddHHmm");
			String dateString=format.format(new Date());
			int ro=(int)(Math.random()*10000);
			String fileNewName=dateString+ro+"."+fileTypeString;
			
			commonUtil.p("新文件名"+fileNewName);
			this.fileFileName=fileNewName;
      FileInputStream inputStream1 = new FileInputStream(f);
      FileOutputStream outputStream = new FileOutputStream(path + "/"
			       + fileNewName);
      byte[] buf = new byte[1024];
      int length = 0;
      while ((length = inputStream1.read(buf)) != -1) {
			   outputStream.write(buf, 0, length);
      }
      inputStream1.close();
      outputStream.flush();
      new Thread(new CircleImage(path + "/"
		       + fileNewName,fileTypeString)).start();
      msg="图片上传成功";
      return SUCCESS;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg="文件夹创建失败";
			return "error";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg="图片上传失败";
			return "error";
		}
	}
	/**
	 * 取文件后缀
	 * @param fileUri
	 * @return
	 */
	public String getFileType(String fileName){
		if(fileName !=null && !"".equals(fileName))
		{
		 String fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
		 return fileType;
		}
		else {
			return null;
		}
		}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
   
	
	
}
