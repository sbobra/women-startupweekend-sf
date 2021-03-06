package laundrylist.view;

import laundrylist.controller.LoginController;
import laundrylist.model.JSONController.JSONRequest;
import laundrylist.model.State;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.laundrylist.R;

public class LoginActivity extends Activity {
	private LoginController controller;
	private Button loginButton;
	private Button newUserButton;
	private EditText usernameTextBox;
	private EditText passwordTextBox;
	private EditText nameTextBox;
	private LinearLayout nameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login);
		controller = new LoginController(this);

		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (nameLayout.getVisibility() == View.VISIBLE) {
					controller.onNewUserPressed(nameTextBox.getText()
							.toString(), usernameTextBox.getText().toString(),
							passwordTextBox.getText().toString());
				} else {
					controller.onLoginPressed(usernameTextBox.getText()
							.toString(), passwordTextBox.getText().toString());
				}
			}
		});
		newUserButton = (Button) findViewById(R.id.newAcctButton);
		newUserButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onNewUserPressed();
				newUserButton.setVisibility(View.GONE);
			}
		});

		usernameTextBox = (EditText) findViewById(R.id.usernameTextBox);
		passwordTextBox = (EditText) findViewById(R.id.passwordTextBox);
		nameTextBox = (EditText) findViewById(R.id.nameTextBox);
		nameLayout = (LinearLayout) findViewById(R.id.nameLayout);

		/*
		 * Object[] data = new Object[2]; data[0] = "jason.haury@gmail.com";
		 * data[1] = "asdf";
		 * State.getInstance().getJSONController().request(JSONRequest.LOGIN,
		 * data);
		 */
	}

	public void onNewUserPressed() {
		nameLayout.setVisibility(View.VISIBLE);
	}
}
