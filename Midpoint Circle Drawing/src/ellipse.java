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

public class ellipse 
{
    private int  xc,yc,rx,ry,origin_x,origin_y;
    private Graphics g;
    private int bd,topleft_x,topleft_y;

    
    
    public void setData(int xc, int yc, int rx,int ry ,int orix, int oriy, int bd, Graphics g)
    {
        origin_x=orix;
        origin_y=oriy;
        this.bd=bd;
        
        this.xc=xc;
        this.yc=yc;
        this.rx=rx;
        this.ry=ry;
        this.xc=0; this.yc=0;
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
//            origin_x+=xc*bd;
//            origin_y+=yc*bd;
//            g.fillRect(origin_x+x*bd,origin_y+y*bd, bd, bd);
//            g.fillRect(origin_x-x*bd,origin_y+y*bd, bd, bd);
//            g.fillRect(origin_x+x*bd,origin_y-y*bd, bd, bd);
//            g.fillRect(origin_x-x*bd,origin_y-y*bd, bd, bd);
//            origin_x-=xc*bd;
//            origin_y-=yc*bd;
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
//            origin_x+=xc*bd;
//            origin_y+=yc*bd;
//            g.fillRect(origin_x+x*bd,origin_y+y*bd, bd, bd);
//            g.fillRect(origin_x-x*bd,origin_y+y*bd, bd, bd);
//            g.fillRect(origin_x+x*bd,origin_y-y*bd, bd, bd);
//            g.fillRect(origin_x-x*bd,origin_y-y*bd, bd, bd);
//            origin_x-=xc*bd;
//            origin_y-=yc*bd;
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
        //test
        double theta=Math.PI/8; 
        double [][] a={{Math.cos(theta),Math.sin(theta),0},{-Math.sin(theta),Math.cos(theta),0},{0,0,1}};
        double [][] b={{x},{y},{1}};
        double [][] c=new double [3][1];
        
        int n=3;
        System.out.println("Multiplying the matrices...");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < 1; j++)
            {
                for (int k = 0; k < n; k++)
                {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                    System.out.println(c[i][j]);
                    
                }
            }
        }
        g.fillRect(origin_x +(int)(c[0][0]) *bd , origin_y + (int)(c[1][0])*bd , bd, bd);
        
    }
        
    
}
