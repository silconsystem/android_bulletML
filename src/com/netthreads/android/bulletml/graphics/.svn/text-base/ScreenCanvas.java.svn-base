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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Implements Canvas screen drawing routines.
 *
 */
public class ScreenCanvas implements IScreen
{
	private Canvas surface = null;

	private Paint paint = null;

	private float lineWidth = 1.0f;

	private int screenWidth = 0;
	private int screenHeight = 0;
	
	public ScreenCanvas(int lineWidth, int screenWidth, int screenHeight)
	{
		this.lineWidth = lineWidth;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(this.lineWidth);
	}

	/**
	 * Clear the screen.
	 * 
	 */
	@Override
	public void clear()
	{
    	surface.drawARGB(0xFF,00,00,00);
	}
	
	/**
	 * draw alpha blended line
	 * 
	 * @param surface
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param color
	 */
	@Override
	public void drawLine(int x1, int y1, int x2, int y2, int color)
	{
		float xa = x1;
		float ya = y1;
		float xb = x2;
		float yb = y2;

		int c = 0xFF000000 | color;
		paint.setColor(c);

		surface.drawLine(xa, ya, xb, yb, paint);
	}

	/**
	 * Draw bitmap to screen.
	 * 
	 * @param bitmap
	 * @param left
	 * @param top
	 */
	@Override
	public void drawBitmap(Bitmap bitmap, float left, float top)
	{
		this.surface.drawBitmap(bitmap, left, top, null);
	}
	
	/**
	 * No canvas no surface to draw to.
	 * 
	 * @param surface
	 */
	public void setCanvas(Canvas surface)
	{
		this.surface = surface;
	}

	public int getScreenWidth()
	{
		return screenWidth;
	}

	public int getScreenHeight()
	{
		return screenHeight;
	}
	
}
