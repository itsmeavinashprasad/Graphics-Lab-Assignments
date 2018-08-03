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


public class myapp2 extends Applet
implements ActionListener
{
    private int origin_x,origin_y,topleft_house_x,topleft_house_y;
    private ArrayList<Integer> button_positions;
    private int bw,bh,dx=-7,dy=-7;
    private int zoomlevel=15,flag=0;
    Graphics g;
    private Grid obj;
    private House obj2;
    private Button left,right,up,down,zoom_in,zoom_out;
    
    public void init()
    {
        this.setSize(800,600);
        setBackground(Color.WHITE);
        
        obj= new Grid();
        obj2=new House();
        
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        topleft_house_x=origin_x-(obj.bw*5);
        topleft_house_y=origin_y-(obj.bh*5);
        
        //implementing buttons
        zoom_in=new Button("ZOOM IN");
        zoom_out=new Button("ZOOM OUT");
        left=new Button("LEFT");
        right=new Button("RIGHT");
        up=new Button("UP");
        down=new Button("DOWN");
        add(left);
        add(right);
        add(up);
        add(down);
        add(zoom_in);
        add(zoom_out);
        zoom_in.addActionListener(this);
        zoom_out.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
     
    }
    
    public void actionPerformed(ActionEvent e)
    {
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
            dx--;
        if(e.getSource()==right)
            dx++;
        if(e.getSource()==up)
            dy--;
        if(e.getSource()==down)
            dy++;
        
        repaint();
    }
    
    public void paint(Graphics g)
    {
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        
        obj.setData(origin_x,origin_y,getWidth(),getHeight(),getX(),getY(),g);
        obj.colorChange(Color.GRAY,Color.BLACK);
        obj.drawHorizontal();
        obj.drawVertical();
        obj.drawAxes();
        
        topleft_house_x=origin_x+dx*obj.bw;
        topleft_house_y=origin_y+dy*obj.bh;
        
        obj2.setData(topleft_house_x,topleft_house_y,obj.bw,obj.bh,g);
        obj2.draw();
    }
    
}


