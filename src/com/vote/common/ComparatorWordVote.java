package com.vote.common;

import java.util.Comparator;
import com.vote.po.WordVote;

public class ComparatorWordVote<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		WordVote p1 = (WordVote) o1;
		WordVote p2 = (WordVote) o2;
		int flag = p1.getSortNumber().compareTo(p2.getSortNumber());
		return flag;
	}

}
