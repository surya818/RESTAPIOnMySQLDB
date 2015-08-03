package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.jdbc.Statement;

import dto.Website;;

public class Project {
public static void main(String[]args) throws Exception{
	Project pro = new Project();
	String JSONO = "{\"title\":\"linkdin\",\"description\":\"LinkedIn USA\",\"url\":\"www.linkedin.com\"}";
	System.out.println(pro.insertData(JSONO));
}



public ArrayList<Website> GetFeeds(Connection connection) throws Exception
{
ArrayList<Website> feedData = new ArrayList<Website>();
try
{
PreparedStatement ps = connection.prepareStatement("SELECT id,title,description,url FROM website");
ResultSet rs = ps.executeQuery();
while(rs.next())
{
Website feedObject = new Website();
feedObject.setId(rs.getInt("id"));
feedObject.setTitle(rs.getString("title"));
feedObject.setDesc(rs.getString("description"));
feedObject.setUrl(rs.getString("url"));
feedData.add(feedObject);
}
return feedData;
}
catch(Exception e)
{
throw e;
}
}
public Website GetFeedsFilter(Connection connection,String query) throws Exception
{
ArrayList<Website> feedData = new ArrayList<Website>();
String finQuery="select * from website where id = "+query+";";
Website feedObject =null;
try
{
PreparedStatement ps = connection.prepareStatement(finQuery);
ResultSet rs = ps.executeQuery();
while(rs.next())
{
 feedObject = new Website();
feedObject.setId(rs.getInt("id"));
feedObject.setTitle(rs.getString("title"));
feedObject.setDesc(rs.getString("description"));
feedObject.setUrl(rs.getString("url"));
}
return feedObject;
}
catch(Exception e)
{
throw e;
}
}


public String insertData(String jsonObject) throws Exception{
	Database db = new Database();
	JSONParser parser  = new JSONParser();
	JSONObject obj = (JSONObject) parser.parse(jsonObject);
	String title = (String) obj.get("title");
	String description = (String)obj.get("description");
	String url = (String)obj.get("url");
	
	Connection conn = db.getConnection();
	java.sql.Statement stmt = conn.createStatement();
	String getQuery = "select id from website order by id desc limit 1";
	ResultSet set = stmt.executeQuery(getQuery);
	int id_val=0;
	while(set.next()){
	 id_val = set.getInt("id");
	}
	
	String insertQuery = "insert into website values (?,?, ?, ?)";
	PreparedStatement pStmt = conn.prepareStatement(insertQuery);
	pStmt.setInt(1, ++id_val);
	pStmt.setString(2, title);
	pStmt.setString(3, description);
	pStmt.setString(4, url);
	pStmt.execute();
	obj.put("id", id_val);
	return obj.toJSONString();
	
	
	
	
	
}
}