package laundrylist.view;

import laundrylist.controller.Goal3Controller;
import laundrylist.model.State;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.laundrylist.R;

public class GoalActivity3 extends Activity {
	private ImageView daysButton, monthsButton, lifetimeButton;
	private Goal3Controller controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_goal3);
		controller = new Goal3Controller(this);

		daysButton = (ImageView) findViewById(R.id.daysButton);
		daysButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				State.getInstance().getGoal().setDuration(1);
				next();
				
			}
		});
		monthsButton = (ImageView) findViewById(R.id.monthsButton);
		monthsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				State.getInstance().getGoal().setDuration(30);
				next();
			}
		});
		lifetimeButton = (ImageView) findViewById(R.id.lifetimeButton);
//		lifetimeButton.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				State.getInstance().getGoal().setDuration(0);
//				next();
//			}
//		});
		
	}
	
	public void next() {
		controller.next();
	}

}
