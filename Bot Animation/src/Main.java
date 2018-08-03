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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Applet
        implements MouseListener,MouseMotionListener,ActionListener
{
    private int origin_x,origin_y;
    private static int dx=0,dy=0;
    private int zoomlevel=10,flag=0;
    private static Graphics g;
    private int x1,x2,y1,y2,bd;
    private static Grid obj;
    private Body obj2;
    
    private Button left,right,up,down,zoom_in,zoom_out;
    
    @Override
    public void init()
    {
        
        this.setSize(700,650);
        setBackground(Color.white);
        addMouseListener(this);
        addMouseMotionListener(this);
        
//        zoom_in=new Button("ZOOM IN");
//        zoom_out=new Button("ZOOM OUT");
//        left=new Button("LEFT");
//        right=new Button("RIGHT");
//        up=new Button("UP");
//        down=new Button("DOWN");
        
//        add(left);
//        add(right);
//        add(up);
//        add(down);
//        add(zoom_in);
//        add(zoom_out);
//        
//        zoom_in.addActionListener(this);
//        zoom_out.addActionListener(this);
//        left.addActionListener(this);
//        right.addActionListener(this);
//        up.addActionListener(this);
//        down.addActionListener(this);
        
        obj=new Grid();
        obj2 = new Body();
        
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        
    }
    
    public void paint(Graphics g)
    {
        int space1=20,space2=10;
        
//        left.setLocation(getX()+space1, getY()+space2);
//        right.setLocation(getX()+space1, getY()+space1+2*space2);
//        up.setLocation(getX()+space1, getY()+2*space1+3*space2);
//        down.setLocation(getX()+space1, getY()+3*space1+4*space2);
//        zoom_in.setLocation(getX()+space1, getY()+4*space1+5*space2);
//        zoom_out.setLocation(getX()+space1, getY()+5*space1+6*space2);
        
        
        //set origin
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        bd=obj.bd;
        g.setColor(Color.BLACK);
        
        //call body
        obj2.draw(origin_x, origin_y, bd, g);

//        Grid
        obj.setData(origin_x, origin_y,getWidth(), getHeight(), getX(), getY(), g);
        obj.chnageColor(Color.gray, Color.gray);
//        obj.drawHorizontal();
        
        g.drawLine(getX(), origin_y+247, getWidth(), origin_y+247);
        g.drawLine(getX(), origin_y+248, getWidth(), origin_y+248);
        g.drawLine(getX(), origin_y+249, getWidth(), origin_y+249);
        try
        {
            Thread.sleep(125);
//            repaint(origin_x-(70*bd), origin_y-(120*bd),300,500);
            repaint();
            
        }catch(Exception e){}
        
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
        repaint();
    } 
    
}