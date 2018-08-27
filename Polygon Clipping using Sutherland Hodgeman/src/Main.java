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
    private static int
            bd,
            xmax=30,
            xmin=-30,
            ymax=25,
            ymin=-25,
            point_toggle=0,
            draw_toggle=0,
            clip_toggle=0,
            max_points=6;

    private static Tuplexy origin;
    private Button
            draw,
            clip;
    private Grid grid;
    private DDA line;
    private Highlight highlight;
    private Color
            back = new Color(200,200,200),
            lines = Color.blue;
    public Graphics g;
    private ArrayList<Tuplexy> poly_points, new_poly_points, clipper_points, temp;

    public void init()
    {
        setSize(900,600);   //sets applet size
        setBackground(Color.white);
        addMouseListener(this);
        addMouseMotionListener(this);
        origin = new Tuplexy();

        draw=new Button("->DRAW<-");
        clip=new Button("->CLIP<-");
        add(draw);
        add(clip);
        clip.addActionListener(this);
        draw.addActionListener(this);

        grid= new Grid();
        line = new DDA();
        highlight = new Highlight();

        poly_points= new ArrayList();
        new_poly_points= new ArrayList();
        clipper_points = new ArrayList();
        for(int i=0; i<max_points; i++)
            poly_points.add(new Tuplexy(70,70));

        clipper_points.add(new Tuplexy(xmin, ymin));
        clipper_points.add(new Tuplexy(xmax, ymin));
        clipper_points.add(new Tuplexy(xmax, ymax));
        clipper_points.add(new Tuplexy(xmin, ymax));
    }
    public void paint(Graphics g)
    {
        //set origin
        origin.setLoc((getX()+getWidth())/2 , (getY()+getHeight())/2);
        bd=grid.bd;

        //highlight area
        g.setColor(new Color(100,100,100));
        g.fillRect(origin.x+xmin*bd, origin.y+ymin*bd, (xmax-xmin)*bd,(ymax-ymin)*bd);
        g.setColor(new Color(100,100,100));
        line.draw(0-origin.x, ymin, getWidth(), ymin, origin.x, origin.y, bd, g);
        line.draw(0-origin.x, ymax, getWidth(), ymax, origin.x, origin.y, bd, g);
        line.draw(xmin, 0-origin.y, xmin, getHeight(), origin.x, origin.y, bd, g);
        line.draw(xmax, 0-origin.y, xmax, getHeight(), origin.x, origin.y, bd, g);

        if(clip_toggle==1)
        {
            suthHodgClip();
            draw_toggle=1;
        }
        
        //draw line
        if(draw_toggle==1)
        {
            drawSides(g);
            showStatus("----------> Drawn <-------------");
            draw_toggle=0;
        }


        drawEndPoints(g);
        drawPointsNames(g);
        drawGrid(g);
        if(clip_toggle==1)
        {
            poly_points.clear();
            for(int i=0;i<max_points;i++)
                poly_points.add(new Tuplexy(70,70));
        }
        clip_toggle=0;
        showStatus("Select polygon point: "+(point_toggle+1));
        System.out.println("===================================================");

    }
    
    void drawEndPoints(Graphics g)
    {
        g.setColor(lines);
        for(int i=0; i<poly_points.size(); i++)
            highlight.draw(poly_points.get(i).x, poly_points.get(i).y, origin.x, origin.y, bd, g);
    }
    void drawPointsNames(Graphics g)
    {
        //
        g.setColor(Color.white);
        for(int i=0; i<poly_points.size(); i++)
            g.drawString(Integer.toString(i)+". "+poly_points.get(i).x+","+poly_points.get(i).y,origin.x+poly_points.get(i).x*bd, origin.y+poly_points.get(i).y*bd);
    }
    void drawGrid(Graphics g)
    {
        //draw grid
        grid.chnageColor( new Color(180,180,180), new Color(180,180,180));
        grid.setData(origin.x,origin.y,getWidth(),getHeight(),getX(),getY(),g);
        grid.drawHorizontal();
        grid.drawAxes();

    }
    void drawSides(Graphics g)
    {
        g.setColor(lines);
        for(int i=0; i<poly_points.size(); i++)
        {
            int k=(i+1)%poly_points.size();
//            System.out.println("i: "+i+"\tk: "+k);
            line.draw(poly_points.get(i).x, poly_points.get(i).y, poly_points.get(k).x, poly_points.get(k).y, origin.x, origin.y, bd, g);
        }
    }
    
    
    void suthHodgClip()
    {
        for(int i=0; i<clipper_points.size(); i++)
        {
            int k=(i+1)%clipper_points.size();
            clip(i,k);
        }
    }
    
    void clip(int p1, int p2)
    {
        int x1=clipper_points.get(p1).x, x2=clipper_points.get(p2).x, y1=clipper_points.get(p1).y, y2=clipper_points.get(p2).y;

        for(int i=0; i<poly_points.size(); i++)
        {
            
            int k= (i+1)%poly_points.size();
            int ix=poly_points.get(i).x, iy=poly_points.get(i).y, kx=poly_points.get(k).x, ky=poly_points.get(k).y;

            // Calculating position of first point
            // w.r.t. clipper line
            int i_pos = (x2-x1) * (iy-y1) - (y2-y1) * (ix-x1);

            // Calculating position of second point
            // w.r.t. clipper line
            int k_pos = (x2-x1) * (ky-y1) - (y2-y1) * (kx-x1);

            i_pos=-i_pos;
            k_pos=-k_pos;
            
            // Case 1 : When both points are inside
            if (i_pos <= 0  && k_pos <= 0)
            {
                //Only second point is added
                new_poly_points.add(poly_points.get(k));
            }

            // Case 2: When only first point is outside
            else if (i_pos > 0  && k_pos <= 0)
            {
                // Point of intersection with edge
                // and the second point is added
                new_poly_points.add(getIntersect(clipper_points.get(p1), clipper_points.get(p2), poly_points.get(i), poly_points.get(k)));
                new_poly_points.add(poly_points.get(k));
            }

            // Case 3: When only second point is outside
            else if (i_pos <= 0  && k_pos > 0)
            {
                //Only point of intersection with edge is added
                new_poly_points.add(getIntersect(clipper_points.get(p1), clipper_points.get(p2), poly_points.get(i), poly_points.get(k)));
            }
            
            // Case 4: When both points are outside
            else
            {
                //No points are added
            }
        }
        for(int i=0;i<new_poly_points.size(); i++)
        {
            if(i<poly_points.size())
                poly_points.set(i, new_poly_points.get(i));
            else
                poly_points.add(new_poly_points.get(i));
        }
        new_poly_points.clear();
    }
    Tuplexy getIntersect(Tuplexy p1, Tuplexy p2, Tuplexy p3, Tuplexy p4)
    {
        int num_x = (p1.x*p2.y - p1.y*p2.x) * (p3.x-p4.x) - (p1.x-p2.x) * (p3.x*p4.y - p3.y*p4.x);
        int num_y = (p1.x*p2.y - p1.y*p2.x) * (p3.y-p4.y) - (p1.y-p2.y) * (p3.x*p4.y - p3.y*p4.x);
        int den = (p1.x-p2.x)*(p3.y-p4.y) - (p1.y-p2.y)*(p3.x-p4.x);
        return new Tuplexy(num_x/den, num_y/den);
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

        poly_points.set(point_toggle, new Tuplexy(x,y));
        point_toggle=(point_toggle+1)%max_points;


        draw_toggle=0;
        clip_toggle=0;
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
            draw_toggle=1;
            clip_toggle=0;
//            crop_flag=0;
        }
        if(e.getSource()==clip)
        {
            clip_toggle=1;
//            draw_toggle=0;
//            if(drawable==0)
//                draw_flag=1;
        }
        repaint();
    }
}