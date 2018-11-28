package es.uca.gii.csi18.anakin.gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import es.uca.gii.csi18.anakin.data.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrActividad extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JTextField txtPlazas;
    private JTextField txtMonitor;
    private Actividad _actividad = null;

    /**
     * Create the frame.
     */
    public IfrActividad(Actividad actividad) {
        _actividad = actividad;

        setResizable(true);
        setClosable(true);
        setTitle("Actividad");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(12, 13, 56, 16);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(80, 10, 116, 22);
        getContentPane().add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblPlazas = new JLabel("Plazas");
        lblPlazas.setBounds(12, 48, 56, 16);
        getContentPane().add(lblPlazas);

        txtPlazas = new JTextField();
        txtPlazas.setBounds(80, 45, 116, 22);
        getContentPane().add(txtPlazas);
        txtPlazas.setColumns(10);

        JLabel lblMonitor = new JLabel("Monitor");
        lblMonitor.setBounds(12, 83, 56, 16);
        getContentPane().add(lblMonitor);

        txtMonitor = new JTextField();
        txtMonitor.setBounds(80, 80, 116, 22);
        getContentPane().add(txtMonitor);
        txtMonitor.setColumns(10);

        if (_actividad != null) {
            txtNombre.setText(_actividad.getNombre());
            txtPlazas.setText(Integer.toString(_actividad.getPlazas()));
            txtMonitor.setText(_actividad.getMonitor());
        }

        JButton butGuardar = new JButton("Guardar");
        butGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    int iPlazas = Integer.parseInt(txtPlazas.getText());
                    if (_actividad == null) {
                        _actividad = Actividad.Create(txtNombre.getText(), iPlazas, txtMonitor.getText());
                    } else {
                        _actividad.setNombre(txtNombre.getText());
                        _actividad.setPlazas(iPlazas);
                        _actividad.setMonitor(txtMonitor.getText());
                        _actividad.Update();
                    }

                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, ee.getMessage());
                }
            }
        });
        butGuardar.setBounds(80, 115, 97, 25);
        getContentPane().add(butGuardar);
    }
}
