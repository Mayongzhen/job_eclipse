package com.aisino.dao;

import java.util.List;

import com.aisino.model.RelevantData;




public interface IRelevant {
	public List<RelevantData> getRelevantDatalist();
	public void insertRelevantdata(RelevantData relevantdata);
	//public void deleteUser(int userid);
	//public User getUser(int id);
	public RelevantData getrelevantdata(int id);
}
