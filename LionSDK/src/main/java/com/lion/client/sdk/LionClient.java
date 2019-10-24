package com.lion.client.sdk;

import com.lion.client.sdk.common.*;
import com.lion.client.sdk.models.*;
import java.io.IOException;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.*;
import org.apache.hc.client5.http.*;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.*;
import org.apache.hc.core5.http.io.entity.*;
import static org.apache.hc.core5.http.ContentType.*;

//import org.apache.hc.core5.http.io.*;
//import org.apache.hc.client5.http.impl.*;
//import jdk.nashorn.internal.parser.JSONParser;
//import sun.net.www.http.*;
//import org.apache.hc.client5.http.entity.*;
//import org.apache.hc.core5.util.Args;
//import org.json.*;
//import org.apache.commons.codec.binary.*;
//import com.google.gson.*;

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


	private String DefaultAPIUri = "http://lion-test.devcloudx.com/api";

	public LionClient(String sdkKey)
	{
		set_sdkKey(sdkKey);
		_httpClient = HttpClients.createDefault();
	}

	public LionClient(String sdkKey, String apiUri)
	{
		set_sdkKey(sdkKey);
		if(!apiUri.isEmpty())
			DefaultAPIUri = apiUri;
		_httpClient = HttpClients.createDefault();
	}

	public final boolean BoolVariation(String key) throws IOException, ParseException {
		String flagStatusAPI = String.format("%1$s/FlagStatuses/%2$s", DefaultAPIUri, key);
		final HttpGet httpget = new HttpGet(flagStatusAPI);
		String headerString = "SDKKey "+get_sdkKey();
		httpget.addHeader("Authorization",headerString);
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

	public final boolean BoolVariation(String key, LionUser user) throws IOException, ParseException {
		return BoolVariation(key, user, false);
	}

	public final boolean BoolVariation(String key, LionUser user, boolean defaultValue) throws IOException, ParseException {
		try
		{
			LionUser feedbackUser = new LionUser(user.getKey());
			//send user request event and save user
			if (!StringHelper.isNullOrEmpty(user.getKey()))
			{
				feedbackUser = SendFlagRequestEvent(key, user);//TODO: hashmap convert
			}

			String requestUrl = String.format("%1$s/Flags/%2$s", DefaultAPIUri, key);
			final HttpGet httpget = new HttpGet(requestUrl);
			String headerString = "SDKKey "+get_sdkKey();
			httpget.addHeader("Authorization",headerString);
			final CloseableHttpResponse response = _httpClient.execute(httpget);
			int statusCode = response.getCode();
			HttpEntity entity = response.getEntity();
			String result = entity != null ? EntityUtils.toString(entity) : "";
			if(!result.isEmpty()) {
				FeatureFlagTargeting flag = JSONHelper.parseObject(result, FeatureFlagTargeting.class);
				//Gson gson = new Gson();
				//FeatureFlagTargeting flag = gson.fromJson(result, FeatureFlagTargeting.class);
				if(flag!=null) {
					Feature feature = new Feature(flag);
					return feature.Evaluate(feedbackUser, defaultValue);
				}
				else
					return defaultValue;
			}
			else
				return defaultValue;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	private LionUser SendFlagRequestEvent(String key, LionUser user) throws IOException, ParseException {
		String userAPI = String.format("%1$s/User", DefaultAPIUri);
		final HttpPost post = new HttpPost(userAPI);
		String headerString = "SDKKey "+get_sdkKey();
		post.addHeader("Authorization",headerString);
		String userJson = JSONHelper.toJSON(user);
		StringEntity jsonEntity = new StringEntity(userJson, APPLICATION_JSON);
		post.setEntity(jsonEntity);
		HttpEntity entity;
		try (CloseableHttpResponse response = _httpClient.execute(post)) {
			int statusCode = response.getCode();
			entity = response.getEntity();
			String result = entity != null ? EntityUtils.toString(entity) : "{}";
			if(statusCode == 200) {
				LionUser resultUser = JSONHelper.parseObject(result, LionUser.class); //JSONHelper无法序列化子对象HashMap
				//Gson gson = new Gson();//属性名为大写，Gson无法反序列化成对象，需要改属性名首字母为小写
				//LionUser resultUser = gson.fromJson(result, LionUser.class);
				return resultUser;
			}
			else
				throw new HttpResponseException(statusCode,result);
		}
		catch (Exception ex){
			throw ex;
		}

	}
}