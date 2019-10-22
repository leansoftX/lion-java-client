package com.lion.client.sdk.models;

import com.lion.client.sdk.*;
import java.util.*;

public class RuleObj
{
	private UUID RuleGuid;
	public final UUID getRuleGuid()
	{
		return RuleGuid;
	}
	public final void setRuleGuid(UUID value)
	{
		RuleGuid = value;
	}

	private String RuleName;
	public final String getRuleName()
	{
		return RuleName;
	}
	public final void setRuleName(String value)
	{
		RuleName = value;
	}

	private UUID VariationGuid;
	public final UUID getVariationGuid()
	{
		return VariationGuid;
	}
	public final void setVariationGuid(UUID value)
	{
		VariationGuid = value;
	}

	private ArrayList<ConditionObj> Conditions;
	public final ArrayList<ConditionObj> getConditions()
	{
		return Conditions;
	}
	public final void setConditions(ArrayList<ConditionObj> value)
	{
		Conditions = value;
	}
}