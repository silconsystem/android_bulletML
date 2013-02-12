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


import jp.gr.java_conf.abagames.bulletml_demo.BulletmlUtil;
import jp.gr.java_conf.abagames.bulletml_demo.noiz.GameManager;
import jp.gr.java_conf.abagames.bulletml_demo.noiz.Sample;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.netthreads.android.bulletml.control.ControlCanvasSurfaceView;
import com.netthreads.android.bulletml.data.StateData;
import com.netthreads.android.bulletml.graphics.RendererCanvas;

/**
 * Main BulletML View.
 * 
 */
public class CanvasActivity extends Activity
{
    private ControlCanvasSurfaceView surfaceView = null;
    
    private GameManager gameManager = null;

    private DisplayMetrics displayMetrics = null;

    private String sampleName = "";

    private StateData state = null;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // ---------------------------------------------------------------
        // Model
        // ---------------------------------------------------------------
        // If no data was given in the intent (because we were started
        // as a MAIN activity), then use our default content provider.
        Intent intent = getIntent();

        if (intent!=null)
        {
            Bundle extras = getIntent().getExtras();
            
            if (extras != null) 
            {
            	// Extract the region name and url from activity bundle
        		sampleName = (String)extras.getSerializable(BulletMLListActivity.DATA_NAME);
            }
        }
        else
        {
        	int index = ApplicationPreferences.getInstance(this).getSample();
        	
            sampleName = Sample.samples[index].name;
        }
        
    	// ---------------------------------------------------------------
        // View
        // ---------------------------------------------------------------
        setContentView(R.layout.main);

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        
        state = new StateData();

        gameManager = new GameManager(this, state, displayMetrics.widthPixels, displayMetrics.heightPixels);
        
        surfaceView = new ControlCanvasSurfaceView(this, state);
        
        int lineWidth = ApplicationPreferences.getInstance(this).getLineWidth();
        
        RendererCanvas spriteRenderer = new RendererCanvas(gameManager, lineWidth, displayWidth, displayHeight);

        // ---------------------------------------------------------------
        // Initialise view state.
        // ---------------------------------------------------------------
        initialiseState(displayMetrics);
        
        // ---------------------------------------------------------------
        // Renderer
        // ---------------------------------------------------------------
        
        surfaceView.setRenderer(spriteRenderer);

        setContentView(surfaceView);
    }
    
    /**
     * Start view.
     * 
     */
    @Override
    protected void onStart() 
    {
    	super.onStart();
    	
    	startBullets();
    }
    
    /**
     * Start BulletML view elements.
     * 
     */
    private void startBullets()
    {
        gameManager.initBullets();
        
        gameManager.loadBulletML(sampleName);
        
        gameManager.setHVStat(0);
        
        BulletmlUtil.setRank(ApplicationPreferences.getInstance(this).getRankValue());
    }
    
    /**
     * Miscellaneous setup.
     *
     * @param The display metrics.
     * 
     */
    private void initialiseState(DisplayMetrics displayMetrics)
    {
        // Pick a initial location for this sprite.
        state.controlX = displayMetrics.widthPixels/2; 
        state.controlY = displayMetrics.heightPixels/2;
    }
    
}
