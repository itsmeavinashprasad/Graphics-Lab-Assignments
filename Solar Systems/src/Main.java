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
    private int dirty_flag=1, toggle_path=1, toggle_fill=1, first_flag=1;
    private Planet venus, mercury, earth, mars, jupiter, saturn, urenus, naptune;
    private ArrayList<Planet> arr;
    private Circle circle;
    private SaturnEllipse ellipse,ellipse2;
    private Button left,right,up,down,zoom_in,zoom_out, show_path, fill;
    
    //image and mediatracker object
    private Image img;
    private MediaTracker tr;
    
    @Override
    public void init()
    {
        
        this.setSize(1366,662);
        setBackground(Color.black);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //initialise image object and media tracker
        tr=new MediaTracker(this);
        img=getImage(getCodeBase(),"milkyway.jpg");
        
        zoom_in=new Button("ZOOM IN");
        zoom_out=new Button("ZOOM OUT");
        left=new Button("LEFT");
        right=new Button("RIGHT");
        up=new Button("UP");
        down=new Button("DOWN");
        show_path=new Button("TOGGLE PATH");
        fill=new Button("FILL PLANET");
        
        add(show_path);
        add(left);
        add(right);
        add(up);
        add(down);
        add(zoom_in);
        add(zoom_out);
        add(fill);
        
        zoom_in.addActionListener(this);
        zoom_out.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
        show_path.addActionListener(this);
        fill.addActionListener(this);
        
        obj=new Grid();

        arr = new ArrayList<Planet>();

        arr.add(new Planet(50,20,4,1,Color.red));    // mercury
        arr.add(new Planet(80, 35, 6,3, new Color(128,128,128)));   //venus
        arr.add(new Planet(110, 50,7 ,2, Color.blue));   // earth
        arr.add(new Planet(155, 70, 9,4, new Color(185,122,87)));   //mars
        arr.add(new Planet(200, 95, 14,7, new Color(228,115,0)));   //jupiter
        arr.add(new Planet(245, 125, 11,6, new Color(112,104,73)));   //saturn
        arr.add(new Planet(295, 145, 7,7, new Color(145,202,221)));   //urenus
        arr.add(new Planet(330, 160, 6,5, new Color(150,3,220)));   //neptune
        
        ellipse= new SaturnEllipse();
        ellipse2= new SaturnEllipse();
        circle = new Circle();
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        
    }
    
    public void paint(Graphics g)
    {
        int space1=20,space2=10;
        
        left.setLocation(getX()+space1, getY()+space2);
        right.setLocation(getX()+space1, getY()+space1+2*space2);
        up.setLocation(getX()+space1, getY()+2*space1+3*space2);
        down.setLocation(getX()+space1, getY()+3*space1+4*space2);
        zoom_in.setLocation(getX()+space1, getY()+4*space1+5*space2);
        zoom_out.setLocation(getX()+space1, getY()+5*space1+6*space2);
        show_path.setLocation(getX()+space1, getY()+6*space1+7*space2);
        fill.setLocation(getX()+space1, getY()+7*space1+8*space2);
        
        System.out.println(getWidth()+"\t"+getHeight());
        g.setColor(Color.black);
        System.out.println("In Paint");
        
        //set origin
        origin_x=(getX()+getWidth())/2;
        origin_y=(getY()+getHeight())/2;
        bd=obj.bd;
        
        //image add
        tr.addImage(img,0);
        g.drawImage(img, 0, 0, this);
        // end 
        
        Tuple temp;
        int i=0;
        while(i<arr.size())
        {
            if(dirty_flag==1)
            {
                System.out.println("Repaint");
                arr.get(i).draw(dx,dy,origin_x, origin_y, bd);
            }
            if(toggle_path==1)
            {
                this.fillPath(arr.get(i).path, g);
            }
            
            temp = (arr.get(i)).getLocation();
            if(i==5)
            {   
                System.out.println("In Saturn");
                ellipse.setData(temp.x, temp.y, 25, 8, origin_x, origin_y, bd,g);
                ellipse.draw();
            }
            if(toggle_fill==1)
            {
                this.fillPlanet(g, temp, arr.get(i).radius,arr.get(i).c);
                g.setColor(new Color(254,221,33));
                g.fillOval(dx*bd+origin_x+bd/2-30, dy*bd+origin_y+bd/2-30, 60, 60);
            }
            circle.draw(temp.x, temp.y, arr.get(i).radius, origin_x, origin_y, bd, g, Color.black);
            i++;
            
        }
        dirty_flag=0;

        
//        Sun
        g.setColor(new Color(254,221,33));
        circle.draw(dx, dy, 15, origin_x, origin_y, bd, g, Color.black);
//        g.fill
        
//        Grid
        obj.setData(origin_x, origin_y,getWidth(), getHeight(), getX(), getY(), g);
//        obj.chnageColor(Color.gray, Color.gray);
//        obj.drawHorizontal();
        try
        {
            Thread.sleep(100);
            repaint();
        }catch(Exception e){}
        
    }
     public void fillPath(ArrayList<Tuple> path,Graphics g)
     {
         g.setColor(Color.lightGray);
         for(int i=0; i<path.size(); i++)
         {
             g.fillRect(origin_x+path.get(i).x*bd, origin_y+path.get(i).y*bd, bd, bd);
         }
     }
     public void fillPlanet(Graphics g,Tuple temp, int radius, Color c)
     {
         g.setColor(c);
         g.fillOval(origin_x+(temp.x-radius)*bd+bd/2,origin_y+(temp.y-radius)*bd+bd/2 , radius*bd*2, radius*bd*2);
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
        dirty_flag=1;
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
        dirty_flag=1;
        
        if(e.getSource()==show_path)
        {
            toggle_path=1-toggle_path;
        }
        if(e.getSource()==fill)
        {
            toggle_fill=1-toggle_fill;
            dirty_flag=0;
        }
        repaint();
    } 
    
}