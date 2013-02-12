/*
 * $Id: GameManager.java,v 1.5 2001/06/03 00:19:12 ChoK Exp $
 *
 * Copyright 2001 Kenta Cho. All rights reserved.
 */
package jp.gr.java_conf.abagames.bulletml_demo.noiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Random;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import jp.gr.java_conf.abagames.bulletml.Action;
import jp.gr.java_conf.abagames.bulletml.Bullet;
import jp.gr.java_conf.abagames.bulletml.Bulletml;
import jp.gr.java_conf.abagames.bulletml.Fire;
import jp.gr.java_conf.abagames.bulletml.IActionElmChoice;
import jp.gr.java_conf.abagames.bulletml.IBulletmlChoice;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.netthreads.android.bulletml.ApplicationPreferences;
import com.netthreads.android.bulletml.data.StateData;
import com.netthreads.android.bulletml.graphics.IScreen;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.util.Log;


/**
 * Handle game status.
 *
 * @version $Revision: 1.5 $
 */
public class GameManager
{
    private final int BULLET_NOT_EXIST = BulletImpl.NOT_EXIST;
    private final int BULLET_MAX = 256;
    private BulletImpl[] bullet = new BulletImpl[BULLET_MAX];
    private int bulletIndex = 0;
    private final int ACTION_MAX = 1024;
    private ActionImpl[] action = new ActionImpl[ACTION_MAX];
    private int actionIndex = 0;
    private final int FRAG_MAX = 16;
    private Frag[] frag = new Frag[FRAG_MAX];
    private int frgIdx = 0;

    private int screenHeight = 0;
	private int screenWidth = 0;
    
    // A curse on three letter variable names! 
    public int xPosition;
    public int yPosition;
    
    private int pxPosition;
    private int pyPosition;
    private final int CLS_WIDTH = 32;
    private final int PEN_COLOR = 0xffddbb;

    // BulletML handler.
    private IActionElmChoice[] topAction;
    public int hvStat = 0;
    private BulletImpl topBullet;
    private int shotCnt = 0;
    private Random rnd = new Random();

    private final int INTERVAL = 16;
    private long prvTickCount = 0;

    private Context context = null;

	private Paint paint = null;
    
	private StateData state = null;
	
    /**
     * @param context
     * @param screenHeight
     * @param screenWidth
     */
    public GameManager(Context context, StateData state, int screenWidth, int screenHeight) 
    {
    	this.context = context;
    	
    	this.state = state;
    	
    	this.screenWidth = screenWidth;
    	this.screenHeight = screenHeight;
    	
    	paint = new Paint();
    	paint.setStyle(Paint.Style.STROKE);
    	paint.setStrokeWidth(ApplicationPreferences.getInstance(context).getLineWidth());
    	
    	initGame();
	}
    
    /**
     * 
     */
    public void initBullets()
    {
        for (int i = 0; i < bullet.length; i++)
        {
            bullet[i] = new BulletImpl(this);
        }

        for (int i = 0; i < action.length; i++)
        {
            action[i] = new ActionImpl(this);
        }

        for (int i = 0; i < frag.length; i++)
        {
            frag[i] = new Frag(this);
        }
    }

    /**
     * 
     * @return
     */
    public BulletImpl getBulletImplInstance()
    {
        for (int i = BULLET_MAX - 1; i >= 0; i--)
        {
            bulletIndex++;
            bulletIndex &= (BULLET_MAX - 1);

            if (bullet[bulletIndex].x == BULLET_NOT_EXIST)
            {
                return bullet[bulletIndex];
            }
        }

        return null;
    }

    /**
     * @return
     */
    public ActionImpl getActionImplInstance()
    {
        for (int i = ACTION_MAX - 1; i >= 0; i--)
        {
            actionIndex++;
            actionIndex &= (ACTION_MAX - 1);

            if (action[actionIndex].pc == ActionImpl.NOT_EXIST)
            {
                return action[actionIndex];
            }
        }

        return null;
    }

    /**
     * @param x
     * @param y
     */
    public void addFrag(int x, int y)
    {
        frgIdx++;
        frgIdx &= (FRAG_MAX - 1);
        frag[frgIdx].set(x, y);
    }

    /**
     * 
     */
    private void initGame()
    {
        xPosition = (int)state.controlX;
        yPosition = (int)state.controlY;
        pxPosition = xPosition + 1;
        pyPosition = yPosition;
    }

    /**
     * 
     */
    private void hitBullet()
    {
        for (int i = 4; i >= 0; i--)
        {
            addFrag(xPosition, yPosition);
        }
    }

