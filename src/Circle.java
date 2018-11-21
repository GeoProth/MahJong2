import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.*;
//import java
public class Circle {

    private double x, y, r;
    private Color c;
    private static Color white = new Color(0xE3E3E3);
    private static Color black = Color.BLACK;
    private static Color red = new Color(0xB22222);
    private static Color blue = new Color(0x00008B);

    public Circle(double centerX, double centerY, double radius, Color c){
        this.x = centerX;
        this.y = centerY;
        this.r = radius;
        this.c = c;
    }

    public void draw(Graphics2D g){

        //ELLIPSE VERSION
        Shape circle = new Ellipse2D.Double(x, y, r, r);
        g.setColor(c);
        g.fill(circle);
        g.setColor(black);
        g.draw(circle);

        Shape ring = new Ellipse2D.Double(x+4,y+4, r-8, r-8);
        g.setColor(white);
        g.draw(ring);

        ring = new Ellipse2D.Double(x+8, y+8, r-16, r-16);
        g.draw(ring);

        ring = new Ellipse2D.Double(x+12, y+12, r-24, r-24);
        g.draw(ring);

        g.setColor(black);
        ring = new Ellipse2D.Double(x+16, y+16, r-32, r-32);
        g.fill(ring);

       // int xCenter = (int)(x + (x + ((Ellipse2D.Double) circle).getWidth()) / 2);
       // int yCenter =(int)(y + (y + ((Ellipse2D.Double) circle).getHeight()) / 2);
       // g.setColor(white);

       // g.drawString("X", xCenter, yCenter);

    }

    public void draw1(Graphics2D g){
        //ELLIPSE VERSION
        Shape circle = new Ellipse2D.Double(x, y, r, r);
        g.setColor(c);
        g.fill(circle);
        g.setColor(black);
        g.draw(circle);
    }

    public void drawDecor(Graphics2D g){

        Shape ring = new Ellipse2D.Double(x+20, y+20, r-40, r-40);
        g.setColor(white);
        g.draw(ring);

        g.setColor(red);
        ring = new Ellipse2D.Double(x+28, y+28, r-54, r-54);
        g.fill(ring);

        g.setColor(black);
        g.draw(ring);

    }


}



