package com.lion.client.sdk.models;

import com.lion.client.sdk.*;

public class ConditionObj
{
	private AttributeObj UserAttribute;
	public final AttributeObj getUserAttribute()
	{
		return UserAttribute;
	}
	public final void setUserAttribute(AttributeObj value)
	{
		UserAttribute = value;
	}

	private String Operation;
	public final String getOperation()
	{
		return Operation;
	}
	public final void setOperation(String value)
	{
		Operation = value;
	}

	private String ExpectValue;
	public final String getExpectValue()
	{
		return ExpectValue;
	}
	public final void setExpectValue(String value)
	{
		ExpectValue = value;
	}
}