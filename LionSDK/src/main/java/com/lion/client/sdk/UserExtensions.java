package com.lion.client.sdk;

import com.lion.client.sdk.common.*;
import java.util.*;

public final class UserExtensions
{
	public static LionUser AndName(LionUser user, String name)
	{
		user.setName(name);
		return user;
	}

	public static LionUser AndEmail(LionUser user, String email)
	{
		user.setEmail(email);
		return user;
	}

	public static LionUser AndCustomAttribute(LionUser user, String attribute, String value)
	{
		if (attribute.equals(""))
		{
			throw new IllegalArgumentException("Attribute Name can not be empty");
		}

		user.getCustom().put(attribute, value);//JValue

		return user;
	}

	public static LionUser AndCustomAttribute(LionUser user, String attribute, boolean value)
	{
		if (attribute.equals(""))
		{
			throw new IllegalArgumentException("Attribute Name can not be empty");
		}

		user.getCustom().put(attribute, String.valueOf(value));//JValue

		return user;
	}

	public static LionUser AndCustomAttribute(LionUser user, String attribute, int value)
	{
		if (attribute.equals(""))
		{
			throw new IllegalArgumentException("Attribute Name can not be empty");
		}

		user.getCustom().put(attribute, String.valueOf(value));//JValue

		return user;
	}

	public static LionUser AndCustomAttribute(LionUser user, String attribute, float value)
	{
		if (attribute.equals(""))
		{
			throw new IllegalArgumentException("Attribute Name can not be empty");
		}

		user.getCustom().put(attribute, String.valueOf(value));//JValue

		return user;
	}

	public static LionUser AndCustomAttribute(LionUser user, String attribute, ArrayList<String> value)
	{
		if (attribute.equals(""))
		{
			throw new IllegalArgumentException("Attribute Name can not be empty");
		}

		String jsonArray = "[\""+ StringHelper.join("\",\"",value.toArray(new String[value.size()]))+"\"]";
		user.getCustom().put(attribute,jsonArray );//JArray

		return user;
	}
}