package model;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import dao.Database;
import dao.Project;
import dto.Website;

public class ProjectManager {
	public static void main(String[]args) throws Exception{
		ProjectManager pm = new ProjectManager();
		ArrayList<Website> list = pm.GetFeeds();
		for(Website we:list){
			String tmps = we.toJSON().toJSONString();
		System.out.println(we.toJSON().toJSONString());
		}
	}

public ArrayList<Website> GetFeeds()throws Exception {
ArrayList<Website> feeds = null;
try {
Database database= new Database();
Connection connection = database.getConnection();
Project project= new Project();
feeds=project.GetFeeds(connection);
}
catch (Exception e) {
throw e;
}
return feeds;
}
public Website GetFeedsFilter(String query)throws Exception {
Website feeds = null;
try {
Database database= new Database();
Connection connection = database.getConnection();
Project project= new Project();
feeds=project.GetFeedsFilter(connection,query);
}
catch (Exception e) {
throw e;
}
return feeds;
}
}