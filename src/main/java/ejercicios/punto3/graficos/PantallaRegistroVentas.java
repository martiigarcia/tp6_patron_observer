package ejercicios.punto3.graficos;

import ejercicios.punto3.modelo.EstacionDeServicio;
import ejercicios.punto3.modelo.PagoDeUnaVenta;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PantallaRegistroVentas extends JFrame {

    private final JPanel contentPane;
    private final JTable table;
    private final DefaultTableModel modelo;
    private final JLabel lblNewLabel;
    private List<PagoDeUnaVenta> listaVentasPagadas;

    public PantallaRegistroVentas(EstacionDeServicio estacionDeServicio) {

        setIconImage(Toolkit.getDefaultToolkit().getImage("images.png"));
        setBackground(new Color(165, 42, 42));
        setTitle("Listado de ventas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(0,0,831, 427);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();


        lblNewLabel = new JLabel("Buscar entre la fecha:");

        JTextField TextField = new JTextField();

        JLabel lblNewLabel_1 = new JLabel("y la fecha: ");

        JTextField TextField_1 = new JTextField();

        JButton btnNewButton = new JButton("Buscar");
        btnNewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(204, 102, 102));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String fecha1 = TextField.getText();
                String fecha2 = TextField_1.getText();
                modelo.setRowCount(0);
                try {
                    listaVentasPagadas = estacionDeServicio.recuperarListaDeVentasPagadasEntreFechas(fecha1, fecha2);
                    mostrarElementosDeTabla(listaVentasPagadas);
                }catch(RuntimeException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        });

        JLabel lblNewLabel_2 = new JLabel("Formato: aaaa-mm-dd");

        JLabel label = new JLabel("Formato: aaaa-mm-dd");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(40, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(TextField, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lblNewLabel_2))
                                                .addGap(49)
                                                .addComponent(lblNewLabel_1)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(TextField_1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(59)
                                                                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(label, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
                                                .addGap(36))))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TextField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnNewButton)
                                        .addComponent(lblNewLabel_1))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(lblNewLabel_2))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        table = new JTable();
        modelo = new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Listros cargados", "Monto abonado", "Fecha de carga"
                }
        );
        listaVentasPagadas = estacionDeServicio.recuperarListaDeVentas();
        mostrarElementosDeTabla(listaVentasPagadas);

        table.getColumnModel().getColumn(0).setPreferredWidth(128);
        table.getColumnModel().getColumn(1).setPreferredWidth(128);
        table.getColumnModel().getColumn(2).setPreferredWidth(128);
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
    }

    public void mostrarElementosDeTabla(List<PagoDeUnaVenta> listaDeVetas){
        for(PagoDeUnaVenta venta : listaDeVetas){
            modelo.addRow(new Object[] {
                    venta.litrosCargados(),
                    venta.calcularMontoAbonado(),
                    venta.fecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))});
        }
        table.setModel(modelo);
    }
}