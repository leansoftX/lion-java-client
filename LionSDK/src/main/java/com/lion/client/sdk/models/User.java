package com.lion.client.sdk.models;

import com.lion.client.sdk.*;
import java.util.*;
import java.time.*;

public class User
{
	private int ID;
	public final int getID()
	{
		return ID;
	}
	public final void setID(int value)
	{
		ID = value;
	}

	private String UserGuid;
	public final String getUserGuid()
	{
		return UserGuid;
	}
	public final void setUserGuid(String value)
	{
		UserGuid = value;
	}

	private String EnvironmentGuid;
	public final String getEnvironmentGuid()
	{
		return EnvironmentGuid;
	}
	public final void setEnvironmentGuid(String value)
	{
		EnvironmentGuid = value;
	}

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

	private LocalDateTime CreateDate = LocalDateTime.MIN;
	public final LocalDateTime getCreateDate()
	{
		return CreateDate;
	}
	public final void setCreateDate(LocalDateTime value)
	{
		CreateDate = value;
	}

	private LocalDateTime ModifyDate = LocalDateTime.MIN;
	public final LocalDateTime getModifyDate()
	{
		return ModifyDate;
	}
	public final void setModifyDate(LocalDateTime value)
	{
		ModifyDate = value;
	}
}