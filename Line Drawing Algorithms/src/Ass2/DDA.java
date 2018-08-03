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
public class DDA
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
        double dx,dy,steps,x,y;
        int k;
        double X_increment,Y_increment;
        
        //1
        dx=x2-x1;
        dy=y2-y1;
        //2
        if(Math.abs(dx)>Math.abs(dy))
            steps=Math.abs(dx);
        else
            steps=Math.abs(dy);
        //3
        X_increment=(dx/steps);
        Y_increment=(dy/steps);
        //4
        x=x1;
        y=y1;
        
        for(k=1;k<=steps;k++)
        {
            x=x+X_increment;
            y=y+Y_increment;
            
            g.fillRect(origin_x+(int)x*bw, origin_y+(int)y*bw, bw, bh);
        }
        g.setColor(Color.LIGHT_GRAY);
    }
}