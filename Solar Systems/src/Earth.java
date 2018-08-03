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

public class Earth 
{
    private ArrayList<Tuple> path;
    private Ellipse obj =  new Ellipse();
    private Graphics g;
    private int origin_x, origin_y, bd, xc, yc, iter=0, size;
    public void draw(int xc, int yc, int orix, int oriy, int bd)
    {
        origin_x=orix;
        origin_y=oriy;
        this.bd=bd;
        int rx=80, ry=40;
        obj.draw(xc, yc, rx, ry, origin_x,origin_x, bd);
        path = obj.storePoints();
        size=path.size();
        for(int i=0;i<path.size();i++)
            System.out.println(path.get(i).x+"    "+path.get(i).y);
    }
    public Tuple getLocation()
    {
        Tuple temp = new Tuple(path.get(iter).x, path.get(iter).y);
        iter=(iter+2)%size;
        return  temp;
    }
    
}
