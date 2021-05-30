package com.mizhousoft.commons.okhttp.digest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Test;

import com.mizhousoft.commons.okhttp.AuthenticationCacheInterceptor;
import com.mizhousoft.commons.okhttp.CachingAuthenticatorDecorator;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * DigestAuthenticatorTest
 *
 * @version
 */
public class DigestAuthenticatorTest
{
	@Test
	public void testNative()
	{
		try
		{
			final DigestAuthenticator authenticator = new DigestAuthenticator(new Credentials("username", "test"));

			final Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();
			final OkHttpClient client = new OkHttpClient.Builder()
			        .authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
			        .addInterceptor(new AuthenticationCacheInterceptor(authCache)).build();

			String url = "http://localhost:8080/demo/user.action";
			Request request = new Request.Builder().url(url).get().build();
			Response response = client.newCall(request).execute();

			System.out.println(response.body().string());

			url = "http://localhost:8080/demo/test.action";
			request = new Request.Builder().url(url).get().build();
			response = client.newCall(request).execute();

			System.out.println(response.body().string());

			url = "http://localhost:8080/demo/test.action";
			request = new Request.Builder().url(url).get().build();
			response = client.newCall(request).execute();

			System.out.println(response.body().string());
		}
		catch (Throwable e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testWithCookie()
	{
		try
		{
			final DigestAuthenticator authenticator = new DigestAuthenticator(new Credentials("username", "test"));

			final Map<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();
			final OkHttpClient client = new OkHttpClient.Builder().authenticator(authenticator).cookieJar(new CookieJar()
			{
				@Override
				public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list)
				{
					cookieStore.put(httpUrl.host(), list);
				}

				@Override
				public List<Cookie> loadForRequest(HttpUrl httpUrl)
				{
					List<Cookie> cookies = cookieStore.get(httpUrl.host());
					return cookies != null ? cookies : new ArrayList<Cookie>();
				}
			}).build();

			String url = "http://localhost:8080/demo/user.action";
			Request request = new Request.Builder().url(url).get().build();
			Response response = client.newCall(request).execute();

			System.out.println(response.body().string());

			url = "http://localhost:8080/demo/test.action";
			request = new Request.Builder().url(url).get().build();
			response = client.newCall(request).execute();

			System.out.println(response.body().string());

			url = "http://localhost:8080/demo/test.action";
			request = new Request.Builder().url(url).get().build();
			response = client.newCall(request).execute();

			System.out.println(response.body().string());
		}
		catch (Throwable e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testPost()
	{
		try
		{
			final DigestAuthenticator authenticator = new DigestAuthenticator(new Credentials("test", "test"));

			final Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();
			final OkHttpClient client = new OkHttpClient.Builder()
			        .authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
			        .addInterceptor(new AuthenticationCacheInterceptor(authCache)).build();

			String url = "http://localhost:/tr069/v1/antenna/fetchAntennaSetting.action";

			RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{}");

			Request request = new Request.Builder().url(url).post(body).build();
			Response response = client.newCall(request).execute();

			System.out.println(response.body().string());

			request = new Request.Builder().url(url).post(body).build();
			response = client.newCall(request).execute();

			System.out.println(response.body().string());
		}
		catch (Throwable e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
