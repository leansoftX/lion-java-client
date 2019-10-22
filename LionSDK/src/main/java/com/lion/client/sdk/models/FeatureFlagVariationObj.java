package com.lion.client.sdk.models;

import com.lion.client.sdk.*;
import java.util.*;

public class FeatureFlagVariationObj
{
	private UUID FeatureFlagVariationGuid;
	public final UUID getFeatureFlagVariationGuid()
	{
		return FeatureFlagVariationGuid;
	}
	public final void setFeatureFlagVariationGuid(UUID value)
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