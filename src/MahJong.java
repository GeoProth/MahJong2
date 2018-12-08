
import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;



public class MahJong extends JFrame {

    private MahJongBoard        game;
    private static Dimension    dim = new  Dimension(1550, 900);
    private int                 seed;
    private JMenuItem           soundItem, restart, load, undo, removed, exit, redo;
    private Stack<Tile>         undoStack = new Stack<>();
    //private Stack<Tile>       showRemoved = new Stack<>();
    private JPanel[]            discard = new JPanel[2];
    private Color               yellow = Color.YELLOW;
    private boolean             removedVisible = false;
    private JFrame              removedFrame;
    private JPanel              removedTiles = new JPanel(new BorderLayout());
    private int                 x, y;


    public MahJong(){

        initRemovedTilePanel();
        //sound = true;
        seed = (int)(System.currentTimeMillis() % 1000000);
        setSize(dim);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MahJong GAME#: " + seed);

        game = new MahJongBoard(this, seed);
        add(game);
        game.addTurnTakenListener(new TurnTakenListener() {
            @Override
            public void turnTaken() {
                removedTilePanel();
            }
        });

        //place in middle of screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2 + 100, (screenSize.height - getHeight()) / 2);
        x = (screenSize.width - getWidth()) / 2 + 99;
        y = (screenSize.height - getHeight()) / 2;

//***********CREATE MENU OPTION BAR**************************************************
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);

//--------add help options to menu---------------------------------------------------
        JMenu help = new JMenu("Help");
        menu.add(help);

        //link to help.html
        JMenuItem helpOptions = new JMenuItem("Game Help");
        helpOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Help h = new Help("help/help.html", "Help");
                h.display();
            }
        });
        help.add(helpOptions);
//----------------------------------------------------------------------------------

//--------add sound option to menu--------------------------------------------------
        JMenu soundOption = new JMenu("Sound");
        menu.add(soundOption);

        //add sound controls
        soundItem = new JMenuItem("Sound Off", 'S');
        soundItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        soundItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSound();
            }
        });
        soundOption.add(soundItem);

//----------------------------------------------------------------------------------

//-------add Game Options to menu------------------------------------------------
        JMenu game = new JMenu("Game");
        menu.add(game);

    //NEW GAME
        JMenuItem newGame = new JMenuItem("New Game", 'N');
        newGame.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(null, "Quit this game and start a new one?",
                        "Game", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    newGame();
                }
            }
        });
        game.add(newGame);

    //RESTART GAME
        restart = new JMenuItem("Restart Game", 'G');
        restart.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(null, "Restart this same Game from beginning?",
                        "Game", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    String newSeed = Integer.toString(seed);
                    loadGame(newSeed);
                }
            }
        });
        game.add(restart);

    //LOAD PREVIOUS GAME
        load = new JMenuItem("Load Game", 'L');
        load.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameNum = JOptionPane.showInputDialog(null, "Enter 6 digit Game Number to load: ");
                if(!gameNum.isEmpty() && gameNum.length() == 6 && isInteger(gameNum)){
                    loadGame(gameNum);

                }
            }
        });
        game.add(load);


 //---------------------------------------------------------------------------------

 //------add Undo to Menu-----------------------------------------------------------
       JMenu move = new JMenu("Move");
       menu.add(move);

       //UNDO
       undo = new JMenuItem("Undo", 'U');
       undo.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
       undo.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(JOptionPane.showConfirmDialog(null, "Undo Previous play?", "Undo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                   undoMove();
               }
           }
       });
       move.add(undo);

       //Removed Tiles Scroll Bar
        removed = new JMenuItem("Removed Tiles", 'R');
        removed.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
        removed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removedVisible = !removedVisible;
                removedFrame.setVisible(removedVisible);
                removedTilePanel();
            }
        });
        move.add(removed);
//--------------------------------------------------------------------------------
//--------add EXIT to Menu--------------------------------------------------------
        JMenu exitOption = new JMenu("Exit");
        menu.add(Box.createHorizontalGlue());
        menu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        menu.add(exitOption);

        exit = new JMenuItem("Exit Game", 'E');
        exit.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(null, "Exit MahJong?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }

            }
        });

        exitOption.add(exit);

