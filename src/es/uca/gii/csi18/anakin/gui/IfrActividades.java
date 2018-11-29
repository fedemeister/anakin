package es.uca.gii.csi18.anakin.gui;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import es.uca.gii.csi18.anakin.data.Actividad;
import es.uca.gii.csi18.anakin.data.Orientacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IfrActividades extends JInternalFrame {
    private JTextField txtNombre;
    private JTextField txtMonitor;
    private JTextField txtPlazas;
    private JTable tabResult;
    private Container pnlParent;

    private JComboBox<Orientacion> cmbOrientacion;

    /**
     * Create the frame.
     */
    public IfrActividades(Container frame) {
        pnlParent = frame;
        setResizable(true);
        setClosable(true);
        setTitle("Actividades");
        setBounds(100, 100, 450, 300);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblNombre = new JLabel("Nombre");
        panel.add(lblNombre);

        txtNombre = new JTextField();
        panel.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblMonitor = new JLabel("Monitor");
        panel.add(lblMonitor);

        txtMonitor = new JTextField();
        panel.add(txtMonitor);
        txtMonitor.setColumns(10);

        JLabel lblPlazas = new JLabel("Plazas");
        panel.add(lblPlazas);

        txtPlazas = new JTextField();
        panel.add(txtPlazas);
        txtPlazas.setColumns(10);

        JLabel lblOrientacion = new JLabel("Orientacion");
        cmbOrientacion.setEditable(true);
        panel.add(cmbOrientacion);
        try {
            cmbOrientacion.setModel(new OrientacionListModel(Orientacion.Select()));
        }catch (Exception e) {
            e.printStackTrace();
        }

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String sNombre = txtNombre.getText().isEmpty() ?
                        null : txtNombre.getText();
                Integer iPlazas = txtPlazas.getText().isEmpty() ?
                        null : Integer.parseInt(txtPlazas.getText());
                String sMonitor = txtMonitor.getText().isEmpty() ?
                        null : txtMonitor.getText();
                String sOrientacion = cmbOrientacion.getEditor().getItem().toString();
                try {
                    tabResult.setModel(new ActividadesTableModel(Actividad.Select(sNombre, iPlazas, sMonitor, sOrientacion)));
                } catch (Exception ee) {
                }
            }
        });
        getContentPane().add(btnBuscar, BorderLayout.SOUTH);

        tabResult = new JTable();
        tabResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Se activa con doble clic sobre una fila
                if (e.getClickCount() == 2) {
                    int iRow = ((JTable) e.getSource()).getSelectedRow();
                    Actividad actividad =
                            ((ActividadesTableModel) tabResult.getModel()).getData(iRow);
                    if (actividad != null) {
                        IfrActividad ifrActividad = new IfrActividad(actividad);
                        ifrActividad.setBounds(10, 27, 244, 192);
                        pnlParent.add(ifrActividad, 0);
                        ifrActividad.setVisible(true);
                    }
                }
            }
        });
        getContentPane().add(tabResult, BorderLayout.CENTER);
    }
}
