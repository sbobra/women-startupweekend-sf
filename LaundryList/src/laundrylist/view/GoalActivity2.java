package laundrylist.view;

import laundrylist.model.State;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.laundrylist.R;

public class GoalActivity2 extends Activity {
	private ImageView myselfButton, friendsButton, anyoneButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_goal2);

		myselfButton = (ImageView) findViewById(R.id.myselfButton);
		myselfButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				State.getInstance().getGoal().setSecurity("me");
				next();
				
			}
		});
		friendsButton = (ImageView) findViewById(R.id.friendsButton);
		friendsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				State.getInstance().getGoal().setSecurity("friends");
				next();
			}
		});
		anyoneButton = (ImageView) findViewById(R.id.anyoneButton);
		anyoneButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				State.getInstance().getGoal().setSecurity("anyone");
				next();
			}
		});
		
	}
	
	public void next() {
		startActivity(new Intent(this, GoalActivity3.class));
		finish();
	}

}
