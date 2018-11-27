import com.sun.codemodel.internal.JOp;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;



public class MahJong extends JFrame {

    private MahJongBoard                game;
    private static Dimension    dim = new Dimension(1550, 900);
    private int                 seed;
    private JMenuItem soundItem, undo, restart, load, redo;


    public MahJong(){

        //sound = true;
        seed = (int)(System.currentTimeMillis() % 1000000);
        setSize(dim);
        //setLayout(new BorderLayout());
       // setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MahJong GAME#: " + seed);

        game = new MahJongBoard(this, seed);
        add(game);

        //place in middle of screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

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
        soundItem = new JMenuItem("Sound Off");
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
        JMenuItem newGame = new JMenuItem("New Game");
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
        restart = new JMenuItem("Restart Game");
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
        load = new JMenuItem("Load Game");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameNum = JOptionPane.showInputDialog(null, "Enter Game Number to load: ");
                if(!gameNum.isEmpty() && gameNum.length() == 6 && isInteger(gameNum)){
                    loadGame(gameNum);

                }
            }
        });
        game.add(load);


 //---------------------------------------------------------------------------------

 //------add Undo to Menu-----------------------------------------------------------



//**********END MENU OPTION BAR*****************************************************


        setVisible(true);

    }// end Mahjong constructor


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
