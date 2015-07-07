package com.vote.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.common.commonUtil;
import com.vote.dao.QuestionnaireDao;
import com.vote.dao.QuestionnaireProjectDao;
import com.vote.po.Questionnaire;
import com.vote.po.QuestionnaireOption;
import com.vote.po.QuestionnaireProject;
import com.vote.service.QuestionnaireService;
@Service("questionnaireService")
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Resource
	private QuestionnaireDao questionnaireDao;
	@Resource
	private QuestionnaireProjectDao questionnaireProjectDao;
	@Override
	public int addQuestionnaire(String[] info, String[] option)
			throws Exception {
	   
		try {
		int projectId = Integer.parseInt(info[0]);
		QuestionnaireProject q = questionnaireProjectDao.get(projectId);
		if (q!=null) {
			Questionnaire questionnaire =new Questionnaire();
			questionnaire.setQuestionnaireProject(q);
			if(info[1]!=null &&!"".equals(info[1]))
				{
				int uid= Integer.parseInt(info[1]);
				questionnaire.setUid(uid);
				}
			else {
				return 0;
			}
			if(info[2]!=null &&!"".equals(info[2]))
			{
			String title = commonUtil.trimInnerSpaceStr(info[2]);
			questionnaire.setTitle(title);
			}
		    else {
			return 0;
		    }
			if(info[3]!=null &&!"".equals(info[3]))
			{
			int type= Integer.parseInt(info[3]);
			commonUtil.p("添加题目类型"+type);
			questionnaire.setType(type);
			}
		else {
			return 0;
		}
			if(info[4]!=null &&!"".equals(info[4]))
			{
			int model= Integer.parseInt(info[4]);
			questionnaire.setModel(model);
			}
		else {
			return 0;
		}
			List<QuestionnaireOption>questionnaireOptions = new ArrayList<QuestionnaireOption>();
			if(option !=null && option.length>0)
			{
		     	for (int i = 0; i < option.length; i++) {
					if (option[i]!=null && !"".equals(option[i])) {
						QuestionnaireOption questionnaireOption = new QuestionnaireOption();
						questionnaireOption.setBody(commonUtil.trimInnerSpaceStr(option[i]));
						questionnaireOption.setType(questionnaire.getType());
						questionnaireOption.setUid(i);
						questionnaireOption.setQuestionnaire(questionnaire);
						questionnaireOptions.add(questionnaireOption);
					}
				}
		     questionnaire.setQuestionnaireOptions(questionnaireOptions);	
			}
		 questionnaire.setAddDate(new Date());
		 if (questionnaireDao.save(questionnaire)) {
			return 1;
		}else {
			return 0;
		}
		}else {
			return 0;
		}
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	    }
	}

	@Override
	public int addQuestionnaire(String[] info) throws Exception {
		
		try {
		int projectId = Integer.parseInt(info[0]);
		QuestionnaireProject q = questionnaireProjectDao.get(projectId);
		if (q!=null) {
			Questionnaire questionnaire =new Questionnaire();
			questionnaire.setQuestionnaireProject(q);
			if(info[1]!=null &&!"".equals(info[1]))
				{
				int uid= Integer.parseInt(info[1]);
				questionnaire.setUid(uid);
				}
			else {
				return 0;
			}
			if(info[2]!=null &&!"".equals(info[2]))
			{
			String title = commonUtil.trimInnerSpaceStr(info[2]);
			questionnaire.setTitle(title);
			}
		    else {
			return 0;
		    }
			if(info[3]!=null &&!"".equals(info[3]))
			{
			int type= Integer.parseInt(info[3]);
			commonUtil.p("添加题目类型"+type);
			questionnaire.setType(type);
			}
		else {
			return 0;
		}
			if(info[4]!=null &&!"".equals(info[4]))
			{
			int model= Integer.parseInt(info[4]);
			questionnaire.setModel(model);
			}
		else {
			return 0;
		}
		 questionnaire.setAddDate(new Date());
		 if (questionnaireDao.save(questionnaire)) {
			return 1;
		}else {
			return 0;
		}
		}else {
			return 0;
		}
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	    }
	}

	@Override
	public Questionnaire getQuestionnaire(long questionnaireId) throws Exception {
		return questionnaireDao.get(questionnaireId);
	}

	@Override
	public boolean setMoveUid(long questionnaireID1, long questionnaireID2)
			throws Exception {
		try {
			Questionnaire q1=questionnaireDao.get(questionnaireID1);
			Questionnaire q2=questionnaireDao.get(questionnaireID2);
			if(q1 !=null && q2 !=null){
				int uid = q1.getUid();
				q1.setUid(q2.getUid());
				q2.setUid(uid);
				return  questionnaireDao.update(q1) &&questionnaireDao.update(q2);
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(long id) throws Exception {
		Questionnaire entity = questionnaireDao.get(id);
		if(entity !=null)
		return questionnaireDao.delete(entity);
		else {
			return false;
		}
	}

	@Override
	public int updateQuestionnaire(String[] info, String[] option)
			throws Exception {
		try {
			long id = Long.parseLong(info[0]);
			Questionnaire questionnaire =questionnaireDao.get(id);
			if (questionnaire !=null) {
				if(info[1]!=null &&!"".equals(info[1]))
					{
					int uid= Integer.parseInt(info[1]);
					questionnaire.setUid(uid);
					}
				else {
					return 0;
				}
				if(info[2]!=null &&!"".equals(info[2]))
				{
				String title = commonUtil.trimInnerSpaceStr(info[2]);
				questionnaire.setTitle(title);
				}
			    else {
				return 0;
			    }
				if(info[3]!=null &&!"".equals(info[3]))
				{
				int type= Integer.parseInt(info[3]);
				questionnaire.setType(type);
				}
			else {
				return 0;
			}
				if(info[4]!=null &&!"".equals(info[4]))
				{
				int model= Integer.parseInt(info[4]);
				questionnaire.setModel(model);
				}
			else {
				return 0;
			}
				List<QuestionnaireOption>questionnaireOptions = new ArrayList<QuestionnaireOption>();
				if(option !=null && option.length>0)
				{
			     	for (int i = 0; i < option.length; i++) {
						if (option[i]!=null && !"".equals(option[i])) {
							QuestionnaireOption questionnaireOption = new QuestionnaireOption();
							questionnaireOption.setBody(commonUtil.trimInnerSpaceStr(option[i]));
							questionnaireOption.setType(questionnaire.getType());
							questionnaireOption.setUid(i);
							questionnaireOption.setQuestionnaire(questionnaire);
							questionnaireOptions.add(questionnaireOption);
						}
					}
			     questionnaire.setQuestionnaireOptions(questionnaireOptions);	
				}
			 questionnaire.setAddDate(new Date());
			 if (questionnaireDao.update(questionnaire)) {
				return 1;
			}else {
				return 0;
			}
			}else {
				return 0;
			}
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		    }
	}

	@Override
	public int updateQuestionnaire(String[] info) throws Exception {
		try {
			long id = Long.parseLong(info[0]);
			Questionnaire questionnaire =questionnaireDao.get(id);
			if (questionnaire !=null) {
				if(info[1]!=null &&!"".equals(info[1]))
					{
					int uid= Integer.parseInt(info[1]);
					questionnaire.setUid(uid);
					}
				else {
					return 0;
				}
				if(info[2]!=null &&!"".equals(info[2]))
				{
				String title = commonUtil.trimInnerSpaceStr(info[2]);
				questionnaire.setTitle(title);
				}
			    else {
				return 0;
			    }
				if(info[3]!=null &&!"".equals(info[3]))
				{
				int type= Integer.parseInt(info[3]);
				commonUtil.p("添加题目类型"+type);
				questionnaire.setType(type);
				}
			else {
				return 0;
			}
				if(info[4]!=null &&!"".equals(info[4]))
				{
				int model= Integer.parseInt(info[4]);
				questionnaire.setModel(model);
				}
			else {
				return 0;
			}
			 questionnaire.setAddDate(new Date());
			 if (questionnaireDao.update(questionnaire)) {
				return 1;
			}else {
				return 0;
			}
			}else {
				return 0;
			}
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		    }
	}

	@Override
	public List<Questionnaire> getQuestionsByProject(int id) throws Exception {
		
		return questionnaireDao.getQuestionsByProject(id);
	}

}
