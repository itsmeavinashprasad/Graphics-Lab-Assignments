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

public class Rectangle 
{
    private DDA obj;
    Rectangle()
    {
        obj = new DDA(); 
    }
    public void draw(int x, int y, int height, int width,int origin_x, int origin_y,int bd, Graphics g)
    {
        obj.draw(x, y, x+width, y, origin_x, origin_y, bd, g);
        obj.draw(x+width, y, x+width, y+height, origin_x, origin_y, bd, g);
        obj.draw(x+width, y+height, x, y+height, origin_x, origin_y, bd, g);
        obj.draw(x, y+height, x, y, origin_x, origin_y, bd, g);
    }
}
