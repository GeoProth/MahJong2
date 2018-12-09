import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Tile extends JPanel implements Cloneable{
    private static Dimension        dim = new Dimension(120, 120);
    private static Polygon          left = new Polygon();
    private static Polygon          lm = new Polygon();
    private static Polygon          bottom = new Polygon();
    private static Polygon          bm = new Polygon();
    private static GradientPaint    rectGrad = new GradientPaint(120,0, Color.decode("#FFE7BA"), 20,100, Color.decode("#DAA520"));
    private static GradientPaint    lPoly = new GradientPaint(5, 5, Color.decode("#006400"), 5, 115, Color.decode("#8FBC8F"));
    private static GradientPaint    lmPoly = new GradientPaint(15, 5, Color.decode("#DAA520"), 0, 120, Color.decode("#FFE7BA"));
    private static GradientPaint    bottomPoly = new GradientPaint(115, 115, Color.decode("#006400"), 5, 115, Color.decode("#8FBC8F"));
    private static GradientPaint    bmPoly = new GradientPaint(105, 105, Color.decode("#DAA520"), 15, 105, Color.decode("#FFE7BA"));
    private static GradientPaint    newColor = new GradientPaint(120, 0, Color.decode("#8B5A00"), 20, 100, Color.decode("#FFB90F"));
    private static Color            black = Color.BLACK;
    private boolean                 selected = false;
    private boolean                 isTile;

    private int x, y, z, zOrder;


    static{ //INITIALIZE POLYGONS POINTS
        //left Poly
        left.addPoint(10, 10);
        left.addPoint(0, 20);
        left.addPoint(0, 120);
        left.addPoint(10, 110);

        //left middle poly
        lm.addPoint(20, 0);
        lm.addPoint(10, 10);
        lm.addPoint(10, 110);
        lm.addPoint(20, 100);

        //Bottom Poly
        bottom.addPoint(0, 120);
        bottom.addPoint(10, 110);
        bottom.addPoint(110, 110);
        bottom.addPoint(100, 120);

        //Bottom Middle Poly
        bm.addPoint(10, 110);
        bm.addPoint(20, 100);
        bm.addPoint(120, 100);
        bm.addPoint(110, 110);

    }
        public Tile(){
            isTile = true;
            setSize(new Dimension(dim));
            setPreferredSize(new Dimension(dim)); //THIS IS NESTED INSIDE OF JFRAME (TAKES A DIMENSION OBJECT )
            setOpaque(false);


        }

        public Object clone() throws CloneNotSupportedException
        {
            return super.clone();
        }

        public Tile(boolean bool){
                this.isTile = bool;
        }


        public void paintComponent(Graphics g) {
            //RECTANGLE
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // if tile is selected, change color
            g2.setPaint(selected ? newColor : rectGrad);
            g.fillRect(20, 0, 100, 100);

            //POLYGONS
            //Left
            g2.setPaint(lPoly);
            g2.fillPolygon(left);

            //PolyGon left Middle
            g2.setPaint(lmPoly);
            g2.fillPolygon(lm);

            //PolyGon Bottom
            g2.setPaint(bottomPoly);
            g2.fillPolygon(bottom);

            //PolyGon Bottom Middle
            g2.setPaint(bmPoly);
            g2.fillPolygon(bm);

            g2.setColor(Color.BLACK);
            g2.drawPolygon(left);
            g2.drawPolygon(lm);
            g2.drawPolygon(bottom);
            g2.drawPolygon(bm);
            g2.drawRect(20, 0, 99, 99);

        }


    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public boolean getSelected(){
        return this.selected;
    }

    public void setZOrder(int zOrder){
        this.zOrder = zOrder;
    }

    public void setCoords(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean isTile(){
        return isTile;
    }
    public Tile getTile(){
        return this;
    }

    public int getXCoord(){
        return x;
    }
    public int getYCoord(){
        return y;
    }
    public int getZCoord(){
        return z;
    }

    public boolean matches(Tile other){
        if(getClass() != other.getClass()){
            return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tile");

        frame.add(new Tile());

        frame.pack();
        frame.setVisible(true);
    }
}

