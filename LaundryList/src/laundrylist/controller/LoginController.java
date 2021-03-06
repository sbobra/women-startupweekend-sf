package laundrylist.controller;

import laundrylist.model.JSONController;
import laundrylist.model.State;
import laundrylist.model.JSONController.JSONPost;
import laundrylist.model.JSONController.JSONRequest;
import laundrylist.view.ExploreActivity;
import laundrylist.view.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class LoginController extends Controller {
	private LoginActivity view;
	private JSONController json;

	public LoginController(LoginActivity activity) {
		this.view = activity;
		json = new JSONController(this);

		// Object[] data = new Object[3];
		//
		// data[0] = "sam.bobra@gmail.com";
		// data[1] = "Samantha Bobra";
		// data[2] = "hi";
		// json.post(JSONPost.NEWACCOUNT, data);
	}

	public void onNewUserPressed(String name, String username, String password) {
		Object[] data = new Object[3];

		data[0] = username;
		data[1] = name;
		data[2] = password;
		State.getInstance().setName(name);
		json.post(JSONPost.NEWACCOUNT, data);

	}

	public void onLoginPressed(String username, String password) {
		Object[] data = new Object[2];

		// data[0] = "jason.haury@gmail.com";
		// data[1] = "asdf";
		if (State.getInstance().DEBUG) {
			JSONObject j = new JSONObject();
			try {
				j.put("id", 1);
				j.put("name", "Bob saggat");
				onJSONReceived(j);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			data[0] = username;
			data[1] = password;
			json.request(JSONRequest.LOGIN, data);
		}
	}

	@Override
	public void onJSONReceived(JSONObject jsonObject) {
		String id = "";
		String name = "";
		try {
			id = jsonObject.getString("id");
			name = jsonObject.getString("name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(view, "Error", duration);
			toast.show();
		}
		Log.i(LoginController.class.getName(), id);
		Log.i(LoginController.class.getName(), name);
		State.getInstance().setID(Integer.valueOf(id));
		State.getInstance().setName(name);

		if (!name.equals("")) {
			Toast.makeText(view.getApplicationContext(), "Welcome, " + State.getInstance().getName(), Toast.LENGTH_SHORT).show();
			view.startActivity(new Intent(view, ExploreActivity.class));
			view.finish();
		} else {
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(view, "Error", duration);
			toast.show();
		}
	}

	@Override
	public void onPostResponded(String s) {
		String id = "";
		try {
			JSONObject jsonObject = new JSONObject(s);
			id = jsonObject.getString("id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(view, "Error", duration);
			toast.show();
		}
		Log.i(LoginController.class.getName(), id);
		State.getInstance().setID(Integer.valueOf(id));
		Toast.makeText(view.getApplicationContext(), "Welcome, " + State.getInstance().getName(), Toast.LENGTH_SHORT).show();
		view.startActivity(new Intent(view, ExploreActivity.class));
		view.finish();
	}
}
