package com.vote.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.common.commonUtil;
import com.vote.dao.QuestionnaireAnswerDao;
import com.vote.dao.QuestionnaireConDao;
import com.vote.dao.QuestionnaireDao;
import com.vote.dao.QuestionnaireOptionDao;
import com.vote.po.Questionnaire;
import com.vote.po.QuestionnaireAnswer;
import com.vote.po.QuestionnaireCon;
import com.vote.po.QuestionnaireOption;
import com.vote.po.QuestionnaireProject;
import com.vote.po.UserCommon;
import com.vote.service.QuestionnaireConService;
@Service("questionnaireConService")
public class QuestionnaireConServiceImpl implements QuestionnaireConService {

	@Resource
	private QuestionnaireConDao questionnaireConDao;
	@Resource
	private QuestionnaireDao questionnaireDao;
	@Resource
	private QuestionnaireAnswerDao questionnaireAnswerDao;
	@Resource
	private QuestionnaireOptionDao questionnaireOptionDao;
	@Override
	public int addQuestionnaireCon(String[] result, String ip) throws Exception {
	
		if(result!=null &&result.length>0){
			List<QuestionnaireAnswer>answers = new ArrayList<QuestionnaireAnswer>();
			QuestionnaireProject questionnaireProject = null;
			boolean[] re =new boolean[result.length];
			for (int i = 0; i < result.length; i++) {
			 	try {
					if(result[i]!=null && result[i].indexOf(",") != -1){ //重要数据检验，不对则为前端异常，直接返回
						String[] info = result[i].split(",");
						if(info[0]!=null && !"".equals(info[0]))
						  {
								long qid = Long.parseLong(info[0]);
								Questionnaire questionnaire = questionnaireDao.get(qid);
								if(questionnaire !=null){
									questionnaireProject = questionnaire.getQuestionnaireProject();
									QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();
									questionnaireAnswer.setQuestionnaire(questionnaire);
									questionnaireAnswer.setUid(questionnaire.getUid());
									questionnaireAnswer.setType(questionnaire.getType());
									questionnaireAnswer.setValue(info[1]); //此处不处理选项支持度+1,交给定时程序处理
									if (info.length>2) {
										questionnaireAnswer.setMessage(info[2]);
									}
									answers.add(questionnaireAnswer);
								}
								else {
									re[i]=false;
								}
						  }
						else {
							re[i]=false;
						}
					}else {
						return 0;
					}
				} catch (Exception e) {
					re[i]=false;
				}
			}
		//不需返回每个答案状态，此处暂不处理re[]
		 if (answers.size()>0) {
			QuestionnaireCon questionnaireCon = new QuestionnaireCon();
			questionnaireCon.setAnswers(answers);
			questionnaireCon.setAddDate(new Date());
			questionnaireCon.setIp(ip);
			if(questionnaireProject !=null)
			questionnaireCon.setQuestionnaireProject(questionnaireProject);
			else {
				return 0;
			}
		  if(questionnaireConDao.save(questionnaireCon))
			  return 2;
		  else {
			return 1;
		   }
		 }else {
			return 0;
		}	
		}
		else {
			return 0;
		}
	}

	@Override
	public int addQuestionnaireCon(String[] result, UserCommon userCommon, String ip)
			throws Exception {
		if(result!=null &&result.length>0){
			List<QuestionnaireAnswer>answers = new ArrayList<QuestionnaireAnswer>();
			QuestionnaireProject questionnaireProject = null;
			boolean[] re =new boolean[result.length];
			for (int i = 0; i < result.length; i++) {
			 	try {
					if(result[i]!=null && result[i].indexOf(",") != -1){ //重要数据检验，不对则为前端异常，直接返回
						String[] info = result[i].split(",");
						if(info[0]!=null && !"".equals(info[0]))
						  {
								long qid = Long.parseLong(info[0]);
								Questionnaire questionnaire = questionnaireDao.get(qid);
								if(questionnaire !=null){
									questionnaireProject = questionnaire.getQuestionnaireProject();
									QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();
									questionnaireAnswer.setQuestionnaire(questionnaire);
									questionnaireAnswer.setType(questionnaire.getType());
									questionnaireAnswer.setUid(questionnaire.getUid());
									questionnaireAnswer.setValue(info[1]); //此处不处理选项支持度+1,交给定时程序处理
									questionnaireAnswer.setUserCommon(userCommon);
									if (info.length>2) {
										questionnaireAnswer.setMessage(info[2]);
									}
									answers.add(questionnaireAnswer);
								}
								else {
									re[i]=false;
								}
						  }
						else {
							re[i]=false;
						}
					}else {
						return 0;
					}
				} catch (Exception e) {
					re[i]=false;
				}
			}
		 if (answers.size()>0) {
			QuestionnaireCon questionnaireCon = new QuestionnaireCon();
			questionnaireCon.setAnswers(answers);
			questionnaireCon.setAddDate(new Date());
			questionnaireCon.setIp(ip);
			if(questionnaireProject !=null)
			questionnaireCon.setQuestionnaireProject(questionnaireProject);
			else {
				return 0;
			}
			questionnaireCon.setUserCommon(userCommon);
		  if(questionnaireConDao.save(questionnaireCon))
			  return 2;
		  else {
			return 1;
		   }
		 }else {
			return 0;
		}	
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean getIsAccess(UserCommon userCommon, String ip,int projectId)
			throws Exception {
		
		if(userCommon !=null){
			if(questionnaireConDao.getQuestionnaireCon(userCommon.getId(),projectId))
				return false;
			else {
				return true;
			}
		}else{
		if(questionnaireConDao.getQuestionnaireCon(ip, projectId))
				return false;
		else {
		 	return true;
		}
	 }
	}

	@Override
	public void startUpdateQuestionnaireOption() throws Exception {
		
				List<QuestionnaireAnswer>answers = questionnaireAnswerDao.getNoUpdateAnswers();
				 commonUtil.p("抓取到未更新答案"+answers.size());
				Map<Long, Integer>zongMap = new HashMap<Long, Integer>();
				for (QuestionnaireAnswer q:answers) {
					String qa = q.getValue();
					if(qa.matches("\\d+") || qa.indexOf("-")!= -1){ //验证数据格式

						String[] id = qa.split("-");
						for (int i = 0; i < id.length; i++) {
							Long aid=null;
							try {
								aid = Long.parseLong(id[i]);
							} catch (Exception e) {
								e.printStackTrace();
								continue;
							}
							if(zongMap.containsKey(aid))
							 {
								int number=zongMap.get(aid);
								zongMap.put(aid, number+1);
							 }
							else {
								zongMap.put(aid, 1);
							}
						}
					}
				}//循环统计完成
	     for (Map.Entry<Long, Integer> entry : zongMap.entrySet()) {
				Long key =entry.getKey();
				Integer value= entry.getValue();
				QuestionnaireOption questionnaireOption = questionnaireOptionDao.get(key);
				if(questionnaireOption !=null){
					questionnaireOption.setNumber(questionnaireOption.getNumber()+value);
					questionnaireOptionDao.update(questionnaireOption);
				}
			 }
	    for (QuestionnaireAnswer questionnaireAnswer : answers) {
			questionnaireAnswer.setIsUpdate(1);
			questionnaireAnswerDao.update(questionnaireAnswer);
		}
				
	}

}




