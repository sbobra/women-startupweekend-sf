package laundrylist.view;

import laundrylist.model.State;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.laundrylist.R;

public class GoalActivity1 extends Activity {
	private ImageView doButton, seeButton, learnButton;
	private EditText goalMission;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_goal1);

		doButton = (ImageView) findViewById(R.id.doButton);
		doButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				State.getInstance().getGoal().setCategory("do");
				next();
				
			}
		});
		seeButton = (ImageView) findViewById(R.id.seeButton);
		seeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				State.getInstance().getGoal().setCategory("see");
				next();
			}
		});
		learnButton = (ImageView) findViewById(R.id.learnButton);
		learnButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				State.getInstance().getGoal().setCategory("learn");
				next();
			}
		});
		
		goalMission = (EditText) findViewById(R.id.goalTextBox);
	}
	
	public void next() {
		State.getInstance().getGoal().setMission(goalMission.getText().toString());
		startActivity(new Intent(this, GoalActivity2.class));
		finish();
	}

}
