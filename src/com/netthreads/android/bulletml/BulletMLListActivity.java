/**
 * Copyright (C) 2009 Alistair Rutherford, Glasgow, Scotland, UK, www.netthreads.co.uk
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.netthreads.android.bulletml;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;

import android.content.Intent;

import android.os.Bundle;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ListView;

import jp.gr.java_conf.abagames.bulletml_demo.noiz.Sample;

/**
 * BulletML List activity. Creates an adapter onto the sample list which extracts the name and inflates
 * a row for the list.
 *
 * @author Alistair Rutherford
 *
 */
public class BulletMLListActivity extends ListActivity
{
    // Constants
    private static final int GAME_ACTIVITY = Menu.FIRST + 1;
    private static final int REQUEST_SETTINGS_ACTIVITY = Menu.FIRST + 2;
	private static final int RESULTS_DIALOG = Menu.FIRST + 3;
    
    public static final String DATA_NAME = "name";

    // Data
    private SampleMLListAdapter adapter = null;

    private Sample[] list = null;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // ---------------------------------------------------------------
        // Model 
        // ---------------------------------------------------------------

        list = Sample.samples;
        
        // List object adapter
        adapter = new SampleMLListAdapter(this, list);

        // ---------------------------------------------------------------
        // View
        // ---------------------------------------------------------------
        setContentView(R.layout.ml_list);

        setListAdapter(adapter);
    }

    /**
     * Called when activity is destroyed. Stops geocode task if it's running.
     *
     */
    @Override
    protected void onDestroy()
    {
        super.onDestroy();

    }

    /**
     * On list item selected. Set data in intent and launch activity.
     *
     */
    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id)
    {
        Sample item = list[position];

        if (item!=null)
        {
        	Intent launchIntent = null;
        	
        	if (ApplicationPreferences.getInstance(this).getOpenGL())
        	{
        		launchIntent = new Intent().setClass(this, GLActivity.class);
        	}
        	else
        	{
        		launchIntent = new Intent().setClass(this, CanvasActivity.class);
        	}
	        
	        // Add region selection to intent.
	        launchIntent.putExtra(DATA_NAME, adapter.getDescription(item));
	        
	        // Make it a sub-activity so we know when it returns
	        startActivityForResult(launchIntent, GAME_ACTIVITY);
        }
    }
    
	
	/**
	 * Created profile dialog.
	 * 
	 */
	@Override
	protected Dialog onCreateDialog(int id)
	{
		Dialog dialog = null;
		if (id == RESULTS_DIALOG)
		{

			String dummy = "No results yet.";
			CharSequence sequence = dummy.subSequence(0, dummy.length() - 1);
			
			dialog = new AlertDialog.Builder(this).setTitle(R.string.dialog_title).setPositiveButton(R.string.dialog_ok, null).setMessage(sequence).create();
		}
		return dialog;
	}

	/**
	 * Replaces the dummy message in the test results dialog with a string that
	 * describes the actual test results.
	 */
	protected void onPrepareDialog(int id, Dialog dialog)
	{
		if (id == RESULTS_DIALOG)
		{
			// Extract final timing information from the profiler.
			final ProfileRecorder profiler = ProfileRecorder.sSingleton;
			final long frameTime = profiler.getAverageTime(ProfileRecorder.PROFILE_FRAME);
			final long frameMin = profiler.getMinTime(ProfileRecorder.PROFILE_FRAME);
			final long frameMax = profiler.getMaxTime(ProfileRecorder.PROFILE_FRAME);

			final long drawTime = profiler.getAverageTime(ProfileRecorder.PROFILE_DRAW);
			final long drawMin = profiler.getMinTime(ProfileRecorder.PROFILE_DRAW);
			final long drawMax = profiler.getMaxTime(ProfileRecorder.PROFILE_DRAW);

			final long flipTime = profiler.getAverageTime(ProfileRecorder.PROFILE_PAGE_FLIP);
			final long flipMin = profiler.getMinTime(ProfileRecorder.PROFILE_PAGE_FLIP);
			final long flipMax = profiler.getMaxTime(ProfileRecorder.PROFILE_PAGE_FLIP);

			final long simTime = profiler.getAverageTime(ProfileRecorder.PROFILE_SIM);
			final long simMin = profiler.getMinTime(ProfileRecorder.PROFILE_SIM);
			final long simMax = profiler.getMaxTime(ProfileRecorder.PROFILE_SIM);

			final float fps = frameTime > 0 ? 1000.0f / frameTime : 0.0f;

			String result = "Frame: " + frameTime + "ms (" + fps + " fps)\n" + "\t\tMin: " + frameMin + "ms\t\tMax: "
					+ frameMax + "\n" + "Draw: " + drawTime + "ms\n" + "\t\tMin: " + drawMin + "ms\t\tMax: " + drawMax
					+ "\n" + "Page Flip: " + flipTime + "ms\n" + "\t\tMin: " + flipMin + "ms\t\tMax: " + flipMax + "\n"
					+ "Sim: " + simTime + "ms\n" + "\t\tMin: " + simMin + "ms\t\tMax: " + simMax + "\n";
			CharSequence sequence = result.subSequence(0, result.length() - 1);
			AlertDialog alertDialog = (AlertDialog) dialog;
			alertDialog.setMessage(sequence);
		}
	}
	
    /**
     * Executed when a sub-activity (e.g. preferences) returns.
     * 
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    {
        super.onActivityResult(requestCode, resultCode, data);
     
		// If returning from game screen we check to see if the profiler dialog should be shown
		if (requestCode==GAME_ACTIVITY)
		{
			if (ApplicationPreferences.getInstance(this).getShowProfile())
			{
				showDialog(RESULTS_DIALOG);
			}
		}
    }
    
    /**
     * Create options menu.
     * 
     * @param The activity context menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        MenuInflater inflater = getMenuInflater();
        
        inflater.inflate(R.menu.ml_list_activity_menu, menu);
        
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * On options item.
     *
     * @param The selected item.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.ml_list_show_settings:
                showSettings();
                break;
            case R.id.ml_list_show_help:
                showHelp();
                break;
            case R.id.ml_list_show_about:
                showAbout();
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * Show settings view
     * 
     */
    public void showSettings()
    {
        Intent launchPreferencesIntent = new Intent().setClass(this, PreferencesActivity.class);
        
        // Make it a sub-activity so we know when it returns
        startActivityForResult(launchPreferencesIntent, REQUEST_SETTINGS_ACTIVITY);
    }

    /**
     * Show help activity.
     *
     */
    public void showHelp()
    {
        // Launch activity
        startActivity(new Intent(this, HelpActivity.class));
    }    

    /**
     * Show about.
     * 
     */
    public void showAbout()
    {
        // Launch activity
        startActivity(new Intent(this, AboutActivity.class));
    }
 
}
