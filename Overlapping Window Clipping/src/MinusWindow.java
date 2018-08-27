
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
public class MinusWindow 
{
    private Tuplexy origin;
    private int bd;
    private DDA line;
    MinusWindow(Tuplexy origin, int bd)
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
        g.fillRect(origin.x+(xmin1+45)*bd, origin.y+(ymin1+50)*bd, (xmax1-xmin1)*bd, (ymax1-ymin1)*bd);
        
        g.setColor(Color.blue);
        for(int i=0; i<p1.size(); i++)
        {
            int k = (i+1)%p1.size();
            line.draw(p1.get(i).x+45, p1.get(i).y+50, p1.get(k).x+45, p1.get(k).y+50, origin.x, origin.y, bd, g);
        }
        
        g.setColor(new Color(230,230,230));
        g.fillRect(origin.x+(xmin2+45)*bd, origin.y+(ymin2+50)*bd, (xmax2-xmin2)*bd, (ymax2-ymin2)*bd);
//        
        g.setColor(new Color(100,100,100));
        line.draw(10,5,45,5,origin.x, origin.y, bd, g);
        line.draw(45,5,45,15,origin.x, origin.y, bd, g);
        line.draw(45,15,30,15,origin.x, origin.y, bd, g);
        line.draw(30,15,30,40,origin.x, origin.y, bd, g);
        line.draw(30,40,45,40,origin.x, origin.y, bd, g);
        line.draw(45,40,45,45,origin.x, origin.y, bd, g);
        line.draw(45,45,10,45,origin.x, origin.y, bd, g);
        line.draw(10,45,10,5,origin.x, origin.y, bd, g);
        
    }
    
}
