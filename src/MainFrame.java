import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame {
    private Container cp;
    private JMenuItem jMenuItemFEixt = new JMenuItem("Exit");
    private JMenuItem jMenuItemGLoto = new JMenuItem("Loto");
    private JMenuBar jmb = new JMenuBar();
    private  JMenu jmf = new JMenu("File");
    private  JMenu jms = new JMenu("Set");
    private  JMenu jmg = new JMenu("Game");
    private  JMenu jma = new JMenu("About");

    private  Container jlfCP;
    private  JInternalFrame jInternalFrame = new JInternalFrame();
    private  JDesktopPane jdp = new JDesktopPane();
    private  JPanel jpn = new JPanel(new GridLayout(1,6,5,5));
    private  JPanel jpn1 = new JPanel(new GridLayout(1,2,5,5));
    private  JLabel jlbs[] = new JLabel[6];
    private  JButton jbtnClose = new JButton("CLOSE");
    private  JButton jbtnRegen = new JButton("GENERATE");
    private  int data[] = new int[6];
    private Random rnd = new Random(System.currentTimeMillis());

    private  JTextField jtf = new JTextField();
    private  JButton jbtns[] = new JButton[9];
    private  LoginFrame loginFrame;
    public  MainFrame(LoginFrame login){
        loginFrame = login;
        initComp();
    }
    private void initComp(){
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout(5,5));
        this.setBounds(100 ,100,400,600);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
        jmb.add(jmf);
        jmb.add(jms);
        jmb.add(jmg);
        jmb.add(jma);
        jmf.add(jMenuItemFEixt);
        jmg.add(jMenuItemGLoto);
        jMenuItemGLoto.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jInternalFrame.setBounds(0,0,300,150);
        jlfCP = jInternalFrame.getContentPane();
        jlfCP.setLayout(new BorderLayout(5,5));
        jlfCP.add(jpn,BorderLayout.CENTER);
        jlfCP.add(jpn1,BorderLayout.SOUTH);
        jpn1.add(jbtnClose);
        jpn1.add(jbtnRegen);
        for(int i = 0; i<6;i++){
            jlbs[i] = new JLabel();
            jlbs[i].setFont(new Font(null,Font.PLAIN,22));
            jlbs[i].setOpaque(true);
            jlbs[i].setBackground(new Color(255,228,128));
            jlbs[i].setHorizontalAlignment(JLabel.CENTER);
            jpn.add(jlbs[i]);
        }


        jInternalFrame.setBounds(0,0,300,300);
        jbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jInternalFrame.dispose();
            }
        });
        jMenuItemGLoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jdp.add(jInternalFrame);
                jInternalFrame.setVisible(true);
            }
        });
        jMenuItemFEixt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        jMenuItemFEixt.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loginFrame.reset();
                loginFrame.setVisible(true);

            }

            private  void lotoGenerate() {
            int i = 0;
            while (i<6);
            data[i] = rnd.nextInt(42)+1;
            int j = 0;
             boolean flag = true;
             while(j<i && flag){
                 if(data[i] == data[j]){
                     flag = false;
                 }
                 j++;
             }
             if(flag){
                 jlbs[i].setText(Integer.toString(data[i]));
                 i++;
             }
            }
            });
        // this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cp.add(jtf, BorderLayout.NORTH);
        cp.add(jpn,BorderLayout.CENTER);
        jtf.setEditable(false);
        for(int i=0; i<9; i++){
            jbtns[i] = new JButton(Integer.toString(i));
            jpn.add(jbtns[i]);
            jbtns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton tmpButton = (JButton) e.getSource();
                    jtf.setText(jtf.getText()+tmpButton.getText());
                }
            });
        }

    }
}
