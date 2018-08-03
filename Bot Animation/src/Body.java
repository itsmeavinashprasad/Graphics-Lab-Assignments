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

public class Body 
{
    private RotRect obj;
    private static int e_torso=5, flag=0, flag2=0, call;
    private static int a1_lleg=-20, a2_lleg=-60, a1_rleg=35, a2_rleg=0;
    private static int a1_lhand=+20, a2_lhand=+40, a1_rhand=-35, a2_rhand=10;
    //torso
    int w_torso, h_torso, x_torso,y_torso;
    
    //neck
    int w_neck,h_neck,x_neck,y_neck;
    
    //head
    int w_head, h_head, x_head, y_head;
    
    //right leg
    int w1_rleg, h1_rleg, x1_rleg, y1_rleg;
    int w2_rleg, h2_rleg, x2_rleg, y2_rleg;
    int w3_rleg, h3_rleg, x3_rleg, y3_rleg;
    
    //left leg
    int w1_lleg, h1_lleg, x1_lleg, y1_lleg;
    int w2_lleg, h2_lleg, x2_lleg, y2_lleg;
    int w3_lleg, h3_lleg, x3_lleg, y3_lleg;
    
    //right hand
    int w1_rhand, h1_rhand, x1_rhand, y1_rhand;
    int w2_rhand, h2_rhand, x2_rhand, y2_rhand;
    int w3_rhand, h3_rhand, x3_rhand, y3_rhand;
    
    //left hand
    int w1_lhand, h1_lhand, x1_lhand, y1_lhand;
    int w2_lhand, h2_lhand, x2_lhand, y2_lhand;
    int w3_lhand, h3_lhand, x3_lhand, y3_lhand;
    Body()
    {
        obj= new RotRect();
    }
    
