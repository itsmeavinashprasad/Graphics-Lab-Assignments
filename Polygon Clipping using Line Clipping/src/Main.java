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
            time=0,
            xmax=30,
            xmin=-30,
            ymax=25,
            ymin=-25,
            point_toggle=0,
            draw_toggle=0,
            clip_toggle=0;
    private int b1, b2, b3, b4;
    private double m;
    private static Tuplexy p0, p1, p2, p3, p4, p5, p6, origin;
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
        clip=new Button("->CLIP<-");
        add(draw);
        add(clip);
        clip.addActionListener(this);
        draw.addActionListener(this);
        
        grid= new Grid();
        line = new DDA();
        highlight = new Highlight();
    }
    public void paint(Graphics g)
    {
        //set origin
        origin.setLoc((getX()+getWidth())/2 , (getY()+getHeight())/2);
        bd=grid.bd;
        
        //highlight area
        g.setColor(Color.white);
        g.fillRect(origin.x+xmin*bd, origin.y+ymin*bd, (xmax-xmin)*bd,(ymax-ymin)*bd);
        g.setColor(new Color(100,100,100));
        line.draw(0-origin.x, ymin, getWidth(), ymin, origin.x, origin.y, bd, g);
        line.draw(0-origin.x, ymax, getWidth(), ymax, origin.x, origin.y, bd, g);
        line.draw(xmin, 0-origin.y, xmin, getHeight(), origin.x, origin.y, bd, g);
        line.draw(xmax, 0-origin.y, xmax, getHeight(), origin.x, origin.y, bd, g);
        
        //drwstat
        int[] arr= new int[6];
        for(int i=0; i<6; i++)
        {
            Tuplexy point1=null, point2= null;
            this.resetBits();
            switch(i)
            {
                case 0: point1=p0;  point2=p1;  break;
                case 1: point1=p1;  point2=p2;  break;
                case 2: point1=p2;  point2=p3;  break;
                case 3: point1=p3;  point2=p4;  break;
                case 4: point1=p4;  point2=p5;  break;
                case 5: point1=p5;  point2=p0;  break;
            }
            if(ifdrawable(point1, point2)==false)
                arr[i]=0;
            else
                arr[i]=1;
        }
        
        for(int i=0; i<6 && (clip_toggle==1); i++)
        {
            System.out.println("I:"+i);
//            drawSides(g);
            
//            try {
//                Thread.sleep(2000);
//            } catch (Exception e) {}
            
            Tuplexy point1=null, point2= null;
            this.resetBits();
            switch(i)
            {
                case 0: point1=p0;  point2=p1;  break;
                case 1: point1=p1;  point2=p2;  break;
                case 2: point1=p2;  point2=p3;  break;
                case 3: point1=p3;  point2=p4;  break;
                case 4: point1=p4;  point2=p5;  break;
                case 5: point1=p5;  point2=p0;  break;
            }
            
            if(arr[i]==0)
            {
                System.out.println("cant draw");
                continue;
            }
            else
            {
                m=getSlope(point1, point2);
                ArrayList<Tuplexy> temp = clip(point1, point2);
                point1=temp.get(0);
                point2=temp.get(1);
                double m2= getSlope(point1, point2);
                if((int)m !=(int) m2)
                {
//                    Tuplexy temp2=point1;   point1=point2; point2=temp2;
                }
                switch(i)
                {
                    case 0: p0=point1;  p1=point2;  
                    System.out.println("0 1");
                    break;
                    case 1: p1=point1;  p2=point2;
                    System.out.println(" 1 2");
                    break;
                    case 2: p2=point1;  p3=point2;
                    System.out.println(" 2 3");
                    break;
                    case 3: p3=point1;  p4=point2;
                    System.out.println(" 3 4");
                    break;
                    case 4: p4=point1;  p5=point2;
                    System.out.println("4 5");
                    break;
                    case 5: p5=point1;  p0=point2;
                    System.out.println("5 0"); 
                    break;
                }
            }
            System.out.println();
            showStatus("----------> Cropped <-------------");
        }

    

        
        
        
        //draw line
        if(draw_toggle==1)
        {
            drawSides(g);
            showStatus("----------> Drawn <-------------");
        }


        drawEndPoints(g);
        drawPointsNames(g);
        drawGrid(g);
        clip_toggle=0;
        System.out.println("===================================================");
    }
    
    void drawEndPoints(Graphics g)
    {
        g.setColor(lines);
        highlight.draw(p0.x, p0.y, origin.x, origin.y, bd, g);
        highlight.draw(p1.x, p1.y, origin.x, origin.y, bd, g);
        highlight.draw(p2.x, p2.y, origin.x, origin.y, bd, g);
        highlight.draw(p3.x, p3.y, origin.x, origin.y, bd, g);
        highlight.draw(p4.x, p4.y, origin.x, origin.y, bd, g);
        highlight.draw(p5.x, p5.y, origin.x, origin.y, bd, g);
        
    }
    void drawPointsNames(Graphics g)
    {
        //
        g.setColor(Color.BLACK);
        g.drawString("0", origin.x+p0.x*bd, origin.y+p0.y*bd);
        g.drawString("1", origin.x+p1.x*bd, origin.y+p1.y*bd);
        g.drawString("2", origin.x+p2.x*bd, origin.y+p2.y*bd);
        g.drawString("3", origin.x+p3.x*bd, origin.y+p3.y*bd);
        g.drawString("4", origin.x+p4.x*bd, origin.y+p4.y*bd);
        g.drawString("5", origin.x+p5.x*bd, origin.y+p5.y*bd);
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
        line.draw(p0.x, p0.y, p1.x, p1.y, origin.x, origin.y, bd, g);
        line.draw(p1.x, p1.y, p2.x, p2.y, origin.x, origin.y, bd, g);
        line.draw(p2.x, p2.y, p3.x, p3.y, origin.x, origin.y, bd, g);
        line.draw(p3.x, p3.y, p4.x, p4.y, origin.x, origin.y, bd, g);
        line.draw(p4.x, p4.y, p5.x, p5.y, origin.x, origin.y, bd, g);
        line.draw(p5.x, p5.y, p0.x, p0.y, origin.x, origin.y, bd, g);
    }
    double getSlope(Tuplexy point1, Tuplexy point2)
    {
        double m2;
        m2=(point2.y-point1.y)/(double)(point2.x-point1.x);
        return m2;
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
//        System.out.println("point : "+str+"\tno: "+n);
    }
    
    ArrayList<Tuplexy> clip(Tuplexy point1, Tuplexy point2)
    {
        int px,py;
        int 
            x1=point1.x,
            x2=point2.x,
            y1=point1.y,
            y2=point2.y;
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
            
            //switch case
            switch(i)
            {
                case 0: point1.setLoc(px, py);  break;
                case 1: point2.setLoc(px, py);  break;
            }
        }
        ArrayList<Tuplexy> temp =new ArrayList();
        temp.add(point1);   temp.add(point2);
        return temp;
        
    }
    
    
    boolean ifdrawable(Tuplexy point1, Tuplexy point2)
    {
        int 
                x1=point1.x,
                x2=point2.x,
                y1=point1.y,
                y2=point2.y;
        
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
            return true;
        else return false;
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