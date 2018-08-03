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
//import static java.lang.Math.ceil;

public class ellipse 
{
    private int  origin_x,origin_y,bd;
    
    private Graphics g;
    
    public void draw(int xc, int yc, int rx,int ry ,int origin_x, int origin_y, int bd,int [] arr, Graphics g)
    {
        this.origin_x=origin_x;
        this.origin_y=origin_y;
        this.bd=bd;
        this.g=g;
        int x,y;
        x=0;
        y=ry;
        
        //region 1 
        double p=pow(ry,2)-pow(rx,2)*ry+(pow(rx,2)/(double)4.0);
        while(pow(ry,2)*x<pow(rx,2)*y)
        {
            this.origin_x+=xc*this.bd;
            this.origin_y+=yc*this.bd;
            
            for(int i=0;i<arr.length;i++)
            {
                if(arr[i]==1)
                    fill(x,y);
                if(arr[i]==8)
                    fill(-x,y);
                if(arr[i]==4)
                    fill(x,-y);
                if(arr[i]==5)
                    fill(-x,-y);
            }
//            fill(x,y);      //1
//            fill(-x,y);     //8
//            fill(x,-y);     //4
//            fill(-x,-y);    //5
            this.origin_x-=xc*this.bd;
            this.origin_y-=yc*this.bd;
            
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
            this.origin_x+=xc*this.bd;
            this.origin_y+=yc*this.bd;
            for(int i=0;i<arr.length;i++)
            {
                if(arr[i]==2)
                    fill(x,y);
                if(arr[i]==7)
                    fill(-x,y);
                if(arr[i]==3)
                    fill(x,-y);
                if(arr[i]==6)
                    fill(-x,-y);
            }
//            fill(x,y);      //2
//            fill(-x,y);     //7
//            fill(x,-y);     //3
//            fill(-x,-y);    //6
            this.origin_x-=xc*this.bd;
            this.origin_y-=yc*this.bd;
            
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
        
    }
    public void fill(int x,int y)
    {
        g.fillRect(this.origin_x+x*this.bd,this.origin_y+y*this.bd, this.bd, this.bd);
        
    }
        
    
}
