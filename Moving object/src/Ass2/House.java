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
public class House
{
    private int topleft_x,topleft_y,x,y;
    private Graphics g;
    private Color c1,c2,c3,c4;
    private int bw,bh;
    
    public void setData(int topleft_x, int topleft_y,int bw, int bh,Graphics g)
    {
        this.topleft_x=topleft_x;
        this.topleft_y=topleft_y;
        this.g=g;
        this.bw=bw;
        this.bh=bh;
    }
    public void draw()
    {
        //==================Draw horizontal lines ====================
        
        //1
        x=topleft_x+(bw*4);
        y=topleft_y;
        for(int i=0;i<7;i++)
        {
            g.fillRect(x, y, bw, bh);
            x+=bw;
        }
        //2
        x=topleft_x;
        y=topleft_y+(bh*4);
        for(int i=0;i<15;i++)
        {
            g.fillRect(x, y, bw, bh);
            x+=bw;
        }
        //3
        x=topleft_x;
        y=topleft_y+(bh*11);
        for(int i=0;i<15;i++)
        {
            g.fillRect(x, y, bw, bh);
            x+=bw;
        }
        //4
        x=topleft_x;
        y=topleft_y+(bh*14);
        for(int i=0;i<15;i++)
        {
            g.fillRect(x, y, bw, bh);
            x+=bw;
        }
        //5
        x=topleft_x+(bw*5);
        y=topleft_y+(bh*7);
        for(int i=0;i<5;i++)
        {
            g.fillRect(x, y, bw, bh);
            x+=bw;
        }
        
        //==================Draw horizontal lines ====================
        
        //1
        x=topleft_x+(bw*2);
        y=topleft_y+(bh*5);
        for(int i=0;i<6;i++)
        {
            g.fillRect(x, y, bw, bh);
            y+=bh;
        }
        //2
        x=topleft_x+(bw*12);
        y=topleft_y+(bh*5);
        for(int i=0;i<6;i++)
        {
            g.fillRect(x, y, bw, bh);
            y+=bh;
        }
        //3
        x=topleft_x+(bw*5);
        y=topleft_y+(bh*8);
        for(int i=0;i<4;i++)
        {
            g.fillRect(x, y, bw, bh);
            y+=bh;
        }
        //4
        x=topleft_x+(bw*9);
        y=topleft_y+(bh*8);
        for(int i=0;i<4;i++)
        {
            g.fillRect(x, y, bw, bh);
            y+=bh;
        }
        //5
        x=topleft_x+(bw*0);
        y=topleft_y+(bh*12);
        for(int i=0;i<2;i++)
        {
            g.fillRect(x, y, bw, bh);
            y+=bh;
        }
        //6
        x=topleft_x+(bw*14);
        y=topleft_y+(bh*12);
        for(int i=0;i<2;i++)
        {
            g.fillRect(x, y, bw, bh);
            y+=bh;
        }
        
        
        //====================draw angled lines======================
        //1
        x=topleft_x+(bw*1);
        y=topleft_y+(bh*3);
        for(int i=0;i<3;i++)
        {
            g.fillRect(x, y, bw, bh);
            x+=bw;
            y-=bh;
        }
        //2
        x=topleft_x+(bw*11);
        y=topleft_y+(bh*1);
        for(int i=0;i<3;i++)
        {
            g.fillRect(x, y, bw, bh);
            x+=bw;
            y+=bh;
        }
    }
    
    public void colorChange(Color c1,Color c2,Color c3,Color c4)
    {
        this.c2=c2;
        this.c2=c2;
        this.c3=c3;
        this.c4=c4;
    }
}
