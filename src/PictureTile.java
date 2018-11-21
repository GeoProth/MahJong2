

import java.awt.*;
import javax.swing.*;
public class PictureTile extends Tile {
    private String              name;
    private ImageIcon    image;

    public PictureTile(String name){
        this.name = name;
        setToolTipText(toString());

        image = new ImageIcon("images/" + this.name + ".png");

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        image = new ImageIcon(image.getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));

        g2.drawImage(image.getImage(), 26, 6, this);
    }


    public String toString(){

        return name;
    }


    public static void main(String[] args) {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Picture Tiles");

        frame.add(new Bamboo1Tile());

        frame.add(new FlowerTile("Chrysanthemum"));
        frame.add(new FlowerTile("Orchid"));
        frame.add(new FlowerTile("Plum"));
        frame.add(new FlowerTile("Bamboo"));

        frame.add(new SeasonTile("Spring"));
        frame.add(new SeasonTile("Summer"));
        frame.add(new SeasonTile("Fall"));
        frame.add(new SeasonTile("Winter"));

        frame.pack();
        frame.setVisible(true);
    }

}

