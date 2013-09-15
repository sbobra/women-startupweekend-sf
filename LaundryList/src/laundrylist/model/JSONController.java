package laundrylist.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import laundrylist.controller.Controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class JSONController {
	public Controller controller;

	public JSONController(Controller c) {
		controller = c;
	}

	public enum JSONRequest {
		LOGIN;
		public static JSONRequest fromInteger(int x) {
			switch (x) {
			case 0:
				return LOGIN;
			}
			return null;
		}

		public static Integer toInteger(JSONRequest x) {
			switch (x) {
			case LOGIN:
				return 0;
			}
			return null;
		}

	}

	public enum JSONPost {
		NEWACCOUNT;
		public static JSONPost fromInteger(int x) {
			switch (x) {
			case 0:
				return NEWACCOUNT;
			}
			return null;
		}

		public static Integer toInteger(JSONPost x) {
			switch (x) {
			case NEWACCOUNT:
				return 0;
			}
			return null;
		}
	}

	public void request(JSONRequest t, Object[] d) {
		Object[] data = new Object[d.length + 1];
		data[0] = t;
		for (int i = 0; i < d.length; i++) {
			data[i + 1] = d[i];
		}
		new JSONTask().execute(data);
	}

	public void post(JSONPost t, Object[] d) {
		Object[] data = new Object[d.length + 1];
		data[0] = t;
		for (int i = 0; i < d.length; i++) {
			data[i + 1] = d[i];
		}
		new JSONPostTask().execute(data);
	}

	private class JSONTask extends AsyncTask<Object[], Void, String> {

		public String createURL(Object[] d) {
			String baseString = "http://orion.haurytech.com";
			Object[] data = (Object[]) d[0];
			JSONRequest type = (JSONRequest) data[0];
			switch (type) {
			case LOGIN:
				String username = (String) data[1];
				String password = (String) data[2];
				baseString += "/login/";
				baseString += username;
				baseString += "/";
				baseString += password;
				Log.i("JSONController", baseString);
				return baseString;
			}
			return "";

		}

		@Override
		protected String doInBackground(Object[]... params) {
			String URL = createURL(params);
			StringBuilder builder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(URL);
			try {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					Log.e(JSONController.class.toString(),
							"Failed to download file");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return builder.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				controller.onJSONReceived(jsonObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private class JSONPostTask extends AsyncTask<Object[], Void, String> {

		public String getURL(Object[] data) {
			String baseString = "http://orion.haurytech.com";
			JSONPost type = (JSONPost) data[0];
			switch (type) {
			case NEWACCOUNT:
				baseString += "/signup";
				return baseString;
			}
			return "";

		}

		public JSONObject getJSON(Object[] data) {
			JSONPost type = (JSONPost) data[0];
			switch (type) {
			case NEWACCOUNT:
				JSONObject object = new JSONObject();
				try {
					object.put("email", (String) data[1]);
					object.put("name", (String) data[2]);
					object.put("password", (String) data[3]);
					return object;
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;

		}

		public String getString(Object[] data) {
			JSONPost type = (JSONPost) data[0];
			switch (type) {
			case NEWACCOUNT:
				String str = "";
				str += "email=" + (String) data[1] + "&";
				str += "password=" + (String) data[3] + "&";
				str += "name=" + (String) data[2];
				return str;
			}
			return "";
		}

		@Override
		protected String doInBackground(Object[]... params) {
			Object[] data = (Object[]) params[0];
			String URL = getURL(data);
			Log.i("JSONController", URL);
			String str = getString(data);
			Log.i("JSONController", str);
			HttpClient client = new DefaultHttpClient();
			HttpConnectionParams
					.setConnectionTimeout(client.getParams(), 10000); // Timeout
																		// Limit
			HttpResponse response;
			try {
				HttpPost post = new HttpPost(URL);
				StringEntity se = new StringEntity(str);
				se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
						"application/x-www-form-urlencoded"));
				post.setEntity(se);
				response = client.execute(post);

				/* Checking response */
				if (response != null) {
					InputStream in = response.getEntity().getContent(); // Get
																		// the
																		// data
																		// in
																		// the
																		// entity
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			/*
			 * JSONObject jsonObject; try { jsonObject = new JSONObject(result);
			 * Log.i(JSONController.class.getName(),
			 * jsonObject.getString("id"));
			 * Log.i(JSONController.class.getName(),
			 * jsonObject.getString("name")); } catch (JSONException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */

		}
	}

}