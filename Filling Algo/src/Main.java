/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Avinash Kumar Prasad
 */
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main 
        extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    private static int
            bd,
            point_toggle=0,
            draw_toggle=0,
            toggle_fill=0,
            time=0;
    private static Tuplexy p0, p1, p2, p3, p4, p5, p6, origin;
    private Button 
            draw,
            fill_button;
    private Grid grid;
    private DDA line;
    private Highlight highlight;
    private Fill fillthread;
    private Color 
            back = Color.white, 
            lines = Color.blue, 
            fill = Color.red;
    public Graphics g;
    BufferedImage img;      //Buffered Image
    public void init()
    {
        setSize(900,600);   //sets applet size
        setBackground(back);
        addMouseListener(this);
        addMouseMotionListener(this);
        p0= new Tuplexy();
        p1= new Tuplexy();
        p2= new Tuplexy();
        p3= new Tuplexy();
        p4= new Tuplexy();
        p5= new Tuplexy();
        p6= new Tuplexy();
        origin = new Tuplexy();
        
        draw=new Button("->DRAW<-");
        fill_button=new Button(">>FILL<<");
        add(draw);
        add(fill_button);
        fill_button.addActionListener(this);
        draw.addActionListener(this);
        
        img = (BufferedImage) createImage(getSize().width, getSize().height);   //creates blank image
        grid= new Grid();
        line = new DDA();
        highlight = new Highlight();
        
    }
    public void paint(Graphics g)
    {
        
        //set origin
        origin.setLoc((getX()+getWidth())/2 , (getY()+getHeight())/2);
        bd=grid.bd;
//        time=0;
        
        Graphics appletgraphics =g;             //storing applets default graphics in appletgraphics variable
        g=img.getGraphics();                    //assigns Image's graphics to g

        g.setColor(back);
        g.fillRect(0, 0, getSize().width, getSize().height);
        
        //draw sides of the polygon
        if(draw_toggle==1)
        {
            g.setColor(lines);
            line.draw(p0.x, p0.y, p1.x, p1.y, origin.x, origin.y, bd, g);
            line.draw(p1.x, p1.y, p2.x, p2.y, origin.x, origin.y, bd, g);
            line.draw(p2.x, p2.y, p3.x, p3.y, origin.x, origin.y, bd, g);
            line.draw(p3.x, p3.y, p4.x, p4.y, origin.x, origin.y, bd, g);
            line.draw(p4.x, p4.y, p5.x, p5.y, origin.x, origin.y, bd, g);
            line.draw(p5.x, p5.y, p0.x, p0.y, origin.x, origin.y, bd, g);
            
        }
        
        
        //highlight end points
        g.setColor(lines);
        highlight.draw(p0.x, p0.y, origin.x, origin.y, bd, g);
        highlight.draw(p1.x, p1.y, origin.x, origin.y, bd, g);
        highlight.draw(p2.x, p2.y, origin.x, origin.y, bd, g);
        highlight.draw(p3.x, p3.y, origin.x, origin.y, bd, g);
        highlight.draw(p4.x, p4.y, origin.x, origin.y, bd, g);
        highlight.draw(p5.x, p5.y, origin.x, origin.y, bd, g);
        g.setColor(fill);
        highlight.draw(p6.x, p6.y, origin.x, origin.y, bd, g);
        
          //draw grid
        grid.chnageColor( new Color(180,180,180), new Color(180,180,180));
        grid.setData(origin.x,origin.y,getWidth(),getHeight(),getX(),getY(),g);
        grid.drawHorizontal();
        grid.drawAxes();
        
        
        appletgraphics.drawImage(img, 0, 0, null);     //plot that image

        //call fill
        if(toggle_fill==1)
        {
            try
            {
                g.setColor(back);
                g.fillRect(origin.x+p6.x*bd, origin.y+p6.y*bd, bd, bd);
                appletgraphics.drawImage(img, 0, 0, null);
                Fill temp =new Fill(img, g, appletgraphics, back, fill, p6, origin, bd);
                temp.t.join();
                System.out.println("done filing, returned to paint");
            }catch(Exception e){}
            toggle_fill=0;
        }
        
        
        
        System.out.println((++time)+"===================================================");
        
        appletgraphics.drawImage(img, 0, 0, null);     //plot that image        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        int mx,my,x,y;
        mx=e.getX()-origin.x;      my=e.getY()-origin.y;
        
        
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
        
        switch(point_toggle)
        {
            case 0:
                p0.setLoc(x,y);   showStatus(p0.x+","+p0.y);    break;
            case 1:
                p1.setLoc(x,y);   showStatus(p1.x+","+p1.y);    break;
            case 2:
                p2.setLoc(x,y);   showStatus(p2.x+","+p2.y);    break;
            case 3:
                p3.setLoc(x,y);   showStatus(p3.x+","+p3.y);    break;
            case 4:
                p4.setLoc(x,y);   showStatus(p4.x+","+p4.y);    break;
            case 5:
                p5.setLoc(x,y);   showStatus(p5.x+","+p5.y);    break;
            case 6:
                p6.setLoc(x,y);   showStatus(p6.x+","+p6.y);    break;
        }

        point_toggle=(point_toggle+1)%7;
        draw_toggle=0;
        toggle_fill=0;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==draw)
        {
            draw_toggle=1;
            toggle_fill=0;
        }
        if(e.getSource()==fill_button)
        {
//            draw_toggle=0;
            toggle_fill=1;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}



