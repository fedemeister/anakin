package es.uca.gii.csi18.anakin.gui;

import javax.swing.*;

import es.uca.gii.csi18.anakin.data.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrActividad extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JTextField txtPlazas;
    private JTextField txtMonitor;
    private Actividad _actividad = null;
    private JComboBox<Orientacion> cmbOrientacion;


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

        JLabel lblOrientacion = new JLabel("Orientacion");
        lblOrientacion.setBounds(10, 11, 82, 14);
        getContentPane().add(lblOrientacion);

        cmbOrientacion = new JComboBox<Orientacion>();
        try {
            cmbOrientacion.setModel(new OrientacionListModel(Orientacion.Select()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmbOrientacion.setBounds(130, 8, 86, 20);
        getContentPane().add(cmbOrientacion);


        if (_actividad != null) {
            txtNombre.setText(_actividad.getNombre());
            txtPlazas.setText(Integer.toString(_actividad.getPlazas()));
            txtMonitor.setText(_actividad.getMonitor());
            cmbOrientacion.setSelectedIndex(_actividad.getOrientacion().getId() - 1);
        }

        JButton butGuardar = new JButton("Guardar");
        butGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    int iPlazas = Integer.parseInt(txtPlazas.getText());

                    if (cmbOrientacion.getModel().getSelectedItem() == null)
                        throw new Exception ("Selecciona una orientación");
                    if (_actividad == null) {
                        _actividad = Actividad.Create(txtNombre.getText(),
                                iPlazas,
                                txtMonitor.getText(),
                                (Orientacion)cmbOrientacion.getModel().getSelectedItem()
                        );
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
