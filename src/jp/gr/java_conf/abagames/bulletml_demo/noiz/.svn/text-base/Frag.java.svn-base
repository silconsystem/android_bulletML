/*
 * $Id: Frag.java,v 1.2 2001/05/14 14:21:58 ChoK Exp $
 *
 * Copyright 2001 Kenta Cho. All rights reserved.
 */
package jp.gr.java_conf.abagames.bulletml_demo.noiz;

import java.util.Random;

import com.netthreads.android.bulletml.graphics.IScreen;

/**
 * Hit frag fireworks.
 *
 * @version $Revision: 1.2 $
 */
public class Frag
{
    public final static int NOT_EXIST = -1;
    private static Random rnd = new Random();
    private int x;
    private int y;
    private int mx;
    private int my;
    private int px;
    private int py;
    public int cnt;
    private GameManager gameManager;
    private final int COLOR = 0xff2222;

    public Frag(GameManager gameManager)
    {
        this.gameManager = gameManager;
        cnt = NOT_EXIST;
    }

    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
        mx = rnd.nextInt() % 32;
        my = rnd.nextInt() % 32;
        cnt = 32 + (Math.abs(rnd.nextInt()) % 32);
    }

    public void move()
    {
        px = x;
        py = y;
        x += (mx << 1);
        y += (my << 1);
        cnt--;
    }

    public void draw(IScreen screen)
    {
        gameManager.drawLine(screen, x >> 4, y >> 4, px >> 4, py >> 4, COLOR);
    }
}
