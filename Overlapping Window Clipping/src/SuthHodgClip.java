
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
public class SuthHodgClip 
{
    private ArrayList<Tuplexy> poly_points, clipper_points, new_poly_points;

    SuthHodgClip(ArrayList<Tuplexy> poly_points, ArrayList<Tuplexy> clipper_points ) 
    {
        this.poly_points=poly_points;
        this.clipper_points=clipper_points;
        new_poly_points= new ArrayList();
//        op("in shc  :  settings points");
    }
    
    
    public ArrayList<Tuplexy> runAlgo()
    {
        for(int i=0; i<this.clipper_points.size(); i++)
        {
            int k=(i+1)%this.clipper_points.size();
            clip(i,k);
        }
        
//        op("in shc  :  after runalgo");
        return poly_points;
    }

    void clip(int p1, int p2)
    {
        int x1=clipper_points.get(p1).x, x2=clipper_points.get(p2).x, y1=clipper_points.get(p1).y, y2=clipper_points.get(p2).y;
//        op("in clip call :"+p1);
        for(int i=0; i<poly_points.size(); i++)
        {
            
            int k= (i+1)%poly_points.size();
            int ix=poly_points.get(i).x, iy=poly_points.get(i).y, kx=poly_points.get(k).x, ky=poly_points.get(k).y;

            // Calculating position of first point
            // w.r.t. clipper line
            int i_pos = (x2-x1) * (iy-y1) - (y2-y1) * (ix-x1);

            // Calculating position of second point
            // w.r.t. clipper line
            int k_pos = (x2-x1) * (ky-y1) - (y2-y1) * (kx-x1);

            i_pos=-i_pos;
            k_pos=-k_pos;
//            System.out.println(i+"\t"+k);
            // Case 1 : When both points are inside
            if (i_pos <= 0  && k_pos <= 0)
            {
//                System.out.println("both inside");
                //Only second point is added
                new_poly_points.add(poly_points.get(k));
//                System.out.println("adding "+new_poly_points.get(new_poly_points.size()-1).x+","+new_poly_points.get(new_poly_points.size()-1).y);
            }

            // Case 2: When only first point is outside
            else if (i_pos > 0  && k_pos <= 0)
            {
//                System.out.println("1st is outside");
                // Point of intersection with edge
                // and the second point is added
                new_poly_points.add(getIntersect(clipper_points.get(p1), clipper_points.get(p2), poly_points.get(i), poly_points.get(k)));
//                System.out.println("adding "+new_poly_points.get(new_poly_points.size()-1).x+","+new_poly_points.get(new_poly_points.size()-1).y);
                new_poly_points.add(poly_points.get(k));
//                System.out.println("adding "+new_poly_points.get(new_poly_points.size()-1).x+","+new_poly_points.get(new_poly_points.size()-1).y);
            }

            // Case 3: When only second point is outside
            else if (i_pos <= 0  && k_pos > 0)
            {
//                System.out.println("2nd is outside");
                //Only point of intersection with edge is added
                new_poly_points.add(getIntersect(clipper_points.get(p1), clipper_points.get(p2), poly_points.get(i), poly_points.get(k)));
//                System.out.println("adding "+new_poly_points.get(new_poly_points.size()-1).x+","+new_poly_points.get(new_poly_points.size()-1).y);
            }

            // Case 4: When both points are outside
            else
            {
//                System.out.println("both outside");
                //No points are added
            }
        }
        for(int i=0;i<new_poly_points.size(); i++)
        {
            if(i<poly_points.size())
                poly_points.set(i, new_poly_points.get(i));
            else
                poly_points.add(new_poly_points.get(i));
        }
        new_poly_points.clear();
    }
    Tuplexy getIntersect(Tuplexy p1, Tuplexy p2, Tuplexy p3, Tuplexy p4)
    {
        int num_x = (p1.x*p2.y - p1.y*p2.x) * (p3.x-p4.x) - (p1.x-p2.x) * (p3.x*p4.y - p3.y*p4.x);
        int num_y = (p1.x*p2.y - p1.y*p2.x) * (p3.y-p4.y) - (p1.y-p2.y) * (p3.x*p4.y - p3.y*p4.x);
        int den = (p1.x-p2.x)*(p3.y-p4.y) - (p1.y-p2.y)*(p3.x-p4.x);
        
        return new Tuplexy(num_x/den, num_y/den);
    }
    void op(String str)
    {
        System.out.println(str);
        System.out.println("Size: "+poly_points.size());
        for(int i=0; i<poly_points.size(); i++)
            System.out.println(poly_points.get(i).x+" , "+poly_points.get(i).y);
        
        System.out.println("--------------------------");
    }
}