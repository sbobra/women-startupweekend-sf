package laundrylist.controller;

import laundrylist.model.Goal;
import laundrylist.model.JSONController;
import laundrylist.model.JSONController.JSONRequest;
import laundrylist.model.State;
import laundrylist.view.CompleteFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class CompleteFragmentController extends Controller {
	private CompleteFragment fragment;
	private JSONController json;

	public CompleteFragmentController(CompleteFragment completeFragment) {
		this.fragment = completeFragment;
		json = new JSONController(this);

	}

	@Override
	public void onJSONReceived(JSONObject jsonObject) {
		String mission = "";
		String daysLeft = "";
		String security = "";
		String category = "";
		String ownerId = "";
		String ownerName = "";
		try {
			mission = jsonObject.getString("mission");
			ownerName = jsonObject.getString("ownerName");
			security = jsonObject.getString("security");
			daysLeft = jsonObject.getString("daysLeft");
			category = jsonObject.getString("category");
			ownerId = jsonObject.getString("ownerId");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("CompleteFragmentController", "Data: " + mission + ownerName
				+ security + daysLeft + category + ownerId);

	}

	@Override
	public void onJSONArrayReceived(JSONArray jsonArray) {
		fragment.clearTable();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject;
			try {
				jsonObject = jsonArray.getJSONObject(i);
				String mission = "";
				String daysLeft = "";
				String security = "";
				String category = "";
				String ownerId = "";
				String duration = "";
				String isComplete = "";
				String photo = "";
				String timestamp = "";

				mission = jsonObject.getString("mission");
				security = jsonObject.getString("security");
				daysLeft = jsonObject.getString("dueDate");
				category = jsonObject.getString("category");
				duration = jsonObject.getString("duration");
				ownerId = jsonObject.getString("ownerId");
				isComplete = jsonObject.getString("isComplete");
				photo = jsonObject.getString("photo");
				timestamp = jsonObject.getString("timestamp");
				
				Goal g = new Goal();
				g.setMission(mission);
				g.setSecurity(security);
				g.setDaysLeft(daysLeft);
				g.setCategory(category);
				g.setDuration(0);//FIX ME
				g.setOwnerId(ownerId);
				g.setIsComplete(isComplete);
				g.setURL(photo);
				g.setTimestamp(timestamp);
				

				Log.i("CompleteFragmentController", "Data: " + mission + security
						+ daysLeft + category + duration + ownerId + isComplete
						+ photo + timestamp);
				fragment.populate(g);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	public void onRefresh() {
		Object[] data = new Object[2];
		data[0] = State.getInstance().getID();
		data[1] = 1;// incomplete goals
		json.request(JSONRequest.MYGOALS, data);
	}
}
