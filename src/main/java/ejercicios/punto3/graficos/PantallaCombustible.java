package ejercicios.punto3.graficos;

import ejercicios.punto3.modelo.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class PantallaCombustible extends JFrame {

    private final JTextField litros;
    private JTextField correoElectronicoText;

    public PantallaCombustible(EstacionDeServicio estacionDeServicio) {
        setIconImage(Toolkit.getDefaultToolkit().getImage("images.png"));
        setBackground(new Color(205, 92, 92));
        setTitle("Cargar narfta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(0, 0, 587, 299);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("LITROS A CARGAR:");
        lblNewLabel.setBounds(12, 32, 165, 16);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("TIPO DE COMBUSTIBLE:");
        lblNewLabel_1.setBounds(12, 100, 165, 16);
        contentPane.add(lblNewLabel_1);

        litros = new JTextField();
        litros.setBounds(267, 29, 265, 22);
        contentPane.add(litros);
        litros.setColumns(10);

        JComboBox<Combustible> tiposCombustibles = new JComboBox<>();

        Combustible combustibleComun = new Comun();
        tiposCombustibles.addItem(combustibleComun);
        Combustible combustibleSuper = new Super();
        tiposCombustibles.addItem(combustibleSuper);


        tiposCombustibles.setBounds(267, 97, 265, 22);
        contentPane.add(tiposCombustibles);

        JButton btnNewButton = new JButton("CONSULTAR MONTO");
        btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(204, 102, 102));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double total =
                            estacionDeServicio.calcularMontoDeLaVenta(LocalDateTime.now(), litros.getText(),
                                    (Combustible) tiposCombustibles.getSelectedItem());
                    JOptionPane.showMessageDialog(null, "Monto a pagar: $" + total);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
        btnNewButton.setBounds(45, 218, 186, 25);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("CONFIRMAR PAGO");
        btnNewButton_1.setBackground(new Color(204, 102, 102));
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int eleccion = JOptionPane.showConfirmDialog(null, "¿Desea confirmar el pago?");
                    if (eleccion == JOptionPane.YES_OPTION) {
                        estacionDeServicio.agregarVenta(LocalDateTime.now(),
                                litros.getText(), (Combustible) tiposCombustibles.getSelectedItem(), correoElectronicoText.getText());
                        JOptionPane.showMessageDialog(null, "Se regitro la venta correctamente.");

                    }

                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
        btnNewButton_1.setBounds(333, 218, 177, 25);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel_2 = new JLabel("CORREO ELECTRONICO DEL CLIENTE:");
        lblNewLabel_2.setBounds(12, 153, 231, 16);
        contentPane.add(lblNewLabel_2);


        correoElectronicoText = new JTextField();
        correoElectronicoText.setBounds(267, 150, 265, 22);
        contentPane.add(correoElectronicoText);
        correoElectronicoText.setColumns(10);
    }
}