    /**
     * Move element pen.
     * 
     * @param The draw canvas.
     */
    public final void movePen(IScreen screen)
    {
        int i;
        BulletImpl bl;
        int a1x;
        int a1y;
        int a2x;
        int a2y;
        int b1x;
        int b1y;
        int b2x;
        int b2y;
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int dnm;
        int x;
        int y;
        int oxPos;
        int oyPos;

        pxPosition = xPosition;
        pyPosition = yPosition;

        if ((state.controlX >= 0) && (state.controlX < screenWidth) && (state.controlY >= 0) && (state.controlY < screenHeight))
        {
        	// WTF are we shifting these values for!!!!
	        xPosition = (((int)state.controlX)<<4)+8;
	        yPosition = (((int)state.controlY)<<4)+8;
        }

        drawLine(screen, pxPosition>>4, pyPosition>>4, xPosition>>4, yPosition>>4, PEN_COLOR);
        
        oxPos = xPosition;
        oyPos = yPosition;

        switch (hvStat)
        {
        case 0:

            if (xPosition < pxPosition)
            {
                xPosition -= CLS_WIDTH;
                pxPosition += CLS_WIDTH;
                a1x = xPosition - CLS_WIDTH;
                a2x = pxPosition + CLS_WIDTH;
            }
            else
            {
                pxPosition -= CLS_WIDTH;
                xPosition += CLS_WIDTH;
                a1x = pxPosition - CLS_WIDTH;
                a2x = xPosition + CLS_WIDTH;
            }

            if (yPosition < pyPosition)
            {
                a1y = yPosition - CLS_WIDTH;
                a2y = pyPosition + CLS_WIDTH;
            }
            else
            {
                a1y = pyPosition - CLS_WIDTH;
                a2y = yPosition + CLS_WIDTH;
            }

            break;

        case 1:

            if (xPosition < pxPosition)
            {
                a1x = xPosition - CLS_WIDTH;
                a2x = pxPosition + CLS_WIDTH;
            }
            else
            {
                a1x = pxPosition - CLS_WIDTH;
                a2x = xPosition + CLS_WIDTH;
            }

            if (yPosition < pyPosition)
            {
                yPosition -= CLS_WIDTH;
                pyPosition += CLS_WIDTH;
                a1y = yPosition - CLS_WIDTH;
                a2y = pyPosition + CLS_WIDTH;
            }
            else
            {
                pyPosition -= CLS_WIDTH;
                yPosition += CLS_WIDTH;
                a1y = pyPosition - CLS_WIDTH;
                a2y = yPosition + CLS_WIDTH;
            }

            break;

        default:
            a1x = a2x = a1y = a2y = 0;

            break;
        }

        for (i = BULLET_MAX - 1; i >= 0; i--)
        {
            bl = bullet[i];

            if (bl.x != BULLET_NOT_EXIST)
            {
                if (bl.y < bl.py)
                {
                    b1y = bl.y - CLS_WIDTH;
                    b2y = bl.py + CLS_WIDTH;
                }
                else
                {
                    b1y = bl.py - CLS_WIDTH;
                    b2y = bl.y + CLS_WIDTH;
                }

                if ((a2y >= b1y) && (b2y >= a1y))
                {
                    if (bl.x < bl.px)
                    {
                        b1x = bl.x - CLS_WIDTH;
                        b2x = bl.px + CLS_WIDTH;
                    }
                    else
                    {
                        b1x = bl.px - CLS_WIDTH;
                        b2x = bl.x + CLS_WIDTH;
                    }

                    if ((a2x >= b1x) && (b2x >= a1x))
                    {
                        a = yPosition - pyPosition;
                        b = pxPosition - xPosition;
                        c = (pxPosition * yPosition) - (pyPosition * xPosition);
                        d = bl.y - bl.py;
                        e = bl.px - bl.x;
                        f = (bl.px * bl.y) - (bl.py * bl.x);
                        dnm = (b * d) - (a * e);

                        if (dnm != 0)
                        {
                            x = ((b * f) - (c * e)) / dnm;
                            y = ((c * d) - (a * f)) / dnm;

                            if ((a1x <= x) && (x <= a2x) && (a1y <= y) && (y <= a2y) && (b1x <= x) && (x <= b2x) && (b1y <= y) && (y <= b2y))
                            {
                                hitBullet();

                                return;
                            }
                        }
                    }
                }
            }
        }

        xPosition = oxPos;
        yPosition = oyPos;
    }

    /**
     * Load ML definition file.
     * 
     * @param The document name. 
     */
    public void loadBulletML(String document)
    {
        try
        {
            Document doc = getDocument(document);
            
            Bulletml bulletML = new Bulletml(doc);
            
            // String type = bulletML.getType();
            // NOTE: Do we need to use the type or can we leave it to the defn.

            IBulletmlChoice[] bmc = bulletML.getContent();
            Vector<Action> aecVct = new Vector<Action>();
            BulletmlNoizUtil.clear();

            for (int i = 0; i < bmc.length; i++)
            {
                IBulletmlChoice be = bmc[i];

                if (be instanceof Action)
                {
                    Action act = (Action) be;

                    if (act.getLabel().startsWith("top"))
                    {
                        aecVct.addElement(act);
                    }

                    BulletmlNoizUtil.addAction(act);
                }
                else if (be instanceof Bullet)
                {
                    BulletmlNoizUtil.addBullet((Bullet) be);
                }
                else if (be instanceof Fire)
                {
                    BulletmlNoizUtil.addFire((Fire) be);
                }
            }

            topAction = new IActionElmChoice[aecVct.size()];
            aecVct.copyInto(topAction);
        }
        catch (Exception e)
        {
        	Log.e("loadBulletML", e.toString());
        	
            e.printStackTrace();
        }
    }

