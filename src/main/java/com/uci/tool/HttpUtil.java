package com.uci.tool;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpUtil {
	public static AsyncHttpClient client = new AsyncHttpClient(); // 瀹炰緥璇濆璞�
	static {
		client.setTimeout(11000); // 璁剧疆閾炬帴瓒呮椂锛屽鏋滀笉璁剧疆锛岄粯璁や负10s
	}

	public static void get(String urlString, AsyncHttpResponseHandler res) // 鐢ㄤ竴涓畬鏁磚rl鑾峰彇涓�釜string瀵硅�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.get(urlString, res);
	}

	public static void post(String urlString, AsyncHttpResponseHandler res) // 鐢ㄤ竴涓畬鏁磚rl鑾峰彇涓�釜string瀵硅�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.post(urlString, res);
		
	}

	public static void get(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) // url閲岄潰甯�?弬鏁�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.get(urlString, params, res);
	}

	public static void post(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) // url閲岄潰甯�?弬鏁�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.post(urlString, params, res);
	}

	public static void get(String urlString, JsonHttpResponseHandler res) // 涓嶅甫鍙傛暟锛岃幏鍙杍son瀵硅薄鎴栬�鏁扮�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.get(urlString, res);
	}

	public static void post(String urlString, JsonHttpResponseHandler res) // 涓嶅甫鍙傛暟锛岃幏鍙杍son瀵硅薄鎴栬�鏁扮�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.post(urlString, res);
	}

	public static void get(String urlString, RequestParams params,
			JsonHttpResponseHandler res) // 甯﹀弬鏁帮紝鑾峰彇json瀵硅薄鎴栬�鏁扮�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.get(urlString, params, res);
	}

	public static void post(String urlString, RequestParams params,
			JsonHttpResponseHandler res) // 甯﹀弬鏁帮紝鑾峰彇json瀵硅薄鎴栬�鏁扮�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.post(urlString, params, res);
	}

	public static void get(String uString, BinaryHttpResponseHandler bHandler) // 涓嬭浇鏁版嵁浣跨敤锛屼細杩斿洖byte鏁版�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.get(uString, bHandler);
	}

	public static void post(String uString, BinaryHttpResponseHandler bHandler) // 涓嬭浇鏁版嵁浣跨敤锛屼細杩斿洖byte鏁版�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.post(uString, bHandler);
	}

	public static void delete(String uString, AsyncHttpResponseHandler handler) // 涓嬭浇鏁版嵁浣跨敤锛屼細杩斿洖byte鏁版�?
	{
		client.addHeader("Authorization", "Bearer " + Constant.TOKEN);
		client.delete(uString, handler);
	}

	public static AsyncHttpClient getClient() {
		return client;
	}
}