import java.awt.*;
import javax.swing.*;

public class SeasonTile extends PictureTile {

    public SeasonTile(String name){
        super(name);
        setToolTipText(toString());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
       // Graphics2D g2 = (Graphics2D)g;
        //g2.drawImage(image, 40, 20, this);
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Season Tiles");

        frame.add(new SeasonTile("Spring"));
        frame.add(new SeasonTile("Summer"));
        frame.add(new SeasonTile("Fall"));
        frame.add(new SeasonTile("Winter"));

        frame.pack();
        frame.setVisible(true);
    }
}

