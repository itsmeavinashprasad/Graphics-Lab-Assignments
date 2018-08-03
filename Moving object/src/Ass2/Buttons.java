/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ass2;
import java.awt.*;
import java.util.*;
/**
 *
 * @author Avinash Kumar Prasad
 */
public class Buttons
{
    private int x,y;
    private int button_height=20,button_width=70;
    private int max_width,max_height,min_width,min_height;
    private Graphics g;
    private Color c1,c2;
    private ArrayList<Integer> pos;
    
    public void setData(int max_width,int max_height,int min_width,int min_height,Graphics g)
    {
        this.max_height=max_height;
        this.max_width=max_width;
        this.min_height=min_height;
        this.min_width=min_width;
        this.g=g;
        pos=new ArrayList();
        pos.add(button_width);
        pos.add(button_height);
        
    }
    public void draw()
    {
        g.setFont(new Font("Constantia",1,20));
        //BUTTON 1
        int x=max_width-20-button_width;
        int y=min_height+10;
        g.setColor(c1);
        g.fillRect(x,y,button_width,button_height);
        g.setColor(c2);
        g.drawString("LEFT",x+3,y+17);
        pos.add(x);
        pos.add(y);

        //BUTTON 2
        y=y+button_height+10;
        g.setColor(c1);
        g.fillRect(x,y,button_width,button_height);
        g.setColor(c2);
        g.drawString("RIGHT",x+3,y+17);
        pos.add(y);
        
        //BUTTON 3
        y=y+button_height+10;
        g.setColor(c1);
        g.fillRect(x,y,button_width,button_height);
        g.setColor(c2);
        g.drawString("UP",x+3,y+17);
        pos.add(y);
        
        //BUTTON 4
        y=y+button_height+10;
        g.setColor(c1);
        g.fillRect(x,y,button_width,button_height);
        g.setColor(c2);
        g.drawString("DOWN",x+3,y+17);
        pos.add(y);
        
        g.setFont(new Font("Constantia",1,15));
        //BUTTON 5
        y=y+button_height+10;
        g.setColor(c1);
        g.fillRect(x,y,button_width,button_height);
        g.setColor(c2);
        g.drawString("Zoom In",x+3,y+17);
        pos.add(y);
        
        //BUTTON 6
        y=y+button_height+10;
        g.setColor(c1);
        g.fillRect(x,y,button_width,button_height);
        g.setColor(c2);
        g.drawString("Zoom Out",x+3,y+17);
        pos.add(y);
    }
        
    public ArrayList<Integer> getData()
    {
        return pos;
    }
    
    public void colorChange(Color c1, Color c2)
    {
        this.c1=c1;
        this.c2=c2;
    }
    
}
