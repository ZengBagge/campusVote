package com.vote.common;

import java.io.File;

public class CircleImage implements Runnable {

	private String path;
	private String type;
	
	public CircleImage(String path,String type){
		this.path = path;
		this.type=type;
	}
	@Override
	public void run() {
		
		File file = new File(path);
		if (!file.exists()) {  //验证文件是否存在
			return;
		}
		 commonUtil.p("图片转换开始");
        ImageUtils.scale2(path, type, path, 600);
        commonUtil.p("图片转换结束");
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}


}
