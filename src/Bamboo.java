import java.awt.*;
import javax.swing.*;



public class Bamboo {

        private int x, y;
        private Color c;
        public Bamboo(int centerX, int centerY, Color c){
            this.x = centerX;
            this.y = centerY;
            this.c = c;
        }

    public void draw(Graphics2D g2){


        g2.setColor(c);
        //    g2.drawRect(x, y, 6, 24);
       // AffineTransform old = g2.getTransform();
        // g2.drawArc(x-4, y+20, 13, 8, 0, 180);
        g2.fillArc(x-4, y+26, 13, 9, 0, 180);
        g2.fillArc(x-4, y + 14, 13, 9, 0, 180);
        g2.fillArc(x-4, y-2, 13, 9, 0, 180);

        g2.fillRect(x, y, 6, 30);
       // g2.setPaint(c);

        g2.setColor(Color.WHITE);
        //g2.setStroke(new BasicStroke(2));
        g2.drawLine(x+3, y+1, x+3, y+10);
        g2.drawLine(x+3, y+16, x+3, y+26);

    }



}
