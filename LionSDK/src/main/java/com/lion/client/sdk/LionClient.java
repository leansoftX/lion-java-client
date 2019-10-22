package com.lion.client.sdk;

import com.lion.client.sdk.common.*;
import com.lion.client.sdk.models.*;

import org.apache.hc.client5.http.entity.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.*;
import org.json.*;

import java.io.IOException;
import org.apache.hc.client5.http.*;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.*;
import org.apache.hc.client5.http.impl.classic.*;
import org.apache.hc.core5.http.io.*;
import org.apache.hc.core5.http.io.entity.*;

import jdk.nashorn.internal.parser.JSONParser;
import sun.net.www.http.*;

import static org.apache.hc.core5.http.ContentType.*;

public class LionClient
{
	private String _sdkKey;
	private String get_sdkKey()
	{
		return _sdkKey;
	}
	private void set_sdkKey(String value)
	{
		_sdkKey = value;
	}
	private CloseableHttpClient _httpClient;


	private static final String DefaultAPIUri = "http://lion-test.devcloudx.com/api";

	public LionClient(String sdkKey)
	{
		set_sdkKey(sdkKey);
		_httpClient = HttpClients.createDefault();
		//_httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("SDKKey", get_sdkKey());
	}

	public final boolean BoolVariation(String key) throws IOException, ParseException {
		String flagStatusAPI = String.format("%1$s/FlagStatuses/%2$s", DefaultAPIUri, key);
		//String result = _httpClient.GetStringAsync(flagStatusAPI).Result;

		final HttpGet httpget = new HttpGet(flagStatusAPI);
		httpget.addHeader("SDKKey",get_sdkKey());
		final CloseableHttpResponse response = _httpClient.execute(httpget);
		int statusCode = response.getCode();
		HttpEntity entity = response.getEntity();
		String result = entity != null ? EntityUtils.toString(entity) : "false";
		return Boolean.parseBoolean(result);
	}

	/**
	 variate the bool type feature flag

	 @param key feature flag key
	 @param user the business user which will sent to the lion service
	 //@param defaultValue the default return value when there are any kind of exception occurred
	 @return
	*/

	public final boolean BoolVariation(String key, LionUser user)
	{
		return BoolVariation(key, user, false);
	}

//C# TO JAVA CONVERTER NOTE: Java does not support optional parameters. Overloaded method(s) are created above:
//ORIGINAL LINE: public bool BoolVariation(string key, LionUser user, bool defaultValue = false)
	public final boolean BoolVariation(String key, LionUser user, boolean defaultValue)
	{
		try
		{
			LionUser feedbackUser = new LionUser(user.getKey());
			//send user request event and save user
			if (!StringHelper.isNullOrEmpty(user.getKey()))
			{
				feedbackUser = SendFlagRequestEvent(key, user);
			}

			String requestUrl = String.format("%1$s/Flags/%2$s", DefaultAPIUri, key);
			//var response = _httpClient.GetAsync(requestUrl);
			//if (response.Result.StatusCode != HttpStatusCode.OK)
			//{
			//	return defaultValue;
			//}
			//var result = response.Result.Content.ReadAsStringAsync().Result;
			//var flag = JsonConvert.<FeatureFlagTargeting>DeserializeObject(result);

			final HttpGet httpget = new HttpGet(requestUrl);
			httpget.addHeader("SDKKey",get_sdkKey());
			final CloseableHttpResponse response = _httpClient.execute(httpget);
			int statusCode = response.getCode();
			HttpEntity entity = response.getEntity();
			String result = entity != null ? EntityUtils.toString(entity) : "{}";
			FeatureFlagTargeting flag = JSONHelper.parseObject(result,FeatureFlagTargeting.class);
			Feature feature = new Feature(flag);
			return feature.Evaluate(feedbackUser, defaultValue);
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	private LionUser SendFlagRequestEvent(String key, LionUser user) throws IOException, ParseException {
		String userAPI = String.format("%1$s/User", DefaultAPIUri);
		//StringContent httpContent = new StringContent(JsonConvert.SerializeObject(user), Encoding.UTF8, "application/json");
		//var response = _httpClient.PostAsync(userAPI, httpContent);
		//if (response.Result.StatusCode != HttpStatusCode.OK)
		//{
		//	LionUser tempVar = new LionUser(user.getKey());
		//	tempVar.setName(user.getName());
		//	tempVar.setEmail(user.getEmail());
		//	tempVar.setCustom(user.getCustom());
		//	return tempVar;
		//}
		//var result = response.Result.Content.ReadAsStringAsync().Result;
		//return JsonConvert.<LionUser>DeserializeObject(result);

		final HttpPost post = new HttpPost(userAPI);
		post.addHeader("SDKKey", get_sdkKey());
		String userJson = JSONHelper.toJSON(user);
		StringEntity jsonEntity = new StringEntity(userJson, APPLICATION_JSON);
		post.setEntity(jsonEntity);
		HttpEntity entity;
		try (CloseableHttpResponse response = _httpClient.execute(post)) {
			int statusCode = response.getCode();
			entity = response.getEntity();
		}
		String result = entity != null ? EntityUtils.toString(entity) : "{}";
		LionUser resultUser = JSONHelper.parseObject(result, LionUser.class);
		return resultUser;
	}
}