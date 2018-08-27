/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
/**
 *
 * @author Avinash Kumar Prasad
 */
public class Highlight 
{
    public void draw(int x1, int y1, int x2, int y2,int orix, int oriy,int bd, Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(orix+x1*bd, oriy+y1*bd, bd, bd);
        
        g.setColor(Color.MAGENTA);
        g.fillRect(orix+x2*bd, oriy+y2*bd, bd, bd);
    }
}
