package ejercicios.punto3.graficos;

import ejercicios.punto3.modelo.EstacionDeServicio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipal extends JFrame {

    private JPanel contentPane;

    public PantallaPrincipal(EstacionDeServicio estacionDeServicio) {
        setForeground(new Color(165, 42, 42));
        setIconImage(Toolkit.getDefaultToolkit().getImage("images.png"));
        setTitle("Pantalla principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0,0,450, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("CARGAR NAFTA");
        btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(204, 102, 102));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                PantallaCombustible pantallaCombustible = new PantallaCombustible(estacionDeServicio);
                pantallaCombustible.setVisible(true);
            }
        });
        btnNewButton.setBounds(101, 65, 228, 25);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("VER REGISTRO DE VENTAS");
        btnNewButton_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setBackground(new Color(204, 102, 102));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PantallaRegistroVentas pantallaRegistroVentas = new PantallaRegistroVentas(estacionDeServicio);
                pantallaRegistroVentas.setVisible(true);
            }
        });
        btnNewButton_1.setBounds(101, 164, 228, 25);
        contentPane.add(btnNewButton_1);
    }
}