//**********END MENU OPTION BAR*****************************************************


        setVisible(true);

    }// end Mahjong constructor

    private void removedTilePanel(){
        removedTiles.removeAll();
        undoStack = game.getRemovedTiles();
        System.out.println(undoStack);

       //JScrollPane scroll = new JScrollPane();
       //scroll.setPreferredSize(new Dimension(240, 2*470));
       //scroll.setBorder(BorderFactory.createRaisedBevelBorder());

       //discard[0] = new JPanel(new FlowLayout(FlowLayout.LEFT));
       //discard[1] = new JPanel(new FlowLayout(FlowLayout.LEFT));
        discard[0] = new JPanel();
        discard[1] = new JPanel();
        discard[0].setPreferredSize(new Dimension(120, 120));
        discard[1].setPreferredSize(new Dimension(120, 120));

       //scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       //scroll.getVerticalScrollBar().setUnitIncrement(10);

       //JPanel panel = new JPanel(new BorderLayout());
       //scroll.setViewportView(panel);

      // panel.add(discard[0], BorderLayout.NORTH);
      // panel.add(discard[1], BorderLayout.SOUTH);
        removedTiles.add(discard[0], BorderLayout.WEST);
        removedTiles.add(discard[1], BorderLayout.EAST);

        discard[0].setBackground(yellow);
        discard[1].setBackground(yellow);
        removedTiles.setBackground(yellow);

        if(undoStack.isEmpty()){
            removedFrame.repaint();
        }

        //discard[0].add(undoStack.pop());
        //discard[1].add(undoStack.pop());
        try {
            while (!undoStack.isEmpty()) {
                Tile t = undoStack.pop();
                Tile clone1 = (Tile)t.clone();
                Tile clone2 = (Tile)t.clone();
                t.setVisible(false);
                t.setSelected(false);
                discard[0].add(clone1);
                t = undoStack.pop();
                t.setVisible(false);
                t.setSelected(false);
                discard[1].add(clone2);
                clone1.setSelected(false);
                clone2.setSelected(false);
                clone1.setVisible(true);
                clone2.setVisible(true);
            }
        }
        catch(Exception e){

        }
        System.out.println(game.getRemovedTiles());
        //scroll.revalidate();
        //scroll.repaint();

      /* JFrame frame = new JFrame();
        frame.setTitle("Removed Tiles");
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame.add(scroll);
       frame.setSize(260, 600);
       frame.setLocation(x - 260, y);
       frame.setVisible(true);
    */


       //scroll.revalidate();
       //scroll.repaint();

    }
    private void initRemovedTilePanel(){
        JScrollPane scroll = new JScrollPane();
        scroll.setPreferredSize(new Dimension(240, 2*470));
        scroll.setBorder(BorderFactory.createRaisedBevelBorder());

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scroll.setViewportView(removedTiles);

        removedFrame = new JFrame();
        removedFrame.setTitle("Removed Tiles");
        removedFrame.setSize(260, 900);
        removedFrame.setLocation(this.x - 260, this.y);
        removedFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        removedFrame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {
                    removedVisible = true;
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                    removedVisible = false;
            }
        });
        removedFrame.add(scroll);

    }


    private void undoMove(){
        if(game.canUndo()){
            game.undoTiles();
            removedTilePanel();
        }
        //undoStack = game.getRemovedTiles();
    }

    private void setSound(){
        // turn sound on/off
        if(game.getSound()){
            game.toggleSound(false);
        }
        else{
            game.toggleSound(true);
        }
        soundItem.setText(game.getSound() ? "Sound Off" : "Sound On");

    }

    private void newGame(){
        remove(game);
        seed = (int) System.currentTimeMillis() % 1000000;
        game = new MahJongBoard(this, seed);
        add(game);
        setTitle("MahJong GAME#: " + seed);
        repaint();
    }
    private boolean isInteger(String str){
        try{
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }
    private void loadGame(String gameNum){
        remove(game);
        seed = Integer.parseInt(gameNum);
        game = new MahJongBoard(this, seed);
        add(game);
        setTitle("MahJong GAME#: " + seed);
        repaint();

    }




    public static void main(String[] args){
        new MahJong();
    }


}
