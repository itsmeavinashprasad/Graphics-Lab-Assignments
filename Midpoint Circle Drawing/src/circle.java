/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Avinash Kumar Prasad
 */

import java.awt.*;
import java.util.*;
import java.math.*;

public class circle 
{
    private int  xc,yc,r,origin_x,origin_y;
    private Graphics g;
    private int bd,topleft_x,topleft_y;

    
    
    public void setData(int xc, int yc, int r,int orix, int oriy, int bd, Graphics g)
    {
        origin_x=orix;
        origin_y=oriy;
        this.bd=bd;
        
        this.xc=xc;
        this.yc=yc;
        this.r=r;
        
        this.g=g;
    }
    
    public void draw()
    {
        g.setColor(Color.GRAY);
        int x,y;
        x=0;
        y=r;
        
        int p=1-r;
        while(x<=y)
        {
            origin_x+=xc*bd;
            origin_y+=yc*bd;
            fill(x,y);
            origin_x-=xc*bd;
            origin_y-=yc*bd;
            ++x;
            if(p<0)
                p+=2*x+1;
            else
            {
                --y;
                p+=2*(x-y)+1;
               
            }
            
            
        }
    }
    public void fill(int x, int y)
    {
        g.fillRect(origin_x+x*bd,origin_y+y*bd, bd, bd);
        g.fillRect(origin_x+x*bd,origin_y-y*bd, bd, bd);
        g.fillRect(origin_x+y*bd,origin_y-x*bd, bd, bd);
        g.fillRect(origin_x-y*bd,origin_y-x*bd, bd, bd);
        g.fillRect(origin_x-x*bd,origin_y-y*bd, bd, bd);
        g.fillRect(origin_x-x*bd,origin_y+y*bd, bd, bd);
        g.fillRect(origin_x-y*bd,origin_y+x*bd, bd, bd);
        g.fillRect(origin_x+y*bd,origin_y+x*bd, bd, bd);
    }
        
    
}
