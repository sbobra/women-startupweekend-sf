/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package laundrylist.view;

import laundrylist.controller.ExploreFragmentController;
import laundrylist.controller.OnRefreshListener;
import laundrylist.model.Goal;

import com.example.laundrylist.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy
 * title indicating the page number, along with some dummy text.
 * 
 * <p>
 * This class is used by the {@link CardFlipActivity} and
 * {@link ScreenSlideActivity} samples.
 * </p>
 */
public class ExploreFragment extends Fragment implements OnRefreshListener {
	/**
	 * The argument key for the page number this fragment represents.
	 */
	public static final String ARG_PAGE = "page";
	public ExploreFragmentController controller = null;
	public ViewGroup rootView;

	/**
	 * The fragment's page number, which is set to the argument value for
	 * {@link #ARG_PAGE}.
	 */
	private int mPageNumber;

	/**
	 * Factory method for this fragment class. Constructs a new fragment for the
	 * given page number.
	 */
	public static ExploreFragment create(int pageNumber) {
		ExploreFragment fragment = new ExploreFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, pageNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt(ARG_PAGE);
		if (controller == null)
			controller = new ExploreFragmentController(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout containing a title and body text.
		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.fragment_explore, container, false);

		this.rootView = rootView;
		return rootView;
	}

	public void populate(final Goal g) {
		if (rootView != null) {
			Log.i("ExploreFragment", "populating");
			TableLayout table = (TableLayout) rootView
					.findViewById(R.id.tableLayout);
			// Inflate your row "template" and fill out the fields.
			TableRow row = (TableRow) LayoutInflater
					.from(rootView.getContext()).inflate(R.layout.explore_row,
							null);
			((TextView) row.findViewById(R.id.mission)).setText(g.getMission());
			((TextView) row.findViewById(R.id.category)).setText("category: "+g
					.getCategory());
			((TextView) row.findViewById(R.id.security)).setText("shared with: "+g
					.getSecurity());
			((TextView) row.findViewById(R.id.duration)).setText("duration: "+g
					.getDuration());
			((TextView) row.findViewById(R.id.daysLeft)).setText("due in: "+g
					.getDaysLeft());
			
			if (Integer.valueOf(g.getIsAd()) == 1) {
				((ImageView) row.findViewById(R.id.goal_cover)).setImageResource(R.drawable.nike);
			}

			table.addView(row);
			TableRow spacer = (TableRow) LayoutInflater.from(
					rootView.getContext()).inflate(R.layout.spacer_row, null);
			table.addView(spacer);
			row.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Log.i("ExploreFragment", "Goal pressed! Goal: " + g.getId());
					Intent intent = new Intent(rootView.getContext(), GoalFocusActivity.class);
					Bundle bundle = new Bundle();
					bundle.putBoolean("mygoal", false);
					bundle.putString("mission", g.getMission());
					bundle.putString("id", g.getId());
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});

			table.requestLayout();
		}
	}

	public void clearTable() {
		if (rootView != null) {
			TableLayout table = (TableLayout) rootView
					.findViewById(R.id.tableLayout);
			int count = table.getChildCount();
			for (int i = 0; i < count; i++) {
				View child = table.getChildAt(i);
				if (child instanceof TableRow)
					((ViewGroup) child).removeAllViews();
			}
		}

	}

	/**
	 * Returns the page number represented by this fragment object.
	 */
	public int getPageNumber() {
		return mPageNumber;
	}

	@Override
	public void onRefresh() {
		// if (controller != null)
		// controller.onRefresh();
		// else
		// controller = new ListFragmentController(this);
		// controller.onRefresh();

	}

	@Override
	public void onResume() {
		super.onResume();
		if (controller != null)
			controller.onRefresh();
		else
			controller = new ExploreFragmentController(this);
		controller.onRefresh();
	}
}
