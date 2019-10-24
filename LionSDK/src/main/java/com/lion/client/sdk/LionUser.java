package com.lion.client.sdk;
import java.util.*;

public class LionUser
{
	private String Key;
	public final String getKey()
	{
		return Key;
	}
	public final void setKey(String value)
	{
		Key = value;
	}

	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	private String Email;
	public final String getEmail()
	{
		return Email;
	}
	public final void setEmail(String value)
	{
		Email = value;
	}

	private HashMap<String, String> Custom; //JToken
	public final HashMap<String, String> getCustom() //JToken
	{
		return Custom;
	}
	public final void setCustom(HashMap<String, String> value)
	{
		Custom = value;
	} //JToken

	public LionUser()
	{
		setCustom(new HashMap<String, String>());//JToken
	}
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