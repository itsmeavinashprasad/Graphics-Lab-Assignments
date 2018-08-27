/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.util.*;
import java.math.*;
/**
 *
 * @author Avinash Kumar Prasad
 */
public class DDA
{
    public void draw(int x1,int y1,int x2,int y2,int origin_x, int origin_y, int bd, Graphics g)
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
            
            g.fillRect(origin_x+(int)x*bd, origin_y+(int)y*bd, bd, bd);
        }
    }
}