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
import static java.lang.Math.pow;
import java.util.*;
import static java.lang.Math.ceil;

public class SaturnEllipse 
{
    private int  xc,yc,rx,ry,origin_x,origin_y;
    private Graphics g;
    private int bd,topleft_x,topleft_y;
    private static int iter=0;

    
    
    public void setData(int xc, int yc, int rx,int ry ,int orix, int oriy, int bd, Graphics g)
    {
        g.setColor(Color.BLACK);
        origin_x=orix;
        origin_y=oriy;
        this.bd=bd;
        
        this.xc=xc;
        this.yc=yc;
        this.rx=rx;
        this.ry=ry;
        this.g=g;
    }
    
    public void draw()
    {
        g.setColor(Color.GRAY);
        int x,y;
        x=0;
        y=ry;
        
        //region 1 
        double p=pow(ry,2)-pow(rx,2)*ry+(pow(rx,2)/(double)4.0);
        while(pow(ry,2)*x<pow(rx,2)*y)
        {
            fill(x,y);
            fill(-x,y);
            fill(x,-y);
            fill(-x,-y);
            x++;
            if(p<=0)
                p+=(2*pow(ry,2)*x)+pow(ry,2);
            else
            {
                y--;
                p+=(2*pow(ry,2)*x)-(2*pow(rx,2)*y)+pow(ry,2);
            }
            
        }
        
        //region 2
        p=(pow(ry,2)*pow((x+0.5),2)) + pow((rx*(y-1)),2) - pow(rx*ry,2);
        while(y>=0)
        {

            fill(x,y);
            fill(-x,y);
            fill(x,-y);
            fill(-x,-y);
            y--;
            if(p>0)
            {
                p+= -( 2*pow(rx,2)*y + pow(rx,2));
            }
            else
            {
                x++;
                p+= 2*pow(ry,2)*x - 2*pow(rx,2)*y + pow(rx,2);
            }
            
        }
        iter=(iter+5)%180;
        
    }
    public void fill(int x,int y)
    {
        double x_dash, y_dash;
        double theta = 0;
        
        theta=Math.toRadians(iter);
//        theta=0;
        x_dash=x*Math.cos(theta) + y*Math.sin(theta);
        y_dash=y*Math.cos(theta) - x*Math.sin(theta);
        g.fillRect(origin_x +(int)(x_dash+xc) *bd , origin_y + (int)(y_dash+yc)*bd , bd, bd);
        
    }
        
    
}
