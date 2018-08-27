/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
/**
 *
 * @author Avinash Kumar Prasad
 */
public class Grid
{
    private int origin_x,origin_y;
    private int x1,x2,y1,y2;
    public int bd=10;
    private Color c1,c2;
    private int max_width,max_height,min_width,min_height;
    private Graphics g;
    
    public void setData(int or_x, int or_y, int max_width,int max_height,int min_width,int min_height,Graphics g)
    {
        
        origin_x=or_x;
        origin_y=or_y;
        this.max_height=max_height;
        this.max_width=max_width;
        this.min_height=min_height;
        this.min_width=min_width;
        this.g=g;
    }
    public void drawHorizontal()
    {
        g.setColor(c1);
        x1=min_width;
        x2=max_width;
        y1=origin_y;
        y2=origin_y;
        //draw horizontal lines
        while(y1>=min_width)
        {
            y1=y1-bd;
            y2=y1;
            g.drawLine(x1,y1,x2,y2);

        }
        y1=origin_y;
        y2=origin_y;
        while(y1<=max_height)
        {
            y1=y1+bd;
            y2=y1;
            g.drawLine(x1,y1,x2,y2);

        }
        
//        draw vertical axes
        x1=origin_x;
        x2=origin_x;
        y1=min_height;
        y2=max_height;
        while(x1>=min_width)
        {
            x1=x1-bd;
            x2=x1;
            g.drawLine(x1,y1,x2,y2);
        }
        x1=origin_x;
        x2=origin_x;
        while(x1<=max_width)
        {
            x1=x1+bd;
            x2=x1;
            g.drawLine(x1,y1,x2,y2);
        }
    }
    
    public void drawAxes()
    {
        //draw axes
        g.setColor(c2);
        g.drawLine(min_width,max_height/2,max_width,max_height/2);
        g.drawLine(min_width,max_height/2+1,max_width,max_height/2+1);
        g.drawLine(min_width,max_height/2-1,max_width,max_height/2-1);
        g.drawLine(max_width/2,min_height,max_width/2,max_height);
        g.drawLine(max_width/2+1,min_height,max_width/2+1,max_height);
        g.drawLine(max_width/2-1,min_height,max_width/2-1,max_height);
    }
    public void chnageColor(Color c1, Color c2)
    {
        this.c1=c1;
        this.c2=c2;
    }
}
