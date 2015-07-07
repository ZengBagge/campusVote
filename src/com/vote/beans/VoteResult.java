
/**
 * @author bagge
 *
 */
package com.vote.beans;

public class VoteResult{
	
	private long  VoteId;
	
	private long number;
	
	private double proporion;
	
	private String name;

	public long getVoteId() {
		return VoteId;
	}

	public void setVoteId(long voteId) {
		VoteId = voteId;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public double getProporion() {
		return proporion;
	}

	public void setProporion(double d) {
		this.proporion = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}