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

package com.netthreads.android.bulletml.graphics;

import jp.gr.java_conf.abagames.bulletml_demo.noiz.GameManager;
import android.graphics.Canvas;

/**
 * Renderer for game surface view.
 * 
 */
public class RendererCanvas implements CanvasSurfaceView.Renderer 
{
	GameManager gameManager = null;
	private ScreenCanvas screenCanvas = null;
	
	/**
	 * Construct.
	 * 
	 * @param Manager object.
	 * 
	 */
	public RendererCanvas(GameManager gameManager, int lineWidth, int screenWidth, int screenHeight) 
	{
		this.gameManager = gameManager;
		
		screenCanvas = new ScreenCanvas(lineWidth, screenWidth, screenHeight);
	}
	
	@Override
	public void drawFrame(Canvas canvas) 
	{
		screenCanvas.setCanvas(canvas);
		
		// Update
        gameManager.update();
        
        gameManager.draw(screenCanvas);
	}

	@Override
	public void sizeChanged(int width, int height) 
	{
		// Nowt
	}


}
