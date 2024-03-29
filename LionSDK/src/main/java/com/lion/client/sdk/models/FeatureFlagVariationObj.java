package com.lion.client.sdk.models;

public class FeatureFlagVariationObj
{
	private String FeatureFlagVariationGuid;
	public final String getFeatureFlagVariationGuid()
	{
		return FeatureFlagVariationGuid;
	}
	public final void setFeatureFlagVariationGuid(String value)
	{
		FeatureFlagVariationGuid = value;
	}

	private String Value;
	public final String getValue()
	{
		return Value;
	}
	public final void setValue(String value)
	{
		Value = value;
	}

	private String DisplayName;
	public final String getDisplayName()
	{
		return DisplayName;
	}
	public final void setDisplayName(String value)
	{
		DisplayName = value;
	}

	private String Description;
	public final String getDescription()
	{
		return Description;
	}
	public final void setDescription(String value)
	{
		Description = value;
	}
}