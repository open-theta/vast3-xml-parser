package net.adsplay.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpGetUtil {

	static class HttpGet {
		OkHttpClient client = new OkHttpClient();
		String url;

		public HttpGet(String url) {
			this.url = url;
		}

		public String run()  {
			try {
				Request request = new Request.Builder().url(url).build();
				Response response = client.newCall(request).execute();
				return response.body().string();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}

	public static String get(String url) {
		return new HttpGet(url).run();
	}
}
