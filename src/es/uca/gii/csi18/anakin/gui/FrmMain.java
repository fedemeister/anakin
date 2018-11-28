package es.uca.gii.csi18.anakin.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmMain window = new FrmMain();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FrmMain() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Residencia Anakin");
        frame.setBounds(100, 100, 800, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mitNuevo = new JMenu("Nuevo");
        menuBar.add(mitNuevo);
        JMenuItem mitNuevoActividad = new JMenuItem("Actividad");
        mitNuevoActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                IfrActividad ifrActividad = new IfrActividad(null);
                ifrActividad.setBounds(10, 27, 750, 500);
                frame.getContentPane().add(ifrActividad);
                ifrActividad.setVisible(true);
            }
        });
        mitNuevo.add(mitNuevoActividad);

        JMenu mitBuscar = new JMenu("Buscar");
        menuBar.add(mitBuscar);

        JMenuItem mitBuscarActividad = new JMenuItem("Actividad");
        mitBuscarActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                IfrActividades ifrActividades = new IfrActividades(frame);
                ifrActividades.setBounds(12, 28, 750, 500);
                // El segundo parámetro es para que siempre aparezca delante
                frame.getContentPane().add(ifrActividades, 0);
                ifrActividades.setVisible(true);
            }
        });
        mitBuscar.add(mitBuscarActividad);
        frame.getContentPane().setLayout(null);
    }
}
