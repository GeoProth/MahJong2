import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;
import javax.swing.*;
import java.util.Stack;


public class MahJongBoard extends JPanel{

   // private String name; //name of file for dragon image
    private ImageIcon           dragon;
    private PlayClip            clip = new PlayClip("audio/stone-scraping.wav", true);
    private boolean             sound;//global sound variable
    private static Dimension    dim = new Dimension(1550, 865);
    private static Color        yellow = Color.yellow;
    private static final int    X_AXIS = 15, //for placement of tiles on 3D array
                                Y_AXIS = 8,
                                Z_AXIS = 5,
                                MAX = 144;

    // 3D array for mapping game tiles
    private int[][][] gameArray = {   { {0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},	// [z0, x, y]
                                        {0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
                                        {0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
                                        {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                        {1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
                                        {0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
                                        {0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
                                        {0,1,1,1,1,1,1,1,1,1,1,1,1,0,0}},

                                    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	// [z1, x, y]
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},

                                    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	// [z2, x, y]
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
                                        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
                                        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
                                        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},

                                    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},    // [z3, x, y]
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},

                                    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},    // [z4, x, y]
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},
                                    };

    private MahJong                 main;
    private Tile[][][]              map = new Tile[Z_AXIS][Y_AXIS][X_AXIS];//game board to place tiles
    private ArrayList<Tile>         deck = new ArrayList<>(MAX);
    private Tile                    first; // first to contain selected tiles for matching
    private Tile                    second;// second to contain selected tiles for matching
    private ArrayList<Tile>         removedTiles = new ArrayList<>(MAX);// hold removed tiles for panel
    private ArrayList<Tile>         redoTiles = new ArrayList<>(MAX);
    private Set<TurnTakenListener>  turnTakenListenerSet = new HashSet<>();


    public MahJongBoard(MahJong game, int seed){
        this.main = game;

        //get background image
        dragon = new ImageIcon("images/dragon_bg.png");

        setLayout(null);
        setSize(dim);
        tileDeck();
        fillGame(seed);
        placeTiles();

        first = new Tile(false);
        second = new Tile(false);
        toggleSound(true);

        setVisible(true);


    }//end constructor

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        setBackground(yellow);//get dragon background

        dragon = new ImageIcon(dragon.getImage().getScaledInstance( 1200,800, Image.SCALE_SMOOTH));
        g2.drawImage(dragon.getImage(), (getWidth() - dragon.getIconWidth()) / 2, (getHeight() - dragon.getIconHeight()) / 2, this);

