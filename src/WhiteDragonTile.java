import java.awt.*;
import javax.swing.*;

public class WhiteDragonTile extends Tile {

    public WhiteDragonTile(){
        setToolTipText("White Dragon");
    }

    private static Color white = Color.WHITE;
    private static Color blue = new Color(0x00008B);
    public String toString(){

        return "White Dragon";
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(blue);
        g2.fillRect(27,7, 14, 8);
        g2.fillRect(55,7, 14, 8);
        g2.fillRect(83,7, 14, 8);
        g2.fillRect(27, 29, 8, 14);
        g2.fillRect(27, 57, 8, 14);
        g2.fillRect(103, 15, 8, 14);
        g2.fillRect(103, 43, 8, 14);
        g2.fillRect(103, 71, 8, 14);
        g2.fillRect(27,85, 14, 8);
        g2.fillRect(55,85, 14, 8);
        g2.fillRect(83,85, 14, 8);


       //White
        g2.setColor(white);
        g2.fillRect(41, 7, 14, 8);
        g2.fillRect(69,7, 14, 8);
        g2.fillRect(97,7, 14, 8);
        g2.fillRect(27, 15, 8, 14);
        g2.fillRect(27, 43, 8, 14);
        g2.fillRect(27, 71, 8, 14);
        g2.fillRect(103, 29, 8, 14);
        g2.fillRect(103, 57, 8, 14);
        g2.fillRect(41,85, 14, 8);
        g2.fillRect(69,85, 14, 8);
        g2.fillRect(97,85, 14, 8);

        g2.setColor(blue);
        g2.drawRect(26, 6, 85,  86);
        g2.drawRect(35, 14, 68, 70);
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("White Dragon");

        frame.add(new WhiteDragonTile());

        frame.pack();
        frame.setVisible(true);
    }


}
