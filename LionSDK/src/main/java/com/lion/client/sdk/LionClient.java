package com.lion.client.sdk;

import com.lion.client.sdk.common.*;
import com.lion.client.sdk.models.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

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

	private String DefaultAPIUri = "http://lion-test.devcloudx.com/api";

	public LionClient(String sdkKey)
	{
		set_sdkKey(sdkKey);
	}

	public LionClient(String sdkKey, String apiUri)
	{
		set_sdkKey(sdkKey);
		if(!apiUri.isEmpty())
			DefaultAPIUri = apiUri;
	}

	public final boolean BoolVariation(String key) throws IOException {
		String result = "false";
		StringBuilder stringBuilder = new StringBuilder();
		String flagStatusAPI = String.format("%1$s/FlagStatuses/%2$s", DefaultAPIUri, key);
		URL url = new URL(flagStatusAPI);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(2000);
		String headerString = "SDKKey "+get_sdkKey();
		conn.setRequestProperty("Authorization", headerString);
		if (conn.getResponseCode() == 200) {
			InputStream in = conn.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			result = stringBuilder.toString();
			bufferedReader.close();
			in.close();
		}
		return Boolean.parseBoolean(result);
	}

	/**
	 variate the bool type feature flag

	 @param key feature flag key
	 @param user the business user which will sent to the lion service
	 //@param defaultValue the default return value when there are any kind of exception occurred
	 @return
	*/

	public final boolean BoolVariation(String key, LionUser user) throws IOException {
		return BoolVariation(key, user, false);
	}

	public final boolean BoolVariation(String key, LionUser user, boolean defaultValue) throws IOException {
		try
		{
			String result = "";
			StringBuilder stringBuilder = new StringBuilder();
			LionUser feedbackUser = new LionUser(user.getKey());
			//send user request event and save user
			if (!StringHelper.isNullOrEmpty(user.getKey()))
			{
				feedbackUser = SendFlagRequestEvent(key, user);//TODO: hashmap convert
			}
			String requestUrl = String.format("%1$s/Flags/%2$s", DefaultAPIUri, key);
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(2000);
			String headerString = "SDKKey "+get_sdkKey();
			conn.setRequestProperty("Authorization", headerString);
			if (conn.getResponseCode() == 200) {
				InputStream in = conn.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line);
				}
				result = stringBuilder.toString();
				bufferedReader.close();
				in.close();
			}
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

	private LionUser SendFlagRequestEvent(String key, LionUser user) throws IOException {
		try{
			String result = "{}";
			StringBuilder stringBuilder = new StringBuilder();
			String userAPI = String.format("%1$s/User", DefaultAPIUri);
			URL url = new URL(userAPI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(2000);
			conn.setReadTimeout(2000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/json");
			String headerString = "SDKKey "+get_sdkKey();
			conn.setRequestProperty("Authorization", headerString);
			String userJson = JSONHelper.toJSON(user);
			byte[] jsonBytes = userJson.getBytes();
			conn.setRequestProperty("Content-Length", String.valueOf(jsonBytes.length));
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(userJson.getBytes());
			outputStream.flush();
			outputStream.close();
			if (conn.getResponseCode() == 200) {
				InputStream in = conn.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line);
				}
				result = stringBuilder.toString();
				bufferedReader.close();
				in.close();
			}
			LionUser resultUser = JSONHelper.parseObject(result, LionUser.class); //JSONHelper无法序列化子对象HashMap
			//Gson gson = new Gson();//属性名为大写，Gson无法反序列化成对象，需要改属性名首字母为小写
			//LionUser resultUser = gson.fromJson(result, LionUser.class);
			return resultUser;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}