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

	private UUID UserGuid;
	public final UUID getUserGuid()
	{
		return UserGuid;
	}
	public final void setUserGuid(UUID value)
	{
		UserGuid = value;
	}

	private UUID EnvironmentGuid;
	public final UUID getEnvironmentGuid()
	{
		return EnvironmentGuid;
	}
	public final void setEnvironmentGuid(UUID value)
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