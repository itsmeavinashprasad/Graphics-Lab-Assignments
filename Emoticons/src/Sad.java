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
import java.util.*;

public class Sad 
{
    private int  xc,yc,r,origin_x,origin_y,bd;
    private Graphics g;
    private int topleft_x,topleft_y;

    private circle obj1=new circle();
    private ellipse obj2 = new ellipse();
    private DDA obj3=new DDA();
    
    public void setData(int xc, int yc, int orix, int oriy, int bd, Graphics g)
    {
        origin_x=orix;
        origin_y=oriy;
        this.bd=bd;
        
        this.xc=xc;
        this.yc=yc;
        
        
        this.g=g;
    }
    public void draw()
    {
        //face
        g.setColor(Color.yellow);
        g.fillOval(origin_x+(xc-50)*bd, origin_y+(yc-50)*bd, 2*50*bd, 2*50*bd);
        g.setColor(Color.black);
        obj1.draw(xc, yc, 50, origin_x, origin_y, bd, g);
        
        //eyes
//        obj1.draw(xc+20, yc-14, 10, origin_x, origin_y, bd, g);
//        obj1.draw(xc-20, yc-14, 10, origin_x, origin_y, bd, g);
        obj3.draw(xc+10, yc-14, xc+32, yc-14, origin_x, origin_y, bd, bd, g);
        obj3.draw(xc-10, yc-14, xc-32, yc-14, origin_x, origin_y, bd, bd, g);
        //eyebrow
        obj3.draw(xc+10, yc-30, xc+32, yc-20, origin_x, origin_y, bd, bd, g);
        obj3.draw(xc-10, yc-30, xc-32, yc-20, origin_x, origin_y, bd, bd, g);
        
        //smile
        obj2.draw(xc, yc+55, 40, 45, origin_x, origin_y, bd, new int [] {4,5}, g);
        
        
    }
    
}
