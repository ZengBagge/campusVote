package com.vote.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.vote.beans.QuestionnaireProjectResult;
import com.vote.common.PageBean;
import com.vote.common.commonUtil;
import com.vote.dao.QuestionnaireAnswerDao;
import com.vote.dao.QuestionnaireConDao;
import com.vote.dao.QuestionnaireDao;
import com.vote.dao.QuestionnaireProjectDao;
import com.vote.po.Questionnaire;
import com.vote.po.QuestionnaireOption;
import com.vote.po.QuestionnaireProject;
import com.vote.po.UserCommon;
import com.vote.po.VoteProject;
import com.vote.service.QuestionnaireProjectService;
@Service("questionnaireProjectService")
public class QuestionnaireProjectServiceImpl implements
		QuestionnaireProjectService {

	@Resource
	private QuestionnaireProjectDao questionnaireProjectDao;
	@Resource
	private QuestionnaireDao questionnaireDao;
	@Resource
	private QuestionnaireConDao questionnaireConDao;
	@Resource
	private QuestionnaireAnswerDao questionnaireAnswerDao;
	@Override
	public String[] addProject(String[] infos, UserCommon u) throws Exception {
		String[] result = new String[10];
		result[9]="0";
		QuestionnaireProject questionnaireProject = new QuestionnaireProject();
		try {
			//验证数据
			if(infos[0]!=null && !"".equals(infos[0]))
			{
				String title=commonUtil.trimInnerSpaceStr(infos[0]);
				questionnaireProject.setTitle(title);
			    result[0] = null;
			}else {
				result[0]="标题不能为空";
				return result;
			}
			if(infos[2]!=null && !"".equals(infos[2]))
			{
				String organization=commonUtil.trimInnerSpaceStr(infos[2]);
				questionnaireProject.setOrganization(organization);
			    result[2] = null;
			}else {
				result[2]="组织方不能为空";
				return result;
			}
			if(infos[3]!=null && !"".equals(infos[3]))
			{
				String contact=commonUtil.trimInnerSpaceStr(infos[3]);
				questionnaireProject.setContact(contact);
			    result[3] = null;
			}else {
				result[3]="组织方不能为空";
				return result;
			}
			if(infos[4]!=null && !"".equals(infos[4]))
			{
				String startDate=commonUtil.trimInnerSpaceStr(infos[4]);
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );  //设置时间转化格式
				Date beginDate = sdf.parse(startDate);
				if (beginDate !=null) {
					questionnaireProject.setBeginDate(beginDate);
				    result[4] = null;
				}else {
					result[4]= "时间格式不正确";
					return result;
				}
			}else {
				result[4]="开始时间不能为空";
				return result;
			}
			if(infos[5]!=null && !"".equals(infos[5]))
			{
				String end=commonUtil.trimInnerSpaceStr(infos[5]);
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );  //设置时间转化格式
				Date endDate = sdf.parse(end);
				if (endDate !=null) {
					questionnaireProject.setEndDate(endDate);
				    result[5] = null;
				}else {
					result[5]= "时间格式不正确";
					return result;
				}
			}else {
				result[5]="结束时间不能为空";
				return result;
			}
			questionnaireProject.setExplains(commonUtil.trimInnerSpaceStr(infos[6]));
			questionnaireProject.setAttention(commonUtil.trimInnerSpaceStr(infos[7]));
			questionnaireProject.setWriter(u);
			questionnaireProject.setAddDate(new Date());
			if(questionnaireProjectDao.save(questionnaireProject))
			{
				result[9]="1";
			   return	result;
			}else {
				result[8]="系统错误";
				return result;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result[8]="系统错误";
			return result;
		}
	}

	@Override
	public QuestionnaireProject getProject(int projectId) throws Exception {
		
		return questionnaireProjectDao.get(projectId);
	}

	@Override
	public String[] updateProject(String[] infos, UserCommon u, int id)
			throws Exception {
		String[] result = new String[10];
		result[9]="0";
		QuestionnaireProject questionnaireProject = questionnaireProjectDao.get(id);
		try {
			//验证数据
			if(infos[0]!=null && !"".equals(infos[0]))
			{
				String title=commonUtil.trimInnerSpaceStr(infos[0]);
				questionnaireProject.setTitle(title);
			    result[0] = null;
			}else {
				result[0]="标题不能为空";
				return result;
			}
			if(infos[2]!=null && !"".equals(infos[2]))
			{
				String organization=commonUtil.trimInnerSpaceStr(infos[2]);
				questionnaireProject.setOrganization(organization);
			    result[2] = null;
			}else {
				result[2]="组织方不能为空";
				return result;
			}
			if(infos[3]!=null && !"".equals(infos[3]))
			{
				String contact=commonUtil.trimInnerSpaceStr(infos[3]);
				questionnaireProject.setContact(contact);
			    result[3] = null;
			}else {
				result[3]="组织方不能为空";
				return result;
			}
			if(infos[4]!=null && !"".equals(infos[4]))
			{
				String startDate=commonUtil.trimInnerSpaceStr(infos[4]);
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );  //设置时间转化格式
				Date beginDate = sdf.parse(startDate);
				if (beginDate !=null) {
					questionnaireProject.setBeginDate(beginDate);
				    result[4] = null;
				}else {
					result[4]= "时间格式不正确";
					return result;
				}
			}else {
				result[4]="开始时间不能为空";
				return result;
			}
			if(infos[5]!=null && !"".equals(infos[5]))
			{
				String end=commonUtil.trimInnerSpaceStr(infos[5]);
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm" );  //设置时间转化格式
				Date endDate = sdf.parse(end);
				if (endDate !=null) {
					questionnaireProject.setEndDate(endDate);
				    result[5] = null;
				}else {
					result[5]= "时间格式不正确";
					return result;
				}
			}else {
				result[5]="结束时间不能为空";
				return result;
			}
			questionnaireProject.setExplains(commonUtil.trimInnerSpaceStr(infos[6]));
			questionnaireProject.setAttention(commonUtil.trimInnerSpaceStr(infos[7]));
			questionnaireProject.setWriter(u);
			questionnaireProject.setAddDate(new Date());
			if(questionnaireProjectDao.update(questionnaireProject))
			{
				result[9]="1";
			   return	result;
			}else {
				result[8]="系统错误";
				return result;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result[8]="系统错误";
			return result;
		}
	}

	@Override
	public boolean delete(int id) throws Exception {
		QuestionnaireProject questionnaireProject =questionnaireProjectDao.get(id);
		if (questionnaireProject != null) {
			return questionnaireProjectDao.delete(questionnaireProject);
		}else {
			return false;
		}
	}

	@Override
	public PageBean getMyProjectList(int page, int pageSize, long id) throws Exception {
		try {
			int allRow=questionnaireProjectDao.getMyProjectNumber(id);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<VoteProject> projects =questionnaireProjectDao.getMyProjectList(offset, length,id);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(projects);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getMyProjectNum(long id) throws Exception {
		
		return questionnaireProjectDao.getMyProjectNumber(id); 
	}

	public QuestionnaireProjectDao getQuestionnaireProjectDao() {
		return questionnaireProjectDao;
	}

	public void setQuestionnaireProjectDao(
			QuestionnaireProjectDao questionnaireProjectDao) {
		this.questionnaireProjectDao = questionnaireProjectDao;
	}

	public QuestionnaireDao getQuestionnaireDao() {
		return questionnaireDao;
	}

	public void setQuestionnaireDao(QuestionnaireDao questionnaireDao) {
		this.questionnaireDao = questionnaireDao;
	}

	@Override
	public boolean setBeginQuestionnaire(int id) throws Exception {
	
		QuestionnaireProject questionnaireProject =questionnaireProjectDao.get(id);
		if (questionnaireProject !=null) {
			questionnaireProject.setIsOpen(2);
			return questionnaireProjectDao.update(questionnaireProject);
		}
		else
		return false;
	}

	@Override
	public List<Questionnaire> getAllQuestionnairesByProject(int projectId) throws Exception {
		QuestionnaireProject questionnaireProject =questionnaireProjectDao.get(projectId);
		if (questionnaireProject !=null) {
			return questionnaireDao.getQuestionsByProject(projectId);
		}else {
			return null;
		}

	}

	@Override
	public boolean setStopQuestionnaire(int id) throws Exception {
		QuestionnaireProject questionnaireProject =questionnaireProjectDao.get(id);
		if (questionnaireProject !=null) {
			questionnaireProject.setIsOpen(1);
			return questionnaireProjectDao.update(questionnaireProject);
		}
		else
		return false;
	}

	@Override
	public PageBean getIndexProjects(int pageSize, int page) throws Exception {
		try {
			int allRow=questionnaireProjectDao.getAllNumber();  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<VoteProject> projects =questionnaireProjectDao.getAllList(offset, length);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(projects);
			pageBean.init();
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public QuestionnaireProjectResult getQuestionnaireProjectResult(int projectId)
			throws Exception {
	    
		try {
			QuestionnaireProjectResult questionnaireProjectResult = new  QuestionnaireProjectResult();
			  QuestionnaireProject questionnaireProject =questionnaireProjectDao.get(projectId);
			  if(questionnaireProject==null)
				  return null;
			  questionnaireProjectResult.setQuestionnaireProject(questionnaireProject);
			  List<Questionnaire>list = questionnaireDao.getQuestionsByProject(projectId);
			  for (Questionnaire questionnaire:list) {
				List<QuestionnaireOption>list2=questionnaire.getQuestionnaireOptions();
				long number =0;
				for (QuestionnaireOption questionnaireOption :list2) {
			           number+=questionnaireOption.getNumber();
				}
				questionnaire.setNumber(number);
				questionnaireDao.update(questionnaire);
			}
			  questionnaireProjectResult.setQuestionnaires(list);
			  int personNumber = questionnaireConDao.getpersonNumber(projectId).intValue(); 
			  questionnaireProjectResult.setPersonNumber(personNumber);
			  int anonymityPersonNumber = questionnaireConDao.getAnonymityPersonNumber(projectId).intValue();
			  questionnaireProjectResult.setAnnoymityPersonNumber(anonymityPersonNumber);
			  questionnaireProjectResult.setQuestionnaireNumber(list.size());
			  questionnaireProjectResult.setBeforeAnswerNumber((personNumber+anonymityPersonNumber)*questionnaireProjectResult.getQuestionnaireNumber());
			  questionnaireProjectResult.setAfterAnswerNumber(questionnaireAnswerDao.getAnswerBumber(projectId).intValue());
			  commonUtil.p("问卷统计结果完成");
			  return questionnaireProjectResult;
		} catch (Exception e) {
			commonUtil.p("问卷统计结果出错");
			e.printStackTrace();
			return null;
		}
	}

}
