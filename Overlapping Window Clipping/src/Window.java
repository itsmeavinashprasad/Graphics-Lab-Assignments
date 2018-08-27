
import java.awt.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Avinash Kumar Prasad
 */
public class Window 
{
    private int xmin, xmax, ymin, ymax;
    private Tuplexy origin;
    private DDA line;
    private Highlight highlight;
    private int bd;
    public boolean activated = false;
    private ArrayList<Tuplexy> poly_points, clipper_points;
    Window(int xmin, int xmax, int ymin, int ymax, int bd, Tuplexy origin)
    {
        this.xmin=xmin;
        this.xmax=xmax;
        this.ymin=ymin;
        this.ymax=ymax;
        this.bd=bd;
        this.origin=origin;
        line = new DDA();
        highlight=new Highlight();
        poly_points=new ArrayList();
        clipper_points = new ArrayList();
    }
    public void plot(int maxWidth, int maxHeight, Graphics g)
    {
        //highlight area
        g.setColor(new Color(200,200,200));
        g.fillRect(origin.x+xmin*bd, origin.y+ymin*bd, (xmax-xmin)*bd,(ymax-ymin)*bd);
        g.setColor(new Color(100,100,100));
        line.draw(xmin, ymin, xmax, ymin, origin.x, origin.y, bd, g);
        line.draw(xmax, ymin, xmax, ymax, origin.x, origin.y, bd, g);
        line.draw(xmax, ymax, xmin, ymax, origin.x, origin.y, bd, g);
        line.draw(xmin, ymax, xmin, ymin, origin.x, origin.y, bd, g);
    }
    public void setPoints(ArrayList<Tuplexy> poly_points)
    {
        this.poly_points=poly_points;
        clipper_points.add(new Tuplexy(xmin, ymin));
        clipper_points.add(new Tuplexy(xmax, ymin));
        clipper_points.add(new Tuplexy(xmax, ymax));
        clipper_points.add(new Tuplexy(xmin, ymax));
        op("in window class  :  after settings points");
        
    }
    public void clip(Graphics g)
    {
        SuthHodgClip obj = new SuthHodgClip(poly_points,clipper_points);
//        poly_points.clear();
        poly_points=obj.runAlgo();
        if(activated==true)
        {
            this.drawSides(g);
            this.drawEndPoints(g);
            this.drawPointsNames(g);
        }
//        op("in window class  :  after running clip");
//        poly_points.clear();
    }
    private void drawEndPoints(Graphics g)
    {
        g.setColor(Color.blue);
        for(int i=0; i<poly_points.size(); i++)
            highlight.draw(poly_points.get(i).x, poly_points.get(i).y, origin.x, origin.y, bd, g);
    }
    private void drawPointsNames(Graphics g)
    {
        g.setColor(Color.white);
        for(int i=0; i<poly_points.size(); i++)
            g.drawString(Integer.toString(i)+". "+poly_points.get(i).x+","+poly_points.get(i).y,origin.x+poly_points.get(i).x*bd, origin.y+poly_points.get(i).y*bd);
    }
    private void drawSides(Graphics g)
    {
        g.setColor(Color.blue);
        for(int i=0; i<poly_points.size(); i++)
        {
            int k=(i+1)%poly_points.size();
            line.draw(poly_points.get(i).x, poly_points.get(i).y, poly_points.get(k).x, poly_points.get(k).y, origin.x, origin.y, bd, g);
        }
    }
    void op(String str)
    {
        System.out.println(str);
        System.out.println("Size: "+poly_points.size());
        for(int i=0; i<poly_points.size(); i++)
            System.out.println(poly_points.get(i).x+" , "+poly_points.get(i).y);
        
        System.out.println("--------------------------");
    }
}
