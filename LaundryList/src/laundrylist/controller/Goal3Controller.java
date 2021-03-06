package laundrylist.controller;

import laundrylist.model.Goal;
import laundrylist.model.JSONController;
import laundrylist.model.JSONController.JSONPost;
import laundrylist.model.State;
import laundrylist.view.ExploreActivity;
import laundrylist.view.GoalActivity3;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Goal3Controller extends Controller {
	private GoalActivity3 view;
	private JSONController json;

	public Goal3Controller(GoalActivity3 activity) {
		this.view = activity;
		json = new JSONController(this);

		// Object[] data = new Object[3];
		//
		// data[0] = "sam.bobra@gmail.com";
		// data[1] = "Samantha Bobra";
		// data[2] = "hi";
		// json.post(JSONPost.NEWACCOUNT, data);
	}

	public void next() {
		Object[] data = new Object[5];

		data[0] = State.getInstance().getID();
		data[1] = State.getInstance().getGoal().getMission();
		data[2] = State.getInstance().getGoal().getDuration();
		data[3] = State.getInstance().getGoal().getSecurity();
		data[4] = State.getInstance().getGoal().getCategory();
		json.post(JSONPost.NEWGOAL, data);
	}

	@Override
	public void onPostResponded(String s) {
		String id = "";
		Log.i("Goal3Controller", id);
		try {
			JSONObject jsonObject = new JSONObject(s);
			id = jsonObject.getString("id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(view, "Error", duration);
			toast.show();
		}

		//reset goal
		State.getInstance().setGoal(new Goal());
		view.startActivity(new Intent(view, ExploreActivity.class));
		view.finish();
	}
}
