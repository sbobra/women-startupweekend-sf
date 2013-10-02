package laundrylist.view;

import laundrylist.controller.GoalFocusController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laundrylist.R;

public class GoalFocusActivity extends Activity {
	private GoalFocusController controller;
//	private Button loginButton;
//	private Button newUserButton;
//	private EditText usernameTextBox;
//	private EditText passwordTextBox;
//	private EditText nameTextBox;
//	private LinearLayout nameLayout;
	boolean myGoal = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_goalfocus);
		
		controller = new GoalFocusController(this);
		Bundle b = getIntent().getExtras();
		Log.i("GoalFocusActivity", b.getString("mission"));
		final String id = b.getString("id");
		myGoal = b.getBoolean("mygoal");
		if (!myGoal) {
			((ImageView) findViewById(R.id.complete)).setVisibility(View.GONE);
		}
		((TextView) findViewById(R.id.mission)).setText(b.getString("mission"));
		ImageView completed = ((ImageView) findViewById(R.id.complete));
		completed.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.i("ListFragment", "Goal completed!");
				controller.completed(id);
			}
		});
//
//		loginButton = (Button) findViewById(R.id.loginButton);
//		loginButton.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				if (nameLayout.getVisibility() == View.VISIBLE) {
//					controller.onNewUserPressed(nameTextBox.getText()
//							.toString(), usernameTextBox.getText().toString(),
//							passwordTextBox.getText().toString());
//				} else {
//					controller.onLoginPressed(usernameTextBox.getText()
//							.toString(), passwordTextBox.getText().toString());
//				}
//			}
//		});
//		newUserButton = (Button) findViewById(R.id.newAcctButton);
//		newUserButton.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				onNewUserPressed();
//				newUserButton.setVisibility(View.GONE);
//			}
//		});
//
//		usernameTextBox = (EditText) findViewById(R.id.usernameTextBox);
//		passwordTextBox = (EditText) findViewById(R.id.passwordTextBox);
//		nameTextBox = (EditText) findViewById(R.id.nameTextBox);
//		nameLayout = (LinearLayout) findViewById(R.id.nameLayout);

		/*
		 * Object[] data = new Object[2]; data[0] = "jason.haury@gmail.com";
		 * data[1] = "asdf";
		 * State.getInstance().getJSONController().request(JSONRequest.LOGIN,
		 * data);
		 */
	}
	
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(this, ExploreActivity.class);
		startActivity(intent);
	}

}
