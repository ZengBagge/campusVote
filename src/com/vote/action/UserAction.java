package com.vote.action;

import it.sauronsoftware.base64.Base64;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vote.common.CookieUtils;
import com.vote.common.HttpUtil;
import com.vote.common.commonUtil;
import com.vote.po.UserCommon;
import com.vote.service.UserService;


@Controller("userAction")
@Scope("prototype")
public class UserAction extends CommonAction{

	@Resource
	private UserService userService;
	private static final long serialVersionUID = 1L;
	public static final String USER_SESSION ="voteUserSession";
    private String uid;
    private String pwd;
    private int school;
    private String errorMesageString;
    private CookieUtils cookieUtils = new CookieUtils();
    private String old_url;
    private String get_access_token_url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "appid=APPID" +
            "&secret=SECRET&" +
            "code=CODE&grant_type=authorization_code";
    private String get_userinfo="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    private String code;
    private String state;
    private   JSONObject userInfoJO;
    private String weiurl;
	static Logger logger = Logger.getLogger(UserAction.class.getName()); //log4j的使用
    
	public String login(){
    	if (session.get(USER_SESSION)==null || "".equals(session.get(USER_SESSION))) { 
    		if(old_url!=null && !"".equals(old_url))
    			session.put("old_url", old_url);
    		 weiurl =HttpUtil.getWeiCode();
			return "login";
		}else {
			return "success";
		}
    	
    }
    
public String loginForm() throws Exception {
		
		try {
			String ip = ServletActionContext.getRequest().getRemoteAddr();
			UserCommon userCommon=userService.getUserByPwd(uid, pwd);
			if(userCommon==null || "".equals(userCommon)){
				errorMesageString="学号或密码错误！";
				return "login";
			}
			else{
				commonUtil.p("登录成功");
				userService.setLoginMessage(ip, userCommon);//设置登录信息
				session.put(USER_SESSION, userCommon);   //设置前端session

							/***************************密码帐号加密*******/
							String uidKey = Base64.encode(uid); //帐号加密
                            String  pwdKey =commonUtil.getRandomString(3)+Base64.encode(pwd);	
							/********************************************/
                             Cookie uidCookie=new Cookie("user.cookie",uidKey+","+pwdKey);
                             uidCookie.setMaxAge(604800);//一个星期
							 response.addCookie(uidCookie);// 添加cookie到response中  
				return SUCCESS;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorMesageString="学号或密码错误！";
			return "login";
		}
		
	}
    public String regForm() throws Exception{
    	if ("".equals(school) || "".equals(uid) ||"".equals(pwd)) {
    		errorMesageString ="注册输入信息有误";
			return "login";
		}else {
			if (userService.addUser(uid, pwd, school)) {
				return loginForm();
			}else {
				errorMesageString = "注册失败，请重试";
				return "login";
			}
		}
    	
    }
/**
 * 用户推出
 * @return
 */
public String logout() {  
    HttpSession session = request.getSession(false);  
    if (session != null)  
        session.removeAttribute(USER_SESSION);  
       session.invalidate();
       Cookie cookie =cookieUtils.delCookie(request);
       if (cookie != null) {
    	   response.addCookie(cookie);
	     }
        commonUtil.p("用户退出登录");
        return "login";  
}  

public Boolean changePass(String pwd,String newPwd){
	try {
		UserCommon userCommon=(UserCommon)session.get(USER_SESSION);
		String uid=userCommon.getUid();
		return userService.setPass(pwd,newPwd,uid);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}

}
public String loginClient(){
	
	try {
		String userString = (String) session.get("edu.yale.its.tp.cas.client.filter.user");
		if(userString != null && !"".equals(userString))
			return SUCCESS;
		else {
			return LOGIN;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return ERROR;
	}
}

public boolean getUserState(HttpSession session){
	UserCommon userCommon= (UserCommon)session.getAttribute(USER_SESSION);
	if (userCommon !=null) {
		return true;
	}else {
		return false;
	}
}

public String weiLogin(){
	
	try {
		commonUtil.p("code:"+code);
		if(code !=null && code.length()>0)
		{
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8"); 
	    get_access_token_url=get_access_token_url.replace("APPID", HttpUtil.APPID);
	    get_access_token_url=get_access_token_url.replace("SECRET", HttpUtil.SECRET);
	    get_access_token_url=get_access_token_url.replace("CODE", code);
	    commonUtil.p("获取access_token地址"+get_access_token_url);
	    String json=HttpUtil.httpRequest(get_access_token_url,"GET",null);
	    commonUtil.p("返回json"+json);
	    if(json !=null){
		    JSONObject jsonObject=JSONObject.fromObject(json);
		    String access_token=jsonObject.getString("access_token");
		    String openid=jsonObject.getString("openid");
	       commonUtil.p("access_token："+access_token);
		    get_userinfo=get_userinfo.replace("ACCESS_TOKEN", access_token);
		    get_userinfo=get_userinfo.replace("OPENID", openid);
	        String userInfoJson=HttpUtil.httpRequest(get_userinfo,"GET",null);
	        commonUtil.p("userInfo:"+userInfoJson);
	       userInfoJO=JSONObject.fromObject(userInfoJson);
	       return SUCCESS;
	    }else {
	    	errorMesageString = "微信登录授权失败";
			return LOGIN;
		}
		}else {
			errorMesageString = "微信登录授权失败";
			return LOGIN;
		}
	} catch (Exception e) {
		errorMesageString = "微信登录授权失败";
		return LOGIN;
	}  
}

public UserService getUserService() {
	return userService;
}

public void setUserService(UserService userService) {
	this.userService = userService;
}

public String getUid() {
	return uid;
}

public void setUid(String uid) {
	this.uid = uid;
}

public String getPwd() {
	return pwd;
}

public void setPwd(String pwd) {
	this.pwd = pwd;
}

public String getErrorMesageString() {
	return errorMesageString;
}

public void setErrorMesageString(String errorMesageString) {
	this.errorMesageString = errorMesageString;
}


public int getSchool() {
	return school;
}

public void setSchool(int school) {
	this.school = school;
}

public CookieUtils getCookieUtils() {
	return cookieUtils;
}

public void setCookieUtils(CookieUtils cookieUtils) {
	this.cookieUtils = cookieUtils;
}

public String getOld_url() {
	return old_url;
}

public void setOld_url(String old_url) {
	this.old_url = old_url;
}

public JSONObject getUserInfoJO() {
	return userInfoJO;
}

public void setUserInfoJO(JSONObject userInfoJO) {
	this.userInfoJO = userInfoJO;
}

public String getWeiurl() {
	return weiurl;
}

public void setWeiurl(String weiurl) {
	this.weiurl = weiurl;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

	
	
}
