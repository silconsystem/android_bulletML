/*
 * Copyright (C) 2008 The Android Open Source Project
 * Copyright (C) 2009 Alistair Rutherford, www.netthreads.co.uk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netthreads.android.bulletml.graphics;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

import jp.gr.java_conf.abagames.bulletml_demo.noiz.GameManager;
import android.content.Context;

import com.netthreads.android.opengl.GLSurfaceView;
import com.netthreads.android.opengl.GLSurfaceView.EGLConfigChooser;


/**
 * An OpenGL ES renderer based on the GLSurfaceView rendering framework.  This
 * class is responsible for drawing a list of renderables to the screen every
 * frame.
 * 
 */
public class RendererGL implements GLSurfaceView.Renderer, EGLConfigChooser
{
	GameManager gameManager = null;
	
	private ScreenGL screen= null;
	
	/**
	 * Construct.
	 * 
	 * @param Manager object.
	 * 
	 */
	public RendererGL(Context context, GameManager gameManager, int lineWidth, int screenWidth, int screenHeight) 
	{
		this.gameManager = gameManager;

        screen = new ScreenGL(lineWidth, screenWidth, screenHeight);
	}
	
	@Override
	public void onDrawFrame(GL10 gl) 
	{
		screen.setSurface(gl);
		
		// Update
        gameManager.update();

        if (screen != null)
        {
            gl.glMatrixMode(GL10.GL_MODELVIEW);

            gameManager.draw(screen);
        }
	}

	
    /**
     * Called when the rendering thread shuts down.  This is a good place to
     * release OpenGL ES resources.
     * @param gl
     */
    public void shutdown(GL10 gl)
    {
    }

	/**
	 * Called on surface creation and when phone orientation is flipped.
	 * 
	 */
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{
    	/*
        * Some one-time OpenGL initialization can be made here probably based
        * on features of this particular context
        */
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1); // Black
        gl.glShadeModel(GL10.GL_FLAT);
        gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        /*
         * By default, OpenGL enables features that improve quality but reduce
         * performance. One might want to tweak that especially on software
         * renderer.
         */
        gl.glDisable(GL10.GL_DITHER);
        gl.glDisable(GL10.GL_LIGHTING);

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
	}
	
	/**
	 * Called on surface creation and when phone orientation is flipped.
	 * 
	 */
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) 
	{
        gl.glViewport(0, 0, width, height);

        /*
         * Set our projection matrix. This doesn't have to be done each time we
         * draw, but usually a new projection needs to be set when the viewport
         * is resized.
         */
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0.0f, width, 0.0f, height, 0.0f, 1.0f);

        // In flat-shaded mode, the triangle will be rendered with the color that was given 
        // to the last vertex specified. In smooth-shaded mode, the colors are interpolated 
        // between the vertices where each vertex can be given a different color.
        gl.glShadeModel(GL10.GL_FLAT);

        // I though this made the line width work on emulator but not on phone! Not it does neither.
        gl.glEnable(GL10.GL_LINE_SMOOTH);
        gl.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_DONT_CARE);
	}

    /**
     * Choose EGL configuration.
     * 
     */
	@Override
	public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) 
	{
		EGLConfig[] configs = new EGLConfig[1];
        int[] num_config = new int[1];
        
        egl.eglChooseConfig(display, getConfigSpec(), configs, 1, num_config);
        
        EGLConfig mEglConfig = configs[0];
        
		return mEglConfig;
	}
	
	/**
	 * Returns EGL config.
	 * 
	 * @return config list.
	 */
    public int[] getConfigSpec()
    {
        // We don't need a depth buffer, and don't care about our
        // colour depth.
        int[] configSpec = { EGL10.EGL_DEPTH_SIZE, 0, EGL10.EGL_NONE };

        return configSpec;
    }
	
}
