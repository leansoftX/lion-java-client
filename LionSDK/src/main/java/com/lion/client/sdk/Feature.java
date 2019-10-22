package com.lion.client.sdk;
import java.util.*;
import com.lion.client.sdk.models.*;
//import org.apache.calcite.linq4j.*;

public class Feature
{
	public FeatureFlagTargeting _flag;
	public Feature(FeatureFlagTargeting flag)
	{
		_flag = flag;
	}
	public final boolean Evaluate(LionUser user, boolean defaultValue)
	{
		return MatchesUserTarget(user, defaultValue);
	}

	private boolean MatchesUserTarget(LionUser user, boolean defaultValue)
	{
		if (!_flag.getIsTargeting())
		{
			//var targetingOffVairation = (from item in _flag.getFeatureFlagVariations() where item.FeatureFlagVariationGuid.equals(_flag.getTargetingOffVariation()) select item).FirstOrDefault();
			FeatureFlagVariationObj targetingOffVairation = null;
			List<FeatureFlagVariationObj> featureFlagVariationObjs = _flag.getFeatureFlagVariations();
			for(FeatureFlagVariationObj item : featureFlagVariationObjs)
			{
				if (item.getFeatureFlagVariationGuid() == _flag.getTargetingOffVariation()) {
					targetingOffVairation = item;
					break;
				}
			}
			if (targetingOffVairation != null)
			{
				return Boolean.parseBoolean(targetingOffVairation.getValue());
			}
			else
			{
				return defaultValue;
			}
		}

		List<User> feature_on_targeting_users = _flag.getFeatureOnTargetingUsers();
		//if (feature_on_targeting_users.Any(i -> i.Key.equals(user.getKey())))
		//{
		//	return true;
		//}
		for(User item : feature_on_targeting_users)
		{
			if (item.getKey().equals(user.getKey())) {
				return true;
			}
		}

		List<User> feature_off_targeting_users = _flag.getFeatureOffTargetingUsers();
		//if (feature_off_targeting_users.Any(i -> i.Key.equals(user.getKey())))
		//{
		//	return false;
		//}

		for(User item : feature_off_targeting_users)
		{
			if (item.getKey() == user.getKey()) {
				return false;
			}
		}

		for (RuleObj rule : _flag.getRules())
		{
			boolean isMatchRule = true;
			for (ConditionObj condition : rule.getConditions())
			{
				if (!isMatchRule)
				{
					break;
				}
				if (condition.getUserAttribute().getDataType().equals("group"))
				{
					//var segment = (from item in _flag.getFeatureFlagSegments() where item.SegmentGuid.toString().equals(condition.getExpectValue()) select item).FirstOrDefault();
					SegmentTargetingObj segment = null;
					for(SegmentTargetingObj item : _flag.getFeatureFlagSegments())
					{
						if (item.getSegmentGuid().toString() == condition.getExpectValue()) {
							segment =  item;
							break;
						}
					}
					if (segment == null)
					{
						isMatchRule = false;
						break;
					}
					else
					{
						boolean userBelongSegment = CheckUserBelongSegment(user, segment);
						if ((!userBelongSegment && condition.getUserAttribute().getName().equals("用户在组中")) || (userBelongSegment && condition.getUserAttribute().getName().equals("用户不在组中")))
						{
							isMatchRule = false;
							break;
						}
					}
				}
				else
				{
					boolean hasAttribute = false;
					String attributeName = "";
					for (String key : user.getCustom().keySet())
					{
						if (key.equals(condition.getUserAttribute().getName()))
						{
							hasAttribute = true;
							attributeName = key;
							break;
						}
					}
					if (!hasAttribute)
					{
						isMatchRule = false;
						break;
					}
					else
					{
						String attributeValue = user.getCustom().get(attributeName).toString();
						isMatchRule = LionMatchOperator.Match(condition.getOperation(), attributeValue, condition.getExpectValue());
					}
				}
			}
			//All conditions are passed
			if (isMatchRule)
			{
				//var matchedVariation = (from item in _flag.getFeatureFlagVariations() where item.FeatureFlagVariationGuid.equals(rule.getVariationGuid()) select item).FirstOrDefault();
				FeatureFlagVariationObj matchedVariation = null;
				for(FeatureFlagVariationObj item : _flag.getFeatureFlagVariations())
				{
					if (item.getFeatureFlagVariationGuid() == rule.getVariationGuid()) {
						matchedVariation = item;
						break;
					}
				}
				if (matchedVariation != null)
				{
					return Boolean.parseBoolean(matchedVariation.getValue());
				}
			}
		}

		//var defaultVariation = (from item in _flag.getFeatureFlagVariations() where item.FeatureFlagVariationGuid.equals(_flag.getDefaultVariation()) select item).FirstOrDefault();
		FeatureFlagVariationObj defaultVariation = null;
		for(FeatureFlagVariationObj item : _flag.getFeatureFlagVariations())
		{
			if (item.getFeatureFlagVariationGuid() == _flag.getDefaultVariation()) {
				defaultVariation = item;
				break;
			}
		}
		if (defaultVariation != null)
		{
			return Boolean.parseBoolean(defaultVariation.getValue());
		}
		else
		{
			return defaultValue;
		}
	}

	private boolean CheckUserBelongSegment(LionUser user, SegmentTargetingObj segment)
	{

		//ArrayList<Object> includeUserKeys = (from item in segment.getIncludeUsers() select item.Key).ToList();
		//if (includeUserKeys.contains(user.getKey()))
		//{
		//	return true;
		//}
		for(User item : segment.getIncludeUsers())
		{
			if (item.getKey() == user.getKey()) {
				return true;
			}
		}

		//ArrayList<Object> excludeUserKeys = (from item in segment.getExcludeUsers() select item.Key).ToList();
		//if (excludeUserKeys.contains(user.getKey()))
		//{
		//	return false;
		//}
		for(User item : segment.getExcludeUsers())
		{
			if (item.getKey() == user.getKey()) {
				return true;
			}
		}

		for (RuleObj rule : segment.getRules())
		{
			if (MatchRule(user, rule))
			{
				return true;
			}
		}
		return false;
	}

	private boolean MatchRule(LionUser user, RuleObj rule)
	{
		for (ConditionObj condition : rule.getConditions())
		{
			boolean hasAttribute = false;
			String attributeName = "";
			for (String key : user.getCustom().keySet())
			{
				if (key.equals(condition.getUserAttribute().getName()))
				{
					hasAttribute = true;
					attributeName = key;
					break;
				}
			}
			if (!hasAttribute)
			{
				return false;
			}
			else
			{
				String attributeValue = user.getCustom().get(attributeName).toString();
				if (!LionMatchOperator.Match(condition.getOperation(), attributeValue, condition.getExpectValue()))
				{
					return false;
				}
			}
		}
		return true;
	}


}