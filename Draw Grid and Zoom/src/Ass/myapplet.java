package Ass;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.math.*;

public class myapplet extends Applet
implements MouseListener,MouseMotionListener
{
    //set origin
    private int origin_x,origin_y;
    private int x1,x2,y1,y2;
    private int bh=50,bw=50;
    private int zoomlevel=5;
    private int button_height=30;
    private int button_width=30;
    private int box_row,box_col,xtopleft,ytopleft;
    

    public void init()
    {
        this.setSize(800,600);
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.WHITE);

    }
    public void mouseEntered(MouseEvent m)
    {

    //repaint();
    }
    public void mouseExited(MouseEvent m)
    {

    }
    public void paint(Graphics g)
    {
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        
        //set parameters
        int x1=getX(),x2=getWidth(),y1=origin_y,y2=origin_y;
        g.setColor(Color.GRAY);

        //=========================DRAW LINES=======================
        //draw horizontal lines
        while(y1>=getY())
        {
            y1=y1-bh;
            y2=y1;
            g.drawLine(x1,y1,x2,y2);

        }
        y1=origin_y;
        y2=origin_y;
        while(y1<=getHeight())
        {
            y1=y1+bh;
            y2=y1;
            g.drawLine(x1,y1,x2,y2);

        }

        //draw vertical lines
        x1=origin_x;
        x2=origin_x;
        y1=getY();
        y2=getHeight();

        while(x1>=getX())
        {
            x1=x1-bw;
            x2=x1;
            g.drawLine(x1,y1,x2,y2);
        }
        x1=origin_x;
        x2=origin_x;
        while(x1<=getWidth())
        {
            x1=x1+bw;
            x2=x1;
            g.drawLine(x1,y1,x2,y2);
        }

        //draw highlighted box
        g.setColor(Color.RED);
        xtopleft=box_row*bw;
        ytopleft=box_col*bh;

        g.fillRect(origin_x+xtopleft, origin_y+ytopleft, bw, bh);


        //draw axes
        g.setColor(Color.BLACK);
        g.drawLine(getX(),getHeight()/2,getWidth(),getHeight()/2);
        g.drawLine(getX(),getHeight()/2+1,getWidth(),getHeight()/2+1);
        g.drawLine(getX(),getHeight()/2-1,getWidth(),getHeight()/2-1);
        g.drawLine(getWidth()/2,getY(),getWidth()/2,getHeight());
        g.drawLine(getWidth()/2+1,getY(),getWidth()/2+1,getHeight());
        g.drawLine(getWidth()/2-1,getY(),getWidth()/2-1,getHeight());
        

        //=================================draw buttons================================
        g.setFont(new Font("Consolas",1,50));
        //BUTTON 1
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getWidth()-20-button_width,getY()+10,button_width,button_height);
        g.setColor(Color.WHITE);
        g.drawString("+",getWidth()-20-button_width,getY()+10+button_height);

        //BUTTON 2
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getWidth()-20-button_width,getY()+2*10+button_height,button_width,button_height);
        g.setColor(Color.WHITE);
        g.drawString("-",getWidth()-20-button_width,getY()+20+2*button_height);
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
    public void mouseClicked(MouseEvent m)
    {
        int mx = m.getX();
        int my = m.getY();
        //==================================zoom in and zoom out=====================================
        if(((mx>=getWidth()-20-button_width)&&(mx<=getWidth()-20))&&((my>=getY()+10)&&(my<=getY()+10+button_height)||(my>=getY()+2*10+button_height)&&(my<=getY()+2*(10+button_height))))
        {
            if((my>=getY()+10)&&(my<=getY()+10+button_height))
            {
                if(zoomlevel<10)
                {
                    bh=bh+10;
                    bw=bw+10;
                    zoomlevel++;
                    repaint();
                }
            }
            if((my>=getY()+2*10+button_height)&&(my<=getY()+2*(10+button_height)))
            {
                if(zoomlevel>=2)
                {
                    bh=bh-10;
                    bw=bw-10;
                    zoomlevel--;
                    repaint();
                }
            }
        }
        else
        {
            //====================highlight a box=======================
            int xpos=mx-origin_x, ypos=my-origin_y;

            box_row=xpos/bw;
            box_col=ypos/bh;
            if(xpos<0)
            {
                box_row--;
            }
            if(ypos<0)
            {
                box_col--;
            }
            repaint();
            //System.out.println(Integer.toString(xpos)+"   "+Integer.toString(ypos));
            //System.out.println(Integer.toString(box_row)+"   "+Integer.toString(box_col));
            //System.out.println(Integer.toString(xtopleft)+"   "+Integer.toString(ytopleft));   
        }
    }	
}

