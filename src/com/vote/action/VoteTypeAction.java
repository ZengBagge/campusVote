package com.vote.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vote.common.commonUtil;
import com.vote.po.VoteType;
import com.vote.service.VoteTypeService;

@Controller("voteTypeAction")
@Scope("prototype")
public class VoteTypeAction extends CommonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private List<VoteType>voteTypeList = null;
	@Resource
	private VoteTypeService voteTypeService;
	
	
	public void getList() {
		
		try {
			this.voteTypeList = voteTypeService.getList();
			commonUtil.p("查询到投票类型"+voteTypeList.size()+"种");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			commonUtil.p("查询错误");
			this.voteTypeList = null;
		}
	}

	public VoteTypeService getVoteTypeService() {
		return voteTypeService;
	}

	public void setVoteTypeService(VoteTypeService voteTypeService) {
		this.voteTypeService = voteTypeService;
	}

	public void setVoteTypeList(List<VoteType> voteTypeList) {
		this.voteTypeList = voteTypeList;
	}

	public List<VoteType> getVoteTypeList() {
		return voteTypeList;
	}
	
	
	
}
