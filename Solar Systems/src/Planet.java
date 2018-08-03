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
import java.util.ArrayList;

public class Planet 
{
    public ArrayList<Tuple> path;
    private Ellipse obj =  new Ellipse();
    private Graphics g;
    private int index=0, iter, size;
    public int radius;
    public Color c;
    private int rx, ry;
    Planet(int rx, int ry, int radius, int iter, Color c)
    {
        this.rx=rx;
        this.ry=ry;
        this.radius=radius;
        this.iter=iter;
        this.c=c;
    }
    public void draw(int xc, int yc, int origin_x, int origin_y, int bd)
    {
        obj.draw(xc, yc, rx, ry, origin_x,origin_x, bd);
        path = obj.storePoints();
        size=path.size();
    }
    public Tuple getLocation()
    {
//        Tuple temp = new Tuple(path.get(index).x, path.get(index).y);
        Tuple temp= path.get(index);
        index=(index+iter)%size;
        return temp;
    }
   
}
