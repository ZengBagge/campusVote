package com.vote.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.common.commonUtil;
import com.vote.dao.PersonVoteDao;
import com.vote.dao.UserDao;
import com.vote.dao.VoteProjectDao;
import com.vote.po.ImageUrl;
import com.vote.po.PersonVote;
import com.vote.po.UserCommon;
import com.vote.po.Vote;
import com.vote.po.VoteProject;
import com.vote.service.PersonVoteService;

@Service("personVoteService")
public class PersonVoteServiceImpl implements PersonVoteService {

	@Resource
	private PersonVoteDao personVoteDao;
	@Resource
	private VoteProjectDao voteProjectDao;
	@Resource
	private UserDao userDao;
	@Override
	public int addPersonVote(String[] info, String[] pic, UserCommon user)
			throws Exception {
       UserCommon userCommon = userDao.get(user.getId());
		PersonVote personVote = new PersonVote();
		commonUtil.p("照片个数"+pic.length);
		if(info[0]!= null && !"".equals(info[0]))
		  {
			int projectId = Integer.parseInt(info[0]);
		    VoteProject voteProject=	voteProjectDao.get(projectId);
		   if (voteProject!=null) {
				if(personVoteDao.isApplyed(userCommon.getId(),projectId))//是否已报名
				{
				commonUtil.p("已经报名乐");	
			     return 0;
				}	   
			personVote.setVoteProject(voteProject);
		   }
		   else {
			return 0;
		   }
		  }else {
			return 0;
		 }
	   if (info[1]!= null && !"".equals(info[1]))
		   {
			   if (userCommon.getName()==null ||"".equals(userCommon.getName())) {
				userCommon.setName(info[1]);
			  }
			   personVote.setName(info[1]);
		   }
		   else {
			return 0;
		}
	   if (info[2]!= null && !"".equals(info[2]))
	   {
		   if (userCommon.getCollege()==null ||"".equals(userCommon.getCollege())) {
			userCommon.setCollege(info[2]);
		  }
		   personVote.setCollege(info[2]);
	   }
	   else {
		return 0;
    	} 
	   if (info[3]!= null && !"".equals(info[3]))
	   {
		   if (userCommon.getMail()==null ||"".equals(userCommon.getMail())) {
			userCommon.setMail(info[3]);
		  }
	   }
	   else {
		return 0;
	 }   
	 String explain= commonUtil.trimInnerSpaceStr(info[4]);
	 personVote.setMotto(explain);
	 List<ImageUrl>imageUrls = new ArrayList<ImageUrl>();
	 for (int i = 0; i < pic.length; i++) {
		if(pic[i]!=null && !"".equals(pic[i]))
		{
		   ImageUrl imageUrl = new ImageUrl();
		   imageUrl.setUrl(pic[i]);
		   imageUrl.setName(userCommon.getName());
		   imageUrls.add(imageUrl);
		}
	}
	 personVote.setPicList(imageUrls);
	 Vote vote = new Vote();
	 vote.setVoteProject(personVote.getVoteProject());
	 vote.setUid(personVote.getVoteProject().getBeginDate().getTime()-new Date().getTime());
	 personVote.setVote(vote);
	 personVote.setAddDate(new Date());
	 userDao.update(userCommon);
	 personVote.setLinkUser(userCommon);
	if (personVoteDao.save(personVote))
		return 1;
	else {
		return 0;
	 }
	}

	@Override
	public boolean delPersonVote(long pid) throws Exception {
	
		PersonVote personVote = personVoteDao.get(pid);
		if (personVote != null) {
		return	personVoteDao.delete(personVote);
		}
		else
		return false;
	}

	@Override
	public int updatePersonVote(String[] info, String pic[], UserCommon user,long id)
			throws Exception {
		
		    PersonVote personVote = personVoteDao.get(id);
		    UserCommon userCommon = userDao.get(user.getId());
			commonUtil.p("照片个数"+pic.length);
			if(info[0]!= null && !"".equals(info[0]))
			  {
				int projectId = Integer.parseInt(info[0]);
			    VoteProject voteProject=	voteProjectDao.get(projectId);
			   if (voteProject!=null) {
					if(personVoteDao.isApplyed(userCommon.getId(),projectId))//是否已报名
					{
					commonUtil.p("已经报名乐");	
				     return 0;
					}	   
				personVote.setVoteProject(voteProject);
			   }
			   else {
				return 0;
			   }
			  }else {
				return 0;
			 }
		   if (info[1]!= null && !"".equals(info[1]))
			   {
				   if (userCommon.getName()==null ||"".equals(userCommon.getName())) {
					userCommon.setName(info[1]);
				  }
				   personVote.setName(info[1]);
			   }
			   else {
				return 0;
			}
		   if (info[2]!= null && !"".equals(info[2]))
		   {
			   if (userCommon.getCollege()==null ||"".equals(userCommon.getCollege())) {
				userCommon.setCollege(info[2]);
			  }
			   personVote.setCollege(info[2]);
		   }
		   else {
			return 0;
	    	} 
		   if (info[3]!= null && !"".equals(info[3]))
		   {
			   if (userCommon.getMail()==null ||"".equals(userCommon.getMail())) {
				userCommon.setMail(info[3]);
			  }
		   }
		   else {
			return 0;
		 }   
		 String explain= commonUtil.trimInnerSpaceStr(info[4]);
		 personVote.setMotto(explain);
		 List<ImageUrl>imageUrls = new ArrayList<ImageUrl>();
		 for (int i = 0; i < pic.length; i++) {
			if(pic[i]!=null && !"".equals(pic[i]))
			{
			   ImageUrl imageUrl = new ImageUrl();
			   imageUrl.setUrl(pic[i]);
			   imageUrl.setName(userCommon.getName());
			   imageUrls.add(imageUrl);
			}
		}
		 personVote.setPicList(imageUrls);
		 Vote vote = new Vote();
		 vote.setVoteProject(personVote.getVoteProject());
		 personVote.setVote(vote);
		 personVote.setAddDate(new Date());
		 userDao.update(userCommon);
		 personVote.setLinkUser(userCommon);
		if (personVoteDao.update(personVote))
			return 1;
		else {
			return 0;
		 }
	}

	@Override
	public List<PersonVote> getPersonVoteByProject(int projectId)
			throws Exception {
		List<PersonVote> list= personVoteDao.getPersonVoteListByProject(projectId);
		commonUtil.p("人物投票项目投票体个数"+list.size());
		for (PersonVote p:list) {
			List<ImageUrl>imageUrls =new ArrayList<ImageUrl>();
			if(p.getPicList().size()>0){
			imageUrls.add(p.getPicList().get(0));
			p.setPicList(imageUrls);
			}
		}
		commonUtil.removeDuplicate(list);
		return list;
	}

	public PersonVoteDao getPersonVoteDao() {
		return personVoteDao;
	}

	public void setPersonVoteDao(PersonVoteDao personVoteDao) {
		this.personVoteDao = personVoteDao;
	}

	
}
