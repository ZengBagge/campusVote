package com.vote.common;

import java.util.Comparator;
import com.vote.po.PersonVote;

public class ComparatorVote<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		PersonVote p1 = (PersonVote) o1;
		PersonVote p2 = (PersonVote) o2;
		int flag = p1.getVote().getUid().compareTo(p2.getVote().getUid());
		return flag;
	}

}
