package laundrylist.controller;

import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;
import laundrylist.model.JSONController;
import laundrylist.model.JSONController.JSONPost;
import laundrylist.view.GoalFocusActivity;

public class GoalFocusController extends Controller {
	private GoalFocusActivity view;
	private JSONController json;

	public GoalFocusController(GoalFocusActivity activity) {
		this.view = activity;
		json = new JSONController(this);

		// Object[] data = new Object[3];
		//
		// data[0] = "sam.bobra@gmail.com";
		// data[1] = "Samantha Bobra";
		// data[2] = "hi";
		// json.post(JSONPost.NEWACCOUNT, data);
	}
	
	public void completed(String id) {
		Object[] data = new Object[3];

		data[0] = Integer.valueOf(id);
		json.post(JSONPost.GOALCOMPLETED, data);
	}

//	public void onNewUserPressed(String name, String username, String password) {
//		Object[] data = new Object[3];
//
//		data[0] = username;
//		data[1] = name;
//		data[2] = password;
//		State.getInstance().setName(name);
//		json.post(JSONPost.NEWACCOUNT, data);
//
//	}
//
//	public void onLoginPressed(String username, String password) {
//		Object[] data = new Object[2];
//
//		// data[0] = "jason.haury@gmail.com";
//		// data[1] = "asdf";
//		if (State.getInstance().DEBUG) {
//			JSONObject j = new JSONObject();
//			try {
//				j.put("id", 1);
//				j.put("name", "Bob saggat");
//				onJSONReceived(j);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else {
//			data[0] = username;
//			data[1] = password;
//			json.request(JSONRequest.LOGIN, data);
//		}
//	}
//
//	@Override
//	public void onJSONReceived(JSONObject jsonObject) {
//		String id = "";
//		String name = "";
//		try {
//			id = jsonObject.getString("id");
//			name = jsonObject.getString("name");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			int duration = Toast.LENGTH_SHORT;
//			Toast toast = Toast.makeText(view, "Error", duration);
//			toast.show();
//		}
//		Log.i(LoginController.class.getName(), id);
//		Log.i(LoginController.class.getName(), name);
//		State.getInstance().setID(Integer.valueOf(id));
//		State.getInstance().setName(name);
//
//		if (!name.equals("")) {
//			Toast.makeText(view.getApplicationContext(), "Welcome, " + State.getInstance().getName(), Toast.LENGTH_SHORT).show();
//			view.startActivity(new Intent(view, ExploreActivity.class));
//			view.finish();
//		} else {
//			int duration = Toast.LENGTH_SHORT;
//			Toast toast = Toast.makeText(view, "Error", duration);
//			toast.show();
//		}
//	}
//
	@Override
	public void onPostResponded(String s) {
		String id = "";
		try {
			JSONObject jsonObject = new JSONObject(s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(view, "Error", duration);
			toast.show();
		}
		Toast.makeText(view.getApplicationContext(), "Goal updated!", Toast.LENGTH_SHORT).show();
	}
}
