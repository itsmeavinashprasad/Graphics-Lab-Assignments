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
            max_points=2;

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
    private ArrayList<Tuplexy> poly_points, clipper_points, temp;
    private Window win1, win2;
    private UnionWindow win3;
    private MinusWindow win4;
    public void init()
    {
        setSize(900,600);   //sets applet size
        setBackground(new Color(230,230,230));
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
        clipper_points = new ArrayList();
        temp= new ArrayList();
        for(int i=0; i<max_points; i++)
            poly_points.add(new Tuplexy(70,70));
    }
    public void paint(Graphics g)
    {
        //set origin
        origin.setLoc((getX()+getWidth())/2 , (getY()+getHeight())/2);
        bd=grid.bd;
        ArrayList<Tuplexy> points1, points2;
        points1 = new ArrayList();
        points2 = new ArrayList();
        win1=new Window(-35, 0, -45, -5, bd, origin);
        win1.plot(getWidth(), getHeight(), g);
        
        win2 = new Window(-15, 20, -35, -10, bd, origin);
        win2.plot(getWidth(), getHeight(), g);
        if(clip_toggle==1)
        {
            op("before win1 ");
            temp = (ArrayList<Tuplexy>) poly_points.clone();
            win1.activated=true;
            win1.setPoints(temp);
            win1.clip(g);
            win1.activated=false;
            points1 = (ArrayList<Tuplexy>) temp.clone();
            
            temp.clear();
            temp = (ArrayList<Tuplexy>) poly_points.clone();
            op("before win2");
            win2.activated=true;
            win2.setPoints(temp);
            win2.clip(g);
            win2.activated=false;
            points2 = (ArrayList<Tuplexy>) temp.clone();

            draw_toggle=0;
        }
        win3= new UnionWindow(origin,bd);
        win3.plot(g, points1, points2);
        
        win4 = new MinusWindow(origin, bd);
        win4.plot(g, points1, points2);
        
        
        if(draw_toggle==1)
        {
            this.drawSides(g);
        }
        this.drawEndPoints(g);
        this.drawPointsNames(g);
        grid.setData(origin.x, origin.y, getWidth(), getHeight(), getX(), getY(), g);
        
        if(clip_toggle==1)
        {
            poly_points.clear();
            for(int i=0;i<max_points;i++)
                poly_points.add(new Tuplexy(70,70));
        }
        clip_toggle=0;
        System.out.println("=============end of paint===================");
    }
    void op(String str)
    {
        System.out.println(str);
        System.out.println("Size: "+poly_points.size());
        for(int i=0; i<poly_points.size(); i++)
            System.out.println(poly_points.get(i).x+" , "+poly_points.get(i).y);
        
        System.out.println("--------------------------");
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