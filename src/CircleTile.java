import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.*;

public class CircleTile extends RankTile {

    private double          centerX = 50.0, //starting x coordinate
                            centerY = 40.0, //starting y coordinate
                            radius  = 80.0/rank; //radius is dependent on number of circles
    private static Color    green   = new Color(0x006400);
    private static Color    blue    = new Color(0x00008B);
    private static Color    red     = new Color(0xB22222);
    public CircleTile(int rank){
        super(rank);
        setToolTipText(toString());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        ArrayList<Circle> list = new ArrayList<>();
        switch(rank){
              case 1: list = circle1(); break;
              case 2: list = circle2(); break;
              case 3: list = circle3(); break;
              case 4: list = circle4(); break;
              case 5: list = circle5(); break;
              case 6: list = circle6(); break;
              case 7: list = circle7(); break;
              case 8: list = circle8(); break;
              case 9: list = circle9(); break;
        }
        for(Circle c : list){
            if(c != null){
                if(rank == 1){
                    c.draw1(g2);
                    c.drawDecor(g2);
                }
                c.draw(g2);
            }

        }
    }

    public ArrayList<Circle> circle1(){

        ArrayList<Circle> list = new ArrayList<>();
        list.add(new Pancake(centerX-20,centerY-30,radius,green));
        //return list;
        int n = 10; //num of inner circles
        int r = 8;
        for (int i = 0; i < n; i++) { // circle of circles
            double theta = 2 * Math.PI * i / n; //angle theta changes based on i
            int x = (int) Math.round(centerX+15 + (radius-51) * Math.cos(theta));
           int y = (int) Math.round(centerY+7 + (radius-51) * Math.sin(theta));
            list.add(new Pancake(x, y,r, blue));
        }
        return list;
    }


    public ArrayList<Circle> circle2(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(new Circle(centerX-4,centerY-38,radius+8,green));
        list.add(new Circle(centerX-4,centerY+10,radius+8,red));
        return list;
    }


    public ArrayList<Circle> circle3(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(new Circle(centerX-26,centerY-36,radius+5,blue));
        list.add(new Circle(centerX+2,centerY-6,radius+5,red));
        list.add(new Circle(centerX+30,centerY+26,radius+5,green));
        return list;
    }


    public ArrayList<Circle> circle4(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(new Circle(centerX-20,centerY-28,radius+20,blue));
        list.add(new Circle(centerX-20,centerY+14,radius+20,blue));
        list.add(new Circle(centerX+20,centerY-28,radius+20,green));
        list.add(new Circle(centerX+20,centerY+14,radius+20,green));
        return list;
    }


    public ArrayList<Circle> circle5(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(new Circle(centerX-28,centerY-34,radius+18,blue));
        list.add(new Circle(centerX+30,centerY-34,radius+18,green));
        list.add(new Circle(centerX-28,centerY+24,radius+18,green));
        list.add(new Circle(centerX+30,centerY+24,radius+18,blue));
        list.add(new Circle(centerX,centerY-6,radius+18,red));
        return list;
    }


    public ArrayList<Circle> circle6(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(new Circle(centerX-20,centerY-38,radius+18,green));
        list.add(new Circle(centerX+20,centerY-38,radius+18,green));
        list.add(new Circle(centerX-20,centerY-6,radius+18,red));
        list.add(new Circle(centerX+20,centerY-6,radius+18,red));
        list.add(new Circle(centerX-20,centerY+26,radius+18,red));
        list.add(new Circle(centerX+20,centerY+26,radius+18,red));
        return list;
    }


    public ArrayList<Circle> circle7(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(new Circle(centerX-28,centerY-38,radius+12,green));
        list.add(new Circle(centerX+6,centerY-26,radius+12,green));
        list.add(new Circle(centerX+40,centerY-14,radius+12,green));

        list.add(new Circle(centerX-16,centerY+6,radius+12,red));
        list.add(new Circle(centerX-16,centerY+30,radius+12,red));
        list.add(new Circle(centerX+20,centerY+6,radius+12,red));
        list.add(new Circle(centerX+20,centerY+30,radius+12,red));
        return list;
    }


    public ArrayList<Circle> circle8(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(new Circle(centerX-16,centerY-38,radius+12,blue));
        list.add(new Circle(centerX-16,centerY-15,radius+12,blue));
        list.add(new Circle(centerX-16,centerY+8,radius+12,blue));
        list.add(new Circle(centerX-16,centerY+32,radius+12,blue));
        list.add(new Circle(centerX+28,centerY-38,radius+12,blue));
        list.add(new Circle(centerX+28,centerY-15,radius+12,blue));
        list.add(new Circle(centerX+28,centerY+8,radius+12,blue));
        list.add(new Circle(centerX+28,centerY+32,radius+12,blue));
        return list;
    }


    public ArrayList<Circle> circle9(){
        ArrayList<Circle> list = new ArrayList<>();
        list.add(new Circle(centerX-28,centerY-38,radius+22,green));
        list.add(new Circle(centerX+5,centerY-38,radius+22,green));
        list.add(new Circle(centerX+36,centerY-38,radius+22,green));
        list.add(new Circle(centerX-28,centerY-6,radius+22,red));
        list.add(new Circle(centerX+5,centerY-6,radius+22,red));
        list.add(new Circle(centerX+36,centerY-6,radius+22,red));
        list.add(new Circle(centerX-28,centerY+26,radius+22,blue));
        list.add(new Circle(centerX+5,centerY+26,radius+22,blue));
        list.add(new Circle(centerX+36,centerY+26,radius+22,blue));
        return list;
    }


    public String toString(){

        return "Circle " + rank;
    }


    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Circle Tiles");

        frame.add(new CircleTile(1));
        frame.add(new CircleTile(2));
        frame.add(new CircleTile(3));
        frame.add(new CircleTile(4));
        frame.add(new CircleTile(5));
        frame.add(new CircleTile(6));
        frame.add(new CircleTile(7));
        frame.add(new CircleTile(8));
        frame.add(new CircleTile(9));

        frame.pack();
        frame.setVisible(true);
    }

}

