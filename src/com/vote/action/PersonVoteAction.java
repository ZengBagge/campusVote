package com.vote.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vote.po.PersonVote;
import com.vote.service.PersonVoteService;

@Controller("personVoteAction")
@Scope("prototype")
public class PersonVoteAction extends CommonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int projectId;
	@Resource
	private PersonVoteService personVoteService;
	private List<PersonVote>list;
	
	public void getPersons() throws Exception{
		
		projectId = (int) request.getAttribute("projectId");
		if (projectId !=0) {
		  	list = personVoteService.getPersonVoteByProject(projectId);
		}
		
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public PersonVoteService getPersonVoteService() {
		return personVoteService;
	}

	public void setPersonVoteService(PersonVoteService personVoteService) {
		this.personVoteService = personVoteService;
	}

	public List<PersonVote> getList() {
		return list;
	}

	public void setList(List<PersonVote> list) {
		this.list = list;
	}

	
}
