package com.vote.common;

import java.io.File;

/**
 * 投票体照片处理
 * @author bagge
 *
 */
public class VoteImage implements Runnable{

	private String path;
	private String type;
	
	public VoteImage(String path,String type){
		this.path = path;
		this.type=type;
	}
	@Override
	public void run() {
		
		File file = new File(path);
		if (!file.exists()) {  //验证文件是否存在
			return;
		}
        ImageUtils.scale2(path,type, path, 567, 390, false);//测试OK
        commonUtil.p("图片转换结束");
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	
}
