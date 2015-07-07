package com.vote.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.beans.VoteResult;
import com.vote.common.DoubleMath;
import com.vote.common.PageBean;
import com.vote.common.commonUtil;
import com.vote.dao.BrowsingHistoryDao;
import com.vote.dao.PersonVoteDao;
import com.vote.dao.VoteConDao;
import com.vote.dao.VoteProjectDao;
import com.vote.dao.VoteTypeDao;
import com.vote.dao.WordVoteDao;
import com.vote.po.BrowsingHistory;
import com.vote.po.PersonVote;
import com.vote.po.UserCommon;
import com.vote.po.VoteProject;
import com.vote.po.VoteType;
import com.vote.po.WordVote;
import com.vote.service.VoteProjectService;

@Service("voteProjectService")
public class VoteProjectServiceImpl implements VoteProjectService {
	
	
	@Resource
	private VoteProjectDao voteProjectDao;
    @Resource
    private VoteTypeDao voteTypeDao;
    @Resource
    private BrowsingHistoryDao browsingHistoryDao;
    @Resource
    private PersonVoteDao personVoteDao;
    @Resource
    private WordVoteDao wordVoteDao;
    @Resource
    private VoteConDao voteConDao;
	@Override
	public String[] addProject(String[] infos, int model,int maxNumber, UserCommon u) throws Exception {
		
		String[] result = new String[10];
		result[9]="0";
		VoteProject voteProject = new VoteProject();
		try {
			//验证数据
			if(infos[0]!=null && !"".equals(infos[0]))
			{
				String title=commonUtil.trimInnerSpaceStr(infos[0]);
				voteProject.setTitle(title);
			    result[0] = null;
			}else {
				result[0]="标题不能为空";
				return result;
			}
			if(infos[1]!=null && !"".equals(infos[1]))
			{
				int typeId =Integer.parseInt(infos[1]); 
				VoteType vt= voteTypeDao.get(typeId);
				if(vt !=null)
			      {
					result[1] = null;
			       voteProject.setVoteType(vt);
			      }else {
			    	  result[1]="投票类别不合法";
					  return result;
				}
			}else {
				result[1]="投票类别不合法";
				return result;
			}
			if(infos[2]!=null && !"".equals(infos[2]))
			{
				String organization=commonUtil.trimInnerSpaceStr(infos[2]);
				voteProject.setOrganization(organization);
			    result[2] = null;
			}else {
				result[2]="组织方不能为空";
				return result;
			}
			if(infos[3]!=null && !"".equals(infos[3]))
			{
				String contact=commonUtil.trimInnerSpaceStr(infos[3]);
				voteProject.setContact(contact);
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
					voteProject.setBeginDate(beginDate);
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
					voteProject.setEndDate(endDate);
				    result[5] = null;
				}else {
					result[5]= "时间格式不正确";
					return result;
				}
			}else {
				result[5]="结束时间不能为空";
				return result;
			}
			voteProject.setExplains(commonUtil.trimInnerSpaceStr(infos[6]));
			voteProject.setAttention(commonUtil.trimInnerSpaceStr(infos[7]));
			voteProject.setWriter(u);
			voteProject.setAddDate(new Date());
			voteProject.setModel(model);
			voteProject.setMaxNumber(maxNumber);
			if(voteProjectDao.save(voteProject))
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
	public String[] updateProject(String[] infos,int model,int maxNumber, UserCommon u,int id)
			throws Exception {
		String[] result = new String[10];
		result[9]="0";
		try {
			VoteProject voteProject = voteProjectDao.get(id);
			if(voteProject == null){
				result[8] ="修改项目找不到";
				return result;
			}
			//验证数据
			if(infos[0]!=null && !"".equals(infos[0]))
			{
				String title=commonUtil.trimInnerSpaceStr(infos[0]);
				voteProject.setTitle(title);
			    result[0] = null;
			}else {
				result[0]="标题不能为空";
				return result;
			}
			if(infos[1]!=null && !"".equals(infos[1]))
			{
				int typeId =Integer.parseInt(infos[1]); 
				VoteType vt= voteTypeDao.get(typeId);
				if(vt !=null)
			      {
					result[1] = null;
			       voteProject.setVoteType(vt);
			      }else {
			    	  result[1]="投票类别不合法";
					  return result;
				}
			}else {
				result[1]="投票类别不合法";
				return result;
			}
			if(infos[2]!=null && !"".equals(infos[2]))
			{
				String organization=commonUtil.trimInnerSpaceStr(infos[2]);
				voteProject.setOrganization(organization);
			    result[2] = null;
			}else {
				result[2]="组织方不能为空";
				return result;
			}
			if(infos[3]!=null && !"".equals(infos[3]))
			{
				String contact=commonUtil.trimInnerSpaceStr(infos[3]);
				voteProject.setContact(contact);
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
					voteProject.setBeginDate(beginDate);
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
					voteProject.setEndDate(endDate);
				    result[5] = null;
				}else {
					result[5]= "时间格式不正确";
					return result;
				}
			}else {
				result[5]="结束时间不能为空";
				return result;
			}
			voteProject.setExplains(commonUtil.trimInnerSpaceStr(infos[6]));
			voteProject.setAttention(commonUtil.trimInnerSpaceStr(infos[7]));
			voteProject.setWriter(u);
			voteProject.setAddDate(new Date());
			voteProject.setModel(model);
			voteProject.setMaxNumber(maxNumber);
			if(voteProjectDao.update(voteProject))
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
		VoteProject entity = voteProjectDao.get(id);
		if(entity !=null)
		{
		if (entity.getIsOpen()>0) {
			return false;
		}
		if(entity.getVoteType().getTypeEntity().equals("PersonVote")){
		List<PersonVote>personVotes=	personVoteDao.getPersonVoteListByProject(entity.getId());
		for (PersonVote personVote:personVotes) {
			personVoteDao.delete(personVote);		
		}
		}
		else if (entity.getVoteType().getTypeEntity().equals("WordVote")) {
			
		}else if (entity.getVoteType().getTypeEntity().equals("TeamVote")) {
			
		}else if (entity.getVoteType().getTypeEntity().equals("PictureVote")) {
			
		}
		   return voteProjectDao.delete(entity);
		}
		else {
			return false;
		}
	}

	@Override
	public PageBean getMyProjectList(int page, int pageSize, long id) throws Exception {
		
		try {
			int allRow=voteProjectDao.getMyProjectNumber(id);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<VoteProject> projects =voteProjectDao.getMyProjectList(offset, length,id);
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

	
	
	public VoteProjectDao getVoteProjectDao() {
		return voteProjectDao;
	}

	public void setVoteProjectDao(VoteProjectDao voteProjectDao) {
		this.voteProjectDao = voteProjectDao;
	}

	public VoteTypeDao getVoteTypeDao() {
		return voteTypeDao;
	}

	public void setVoteTypeDao(VoteTypeDao voteTypeDao) {
		this.voteTypeDao = voteTypeDao;
	}

	@Override
	public int getMyProjectNum(long id) throws Exception {
		
		return voteProjectDao.getMyProjectNumber(id);
	}

	@Override
	public VoteProject getProject(int projectId) throws Exception {
		
		return voteProjectDao.get(projectId);
	}

	@Override
	public boolean publish(int id) throws Exception {
	    VoteProject voteProject = voteProjectDao.get(id);
	    if (voteProject != null) {
		 return 	voteProjectDao.publish(voteProject.getId());
		}
	    else
	    	return false;
	}
     
	@Override
	public boolean setbeginVote(int id) throws Exception {
	    VoteProject voteProject = voteProjectDao.get(id);
	    if (voteProject != null) {
			voteProject.setIsOpen(2);
			if(voteProjectDao.update(voteProject))
				return true;
			else {
				return false;
			}
		}
	    else
	    	return false;
	}

	@Override
	public PageBean getApplyProjectList(int pageSize, int page) throws Exception {
		try {
			int allRow=voteProjectDao.getApplyProjectNumber();  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<VoteProject> projects =voteProjectDao.getApplyProjectList(offset, length);
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
	public PageBean getUnderwayProjectList(int pageSize, int page) throws Exception {
		try {
			int allRow=voteProjectDao.getUnderwayProjectNumber();  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<VoteProject> projects =voteProjectDao.getUnderwayProjectList(offset, length);
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
	
	/**
	 * 主页推荐
	 * 默认推荐
	 */
	@Override
	public List<VoteProject> getIndexProjectList(String ip) throws Exception {
		
		List<VoteProject>list = new ArrayList<VoteProject>();
		List<BrowsingHistory>list2=browsingHistoryDao.getListByIp(ip);
		if(list2 !=null){
			for(BrowsingHistory b: list2){
				commonUtil.p("推荐活动Id"+b.getProjectId());
				int id=b.getProjectId();
                VoteProject voteProject = voteProjectDao.get(id);
                list.add(voteProject);
			}		
		}
		int m = 5-list.size();
		List<VoteProject>list3=voteProjectDao.getNewIndexList(m);
		list.addAll(list3);
		commonUtil.removeDuplicate(list);
		return list;
	}

	@Override
	public List<VoteResult> getResultByProject(int projectId) throws Exception {
		
		List<VoteResult>voteResults = new ArrayList<VoteResult>();
		VoteProject voteProject = voteProjectDao.get(projectId);
		if(voteProject !=null){
			String type = voteProject.getVoteType().getTypeDao();
			if("PersonVoteDao".equals(type)){
				List<PersonVote>list = personVoteDao.getPersonVoteListByProject(projectId);
				double zong=0.0;
				for(PersonVote p:list){
					zong = zong+p.getVote().getPoll();
				}
				for (Iterator<PersonVote> iterator = list.iterator(); iterator.hasNext();) {
					PersonVote personVote = (PersonVote) iterator.next();
					VoteResult voteResult =new VoteResult();
					voteResult.setName(personVote.getName());
					voteResult.setNumber(personVote.getVote().getPoll());
					if(zong!=0 && personVote.getVote().getPoll()!=0)
					{
					Double r=DoubleMath.div( (double)personVote.getVote().getPoll(),zong, 3);	
					voteResult.setProporion(r*100);
					}
					else {
						voteResult.setProporion(0);
					}
					voteResult.setVoteId(personVote.getVote().getId());
					voteResults.add(voteResult);
				}
			}
			else if("WordVoteDao".equals(type)){
				List<Serializable>list = wordVoteDao.getWordVoteByProject(projectId);
				double zong=0.0;
				for(Serializable p:list){
					WordVote w = (WordVote) p;
					zong = zong+w.getVote().getPoll();
				}
				for (Iterator<Serializable> iterator = list.iterator(); iterator.hasNext();) {
					WordVote wordVote = (WordVote) iterator.next();
					VoteResult voteResult =new VoteResult();
					voteResult.setName(wordVote.getContent());
					voteResult.setNumber(wordVote.getVote().getPoll());
					if(zong!=0 && wordVote.getVote().getPoll()!=0)
					{
					Double r=DoubleMath.div( (double)wordVote.getVote().getPoll(),zong, 3);	
					voteResult.setProporion(r*100);
					}
					else 
					voteResult.setProporion(0);		
					voteResult.setVoteId(wordVote.getVote().getId());
					voteResults.add(voteResult);
				}
				
			}
          else if("PictureVoteDao".equals(type)){
				

			}
          else if("TeamVoteDao".equals(type)){
	
	
           }
			return voteResults;
		}else {
			return null;
		}
	}

	public BrowsingHistoryDao getBrowsingHistoryDao() {
		return browsingHistoryDao;
	}

	public void setBrowsingHistoryDao(BrowsingHistoryDao browsingHistoryDao) {
		this.browsingHistoryDao = browsingHistoryDao;
	}

	public PersonVoteDao getPersonVoteDao() {
		return personVoteDao;
	}

	public void setPersonVoteDao(PersonVoteDao personVoteDao) {
		this.personVoteDao = personVoteDao;
	}

	public WordVoteDao getWordVoteDao() {
		return wordVoteDao;
	}

	public void setWordVoteDao(WordVoteDao wordVoteDao) {
		this.wordVoteDao = wordVoteDao;
	}

}
