package com.vote.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.common.ComparatorWordVote;
import com.vote.common.commonUtil;
import com.vote.dao.VoteProjectDao;
import com.vote.dao.WordVoteDao;
import com.vote.po.Vote;
import com.vote.po.VoteProject;
import com.vote.po.WordVote;
import com.vote.service.WordVoteService;

@Service("wordVoteService")
public class WordVoteServiceImpl implements WordVoteService {

	@Resource
	private WordVoteDao wordVoteDao;
	@Resource
	private VoteProjectDao voteProjectDao;
	
	@Override
	public int addWordVote(String[] info) throws Exception {
		
		WordVote wordVote = new WordVote();
		if(info[0]!= null && !"".equals(info[0]))
		  {
			int projectId = Integer.parseInt(info[0]);
		    VoteProject voteProject=	voteProjectDao.get(projectId);
		   if (voteProject!=null) {
			wordVote.setVoteProject(voteProject);
		   }
		   else {
			return 0;
		   }
		  }else {
			return 0;
		 }
		   Long sortNumber=null;
			try {
				 sortNumber = Long.parseLong(info[1]);
			} catch (Exception e) {
				sortNumber = (long) 100;
			}
			wordVote.setSortNumber(sortNumber);
		 if (info[2]!=null && !"".equals(info[2])) {
				String content=commonUtil.trimInnerSpaceStr(info[2]);
				wordVote.setContent(content);
		 }else {
			return 0;
		}
	   wordVote.setAddDate(new Date());
	   Vote vote = new Vote();
	   vote.setVoteProject(wordVote.getVoteProject());
	   vote.setUid(wordVote.getSortNumber());
	   wordVote.setVote(vote);
	   if(wordVoteDao.save(wordVote))
		   return 1;
	   else {
		return 0;
	}
	}

	@Override
	public boolean delWordVote(long pid) throws Exception {
		WordVote wordVote = wordVoteDao.get(pid);
		if (wordVote != null) {
		return	wordVoteDao.delete(wordVote);
		}
		else
		return false;
	}

	@Override
	public int updateWordVote(String[] info, long id) throws Exception {
		WordVote wordVote = wordVoteDao.get(id);
		if(info[0]!= null && !"".equals(info[0]))
		  {
			int projectId = Integer.parseInt(info[0]);
		    VoteProject voteProject=	voteProjectDao.get(projectId);
		   if (voteProject!=null) {
			wordVote.setVoteProject(voteProject);
		   }
		   else {
			return 0;
		   }
		  }else {
			return 0;
		 }
		   Long sortNumber=null;
			try {
				 sortNumber = Long.parseLong(info[1]);
			} catch (Exception e) {
				sortNumber = (long) 100;
			}
			wordVote.setSortNumber(sortNumber);
		 if (info[2]!=null && !"".equals(info[2])) {
				String content=commonUtil.trimInnerSpaceStr(info[2]);
				wordVote.setContent(content);
		 }else {
			return 0;
		}
	   wordVote.setAddDate(new Date());
	   Vote vote = new Vote();
	   vote.setVoteProject(wordVote.getVoteProject());
	   vote.setUid(wordVote.getSortNumber());
	   wordVote.setVote(vote);
	   if(wordVoteDao.update(wordVote))
		   return 1;
	   else {
		return 0;
	}
	}

	@Override
	public List<WordVote> getWordVoteByProject(int projectId) throws Exception {
			List<WordVote>wordVotes = new ArrayList<WordVote>();
			List<Serializable>list= wordVoteDao.getWordVoteByProject(projectId);
			for(Serializable s:list){
				WordVote wordVote =(WordVote) s;
				wordVotes.add(wordVote);
			}
		  Comparator< WordVote> comparator = new ComparatorWordVote<WordVote>();
		  Collections.sort(wordVotes,comparator);
		  return wordVotes;
	}

	@Override
	public WordVote getWordVote(long vid) throws Exception {
		
		return wordVoteDao.get(vid);
	}

}
