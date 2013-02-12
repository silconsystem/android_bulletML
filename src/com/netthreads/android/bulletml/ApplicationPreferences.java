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

import jp.gr.java_conf.abagames.bulletml_demo.noiz.Sample;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class ApplicationPreferences 
{
	public static final String NAME = "preferences";
	
	public static final String RANK_TEXT = "Difficulty";
	public static final int RANK_DEFAULT = 10;
	public static final int RANK_MAX = 10;
	
	public static final String LINE_WIDTH_TEXT = "Line Width";
	public static final int LINE_WIDTH_DEFAULT = 1;
	public static final int LINE_WIDTH_MAX = 10;

	public static final String SHOW_PROFILE_TEXT = "Show Profile";
	public static final boolean SHOW_PROFILE_DEFAULT = false;

	public static final String OPENGL_RENDER_TEXT = "OpenGL";
	public static final boolean OPENGL_RENDER_DEFAULT = false;

	public static final String SAMPLE_TEXT = "Samples";
	public static final int SAMPLE_DEFAULT = 5; // 0==template
	
	// We use hex as these are bitmaps
	public static final int UNCHANGED = 0x00;
	public static final int CHANGED_SETTINGS = 0x01;
	
	// Preferences 
	private static ApplicationPreferences instance = null;
    private SharedPreferences settings = null;

    private final float[] difficulty = { 0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f };
    
	/**
	 * Singleton access.
	 * 
	 * @param context
	 * 
	 * @return The preferences object.
	 */
	public static ApplicationPreferences getInstance(Context context)
	{
		if (instance==null)
		{
			instance = new ApplicationPreferences(context);
		}
		
		return instance;
	}

	private ApplicationPreferences(Context context) 
	{
        settings = context.getSharedPreferences(ApplicationPreferences.NAME, Activity.MODE_PRIVATE);
	}

    /**
     * Return Rank selection
     * 
     * @return Rank value
     */
    public int getRank()
    {
		int value = settings.getInt(RANK_TEXT, RANK_DEFAULT);

		return value;
    }

    /**
     * Return Rank value
     * 
     * @return Rank value
     */
    public float getRankValue()
    {
        float value = difficulty[getRank()];

        return value;
    }
    
    /**
     * Set the rank
     *
     * @param The value string
     */
    public void setRank(int value)
    {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(RANK_TEXT, value);
        editor.commit();
    }

    /**
     * Return line width
     * 
     * @return value
     */
    public int getLineWidth()
    {
		int value = settings.getInt(LINE_WIDTH_TEXT, LINE_WIDTH_DEFAULT);

		return value;
    }

    /**
     * Set the line thickness
     *
     * @param The value
     */
    public void setLineWidth(int value)
    {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(LINE_WIDTH_TEXT, value);
        editor.commit();
    }
    
    /**
     * Return Sample
     * 
     * @return Rank value
     */
    public Sample getSampleData()
    {
        Sample sml = Sample.samples[getSample()];
		
		return sml;
    }
    
    /**
     * Return Sample
     * 
     * @return Rank value
     */
    public int getSample()
    {
		int value = settings.getInt(SAMPLE_TEXT, SAMPLE_DEFAULT);

		return value;
    }
    
    /**
     * Set the sample setting
     *
     * @param The value string
     */
    public void setSample(int value)
    {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(SAMPLE_TEXT, value);
        editor.commit();
    } 

    /**
     * Return profiler setting
     * 
     * @return value
     */
    public boolean getShowProfile()
    {
		boolean value = settings.getBoolean(SHOW_PROFILE_TEXT, SHOW_PROFILE_DEFAULT);

		return value;
    }

    /**
     * Set the profiler setting
     *
     * @param The value
     */
    public void setShowProfile(boolean value)
    {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(SHOW_PROFILE_TEXT, value);
        editor.commit();
    }

    /**
     * Return renderer setting
     * 
     * @return value
     */
    public boolean getOpenGL()
    {
		boolean value = settings.getBoolean(OPENGL_RENDER_TEXT, OPENGL_RENDER_DEFAULT);

		return value;
    }

    /**
     * Set the renderer setting
     *
     * @param The value
     */
    public void setOpenGL(boolean value)
    {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(OPENGL_RENDER_TEXT, value);
        editor.commit();
    }    
}
