
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Avinash Kumar Prasad
 */
class Fill extends Applet implements Runnable
{
    
    public int lock;
    BufferedImage img;
    public Thread t;
    private Color back, fill;
    private Graphics g, appletgraphics;
    private Tuplexy point, origin;
    private int bd;
    private Fill T1,T2,T3,T4, parent;
    
    @Override
    public void start()
    {
        lock=0;
       if(t==null)
       {
           t=new Thread(this );
           t.start();
       }
    }
    @Override
    public void run() 
    {
        g.setColor(fill);
        int x = origin.x+(point.x*bd);
        int y = origin.y+(point.y*bd);
        int d=bd/2;
        synchronized(this)
        {
            try
            {
                if(img.getRGB(x+d, y+d)==back.getRGB())
                {
                    g.fillRect(x, y, bd, bd);
                    appletgraphics.drawImage(img, 0, 0, null);
                    T1= new Fill(img, g,appletgraphics, back, fill, new Tuplexy(point.x,point.y+1), origin, bd,this);
                    T2= new Fill(img, g,appletgraphics, back, fill, new Tuplexy(point.x+1,point.y), origin, bd,this);
                    T3= new Fill(img, g,appletgraphics, back, fill, new Tuplexy(point.x,point.y-1), origin, bd,this);
                    T4= new Fill(img, g,appletgraphics, back, fill, new Tuplexy(point.x-1,point.y), origin, bd,this);

                    this.dowait();
                    try
                    {
                        parent.donotify();
                    }catch(NullPointerException e)
                    {
                        System.out.println("parent");
                    }
                }
                else
                {
                    parent.donotify();
                }
            }
            catch(Exception e){}
        }    
        
    }
    Fill(BufferedImage img, Graphics g, Graphics appletgraphics, Color back, Color fill, Tuplexy point, Tuplexy origin, int bd,Fill parent)
    {
        this.origin=origin;
        this.img=img;
        this.fill=fill;
        this.back=back;
        this.g=g;
        this.appletgraphics=appletgraphics;
        this.bd=bd;
        this.point=point;
        this.parent=parent;
        this.start();
    }
    Fill(BufferedImage img, Graphics g, Graphics appletgraphics, Color back, Color fill, Tuplexy point, Tuplexy origin, int bd)
    {
        this.origin=origin;
        this.img=img;
        this.fill=fill;
        this.back=back;
        this.g=g;
        this.appletgraphics=appletgraphics;
        this.bd=bd;
        this.point=point;
        this.start();
    }
    public void dowait()
    {
        synchronized(this)
        {
            try
            {
                this.wait();
            }catch(Exception e){}
        }
    }
    public void donotify()
    {
        synchronized(this)
        {
            if((++lock)==4)
            {
                try
                {
                    this.notify();
                }catch(Exception e){}
            }
        }
    }
}