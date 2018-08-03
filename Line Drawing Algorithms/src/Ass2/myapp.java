/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ass2;

/**
 *
 * @author Avinash Kumar Prasad
 */


import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;


public class myapp extends Applet
        implements MouseListener,MouseMotionListener,ActionListener
{
    private int origin_x,origin_y;
    
    private int dx=-7,dy=-7;
    private int zoomlevel=15,flag=0;
    private Graphics g;
    private int x1,x2,y1,y2,click_flag=0,selection_flag=0,dda_flag=0,bres_flag=0,midp_flag=0;
    
    private Grid obj;
    private DDA obj2;
    private Bresenham_line obj3;
    private Midpoint obj4;
    private Highlight obj5;
    private Button DDA,bres,mid,left,right,up,down,zoom_in,zoom_out,clear;
    public void init()
    {
        this.setSize(800,600);
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        obj= new Grid();
        obj2=new DDA();
        obj3=new Bresenham_line();
        obj4=new Midpoint();
        obj5=new Highlight();
        
        DDA=new Button("DDA!!");
        bres=new Button("BRESENHAM!!");
        mid=new Button("Mid-Point!!");
        zoom_in=new Button("ZOOM IN");
        zoom_out=new Button("ZOOM OUT");
        left=new Button("LEFT");
        right=new Button("RIGHT");
        up=new Button("UP");
        down=new Button("DOWN");
        clear=new Button("CLEAR");
        
        add(DDA);
        add(bres);
        add(mid);
        add(clear);
        add(left);
        add(right);
        add(up);
        add(down);
        add(zoom_in);
        add(zoom_out);
        
        DDA.addActionListener(this);
        bres.addActionListener(this);
        mid.addActionListener(this);
        clear.addActionListener(this);
        zoom_in.addActionListener(this);
        zoom_out.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
        
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        
    }
    
    public void paint(Graphics g)
    {
        //set origin
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        
        
        
        
        if(dda_flag==1)
        {
            //dda
            obj2.setData(x1,y1,x2,y2,origin_x,origin_y, obj.bw, obj.bh, g);
            obj2.draw();
        }
        if(bres_flag==1)
        {
            //Breshenham's Line
            obj3.setData(x1,y1,x2,y2,origin_x,origin_y, obj.bw, obj.bh, g);
            obj3.draw();
        }
        if(midp_flag==1)
        {
            //Mid Point Line
            obj4.setData(x1,y1,x2,y2,origin_x,origin_y, obj.bw, obj.bh, g);
            obj4.draw();
        }
        
        
        //highlight end points
        obj5.draw(x1,y1,x2,y2,origin_x,origin_y, obj.bw, obj.bh, g);
        
        //draw grid
        obj.setData(origin_x,origin_y,getWidth(),getHeight(),getX(),getY(),g);
        obj.colorChange(Color.GRAY, Color.BLACK);
        obj.drawHorizontal();
        obj.drawVertical();
        obj.drawAxes();
        
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        int mx,my,x,y;
        mx=e.getX()-origin_x;      my=e.getY()-origin_y;
        
        
        //====================get box locations=======================
        
        if(mx>=0)
        {
            x=mx/obj.bw;
            if(my>0)
                y=my/obj.bh;
            else
                y=my/obj.bh-1;
        }
        else
        {
            x=mx/obj.bw-1;
            if(my>0)
                y=my/obj.bh;
            else
                y=my/obj.bh-1;
        }
        
        if(click_flag==0)
        {
            x1=x;   y1=y;
            click_flag=1;
            dda_flag=0;     bres_flag=0;    midp_flag=0;
        }
        else
        {
            x2=x;   y2=y;
            click_flag=0;
            dda_flag=0;     bres_flag=0;    midp_flag=0;
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e){}

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==DDA)
        {
            bres_flag=0; 
            dda_flag=1;     midp_flag=0;
        }
        if(e.getSource()==bres)
        {
            dda_flag=0;
            bres_flag=1; midp_flag=0;
        }
        if(e.getSource()==mid)
        {
            midp_flag=1;
            dda_flag=0;     bres_flag=0;
        }
        if(e.getSource()==zoom_in)
        {
            if(zoomlevel<25)
            {
                obj.bh+=2;
                obj.bw+=2;
                zoomlevel++;
            }
        }
        if(e.getSource()==zoom_out)
        {
            if(zoomlevel>6)
            {
                obj.bh-=2;
                obj.bw-=2;
                zoomlevel--;
            }
        }
        if(e.getSource()==left)
        {
            x1--; x2--;
        }
        if(e.getSource()==right)
        {
            x1++;   x2++;
        }
        if(e.getSource()==up)
        {
            y1--;   y2--;
        }
        if(e.getSource()==down)
        {
            y1++;   y2++;
        }
        if(e.getSource()==clear)
        {
            dda_flag=0;     bres_flag=0;    midp_flag=0;
            click_flag=0;
        }
        repaint();
    }    
}