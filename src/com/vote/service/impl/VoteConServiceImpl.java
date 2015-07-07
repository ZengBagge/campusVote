package com.vote.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.common.DateUtil;
import com.vote.common.PageBean;
import com.vote.common.commonUtil;
import com.vote.dao.VoteConDao;
import com.vote.dao.VoteDao;
import com.vote.po.UserCommon;
import com.vote.po.Vote;
import com.vote.po.VoteCon;
import com.vote.po.VoteProject;
import com.vote.service.VoteConService;


@Service("voteConService")
public class VoteConServiceImpl implements VoteConService {

	@Resource
	private VoteConDao voteConDao;
	@Resource
	private VoteDao voteDao;
	@Override
	public boolean addVoteCon(Vote vote, UserCommon userCommon,
                   String ip) throws Exception {
		         commonUtil.p("投票service进入记录");
				try {
					vote.setPoll(vote.getPoll()+1);//票数加一
					if(voteDao.update(vote)){
					VoteCon voteCon = new VoteCon();
					voteCon.setAddDate(new Date());
					voteCon.setUserCommon(userCommon);
					voteCon.setIp(ip);
					voteCon.setVote(vote);
					voteCon.setMessage("投票成功");
                    return voteConDao.save(voteCon);
					}else {
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
	}

	@Override
	public boolean del(long id) throws Exception {
		VoteCon entity=voteConDao.get(id);
		if (entity!=null) {
			entity.setState(-1);
            return voteConDao.update(entity);
		}else
		return false;
	}

	@Override
	public boolean delTrue(long id) throws Exception {
		VoteCon entity=voteConDao.get(id);
		if (entity!=null) {
            return voteConDao.delete(entity);
		}else
		return false;
	}

	@Override
	public PageBean getVoteConListByUser(int page,int pageSize,long id) throws Exception {
		try {
			int allRow=voteConDao.getListNumberByUser(id);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
            final int length = pageSize;//每页记录数
            final int currentPage = PageBean.countCurrentPage(page);
            List<VoteProject> projects =voteConDao.getListByUser(offset, length,id);
            PageBean pageBean = new PageBean();
			pageBean.setPageSize(pageSize);
			pageBean.setCurrentPage(currentPage);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(projects);
			pageBean.init();
			commonUtil.p("获取到投票记录"+projects.size()+"条");
			return pageBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public VoteConDao getVoteConDao() {
		return voteConDao;
	}

	public void setVoteConDao(VoteConDao voteConDao) {
		this.voteConDao = voteConDao;
	}

	public VoteDao getVoteDao() {
		return voteDao;
	}

	public void setVoteDao(VoteDao voteDao) {
		this.voteDao = voteDao;
	}

	@Override
	public boolean getAccess(long uid, Vote vote) throws Exception {
		
		VoteProject voteProject = vote.getVoteProject();
		int zong=voteConDao.getNumberByUserAndProject(uid,voteProject.getId());
		commonUtil.p("活动投票总数"+zong);
		if(zong == -1)
			return false;
		if(voteProject.getMaxNumber()<=zong)
			return false;
		List<VoteCon>voteCons = voteConDao.getListByUserAndVote(uid,vote.getId());
		if (voteCons==null || voteCons.size()<1) {
			return true;
		}
		else if(voteCons.size()==1){
			if (voteProject.getModel()==0) {
				return false;
			}else if (voteProject.getModel()==1) {
				Date addDate = voteCons.get(0).getAddDate();
				if(DateUtil.getDay(addDate)==DateUtil.getDay(new Date()))
					return false;
			}
		}else {
			for (VoteCon v:voteCons) {
				Date aDate = v.getAddDate();
				if(DateUtil.getDay(aDate)==DateUtil.getDay(new Date()))
					return false;
			}
		}
	return true;
	}

	@Override
	public int getTimeAccess(Vote vote) throws Exception {
       
		VoteProject voteProject = vote.getVoteProject();
		Date beginDate=voteProject.getBeginDate();
		Date endDate = voteProject.getEndDate();
		Date now = new Date();
		if (DateUtil.compareDate(now, beginDate)==-1) {
			return -2;
		}
		if (DateUtil.compareDate(now, endDate)==1) {
			return -1;
		}
		return 0;
	}

}
