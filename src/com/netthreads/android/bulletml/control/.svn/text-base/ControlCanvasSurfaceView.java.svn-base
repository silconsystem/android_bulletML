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
package com.netthreads.android.bulletml.control;

import android.content.Context;
import android.view.MotionEvent;

import com.netthreads.android.bulletml.data.StateData;
import com.netthreads.android.bulletml.graphics.CanvasSurfaceView;

/**
 * Canvas Surface view with input handling. 
 *
 */
public class ControlCanvasSurfaceView extends CanvasSurfaceView 
{
	private final static long SLEEP_TIME =100L;
	
	private StateData state = null;
	
    public ControlCanvasSurfaceView(Context context, StateData stateData) 
    {
        super(context);
        
        this.state = stateData;
    }


    /**
     * Triggered on touch event. Note we defer the processing by queueing it.
     * 
     * @param The touch event.
     */
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