    public void draw(int origin_x, int origin_y, int bd, Graphics g)
    {
        //        ===========================RIGHT HAND================================================
        //right hand
        w1_rhand=15;     h1_rhand=40;     x1_rhand=-10;    y1_rhand=y_torso+10;
        obj.draw(x1_rhand, y1_rhand, h1_rhand, w1_rhand, a1_rhand, origin_x, origin_y, bd, g, 1, Color.RED);
        
        w2_rhand=15;     h2_rhand=40;     
        x2_rhand=(int) Math.ceil(x1_rhand+h1_rhand*Math.sin(Math.toRadians(a1_rhand))); 
        y2_rhand=(int) Math.ceil(y1_rhand+h1_rhand*Math.cos(Math.toRadians(a1_rhand)));
        obj.draw(x2_rhand, y2_rhand, h2_rhand, w2_rhand, a2_rhand, origin_x, origin_y, bd, g, 1, new Color(255,203,151));
        
        w3_rhand=15;     h3_rhand=20;
        x3_rhand=(int) Math.ceil(x2_rhand+h2_rhand*Math.sin(Math.toRadians(a2_rhand)));
        y3_rhand=(int) Math.ceil(y2_rhand+h2_rhand*Math.cos(Math.toRadians(a2_rhand)));
        obj.draw(x3_rhand, y3_rhand, h3_rhand, w3_rhand, 0, origin_x, origin_y, bd, g, 2, new Color(255,203,151));
        
//        =====================================RIGHT LEG======================================
        
        //right leg
        w1_rleg=30;     h1_rleg=45;     x1_rleg=-10;    y1_rleg=y_torso+h_torso;
        obj.draw(x1_rleg, y1_rleg, h1_rleg, w1_rleg, a1_rleg, origin_x, origin_y, bd, g, 1, Color.blue);
        
        w2_rleg=20;     h2_rleg=45;     
        x2_rleg=(int) Math.ceil(x1_rleg+2+h1_rleg*Math.sin(Math.toRadians(a1_rleg))); 
        y2_rleg=(int) Math.ceil(y1_rleg+h1_rleg*Math.cos(Math.toRadians(a1_rleg)));
        obj.draw(x2_rleg, y2_rleg, h2_rleg, w2_rleg, a2_rleg, origin_x, origin_y, bd, g, 1, new Color(255,203,151));
        
        w3_rleg=35;     h3_rleg=12;
        x3_rleg=(int) Math.ceil(x2_rleg+h2_rleg*Math.sin(Math.toRadians(a2_rleg)));
        y3_rleg=(int) Math.ceil(y2_rleg+h2_rleg*Math.cos(Math.toRadians(a2_rleg)));
        obj.draw(x3_rleg, y3_rleg, h3_rleg, w3_rleg, 0, origin_x, origin_y, bd, g, 2, Color.black);
//        ====================================TORSO, NECK, HEAD======================================
        //torso
        w_torso=50;     h_torso=80;     x_torso=-25;   y_torso=-60+e_torso;
        obj.draw(x_torso, y_torso, h_torso, w_torso, 0, origin_x, origin_y, bd, g, 2, Color.RED);
        
        g.setColor(Color.white);
        g.fillRect(origin_x+(x_torso+1)*bd, origin_y+(y_torso+1)*bd, (w_torso-1)*bd, (h_torso-1)*bd);
        g.setColor(Color.black);
        //neck
        w_neck=12;      h_neck=12;      x_neck=-6;      y_neck=y_torso-h_neck;
        obj.draw(x_neck, y_neck, h_neck, w_neck, 0, origin_x, origin_y, bd, g, 2, new Color(255,203,151));
        
        //head
        w_head=30;      h_head=40;      x_head=-15;     y_head=y_neck-h_head;
        obj.draw(x_head, y_head, h_head, w_head, 0, origin_x, origin_y, bd, g, 2, new Color(255,203,151));



//        =====================================LEFT LEG======================================
        
        //left leg
        w1_lleg=30;     h1_lleg=45;     x1_lleg=-10;    y1_lleg=y_torso+h_torso;
        obj.draw(x1_lleg, y1_lleg, h1_lleg, w1_lleg, a1_lleg, origin_x, origin_y, bd, g, 1, Color.blue);
        
        w2_lleg=20;     h2_lleg=45;     
        x2_lleg=(int) Math.ceil(x1_lleg+2+h1_lleg*Math.sin(Math.toRadians(a1_lleg))); 
        y2_lleg=(int) Math.ceil(y1_lleg+h1_lleg*Math.cos(Math.toRadians(a1_lleg)));
        obj.draw(x2_lleg, y2_lleg, h2_lleg, w2_lleg, a2_lleg, origin_x, origin_y, bd, g, 1, new Color(255,203,151));
        
        w3_lleg=35;     h3_lleg=12;
        x3_lleg=(int) Math.ceil(x2_lleg+h2_lleg*Math.sin(Math.toRadians(a2_lleg)));
        y3_lleg=(int) Math.ceil(y2_lleg+h2_lleg*Math.cos(Math.toRadians(a2_lleg)));
        obj.draw(x3_lleg, y3_lleg, h3_lleg, w3_lleg, 0, origin_x, origin_y, bd, g, 2, Color.black);
        
//        ======================================LEFT HAND=====================================
        
        //left hand
        w1_lhand=15;     h1_lhand=40;     x1_lhand=-10;    y1_lhand=y_torso+10;
        obj.draw(x1_lhand, y1_lhand, h1_lhand, w1_lhand, a1_lhand, origin_x, origin_y, bd, g, 1, Color.RED);
        
        w2_lhand=15;     h2_lhand=40;     
        x2_lhand=(int) Math.ceil(x1_lhand+h1_lhand*Math.sin(Math.toRadians(a1_lhand))); 
        y2_lhand=(int) Math.ceil(y1_lhand+h1_lhand*Math.cos(Math.toRadians(a1_lhand)));
        obj.draw(x2_lhand, y2_lhand, h2_lhand, w2_lhand, a2_lhand, origin_x, origin_y, bd, g, 1, new Color(255,203,151));
        
        w3_lhand=15;     h3_lhand=20;
        x3_lhand=(int) Math.ceil(x2_lhand+h2_lhand*Math.sin(Math.toRadians(a2_lhand)));
        y3_lhand=(int) Math.ceil(y2_lhand+h2_lhand*Math.cos(Math.toRadians(a2_lhand)));
        obj.draw(x3_lhand, y3_lhand, h3_lhand, w3_lhand, 0, origin_x, origin_y, bd, g, 2, new Color(255,203,151));

//        =====================================ANGLE UPDATIONS======================================
       

        

        
        
        // 1st half
        if(flag==0)
        {
            
            
            if(flag2==0)
            {
                a2_lleg+=12;
                a1_lleg+=4;
                a1_lhand-=4;
                a2_lhand-=8;
                
                a1_rleg-=7;
                a1_rhand+=7;
                a2_rhand-=2;
            }
            else
            {
                a1_lleg+=7;
                a1_lhand-=7;
                a2_lhand+=2;
                
                a1_rleg-=4;
                a2_rleg-=12;
                a1_rhand+=4;
                a2_rhand+=8;
            }
        }
        
        // 2nd half
        else
        {
            
            
            if(flag2==0)
            {
                a1_lleg-=7;
                a1_lhand+=7;
                a2_lhand-=2;
                
                a1_rleg+=4;
                a2_rleg+=12;
                a1_rhand-=4;
                a2_rhand-=8;
            }
            else
            {
                a2_lleg-=12;
                a1_lleg-=4;
                a1_lhand+=4;
                a2_lhand+=8;
                
                a1_rhand-=7;
                a2_rhand+=2;
                a1_rleg+=7;
            }
        }
        
        if(flag2==0)
        {
            e_torso-=2;
        }
        else
        {
            e_torso+=2;
        }
        call++;
        if(call%10==0)
            flag=1-flag;
        if(call%5==0)
            flag2=1-flag2;
    }
    
}
