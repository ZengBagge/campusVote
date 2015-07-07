package com.vote.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.common.ComparatorVote;
import com.vote.dao.PersonVoteDao;
import com.vote.dao.TeamVoteDao;
import com.vote.dao.VoteDao;
import com.vote.dao.VoteProjectDao;
import com.vote.dao.WordVoteDao;
import com.vote.po.PersonVote;
import com.vote.po.TeamVote;
import com.vote.po.Vote;
import com.vote.po.VoteProject;
import com.vote.service.VoteService;

@Service("voteService")
public class VoteServiceImpl implements VoteService{

	@Resource
	private VoteProjectDao voteProjectDao;
	@Resource
	private VoteDao voteDao;
	@Resource
	private PersonVoteDao personVoteDao;
	@Resource
	private TeamVoteDao teamVoteDao;
	@Resource
	private WordVoteDao wordVoteDao;
	
	@Override
	public List<Serializable> getClassifyVotesByProject(int projectId)
			throws Exception {
		
		List<Serializable>allVote= new ArrayList<Serializable>();
      	VoteProject voteProject =	voteProjectDao.get(projectId);
      	if (voteProject != null) {
      		String typeDao=	voteProject.getVoteType().getTypeDao();
            if("PersonDao".equals(typeDao))
            {
          		List<PersonVote> voteProjectList = personVoteDao.getPersonVoteListByProject(voteProject.getId());
          		allVote.addAll(voteProjectList);
              	ComparatorVote<Serializable> comparatorVote = new  ComparatorVote<Serializable>();
              	Collections.sort(allVote, comparatorVote);
            }else if ("TeamVoteDao".equals(typeDao)) {
            	List<TeamVote> voteProjectList = teamVoteDao.getTeamVoteListByProject(voteProject.getId());
            	allVote.addAll(voteProjectList);
			}
		}
		return allVote;
	}

	/**
	 * 人物投票和团队投票
	 */
	@Override
	public List<Serializable> getClassifyVotesUserByProject(int projectId)
			throws Exception {
		List<Serializable>allUser= new ArrayList<Serializable>();
      	VoteProject voteProject =	voteProjectDao.get(projectId);
      	if (voteProject != null) {
      		String typeDao=voteProject.getVoteType().getTypeDao();
            if("PersonVoteDao".equals(typeDao))
            {
          		List<PersonVote> voteProjectList = personVoteDao.getPersonVoteListByProject(voteProject.getId());	
          		for (PersonVote p:voteProjectList) {
					allUser.add(p.getLinkUser());
				}
            }else if ("TeamVoteDao".equals(typeDao)) {
            	List<TeamVote> voteProjectList = teamVoteDao.getTeamVoteListByProject(voteProject.getId());
            	for (TeamVote t:voteProjectList) {
                  allUser.addAll(t.getLinkUser());
				}
			}else if("WordVoteDao".equals(typeDao)){
				allUser = wordVoteDao.getWordVoteByProject(projectId);
			}
    		return allUser;
		}else {
			return null;
		}
	}

	@Override
	public Vote getVote(Long voteId) throws Exception {
		return voteDao.get(voteId);
	}

}
