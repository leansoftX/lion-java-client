package com.lion.client.sdk.models;

import com.lion.client.sdk.*;
import java.util.*;
import java.time.*;

public class FeatureFlagTargeting
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

	private UUID FeatureFlagGuid;
	public final UUID getFeatureFlagGuid()
	{
		return FeatureFlagGuid;
	}
	public final void setFeatureFlagGuid(UUID value)
	{
		FeatureFlagGuid = value;
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

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#region Targeting

	private UUID EnvironmentGuid;
	public final UUID getEnvironmentGuid()
	{
		return EnvironmentGuid;
	}
	public final void setEnvironmentGuid(UUID value)
	{
		EnvironmentGuid = value;
	}

	private boolean IsTargeting;
	public final boolean getIsTargeting()
	{
		return IsTargeting;
	}
	public final void setIsTargeting(boolean value)
	{
		IsTargeting = value;
	}

	private UUID DefaultVariation;
	public final UUID getDefaultVariation()
	{
		return DefaultVariation;
	}
	public final void setDefaultVariation(UUID value)
	{
		DefaultVariation = value;
	}

	private UUID TargetingOffVariation;
	public final UUID getTargetingOffVariation()
	{
		return TargetingOffVariation;
	}
	public final void setTargetingOffVariation(UUID value)
	{
		TargetingOffVariation = value;
	}

	private List<User> FeatureOnTargetingUsers;
	public final List<User> getFeatureOnTargetingUsers()
	{
		return FeatureOnTargetingUsers;
	}
	public final void setFeatureOnTargetingUsers(List<User> value)
	{
		FeatureOnTargetingUsers = value;
	}

	private List<User> FeatureOffTargetingUsers;
	public final List<User> getFeatureOffTargetingUsers()
	{
		return FeatureOffTargetingUsers;
	}
	public final void setFeatureOffTargetingUsers(List<User> value)
	{
		FeatureOffTargetingUsers = value;
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

	private List<FeatureFlagVariationObj> FeatureFlagVariations;
	public final List<FeatureFlagVariationObj> getFeatureFlagVariations()
	{
		return FeatureFlagVariations;
	}
	public final void setFeatureFlagVariations(List<FeatureFlagVariationObj> value)
	{
		FeatureFlagVariations = value;
	}

	private List<SegmentTargetingObj> FeatureFlagSegments;
	public final List<SegmentTargetingObj> getFeatureFlagSegments()
	{
		return FeatureFlagSegments;
	}
	public final void setFeatureFlagSegments(List<SegmentTargetingObj> value)
	{
		FeatureFlagSegments = value;
	}

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#endregion
}