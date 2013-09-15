package laundrylist.controller;

import org.json.JSONException;
import org.json.JSONObject;

import laundrylist.model.JSONController;
import laundrylist.model.JSONController.JSONRequest;
import laundrylist.model.State;
import laundrylist.view.ExploreActivity;
import laundrylist.view.LoginActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class LoginController extends Controller {
	private LoginActivity view;
	private JSONController json;

	public LoginController(LoginActivity activity) {
		this.view = activity;
		json = new JSONController(this);
	}

	public void onLoginPressed(String username, String password) {
		Object[] data = new Object[2];

		// data[0] = "jason.haury@gmail.com";
		// data[1] = "asdf";

		data[0] = username;
		data[1] = password;
		json.request(JSONRequest.LOGIN, data);
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
		
		if (!name.equals("")) {
			view.startActivity(new Intent(view, ExploreActivity.class));
			view.finish();
		} else {
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(view, "Error", duration);
			toast.show();
		}
	}
}
