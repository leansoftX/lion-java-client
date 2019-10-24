package com.lion.client.sdk;

public final class LionMatchOperator
{
	public static boolean Match(String logicOperator, String firstValue, String secondValue)
	{
		boolean result = true;
		switch (logicOperator)
		{
			case "greater_than":
				result = GreaterThan(firstValue, secondValue);
				break;
			case "less_than":
				result = LessThan(firstValue, secondValue);
				break;
			case "equal":
				result = firstValue.equals(secondValue);
				break;
			case "contain":
				result = Contains(firstValue, secondValue);
				break;
			case "starts_with":
				result = StartWith(firstValue, secondValue);
				break;
			case "ends_with":
				result = Endwith(firstValue, secondValue);
				break;
			default:
				break;
		}
		return result;
	}

	public static boolean GreaterThan(String firstValue, String secondValue)
	{
		try
		{
			return Double.parseDouble(firstValue) > Double.parseDouble(secondValue);
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static boolean LessThan(String firstValue, String secondValue)
	{
		try
		{
			return Double.parseDouble(firstValue) < Double.parseDouble(secondValue);
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static boolean equals(String firstValue, String secondValue)
	{
		return firstValue.equals(secondValue);
	}

	public static boolean Contains(String firstValue, String secondValue)
	{
		return firstValue.contains(secondValue);
	}

	public static boolean StartWith(String firstValue, String secondValue)
	{
		return firstValue.startsWith(secondValue);
	}

	public static boolean Endwith(String firstValue, String secondValue)
	{
		return firstValue.endsWith(secondValue);
	}
}