import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class BambooTile extends RankTile {

    private int centerX = 65, //starting x coordinate
                centerY = 36; //starting y coordinate

   private static Color green = new Color(0x006400);
   private static Color blue = new Color(0x00008B);
   private static Color red = new Color(0xB22222);

    public BambooTile(int rank){
        super(rank);
        setToolTipText(toString());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ArrayList<Bamboo> list= new ArrayList<>();
        switch(rank){
            case 2: list = bam2(); break;
            case 3: list = bam3(); break;
            case 4: list = bam4(); break;
            case 5: list = bam5(); break;
            case 6: list = bam6(); break;
            case 7: list = bam7(); break;
            case 8: list = bam8(); break;
            case 9: list = bam9(); break;

        }
        for(Bamboo b : list){
            if(g != null)
                b.draw(g2);

        }
    }

    public ArrayList<Bamboo> bam2(){
        ArrayList<Bamboo> list = new ArrayList<>();
        list.add(new Bamboo(centerX, centerY-18,    blue));
        list.add(new Bamboo(centerX, centerY+18,    green));
        return list;
    }

    public ArrayList<Bamboo> bam3(){
        ArrayList<Bamboo> list = new ArrayList<>();
        list.add(new Bamboo(centerX,    centerY-16, blue));
        list.add(new Bamboo(centerX-14, centerY+16, green));
        list.add(new Bamboo(centerX+14, centerY+16, green));
        return list;
    }

    public ArrayList<Bamboo> bam4(){
        ArrayList<Bamboo> list = new ArrayList<>();
        list.add(new Bamboo(centerX-14, centerY-18, blue));
        list.add(new Bamboo(centerX+14, centerY-18, green));
        list.add(new Bamboo(centerX-14, centerY+18, green));
        list.add(new Bamboo(centerX+14, centerY+18, blue));
        return list;
    }

    public ArrayList<Bamboo> bam5(){
        ArrayList<Bamboo> list = new ArrayList<>();
        list.add(new Bamboo(centerX-22, centerY-18, green));
        list.add(new Bamboo(centerX+22, centerY-18, blue));
        list.add(new Bamboo(centerX-22, centerY+18, blue));
        list.add(new Bamboo(centerX+22, centerY+18, green));
        list.add(new Bamboo(centerX,    centerY,    red));
        return list;
    }

    public ArrayList<Bamboo> bam6(){
        ArrayList<Bamboo> list = new ArrayList<>();
        list.add(new Bamboo(centerX-22, centerY-18, green));
        list.add(new Bamboo( centerX,centerY-18, green));
        list.add(new Bamboo(centerX+22, centerY-18, green));
        list.add(new Bamboo(centerX-22, centerY+18, blue));
        list.add(new Bamboo(centerX,centerY+18, blue));
        list.add(new Bamboo(centerX+22, centerY+18, blue));
        return list;
    }

    public ArrayList<Bamboo> bam7(){
        ArrayList<Bamboo> list = new ArrayList<>();
        list.add(new Bamboo(centerX,centerY-34, red));
        list.add(new Bamboo(centerX-22, centerY-1,    green));
        list.add(new Bamboo(centerX, centerY-1,    blue));
        list.add(new Bamboo(centerX+22, centerY-1,    green));
        list.add(new Bamboo(centerX-22, centerY+32, green));
        list.add(new Bamboo(centerX,centerY+32, blue));
        list.add(new Bamboo(centerX+22, centerY+32, green));
        return list;
    }

    public ArrayList<Bamboo> bam8(){
        ArrayList<Bamboo> list = new ArrayList<>();

        list.add(new Bamboo(centerX-28, centerY-33, green));
        list.add(new Bamboo(centerX,centerY-33, green));
        list.add(new Bamboo(centerX+28, centerY-33, green));
        list.add(new Bamboo(centerX-16, centerY,    red));
        list.add(new Bamboo(centerX+16, centerY,    red));
        list.add(new Bamboo(centerX-28, centerY+32, blue));
        list.add(new Bamboo(centerX,centerY+32, blue));
        list.add(new Bamboo(centerX+28, centerY+32, blue));
        return list;
    }

    public ArrayList<Bamboo> bam9(){
        ArrayList<Bamboo> list = new ArrayList<>();
        list.add(new Bamboo(centerX-24, centerY-34, red));
        list.add(new Bamboo(centerX,    centerY-34, blue));
        list.add(new Bamboo(centerX+24, centerY-34, green));
        list.add(new Bamboo(centerX-24, centerY-1,    red));
        list.add(new Bamboo(centerX,    centerY-1,    blue));
        list.add(new Bamboo(centerX+24, centerY-1,    green));
        list.add(new Bamboo(centerX-24, centerY+32, red));
        list.add(new Bamboo(centerX,    centerY+32, blue));
        list.add(new Bamboo(centerX+24, centerY+32, green));
        return list;
    }


    public String toString(){

        return "Bamboo " + rank;
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bamboo Tiles");

        frame.add(new BambooTile(2));
        frame.add(new BambooTile(3));
        frame.add(new BambooTile(4));
        frame.add(new BambooTile(5));
        frame.add(new BambooTile(6));
        frame.add(new BambooTile(7));
        frame.add(new BambooTile(8));
        frame.add(new BambooTile(9));

        frame.pack();
        frame.setVisible(true);
    }


}

