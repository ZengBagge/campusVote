/**
 * @author bagge
 *  定时任务，完成问卷题目选项支持率
 */
package com.vote.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.vote.common.commonUtil;
import com.vote.service.QuestionnaireConService;

@Component("questionnaireTask")
public class QuestionnaireTask{
	
	@Resource
	private  QuestionnaireConService questionnaireConService;
	
	
	@Scheduled(fixedDelay=1000*60*60)//暂定为1小时执行一次
	public void init(){
		commonUtil.p("问卷题目热度更改调度任务进行中");
		try {
			questionnaireConService.startUpdateQuestionnaireOption();
		} catch (Exception e) {
			e.printStackTrace();
			commonUtil.p("问卷题目热度更改调度任务出错");
		}finally{
			commonUtil.p("问卷题目热度更改调度任务已完成");
		}
	}

}