package webService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import model.ProjectManager;
import dao.Project;
import dto.Website;

@Path("/")
public class FeedService {

@GET
@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
public String feed() throws Exception
{
String feeds = null;
JSONArray jos=new JSONArray();

ArrayList<Website> feedData = new ArrayList<Website>();

ProjectManager projectManager= new ProjectManager();
	feedData = projectManager.GetFeeds();
	
	for(Website tmp:feedData){
		jos.add(tmp.toJSON());
		System.out.println(tmp.toJSON().toJSONString());
	}

return jos.toJSONString();

}



@GET
@Path("/data/{param}")
@Produces(MediaType.APPLICATION_JSON)
public String feedFilter(@PathParam("param") String query) throws Exception
{
String feeds = null;
JSONArray jos=new JSONArray();

Website feedData = null;

ProjectManager projectManager= new ProjectManager();
	feedData = projectManager.GetFeedsFilter(query);
	
	
try{
return feedData.toJSON().toJSONString();
}
catch(Exception e){
return "NO DATA FOUND \n"+"\n";
}

}

@POST
@Path("/in")
@Consumes("application/json")
@Produces("application/json")
public Response postJSONObject(String jsonString){
	JSONObject jsonObj=null;
	try{
		JSONParser jp = new JSONParser();
		jsonObj = (JSONObject) jp.parse(jsonString);
	}
	catch(ParseException pe){
		return Response.status(400).entity("\n"+"400 error - Bad Request or Malformed JSON Request Body").build();
	}
	
		Set set=jsonObj.keySet();
		System.out.println(set);
		if((set.size()!=3) || !(set.contains("title")) || !(set.contains("description")) || !(set.contains("url"))){
			return Response.status(400).entity("400 error - All fields not present in request body"+"\n").build();
		}
		Collection coll = jsonObj.values();
		Iterator it = coll.iterator();
		while(it.hasNext()){
			if(it.next().toString().trim().length()==0){
				return Response.status(400).entity("400 error - Request body validation failed"+"\n").build();
			}
		}
		if(coll.size()!=3 || coll.contains(null) || coll.contains("")){
			return Response.status(400).entity("400 error - Request body validation failed"+"\n").build();
		}
		
	
	Project project = new Project();
	String jsonRecordInserted="";
	try {
		jsonRecordInserted=project.insertData(jsonString);
	} catch (ClassCastException e) {
		// TODO Auto-generated catch block
		return Response.status(400).entity(jsonRecordInserted+"\n").build();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(jsonRecordInserted+"\n");
	return Response.status(201).entity(jsonRecordInserted+"\n").build();
}

}