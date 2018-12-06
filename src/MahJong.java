
import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;



public class MahJong extends JFrame {

    private MahJongBoard        game;
    private static Dimension    dim = new  Dimension(1550, 900);
    private int                 seed;
    private JMenuItem           soundItem, restart, load, undo, removed, redo;
    private Stack<Tile>         undoStack = new Stack<>();
    //private Stack<Tile>         showRemoved = new Stack<>();
    private JPanel[]            discard = new JPanel[2];
    private Color               yellow = Color.YELLOW;
    private int                 x, y;


    public MahJong(){

        //sound = true;
        seed = (int)(System.currentTimeMillis() % 1000000);
        setSize(dim);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MahJong GAME#: " + seed);

        game = new MahJongBoard(this, seed);
        add(game);

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
                int yes = JOptionPane.showConfirmDialog(null, "Quit this game and start a new one?",
                        "Game", JOptionPane.YES_NO_OPTION);
                if(yes == 0){
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
                int yes = JOptionPane.showConfirmDialog(null, "Restart this same Game from beginning?",
                        "Game", JOptionPane.YES_NO_OPTION);
                if(yes == 0){
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
               //removedTilePanel();
               int yes = JOptionPane.showConfirmDialog(null, "Undo Previous play?", "Undo", JOptionPane.YES_NO_OPTION);
               if(yes == 0){
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
                removedTilePanel();
            }
        });
        move.add(removed);
//--------------------------------------------------------------------------------
//--------add EXIT to Menu--------------------------------------------------------
        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
       // exit.setLayout();
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int yes = JOptionPane.showConfirmDialog(null, "Exit MahJong?", "Exit", JOptionPane.YES_NO_OPTION);
                if(yes == 0){
                    System.exit(0);
                }
            }
        });
        menu.add(Box.createHorizontalGlue());
        menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        menu.add(exit);

//**********END MENU OPTION BAR*****************************************************


        setVisible(true);

    }// end Mahjong constructor

    private void removedTilePanel(){
        undoStack = game.getRemovedTiles();
        System.out.println(undoStack);

       JScrollPane scroll = new JScrollPane();
       scroll.setPreferredSize(new Dimension(240, 2*470));
       scroll.setBorder(BorderFactory.createRaisedBevelBorder());

       //discard[0] = new JPanel(new FlowLayout(FlowLayout.LEFT));
       //discard[1] = new JPanel(new FlowLayout(FlowLayout.LEFT));
        discard[0] = new JPanel();
        discard[1] = new JPanel();
        discard[0].setPreferredSize(new Dimension(120, 120));
        discard[1].setPreferredSize(new Dimension(120, 120));

       scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       //scroll.getVerticalScrollBar().setUnitIncrement(10);

       JPanel panel = new JPanel(new BorderLayout());
       scroll.setViewportView(panel);

      // panel.add(discard[0], BorderLayout.NORTH);
      // panel.add(discard[1], BorderLayout.SOUTH);
        panel.add(discard[0], BorderLayout.WEST);
        panel.add(discard[1], BorderLayout.EAST);

        discard[0].setBackground(yellow);
        discard[1].setBackground(yellow);
        panel.setBackground(yellow);

        //discard[0].add(undoStack.pop());
        //discard[1].add(undoStack.pop());
        while(!undoStack.isEmpty()) {
            Tile t = undoStack.pop();
            t.setVisible(true);
            t.setSelected(false);
            discard[0].add(t);
            t = undoStack.pop();
            t.setVisible(true);
            t.setSelected(false);
            discard[1].add(t);
        }
        //scroll.revalidate();
        //scroll.repaint();

        

       JFrame frame = new JFrame();
       frame.setTitle("Removed Tiles");
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame.add(scroll);
       frame.setSize(260, 600);
       frame.setLocation(x - 260, y);
       frame.setVisible(true);

       //scroll.revalidate();
       //scroll.repaint();

    }

    private void undoMove(){
        if(game.canUndo()){
            game.undoTiles();
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
