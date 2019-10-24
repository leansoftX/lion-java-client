package com.lion.client.sdk.models;

import java.util.*;

public class RuleObj
{
	private String RuleGuid;
	public final String getRuleGuid()
	{
		return RuleGuid;
	}
	public final void setRuleGuid(String value)
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

	private String VariationGuid;
	public final String getVariationGuid()
	{
		return VariationGuid;
	}
	public final void setVariationGuid(String value)
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