    /**
     * Read file contents into string.
     * 
     * @param The file name
     * 
     * @return Contents string.
     * 
     * @throws IOException
     */
    private String readFile(String fname) throws IOException
    {
    	String line ="";
    	
    	AssetManager assetManager = context.getResources().getAssets(); 
    	InputStream stream = assetManager.open(fname);
    	
        BufferedReader dis =new BufferedReader (new InputStreamReader(stream)); 
        
        StringBuffer fBuf = new StringBuffer() ;

        while ( (line = dis.readLine())!= null) 
        {
        	fBuf.append(line);
        }
        
        return fBuf.toString();
    }
    
    /**
     * Build DOM document from file.
     * 
     * @param The file name.
     * 
     * @return DOM Document.
     * 
     * @throws IOException
     * @throws FactoryConfigurationError 
     * @throws ParserConfigurationException 
     * @throws SAXException 
     */
    Document getDocument(String fname) throws IOException, ParserConfigurationException, FactoryConfigurationError, SAXException
    {
    	Document document = null;
    	
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         
        String in = readFile(fname);
         
        document = builder.parse(new InputSource(new StringReader(in))); 

    	 return document;
    }
    
    /**
     * @param hvs
     */
    public void setHVStat(int hvs)
    {
        hvStat = hvs;
    }

    /**
     * 
     */
    private void addBullets()
    {
        if ((topBullet != null) && (topBullet.x != BulletImpl.NOT_EXIST) && !topBullet.isAllActionFinished())
        {
            return;
        }

        shotCnt--;

        if (shotCnt > 0)
        {
            return;
        }

        shotCnt = 60;
        topBullet = getBulletImplInstance();

        if (topBullet == null)
        {
            return;
        }

        switch (hvStat)
        {
        case 0:
            topBullet.set(topAction, (120 + (Math.abs(rnd.nextInt()) % 80)) << 4, (20 + (Math.abs(rnd.nextInt()) % 40)) << 4, 0);

            break;

        case 1:
            topBullet.set(topAction, (300 - (Math.abs(rnd.nextInt()) % 40)) << 4, (120 + (Math.abs(rnd.nextInt()) % 80)) << 4, 0);

            break;
        }

        topBullet.speed = 0;
        topBullet.direction = 0;
    }

    /**
     * Move bullet elements.
     * 
     * @param The draw canvas
     */
    private void moveBullets()
    {
        for (int i = BULLET_MAX - 1; i >= 0; i--)
        {
            if (bullet[i].x != BULLET_NOT_EXIST)
            {
                bullet[i].move();
            }
        }
    }

    /**
     * Draw bullet elements.
     * 
     * @param The draw canvas
     */
    private void drawBullets(IScreen screen)
    {
        for (int i = BULLET_MAX - 1; i >= 0; i--)
        {
            if (bullet[i].x != BULLET_NOT_EXIST)
            {
                bullet[i].draw(screen);
            }
        }
    }

    /**
     * Move frag elements.
     * 
     * @param The draw canvas
     */
    private void moveFrags()
    {
        for (int i = FRAG_MAX - 1; i >= 0; i--)
        {
            if (frag[i].cnt != Frag.NOT_EXIST)
            {
                frag[i].move();
            }
        }
    }

    /**
     * Draw frag elements.
     * 
     * @param The draw canvas
     */
    private void drawFrags(IScreen screen)
    {
        for (int i = FRAG_MAX - 1; i >= 0; i--)
        {
            if (frag[i].cnt != Frag.NOT_EXIST)
            {
                frag[i].draw(screen);
            }
        }
    }

    /**
     * Update visual items.
     * 
     */
    public void update()
    {
        long nowTick = System.currentTimeMillis();
        
        int frame = (int) (nowTick - prvTickCount) / INTERVAL;

        if (frame <= 0)
        {
            frame = 1;
            prvTickCount = nowTick;
        }
        else if (frame > 5)
        {
            frame = 5;
            prvTickCount = nowTick;
        }
        else
        {
            prvTickCount += (frame * INTERVAL);
        }

        for (int i = 0; i < frame; i++)
        {
            addBullets();
            moveBullets();
            moveFrags();
        }
    }

    /**
     * Draw managed items.
     * 
     * @param canvas
     */
    public void draw(IScreen screen)
    {
    	// Clear the screen
    	screen.clear();
    	
	    drawBullets(screen);
	    
	    drawFrags(screen);
	    
	    movePen(screen);
    }

    /**
     * draw alpha blended line
     * 
     * @param canvas
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     */
    public final void drawLine(IScreen screen, int x1, int y1, int x2, int y2, int color)
    {
    	screen.drawLine(x1, y1, x2, y2, color);
    }

    
    /**
     * Return screen height.
     * 
     * @return Screen height.
     */
    public int getScreenHeight() 
    {
		return screenHeight;
	}

	/**
	 * Return screen width.
	 * 
	 * @return Screen width
	 */
	public int getScreenWidth() 
	{
		return screenWidth;
	}
    
}
