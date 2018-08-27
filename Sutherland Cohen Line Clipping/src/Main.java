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
    

    private int flag=0,bd;
    private Graphics g;
    private int x1=0,x2=0,y1=0,y2=0,click_flag=0,draw_flag=0, clip_flag=0, drawable=0;

    private int xmin=-30, xmax=+30, ymin=-23, ymax=+23;
    private static int b1=0, b2=0, b3=0, b4=0;
    private double m;
    private int count=0;
    private Grid obj;
    private DDA obj2;
    private Highlight obj5;
    private Button draw, clip;
    public void init()
    {
        this.setSize(800,600);
        setBackground(Color.lightGray);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        draw= new Button("DRAW");
        add(draw);
        draw.addActionListener(this);
        clip= new Button("CLIP");
        add(clip);
        clip.addActionListener(this);
        
        obj= new Grid();
        obj2=new DDA();
        obj5=new Highlight();
        
        
    }
    
    public void paint(Graphics g)
    {
        System.out.println("Paint called "+count+++"times");
        //set origin
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        bd=obj.bd;
        
        //highlight area
        g.setColor(Color.white);
        g.fillRect(origin_x+xmin*bd, origin_y+ymin*bd, (xmax-xmin)*bd,(ymax-ymin)*bd);
        g.setColor(new Color(100,100,100));
        obj2.draw(0-origin_x, ymin, getWidth(), ymin, origin_x, origin_y, bd, g);
        obj2.draw(0-origin_x, ymax, getWidth(), ymax, origin_x, origin_y, bd, g);
        obj2.draw(xmin, 0-origin_y, xmin, getHeight(), origin_x, origin_y, bd, g);
        obj2.draw(xmax, 0-origin_y, xmax, getHeight(), origin_x, origin_y, bd, g);
        
        
        
            
        //calculate slope of the line

        if(flag==0)
            m=0;    
        else
            m=(y2-y1)/(double)(x2-x1);
        System.out.println("m: "+m);
        int drawable_flag=ifdrawable();
        
        if(clip_flag==1)
        {
            clip();

            
            showStatus("----------> Cropped <-------------");
        }

        if(drawable_flag==0)
        {
            showStatus("Line can not be drawn");
//            System.out.println("Line is not drawable");
        }
        
        
        //draw line
            if(((draw_flag==1) && (clip_flag==1) && (drawable_flag==1)) ||  ((draw_flag==1) &&(clip_flag==0)))
            {
                g.setColor(Color.blue);
                obj2.draw(x1, y1, x2, y2, origin_x, origin_y, bd, g);
                showStatus("----------> Drawn <-------------");
            }
         clip_flag=0;    
        
        //highlight end points
        obj5.draw(x1,y1,x2,y2,origin_x,origin_y, bd, g);   
        
        
        //draw grid
        obj.chnageColor( new Color(180,180,180), new Color(180,180,180));
        obj.setData(origin_x,origin_y,getWidth(),getHeight(),getX(),getY(),g);
        obj.drawHorizontal();
        obj.drawAxes();
        System.out.println("===================================================");
    }
    void resetBits()
    {
        b1=0; b2=0; b3=0; b4=0;
    }
    void setBits(int px, int py, int point)
    {
        if(py<=ymin)
            b2=1;
        else b2=0;
        if(py>=ymax)
            b1=1;
        else b1=0;
        if(px<xmin)
            b4=1;
        else b4=0;
        if(px>xmax)
            b3=1;
        else b3=0;
        String str = b1+""+b2+""+b3+""+b4;
        int n=Integer.parseInt(str, 2);
        System.out.println("point : "+str+"\tno: "+n);
    }
    
    void clip()
    {
        int px,py;
        for(int i=0;i<2;i++)
        {
            if(i==0)
            {px=x1;  py=y1;}
            else
            {px=x2;  py=y2;}
            
            //----------setbits-------------
            resetBits();
            setBits(px,py,i+1);
            
            //bottom
            if(b1!=0)
            {
                px=(int)(px+(double)(ymax-py)/m);
                py=ymax;
            }
            //top
            if(b2!=0)
            {
                px=(int)(px+(double)(ymin-py)/m);
                py=ymin;
            }
            //right
            if(b3!=0)
            {
                py=(int)(py+m*(xmax-px));
                px=xmax;
            }
            //left
            if(b4!=0)
            {
                py=(int)(py+m*(xmin-px));
                px=xmin;
            }
            if(i==0)
            {
                x1=px; y1=py;
            }
            else
            {
                x2=px; y2=py;
            }
        }
        
    }
    int ifdrawable()
    {
        //if drawable
        resetBits();
        setBits(x1,y1,1);
        String str = b1+""+b2+""+b3+""+b4;
        int n1=Integer.parseInt(str, 2);
//        System.out.println("point : "+str+"\tno: "+n1);
        
        resetBits();
        setBits(x2,y2,2);
        String str2 = b1+""+b2+""+b3+""+b4;
        int n2=Integer.parseInt(str2, 2);
//        System.out.println("point : "+str2+"\tno: "+n2);
        
        if((n1 & n2) == 0)
            return 1;
        else return 0;
    }
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        int mx,my,x,y;
        mx=e.getX()-origin_x;      my=e.getY()-origin_y;
        
        
        //====================get box locations=======================
        
        if(mx>=0)
        {
            x=mx/bd;
            if(my>0)
                y=my/bd;
            else
                y=my/bd-1;
        }
        else
        {
            x=mx/bd-1;
            if(my>0)
                y=my/bd;
            else
                y=my/bd-1;
        }
        
        if(click_flag==0)
        {
            x1=x;   y1=y;
            click_flag=1;
        }
        else
        {
            x2=x;   y2=y;
            click_flag=0;
        }
        draw_flag=0;
        clip_flag=0;
        flag=1;
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
        if(e.getSource()==draw)
        {
            draw_flag=1;
            clip_flag=0;
        }
        if(e.getSource()==clip)
        {
            clip_flag=1;
            if(drawable==0)
                draw_flag=1;
        }
        repaint();
    }    
}