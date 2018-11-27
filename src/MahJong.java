import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;



public class MahJong extends JFrame {

    private MahJongBoard                game;
    private static Dimension    dim = new Dimension(1550, 900);
   // public boolean              sound;
    private int                 seed;
    private JMenuItem soundItem, undo, restart, redo;


    public MahJong(){

        //sound = true;
        seed = (int)(System.currentTimeMillis() % 100000);
        setSize(dim);
        //setLayout(new BorderLayout());
       // setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MahJong Game");

        game = new MahJongBoard(this);
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
        JMenuItem helpOptions = new JMenuItem("HelpOptions");
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
    }


    public static void main(String[] args){

        new MahJong();
    }


}
