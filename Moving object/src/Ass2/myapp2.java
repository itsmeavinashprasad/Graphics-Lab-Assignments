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
implements MouseListener,MouseMotionListener
{
    private int origin_x,origin_y,topleft_house_x,topleft_house_y;
    private ArrayList<Integer> button_positions;
    private int bw,bh,dx=-5,dy=-5;
    private int zoomlevel=15,flag=0;
    Graphics g;
    
    private Grid obj;
    private House obj2;
    private Buttons obj3;
    
    public void init()
    {
        this.setSize(800,600);
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.WHITE);
        
        obj= new Grid();
        obj2=new House();
        obj3=new Buttons();
        
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        topleft_house_x=origin_x-(obj.bw*5);
        topleft_house_y=origin_y-(obj.bh*5);

    }
    
    public void mouseClicked(MouseEvent m)
    {
        int mx = m.getX();
        int my = m.getY();
        //0=width, 1=height, 2=x, 3=y1, 4=y2, 5=y3, 6=y4, 7=y5, 8=y6
        if((mx>=button_positions.get(2))
            &&(mx<=button_positions.get(2)+button_positions.get(2)+button_positions.get(0)))
        {
            //LEFT BUTTON CLICKED
            if((my>=button_positions.get(3))&&(my<=button_positions.get(3)+button_positions.get(1)))
            {
                System.out.println("LEFT");
                dx--;
            }
            //RIGHT BUTTON CLICKED
            if((my>=button_positions.get(4))&&(my<=button_positions.get(4)+button_positions.get(1)))
            {
                System.out.println("RIGHT");
                dx++;
            }
            
            //UP BUTTON CLICKED
            if((my>=button_positions.get(5))&&(my<=button_positions.get(5)+button_positions.get(1)))
            {
                System.out.println("UP");
                dy--;
            }
            
            //DOWN
            if((my>=button_positions.get(6))&&(my<=button_positions.get(6)+button_positions.get(1)))
            {
                System.out.println("DOWN");
                dy++;
            }
            
            //ZOOM IN
            if((my>=button_positions.get(7))&&(my<=button_positions.get(7)+button_positions.get(1)))
            {
                if(zoomlevel<25)
                {
                    obj.bh+=2;
                    obj.bw+=2;
                    zoomlevel++;
                }
                System.out.println("ZOOM OUT");
                
            }
            
            //ZOOM OUT
            if((my>=button_positions.get(8))&&(my<=button_positions.get(8)+button_positions.get(1)))
            {
                if(zoomlevel>6)
                {
                    obj.bh-=2;
                    obj.bw-=2;
                    zoomlevel--;
                }
                System.out.println("ZOOM OUT");
            }
            flag=1;
        }
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
        
        obj3.setData(getWidth(),getHeight(),getX(),getY(),g);
        obj3.colorChange(new Color(255,231,147),Color.BLACK);
        obj3.draw();
        button_positions=obj3.getData();
    }
    public void mouseEntered(MouseEvent m)
    {
    }
    public void mouseExited(MouseEvent m)
    {
    }
    public void mousePressed(MouseEvent m)
    {
    }
    public void mouseReleased(MouseEvent m)
    {
    }
    public void mouseMoved(MouseEvent m)
    {
    }
    public void mouseDragged(MouseEvent m)
    {
    }
}


