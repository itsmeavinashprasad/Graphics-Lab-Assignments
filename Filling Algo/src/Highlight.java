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
    public void draw(int x, int y,int orix, int oriy,int bd, Graphics g)
    {
        g.fillRect(orix+x*bd, oriy+y*bd, bd, bd);
    }    
}
