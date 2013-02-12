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

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

import com.netthreads.android.bulletml.widget.SeekBarPreference;


/**
 * Preferences settings activity.
 *
 *
 */
public class PreferencesActivity extends PreferenceActivity
{
	private SeekBarPreference rankPref = null;
	private SeekBarPreference lineWidthPref = null;
	
    /*
     * View Create
     *
     * (non-Javadoc)
     * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Set the name of the preferences this PreferenceActivity will manage
        getPreferenceManager().setSharedPreferencesName(ApplicationPreferences.NAME);

        // Create and note geocode pref screen
        setPreferenceScreen(createPreferenceScreen());
    }
    
    /**
     * On resume.
     *
     */
    @Override
    protected void onResume()
    {
        super.onResume();

        // We can hook listeners for changes in here
    }

    /**
     * On pause we uninstall changed listener.
     *
     */
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    
    /**
     * Build preference view.
     *
     * (*) Annoyingly we have to do this. If you miss it out then the preference title will
     * not get intialised properly. The reason for this is the at the control requires a 
     * value for the default to exist.
     * 
     * @return view
     */
    private PreferenceScreen createPreferenceScreen()
    {
        PreferenceScreen root = getPreferenceManager().createPreferenceScreen(this);

        ApplicationPreferences preferences = ApplicationPreferences.getInstance(this);
        
        // ---------------------------------------------------------------
        // Preferences Category 
        // ---------------------------------------------------------------
        PreferenceCategory inlinePrefCat = new PreferenceCategory(this);
        inlinePrefCat.setTitle(R.string.data_preferences);
        root.addPreference(inlinePrefCat);

        // Difficulty slider
        rankPref = new SeekBarPreference(this);
        
        rankPref.setKey(ApplicationPreferences.RANK_TEXT);
        rankPref.setTitle(ApplicationPreferences.RANK_TEXT);
        rankPref.setOffset(1);
        rankPref.setMax(ApplicationPreferences.RANK_MAX);
        rankPref.setProgress(preferences.getRank()); // See note (*)

        inlinePrefCat.addPreference(rankPref);
        
        // Line width slider
        lineWidthPref = new SeekBarPreference(this);
        
        lineWidthPref.setKey(ApplicationPreferences.LINE_WIDTH_TEXT);
        lineWidthPref.setTitle(ApplicationPreferences.LINE_WIDTH_TEXT);
        lineWidthPref.setOffset(1);
        lineWidthPref.setMax(ApplicationPreferences.LINE_WIDTH_MAX);
        lineWidthPref.setProgress(preferences.getLineWidth()); // See note (*)
        
        inlinePrefCat.addPreference(lineWidthPref);

        // OpenGL renderer
        boolean rendererChecked = preferences.getOpenGL();
        CheckBoxPreference rendererPref = new CheckBoxPreference(this);
        rendererPref.setKey(ApplicationPreferences.OPENGL_RENDER_TEXT);
        rendererPref.setTitle(ApplicationPreferences.OPENGL_RENDER_TEXT);
        rendererPref.setChecked(rendererChecked);
        
        inlinePrefCat.addPreference(rendererPref);
        
        // Profile data switch
        boolean profilerChecked = preferences.getShowProfile();
        CheckBoxPreference profilerPref = new CheckBoxPreference(this);
        profilerPref.setKey(ApplicationPreferences.SHOW_PROFILE_TEXT);
        profilerPref.setTitle(ApplicationPreferences.SHOW_PROFILE_TEXT);
        profilerPref.setChecked(profilerChecked);
        
        inlinePrefCat.addPreference(profilerPref);
        
        return root;
    }
    
}
