package laundrylist.controller;

import laundrylist.model.JSONController;
import laundrylist.model.JSONController.JSONRequest;
import laundrylist.model.State;
import laundrylist.view.ListFragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class ListFragmentController extends Controller{
	private ListFragment fragment;
	private JSONController json;

	public ListFragmentController(ListFragment listFragment) {
		this.fragment = listFragment;
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
		Log.i("ListFragmentController", "Data: " + mission + ownerName + security + daysLeft + category + ownerId);

	}

	public void onRefresh() {
		Object[] data = new Object[2];
		data[0] = State.getInstance().getID();
		data[1] = 0;//incomplete goals
		json.request(JSONRequest.MYGOALS, data);
	}
}
