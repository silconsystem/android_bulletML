package com.netthreads.android.bulletml.control;

import android.content.Context;
import android.view.MotionEvent;

import com.netthreads.android.bulletml.data.StateData;
import com.netthreads.android.opengl.GLSurfaceView;


/**
 * GL Surface view with input handling. 
 * 
 *
 */
public class ControlGLSurfaceView extends GLSurfaceView 
{
	private final static long SLEEP_TIME =100L;
	
	private StateData state = null;
	
    public ControlGLSurfaceView(Context context, StateData stateData) 
    {
        super(context);
        
        this.state = stateData;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) 
    {
		try
		{
			// -----------------------------------------------------------
			// We need to relinquish some CPU time to the draw thread. To 
			// do this we have a wee sleep here which will let the 
			// scheduler switch back to the draw thread. Otherwise the
			// UI thread and the draw thread compete for CPU and the FPS 
			// rate drops.
			// -----------------------------------------------------------
			Thread.sleep(SLEEP_TIME);

			queueEvent(new Runnable()
			{
				public void run()
				{
					state.controlX = event.getX();
					state.controlY = event.getY();
	
					if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE)
					{
						state.touched = true;
					}
					else
					{
						state.touched = false;
					}
				}
			});
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		return true;
    }

}