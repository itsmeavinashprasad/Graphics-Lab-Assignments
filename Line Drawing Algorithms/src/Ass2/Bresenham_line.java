/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ass2;

import java.awt.*;
import java.util.*;
import java.math.*;
/**
 *
 * @author Avinash Kumar Prasad
 */
public class Bresenham_line
{
    private int  x1,x2,y1,y2,origin_x,origin_y;
    private Graphics g;
    private Color c;
    private int bw,bh,topleft_x,topleft_y;
    
    public void setData(int x1,int y1,int x2,int y2,int orix, int oriy, int bw,int bh, Graphics g)
    {
        origin_x=orix;
        origin_y=oriy;
        this.bw=bw;
        this.bh=bh;
        
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        
        this.g=g;
    }
    public void draw()
    {
        
        int x,y,flag=0;
        if(x1>x2)
        {
            x=x2;   y=y2;
            flag=1;
        }
        else
        {
            x=x1;   y=y1;
        }
        int dx=x2-x1, dy=y2-y1;
        int p=(2*dy)-dx;
        
        int count=Math.abs(dx);
        for(int i=0;i<count;i++)
        {
            g.fillRect(origin_x+(int)x*bw, origin_y+(int)y*bw, bw, bh);

            if(p<0)
            {
                p+=2*dy;
            }
            else
            {
                p+=2*(dy-dx);
                y++;
            }
            x++;
        }    
    }
}