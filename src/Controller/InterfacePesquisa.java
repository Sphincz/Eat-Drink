package Controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InterfacePesquisa extends JFrame {

    public InterfacePesquisa() {
    }
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ControllerPesquisa controlPesquisa;

    /**
     * Launch the application.
     */
    public void execute() {
        controlPesquisa = new ControllerPesquisa();
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    InterfacePesquisa frame = new InterfacePesquisa();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createComentario() {
        controlPesquisa.createComentario();
    }

    public void addFotografia() {
        controlPesquisa.addFotografia();
    }

    /**
     * Create the frame.
     */
    public void initWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }
}
