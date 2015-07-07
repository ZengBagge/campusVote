package com.vote.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vote.po.WordVote;
import com.vote.service.WordVoteService;

@Controller("wordVoteAction")
@Scope("prototype")
public class WordVoteAction extends CommonAction{

	private static final long serialVersionUID = 1L;
    private int projectId;
    @Resource
    private WordVoteService wordVoteService;
	private List<WordVote>votes;
	public String getWords() throws Exception{
		
		try {
			projectId = (int) request.getAttribute("projectId");
			votes = wordVoteService.getWordVoteByProject(projectId);
			return "wordProject";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public WordVoteService getWordVoteService() {
		return wordVoteService;
	}

	public void setWordVoteService(WordVoteService wordVoteService) {
		this.wordVoteService = wordVoteService;
	}

	public List<WordVote> getVotes() {
		return votes;
	}

	public void setVotes(List<WordVote> votes) {
		this.votes = votes;
	}
	
	
}
