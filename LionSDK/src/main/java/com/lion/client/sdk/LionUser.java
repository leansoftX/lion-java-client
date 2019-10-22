package com.lion.client.sdk;

import org.json.*;
//import Newtonsoft.Json.*;
//import Newtonsoft.Json.Linq.*;
import java.util.*;

public class LionUser
{
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [JsonProperty(PropertyName = "key", NullValueHandling = NullValueHandling.Ignore)] public string Key {get;set;}
	private String Key;
	public final String getKey()
	{
		return Key;
	}
	public final void setKey(String value)
	{
		Key = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [JsonProperty(PropertyName = "name", NullValueHandling = NullValueHandling.Ignore)] public string Name {get;set;}
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [JsonProperty(PropertyName = "email", NullValueHandling = NullValueHandling.Ignore)] public string Email {get;set;}
	private String Email;
	public final String getEmail()
	{
		return Email;
	}
	public final void setEmail(String value)
	{
		Email = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [JsonProperty(PropertyName = "custom", NullValueHandling = NullValueHandling.Ignore)] public Dictionary<string, JToken> Custom {get;set;}
	private HashMap<String, String> Custom; //JToken
	public final HashMap<String, String> getCustom() //JToken
	{
		return Custom;
	}
	public final void setCustom(HashMap<String, String> value)
	{
		Custom = value;
	} //JToken

	public LionUser(String key)
	{
		setKey(key);
		setCustom(new HashMap<String, String>());//JToken

	}
	public static LionUser WithKey(String key)
	{
		return new LionUser(key);
	}
}