        g2.setColor(Color.BLACK);


    }//end paintComponent

    //place tiles on board according to zOrder, build from top to bottom, left to right
    private void placeTiles(){
        int zOrder = 0;
        for(int z = 4; z >= 0; z--){
            for(int y = 7; y >= 0; y--){
                for(int x = 0; x < 15; x++){


                    if(map[z][y][x].isTile()){
                        //System.out.println(map[z][y][x]);
                        Tile t = map[z][y][x].getTile();

                        //set placements of tiles based on x,y,z and size of tiles with respect to board
                        //top layer special case
                        if(x == 6 && y == 3 && z == 4){
                            t.setBounds(745, 300, 120, 120);

                        }
                        //right most tiles special cases
                        else if((x == 14 && y == 3 && z == 0) || (x == 13 && y == 3 && z == 0)){
                            t.setBounds(x * 100 + 20, 360, 120, 120);

                        }
                        //left most tile special case
                        else if(x == 0 && y == 4 && z == 0){
                            t.setBounds( 20,  370, 120, 120);

                        }

                        else{
                            t.setBounds(x * 100 + (z*20) + 20, y * 100 - (z*20) + 20, 120, 120);

                        }
                        t.setCoords(x,y,z);
                        setComponentZOrder(t, zOrder);
                        map[z][y][x].setZOrder(zOrder);
                        zOrder++;


                        t.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                if(isTileOpen(t.getXCoord(), t.getYCoord(), t.getZCoord())) {

                                    //if no tile has been selected yet
                                    if(!first.isTile()){

                                        first = map[t.getZCoord()][t.getYCoord()][t.getXCoord()];
                                        first.setSelected(true);
                                        //clip.play();
                                        repaint();
                                    }
                                    else {
                                        second = map[t.getZCoord()][t.getYCoord()][t.getXCoord()];
                                        second.setSelected(true);

                                        if (first.matches(second) && first != map[t.getZCoord()][t.getYCoord()][t.getXCoord()]) {

                                            //t.setSelected(true);
                                            map[t.getZCoord()][t.getYCoord()][t.getXCoord()].setSelected(true);


                                            removedTiles.add(first);
                                            removedTiles.add(second);

                                            if(sound)
                                                clip.play();


                                            map[t.getZCoord()][t.getYCoord()][t.getXCoord()] = new Tile(false);
                                            map[first.getZCoord()][first.getYCoord()][first.getXCoord()] = new Tile(false);

                                            t.setVisible(false);
                                            first.setVisible(false);

                                            first = new Tile(false);
                                            second = new Tile(false);
                                            //System.out.println(removedTiles);

                                            for(TurnTakenListener ttl : turnTakenListenerSet){
                                                ttl.turnTaken();
                                            }

                                            if(removedTiles.size() == MAX){
                                                gameWon();
                                            }

                                            //repaint();

                                        }//end if
                                        else if(!first.matches(second) && first != second){
                                            first.setSelected(false);
                                            repaint();
                                            first = second;
                                            first.setSelected(true);
                                            repaint();
                                            second = new Tile(false);
                                        }//end else if

                                    }// end else

                                }// end if tile is open
                            }//end mouse pressed
                        });//end mouseListener

                        add(t);

                    }//end if isTile

                }
            }
        }//end loops

    }// end placeTile// s

    public boolean isTileOpen(int x, int y, int z)
    {
        if (x == 0 || x == X_AXIS-1 || z == Z_AXIS-1) {
            return true;
        }
        if(z == 3 && map[Z_AXIS - 1][3][6].isTile()){
            return false;
        }

        return !map[z+1][y][x].isTile() &&
                (!map[z][y][x-1].isTile() || !map[z][y][x+1].isTile());
    }


    private void tileDeck(){
        //build deck of all tiles

        for (int i = 0; i < 4; i++)
        {
            // CharacterTiles
            for (char c = '1'; c <= '9'; c++)
                deck.add(new CharacterTile(c));
                deck.add(new CharacterTile('N'));
                deck.add(new CharacterTile('E'));
                deck.add(new CharacterTile('W'));
                deck.add(new CharacterTile('S'));
                deck.add(new CharacterTile('C'));
                deck.add(new CharacterTile('F'));

            // CircleTiles
            for (int j = 1; j < 10; j++)
                deck.add(new CircleTile(j));

            // BambooTiles
            for (int j = 2; j < 10; j++)
                deck.add(new BambooTile(j));

            // Special Tiles
            deck.add(new WhiteDragonTile());
            deck.add(new Bamboo1Tile());
        }

        // FlowerTiles
        deck.add(new FlowerTile("Chrysanthemum"));
        deck.add(new FlowerTile("Orchid"));
        deck.add(new FlowerTile("Plum"));
        deck.add(new FlowerTile("Bamboo"));

        // SeasonTiles
        deck.add(new SeasonTile("Spring"));
        deck.add(new SeasonTile("Summer"));
        deck.add(new SeasonTile("Fall"));
        deck.add(new SeasonTile("Winter"));

    }//end tileDeck

    //fill a game block with tiles randomly selected using gameMap
    private void fillGame(int seed){
        Random rand = new Random(seed);
        //Collections.shuffle(deck, rand);
        for(int z = 0; z < Z_AXIS; z++){
            for(int y = 0; y < Y_AXIS; y++){
                for(int x = 0; x < X_AXIS; x++){
                    if(gameArray[z][y][x] == 1){//find 1's from gameArray and match map with tile placements

                        map[z][y][x] = deck.remove(rand.nextInt(deck.size()));//random selection for tile
                        //System.out.println(map[z][y][x]);

                    }
                    else{
                        map[z][y][x] = new Tile(false);
                    }
                }
            }
        }


    }//end fillGame

    public Stack<Tile> getRemovedTiles(){
        Stack<Tile> removeTileStack = new Stack<>();
        for(Tile t : removedTiles){
            removeTileStack.push(t);
        }
        //System.out.println(removedTiles);
        return removeTileStack;
    }
    public boolean canUndo(){
        return !removedTiles.isEmpty();
    }

    public void undoTiles(){
        //removedTiles list
        Tile undo1 = removedTiles.remove(removedTiles.size() - 1);
        Tile undo2 = removedTiles.remove(removedTiles.size() - 1);

        redoTiles.add(undo1);
        redoTiles.add(undo2);


        map[undo1.getZCoord()][undo1.getYCoord()][undo1.getXCoord()].setVisible(true);
        map[undo2.getZCoord()][undo2.getYCoord()][undo2.getXCoord()].setVisible(true);
        map[undo1.getZCoord()][undo1.getYCoord()][undo1.getXCoord()].setSelected(false);
        map[undo2.getZCoord()][undo2.getYCoord()][undo2.getXCoord()].setSelected(false);


    }

    public boolean canRedo(){
        return !redoTiles.isEmpty();
    }

    public void redoTiles(){
        Tile redo1 = redoTiles.remove(redoTiles.size() - 1);
        Tile redo2 = redoTiles.remove(redoTiles.size() -1 );

        removedTiles.add(redo1);
        removedTiles.add(redo2);

        map[redo1.getZCoord()][redo1.getYCoord()][redo1.getXCoord()].setVisible(false);
        map[redo2.getZCoord()][redo2.getYCoord()][redo2.getXCoord()].setVisible(false);
        map[redo1.getZCoord()][redo1.getYCoord()][redo1.getXCoord()].setSelected(false);
        map[redo2.getZCoord()][redo2.getYCoord()][redo2.getXCoord()].setSelected(false);
    }

    public void addTurnTakenListener(TurnTakenListener ttl){
        turnTakenListenerSet.add(ttl);
    }


    public void gameWon(){
        Fireworks fire = new Fireworks(this);

        fire.setExplosions(10, 1000);
        fire.setSound(sound);
        fire.fire();

    }

    public void toggleSound(boolean s){
        sound = s;
    }

    public boolean getSound(){
        return sound;
    }





}// end Class
