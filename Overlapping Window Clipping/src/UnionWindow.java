
import java.awt.Color;
import java.awt.Graphics;
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
public class UnionWindow 
{
    private Tuplexy origin;
    private int bd;
    private DDA line;
    UnionWindow(Tuplexy origin, int bd)
    {
        this.origin=origin;
        this.bd=bd;
        line = new DDA();
    }
    public void plot(Graphics g, ArrayList<Tuplexy> p1, ArrayList<Tuplexy> p2)
    {
        int 
                xmin1=-35,  ymin1=-45,
                xmax1=0,  ymax1=-5,
                xmin2=-15,   ymin2=-35,
                xmax2=20,    ymax2=-10;
        g.setColor(new Color(200,200,200));
        g.fillRect(origin.x+(xmin1-25)*bd, origin.y+(ymin1+50)*bd, (xmax1-xmin1)*bd, (ymax1-ymin1)*bd);
        g.fillRect(origin.x+(xmin2-25)*bd, origin.y+(ymin2+50)*bd, (xmax2-xmin2)*bd, (ymax2-ymin2)*bd);
        
        
        g.setColor(Color.blue);
        for(int i=0; i<p1.size(); i++)
        {
            int k = (i+1)%p1.size();
            line.draw(p1.get(i).x - 25, p1.get(i).y+50, p1.get(k).x - 25, p1.get(k).y+50, origin.x, origin.y, bd, g);
        }
        for(int i=0; i<p2.size(); i++)
        {
            int k = (i+1)%p2.size();
            line.draw(p2.get(i).x - 25 , p2.get(i).y+50, p2.get(k).x-25, p2.get(k).y+50, origin.x, origin.y, bd, g);
        }
        
        g.setColor(new Color(100,100,100));
        line.draw(-60, 5, -25, 5, origin.x, origin.y, bd, g);
        line.draw(-25, 5, -25, 15, origin.x, origin.y, bd, g);
        line.draw(-25,15,-5,15, origin.x,origin.y, bd, g);
        line.draw(-5,15,-5,40, origin.x,origin.y, bd, g);
        line.draw(-5,40,-25,40,origin.x,origin.y, bd, g);
        line.draw(-25,40,-25,45,origin.x,origin.y, bd, g);
        line.draw(-25,45,-60,45,origin.x,origin.y, bd, g);
        line.draw(-60,45,-60,5,origin.x,origin.y, bd, g);
    }
    
}
