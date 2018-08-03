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
import java.math.*;

public class RotRect 
{
    private DDA obj;
    RotRect()
    {
        obj = new DDA(); 
    }
    public void draw(int x, int y, int height, int width, int angle,int origin_x, int origin_y,int bd, Graphics g, int flag, Color c)
    {
        int d_dash=(int) Math.ceil(((int)height*Math.sin(Math.toRadians(angle))));
        int h_dash=(int) Math.ceil((int)height*Math.cos(Math.toRadians(angle)));
        obj.draw(x, y, x+width, y, origin_x, origin_y, bd, g);
        obj.draw(x+width, y, x+width+d_dash, y+h_dash, origin_x, origin_y, bd, g);
        obj.draw(x+width+d_dash, y+h_dash, x+d_dash, y+h_dash, origin_x, origin_y, bd, g);
        obj.draw(x+d_dash, y+h_dash, x, y, origin_x, origin_y, bd, g);
        
//        if(angle==0)
//            flag=2;
//        if(flag==1)
//        {
//            g.setColor(c);
//            for(int i=1; i<width;i++)
//            {
//                obj.draw(x+i, y, x+d_dash+i, y+h_dash-1, origin_x, origin_y, bd, g);
//            }
//            g.setColor(Color.black);
//        }
//        if(flag==2)
//        {
//            g.setColor(c);
//            g.fillRect(origin_x+(x+1)*bd, origin_y+(y+1)*bd, (width-1)*bd, (height-1)*bd);
//            g.setColor(Color.black);
//        }
        
    }
}
