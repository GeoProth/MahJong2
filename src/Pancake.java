import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Pancake extends Circle {

    private double x, y, r;
    private Color c;
    private static Color red = new Color(0xB22222);


    public Pancake(double centerX, double centerY, double radius, Color c){
            super(centerX, centerY, radius, c);
            this.x = centerX;
            this.y = centerY;
            this.r = radius;
            this.c = c;
    }

    public void draw(Graphics2D g){
            super.draw1(g);
           // Shape circle = new Ellipse2D.Double(x, y, r, r);
            //g.setColor(c);
            //g.fill(circle);
        super.drawDecor(g);

    }
}
