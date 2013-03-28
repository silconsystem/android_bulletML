/*
 * Copyright (C) 2009 The Android Open Source Project
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
package com.netthreads.android.opengl;


/**
 * Base class defining the core set of information necessary to render (and move
 * an object on the screen.  This is an abstract type and must be derived to
 * add methods to actually draw (see CanvasSprite and GLSprite).
 */
public abstract class Renderable
{
    // Position.
    public float x;
    public float y;
    public float z;

    // Velocity.
    public float velocityX;
    public float velocityY;
    public float velocityZ;

    // Size.
    private float renderWidth;
    private float renderHeight;

    /**
     * Constructor takes draw width and height.
     * 
     * @param width
     * @param height
     */
    public Renderable(int width, int height) 
    {
		renderWidth = width;
		renderHeight = height;
	}
    
    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getZ()
    {
        return z;
    }

    public void setZ(float z)
    {
        this.z = z;
    }

    public float getVelocityX()
    {
        return velocityX;
    }

    public void setVelocityX(float velocityX)
    {
        this.velocityX = velocityX;
    }

    public float getVelocityY()
    {
        return velocityY;
    }

    public void setVelocityY(float velocityY)
    {
        this.velocityY = velocityY;
    }

    public float getVelocityZ()
    {
        return velocityZ;
    }

    public void setVelocityZ(float velocityZ)
    {
        this.velocityZ = velocityZ;
    }

    public float getRenderWidth()
    {
        return renderWidth;
    }

    public void setRenderWidth(float renderWidth)
    {
        this.renderWidth = renderWidth;
    }

    public float getRenderHeight()
    {
        return renderHeight;
    }

    public void setRenderHeight(float renderHeight)
    {
        this.renderHeight = renderHeight;
    }
}
