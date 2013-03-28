package com.netthreads.android.bulletml;

import jp.gr.java_conf.abagames.bulletml_demo.BulletmlUtil;
import jp.gr.java_conf.abagames.bulletml_demo.noiz.GameManager;
import jp.gr.java_conf.abagames.bulletml_demo.noiz.Sample;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.netthreads.android.bulletml.control.ControlGLSurfaceView;
import com.netthreads.android.bulletml.data.StateData;
import com.netthreads.android.bulletml.graphics.RendererGL;

/**
 * Ship sprite application.
 *
 */
public class GLActivity extends Activity
{
    public static final int SCREEN_OFFSET = 50; // GL screen height always -50 out.
    
    private ControlGLSurfaceView glSurfaceView = null;
    
    private StateData state = null;
    
    private GameManager gameManager = null;

    private DisplayMetrics displayMetrics = null;

    private String sampleName = "";
    
    /**
     * Construct elements.
     * 
     */
    public GLActivity() 
    {
    	state  = new StateData();
	}
    
	/**
	 * Create main view elements
	 *
	 */
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
        int displayHeight = displayMetrics.heightPixels-SCREEN_OFFSET;

        state = new StateData();

        gameManager = new GameManager(this, state, displayWidth, displayHeight);
        
        glSurfaceView = new ControlGLSurfaceView(this, state);

        int lineWidth = ApplicationPreferences.getInstance(this).getLineWidth();
        
        RendererGL renderer = new RendererGL(this, gameManager, lineWidth, displayWidth, displayHeight);

        glSurfaceView.setEGLConfigChooser(renderer);
        
        // ---------------------------------------------------------------
        // Initialise view state.
        // ---------------------------------------------------------------
        initialiseState(displayMetrics);

        // ---------------------------------------------------------------
        // View startup
        // ---------------------------------------------------------------

        // Assign renderer to surface
        glSurfaceView.setRenderer(renderer);

        // GL surface will run processor.
        setContentView(glSurfaceView);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        
        glSurfaceView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        
        glSurfaceView.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
    	glSurfaceView.onKeyDown(keyCode, event);
    	
    	return super.onKeyDown(keyCode, event); 
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) 
    {
    	glSurfaceView.onKeyUp(keyCode, event);
    	
    	return super.onKeyUp(keyCode, event); 
    }
    
    @Override
    public boolean onTrackballEvent(MotionEvent event) 
    {
    	glSurfaceView.onTrackballEvent(event);
    	
    	return super.onTrackballEvent(event); 
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