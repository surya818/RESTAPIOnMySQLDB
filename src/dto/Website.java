package dto;

import org.json.simple.JSONObject;

public class Website {
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private String title;
	private String desc;
	private String url;
	
	public JSONObject toJSON(){
		JSONObject jo = new JSONObject();
		jo.put("id", this.id);
		jo.put("title", this.getTitle());
		jo.put("description", this.getDesc());
		jo.put("url", this.getUrl());
		
		try{
		return jo;
		}
		catch(NullPointerException npe){
			System.out.println("NULL DB OBJECT YO!!!");
		}
		return jo;
	}
}
