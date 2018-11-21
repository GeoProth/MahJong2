import java.util.*;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.*;

public class CharacterTile extends Tile {
    private static final HashMap<Character, Character> tileMap = new HashMap<>();
    private static char wan = '\u842C';
    protected char symbol;
    private static Color red = new Color(0xB22222);
    private static Color black = Color.BLACK;
    private static Color green = Color.decode("#2E8B57");
    private static Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 78);
    private static Font myFont2 = new Font("Serif", Font.ITALIC | Font.BOLD, 40);

    static {
        tileMap.put('1', '\u4E00');
        tileMap.put('2', '\u4E8C');
        tileMap.put('3', '\u4E09');
        tileMap.put('4', '\u56DB');
        tileMap.put('5', '\u4E94');
        tileMap.put('6', '\u516D');
        tileMap.put('7', '\u4E03');
        tileMap.put('8', '\u516B');
        tileMap.put('9', '\u4E5D');
        tileMap.put('N', '\u5317'); // North
        tileMap.put('E', '\u6771'); // East
        tileMap.put('W', '\u897F'); // West
        tileMap.put('S', '\u5357'); // South
        tileMap.put('C', '\u4E2d'); // Red
        tileMap.put('F', '\u767C'); // Green

    }


    public CharacterTile(char symbol) {
        this.symbol = symbol;
        setToolTipText(toString());
    }

   // public CharacterTile copy(){
       // return new CharacterTile(this.symbol);

   // }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //Draw Small Character in upper right corner
        Font f = g2.getFont().deriveFont(18F);
        g2.setFont(f);
        g2.setColor(red);
        g2.drawString(Character.toString(symbol), 100, 18);

        if(Character.isDigit(symbol)) {
            //Draw Large Chinese Character
            //set color
            ///f = g2.getFont().deriveFont(40F);
            g2.setFont(myFont2);
            g2.setColor(black);
            g2.drawString(toChinese(), 50, 40);

            //Draw WAN
            g2.setColor(red);
            g2.drawString(Character.toString(wan), 50, 85);

        }
        else{

           // f = g2.getFont().deriveFont(78F);
            g2.setFont(myFont);

            if(symbol == 'C'){
                g2.setColor(red);

            }
            else if(symbol == 'F'){
                g2.setColor(green);
            }
            else{
                g2.setColor(black);
            }
            g2.drawString(toChinese(), 30, 80);
        }
    }



    public boolean matches(Tile other) {
        if (super.matches(other)) {
            CharacterTile ct = ((CharacterTile) other);
            return symbol == ct.symbol;

        }
        return false;
    }

    public final String toString() {

        if (Character.isDigit(symbol)) {
            return "Character " + symbol;
        }
        switch (symbol) {
            case 'N':
                return "North Wind";
            case 'E':
                return "East Wind";
            case 'W':
                return "West Wind";
            case 'S':
                return "South Wind";
            case 'C':
                return "Red Dragon";
            case 'F':
                return "Green Dragon";

        }

        return "invalid";

    }

    public String toChinese() {
        return Character.toString(tileMap.get(symbol));
    }

    public static void main(String[] args)
    {
        JFrame		frame = new JFrame();
        JPanel		tiles = new JPanel();
        JScrollPane	scroller = new JScrollPane(tiles);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Character Tiles");
        frame.add(scroller);

        // Try something like this if your tiles don't fit on the screen.
        // Replace "tile width" and "tile height" with your values.
        //scroller.setPreferredSize(new Dimension(8 * tile width, 40 + tile height));

        tiles.add(new CharacterTile('1'));
        tiles.add(new CharacterTile('2'));
        tiles.add(new CharacterTile('3'));
        tiles.add(new CharacterTile('4'));
        tiles.add(new CharacterTile('5'));
        tiles.add(new CharacterTile('6'));
        tiles.add(new CharacterTile('7'));
        tiles.add(new CharacterTile('8'));
        tiles.add(new CharacterTile('9'));
        tiles.add(new CharacterTile('N'));
        tiles.add(new CharacterTile('E'));
        tiles.add(new CharacterTile('W'));
        tiles.add(new CharacterTile('S'));
        tiles.add(new CharacterTile('C'));
        tiles.add(new CharacterTile('F'));

        frame.pack();
        frame.setVisible(true);
    }

}
