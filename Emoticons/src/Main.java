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

public class Main extends Applet
        implements MouseListener,MouseMotionListener,ActionListener
{
    private int origin_x,origin_y;
    
    private int dx=0,dy=0;
    private int zoomlevel=10,flag=0;
    private Graphics g;
    private int x1,x2,y1,y2;
    private int flag_happy, flag_upset, flag_angry, flag_cunning, flag_fearful, flag_surprized, flag_sad;
    
    private Grid obj;
    private circle obj2;
    private ellipse obj3;
    
    private Happy obj_happy=new Happy();
    private Upset obj_upset=new Upset();
    private Angry obj_angry= new Angry();
    private Cunning obj_cunning= new Cunning();
    private Fearful obj_fearful=new Fearful();
    private Surprized obj_surprized=new Surprized();
    private Sad obj_sad=new Sad();
    
    private Button left,right,up,down,zoom_in,zoom_out,clear;
    private Button happy, upset,angry, cunning,fearful,surprized,sad;
    public void init()
    {
        this.setSize(800,600);
        setBackground(Color.white);
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
        
        happy=new Button("HAPPY");
        upset=new Button("UPSET");
        angry=new Button("ANGRY");
        cunning=new Button("CUNNING");
        fearful=new Button("FEARFUL");
        surprized=new Button("SURPRIZED");
        sad=new Button("SAD");
        
        add(clear);
        add(left);
        add(right);
        add(up);
        add(down);
        add(zoom_in);
        add(zoom_out);
        
        add(happy);
        add(upset);
        add(angry);
        add(cunning);
        add(fearful);
        add(surprized);
        add(sad);
        
        
        clear.addActionListener(this);
        zoom_in.addActionListener(this);
        zoom_out.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
        
        happy.addActionListener(this);
        upset.addActionListener(this);
        angry.addActionListener(this);
        cunning.addActionListener(this);
        fearful.addActionListener(this);
        surprized.addActionListener(this);
        sad.addActionListener(this);
        
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        
    }
    
    public void paint(Graphics g)
    {
        int space1=20,space2=10;
        
        happy.setLocation(getWidth()-100, getY()+space2);
        upset.setLocation(getWidth()-100, getY()+space1+2*space2);
        angry.setLocation(getWidth()-100, getY()+2*space1+3*space2);
        cunning.setLocation(getWidth()-100, getY()+3*space1+4*space2);
        fearful.setLocation(getWidth()-100, getY()+4*space1+5*space2);
        surprized.setLocation(getWidth()-100, getY()+5*space1+6*space2);
        sad.setLocation(getWidth()-100, getY()+6*space1+7*space2);
        
        left.setLocation(getX()+space1, getY()+space2);
        right.setLocation(getX()+space1, getY()+space1+2*space2);
        up.setLocation(getX()+space1, getY()+2*space1+3*space2);
        down.setLocation(getX()+space1, getY()+3*space1+4*space2);
        zoom_in.setLocation(getX()+space1, getY()+4*space1+5*space2);
        zoom_out.setLocation(getX()+space1, getY()+5*space1+6*space2);
        clear.setLocation(getX()+space1, getY()+6*space1+7*space2);
        
        int radius=50;
        g.setColor(Color.black);
        //set origin
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        
        if(flag_happy==1)
        {
            obj_happy.setData(dx,dy,origin_x,origin_y,obj.bd,g);
            obj_happy.draw();
        }
        else if(flag_upset==1)
        {
            obj_upset.setData(dx,dy,origin_x,origin_y,obj.bd,g);
            obj_upset.draw();
        }
        else if(flag_angry==1)
        {
            obj_angry.setData(dx,dy,origin_x,origin_y,obj.bd,g);
            obj_angry.draw();   
        }
        else if(flag_cunning==1)
        {
            obj_cunning.setData(dx,dy,origin_x,origin_y,obj.bd,g);
            obj_cunning.draw();
        }
        else if(flag_fearful==1)
        {
            obj_fearful.setData(dx,dy,origin_x,origin_y,obj.bd,g);
            obj_fearful.draw();
        }
        else if(flag_surprized==1)
        {
            obj_surprized.setData(dx,dy,origin_x,origin_y,obj.bd,g);
            obj_surprized.draw();
        }
        else if(flag_sad==1)
        {
            obj_sad.setData(dx,dy,origin_x,origin_y,obj.bd,g);
            obj_sad.draw();
        }
        
       
//        draw grid
        obj.setData(origin_x,origin_y,getWidth(),getHeight(),getX(),getY(),g);
//        obj.chnageColor(new Color(255,242,0), new Color(255,242,0));
//        obj.drawHorizontal();
//        obj.drawAxes();
        
        
        
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
        else if(e.getSource()==zoom_out)
        {
            if(zoomlevel>8)
            {
                obj.bd-=2;
                obj.bd-=2;
                zoomlevel--;
            }
        }
        else if(e.getSource()==left)
            dx--;
        else if(e.getSource()==right)
            dx++;
        else if(e.getSource()==up)
            dy--;
        else if(e.getSource()==down)
            dy++;
        else if(e.getSource()==clear)
        {
            System.out.println("clear ");
            flag_happy=0; flag_upset=0; flag_angry=0; flag_cunning=0;
            flag_fearful=0; flag_surprized=0; flag_sad=0;
        }
        else if(e.getSource()==happy)
        {
            System.out.println("happy ");
            flag_happy=1; flag_upset=0; flag_angry=0; flag_cunning=0;
            flag_fearful=0; flag_surprized=0; flag_sad=0;
        }
        else if(e.getSource()==upset)
        {
            flag_happy=0; flag_upset=1; flag_angry=0; flag_cunning=0;
            flag_fearful=0; flag_surprized=0; flag_sad=0;
        }
        else if(e.getSource()==angry)
        {
            flag_happy=0; flag_upset=0; flag_angry=1; flag_cunning=0;
            flag_fearful=0; flag_surprized=0; flag_sad=0;
        }
        else if(e.getSource()==cunning)
        {
            flag_happy=0; flag_upset=0; flag_angry=0; flag_cunning=1;
            flag_fearful=0; flag_surprized=0; flag_sad=0;
        }
        else if(e.getSource()==fearful)
        {
            flag_happy=0; flag_upset=0; flag_angry=0; flag_cunning=0;
            flag_fearful=1; flag_surprized=0; flag_sad=0;
        }
        else if(e.getSource()==surprized)
        {
            flag_happy=0; flag_upset=0; flag_angry=0; flag_cunning=0;
            flag_fearful=0; flag_surprized=1; flag_sad=0;
        }
        else if(e.getSource()==sad)
        {
            flag_happy=0; flag_upset=0; flag_angry=0; flag_cunning=0;
            flag_fearful=0; flag_surprized=0; flag_sad=1;
        }
        repaint();
    }    
}