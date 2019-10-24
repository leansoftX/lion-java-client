package com.lion.client.sdk.models;

import java.util.*;
import java.time.*;

public class SegmentTargetingObj
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

	private String SegmentGuid;
	public final String getSegmentGuid()
	{
		return SegmentGuid;
	}
	public final void setSegmentGuid(String value)
	{
		SegmentGuid = value;
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

	private String Key;
	public final String getKey()
	{
		return Key;
	}
	public final void setKey(String value)
	{
		Key = value;
	}

	private LocalDateTime CreateDate ;
	public final LocalDateTime getCreateDate()
	{
		return CreateDate;
	}
	public final void setCreateDate(LocalDateTime value)
	{
		CreateDate = value;
	}

	private LocalDateTime ModifyDate ;
	public final LocalDateTime getModifyDate()
	{
		return ModifyDate;
	}
	public final void setModifyDate(LocalDateTime value)
	{
		ModifyDate = value;
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

	private List<User> IncludeUsers;
	public final List<User> getIncludeUsers()
	{
		return IncludeUsers;
	}
	public final void setIncludeUsers(List<User> value)
	{
		IncludeUsers = value;
	}

	private List<User> ExcludeUsers;
	public final List<User> getExcludeUsers()
	{
		return ExcludeUsers;
	}
	public final void setExcludeUsers(List<User> value)
	{
		ExcludeUsers = value;
	}

	private List<RuleObj> Rules;
	public final List<RuleObj> getRules()
	{
		return Rules;
	}
	public final void setRules(List<RuleObj> value)
	{
		Rules = value;
	}

	private List<String> DeleteRules;
	public final List<String> getDeleteRules()
	{
		return DeleteRules;
	}
	public final void setDeleteRules(List<String> value)
	{
		DeleteRules = value;
	}
}