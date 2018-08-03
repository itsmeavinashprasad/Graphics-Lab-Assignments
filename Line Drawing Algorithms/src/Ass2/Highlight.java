/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ass2;

import java.awt.*;
/**
 *
 * @author Avinash Kumar Prasad
 */
public class Highlight 
{
    public void draw(int x1, int y1, int x2, int y2,int orix, int oriy,int bw, int bh, Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(orix+x1*bw, oriy+y1*bh, bw, bh);
        
        g.setColor(Color.BLUE);
        g.fillRect(orix+x2*bw, oriy+y2*bh, bw, bh);
    }
}
