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
import java.awt.event.*;
import java.applet.*;
import java.util.*;

public class myapp extends Applet
        implements MouseListener,MouseMotionListener,ActionListener
{
    private int origin_x,origin_y;
    
    private int dx=2,dy=0;
    private int zoomlevel=10,flag=0;
    private Graphics g;
    private int x1,x2,y1,y2;
    
    private Grid obj;
    private circle obj2;
    private ellipse obj3;
    private Button left,right,up,down,zoom_in,zoom_out,clear;
    public void init()
    {
        this.setSize(800,600);
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        obj= new Grid();
        obj2=new circle();
        obj3=new ellipse();
        zoom_in=new Button("ZOOM IN");
        zoom_out=new Button("ZOOM OUT");
        left=new Button("LEFT");
        right=new Button("RIGHT");
        up=new Button("UP");
        down=new Button("DOWN");
        clear=new Button("CLEAR");
        
        
        add(clear);
        add(left);
        add(right);
        add(up);
        add(down);
        add(zoom_in);
        add(zoom_out);
        
        
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
        g.setColor(Color.DARK_GRAY);
        //set origin
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        
        g.fillRect(origin_x+dx*obj.bd , origin_y+dy*obj.bd , obj.bd, obj.bd);
        
//        obj2.setData(dx, dy,45, origin_x, origin_y,obj.bd, g);
//        obj2.draw();
        
        obj3.setData(dx, dy,25,45, origin_x, origin_y,obj.bd, g);
        obj3.draw();

        
//        draw grid
        obj.setData(origin_x,origin_y,getWidth(),getHeight(),getX(),getY(),g);
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
            x=mx/obj.bd;
            if(my>0)
                y=my/obj.bd;
            else
                y=my/obj.bd-1;
        }
        else
        {
            x=mx/obj.bd-1;
            if(my>0)
                y=my/obj.bd;
            else
                y=my/obj.bd-1;
        }
        dx=x;
        dy=y;
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
        
        if(e.getSource()==zoom_in)
        {
            if(zoomlevel<14)
            {
                obj.bd+=2;
                obj.bd+=2;
                zoomlevel++;
            }
        }
        if(e.getSource()==zoom_out)
        {
            if(zoomlevel>8)
            {
                obj.bd-=2;
                obj.bd-=2;
                zoomlevel--;
            }
        }
        if(e.getSource()==left)
        {
            dx--;
        }
        if(e.getSource()==right)
        {
            dx++;
        }
        if(e.getSource()==up)
        {
            dy--;
        }
        if(e.getSource()==down)
        {
            dy++;
        }
        if(e.getSource()==clear)
        {
           
        }
        repaint();
    }    
}