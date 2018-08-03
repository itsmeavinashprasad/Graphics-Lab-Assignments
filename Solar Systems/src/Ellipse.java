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
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Collections;

public class Ellipse 
{
    private int  origin_x,origin_y,bd;
    private ArrayList<Tuple> arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, path;
    
    public void draw(int xc, int yc, int rx,int ry ,int origin_x, int origin_y, int bd)
    {
        arr1= new ArrayList<Tuple>();
        arr2= new ArrayList<Tuple>();
        arr3= new ArrayList<Tuple>();
        arr4= new ArrayList<Tuple>();
        arr5= new ArrayList<Tuple>();
        arr6= new ArrayList<Tuple>();
        arr7= new ArrayList<Tuple>();
        arr8= new ArrayList<Tuple>();
        path= new ArrayList<Tuple>();
        
        
        this.origin_x=origin_x;
        this.origin_y=origin_y;
        this.bd=bd;
        int x,y;
        x=0;
        y=ry;
        
        //region 1 
        double p=pow(ry,2)-pow(rx,2)*ry+(pow(rx,2)/(double)4.0);
        while(pow(ry,2)*x<pow(rx,2)*y)
        {
            arr1.add(new Tuple(xc+x,yc+y));
            arr8.add(new Tuple(xc-x,yc+y));
            arr4.add(new Tuple(xc+x,yc-y));
            arr5.add(new Tuple(xc+-x,yc-y));
            
            x++;
            if(p<=0)
                p+=(2*pow(ry,2)*x)+pow(ry,2);
            else
            {
                y--;
                p+=(2*pow(ry,2)*x)-(2*pow(rx,2)*y)+pow(ry,2);
            }
            
        }
        
        //region 2
        p=(pow(ry,2)*pow((x+0.5),2)) + pow((rx*(y-1)),2) - pow(rx*ry,2);
        while(y>=0)
        {
            arr2.add(new Tuple(xc+x,yc+y));
            arr7.add(new Tuple(xc-x,yc+y));
            arr3.add(new Tuple(xc+x,yc-y));
            arr6.add(new Tuple(xc-x,yc-y));
            
            y--;
            if(p>0)
            {
                p+= -( 2*pow(rx,2)*y + pow(rx,2));
            }
            else
            {
                x++;
                p+= 2*pow(ry,2)*x - 2*pow(rx,2)*y + pow(rx,2);
            }
            
        }
        
    }
    public ArrayList<Tuple> storePoints()
    {
        path.addAll(arr1);
        path.addAll(arr2);
        Collections.reverse(arr3);
        path.addAll(arr3);
        Collections.reverse(arr4);
        path.addAll(arr4);
        path.addAll(arr5);
        path.addAll(arr6);
        Collections.reverse(arr7);
        Collections.reverse(arr8);
        path.addAll(arr7);
        path.addAll(arr8);
//        for(int i=0;i<arr1.size();i++)
//            path.add(arr1.get(i));
//        for(int i=0; i<arr2.size(); i++)
//            path.add(arr2.get(i));
//        for(int i=arr3.size()-1; i>=0; i--)
//            path.add(arr3.get(i));
//        for(int i=arr4.size()-1; i>=0; i--)
//            path.add(arr4.get(i));
//        for(int i=0; i<arr5.size(); i++)
//            path.add(arr5.get(i));
//        for(int i=0; i<arr6.size(); i++)
//            path.add(arr6.get(i));
//        for(int i=arr7.size()-1; i>=0; i--)
//            path.add(arr7.get(i));
//        for(int i=arr8.size()-1; i>=0; i--)
//            path.add(arr8.get(i));
        
        return path;
    }
}