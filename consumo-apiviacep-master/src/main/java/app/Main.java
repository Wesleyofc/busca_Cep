import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Endereco;
import service.ViaCepService;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Consulta de CEP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ViaCepService vcs = new ViaCepService();
        JPanel panel = new JPanel();
        JTextField textField = new JTextField(10);
        JTextArea textArea = new JTextArea(10, 30);
        JButton button = new JButton("Consultar");
        JLabel cepLabel = new JLabel("CEP:");
        JLabel resultadoLabel = new JLabel("Resultado:");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cep = textField.getText();
                if (cep.equals("1")) {
                    textArea.append("Consulta de CEP finalizada.");
                    return;
                }
                try {
                    Endereco endereco = vcs.getEndereco(cep);
                    String resultado = "CEP: " + endereco.getCep() + "\n" +
                            "Logradouro: " + endereco.getLogradouro() + "\n" +
                            "UF: " + endereco.getUf() + "\n" +
                            "Complemento: " + endereco.getComplemento() + "\n" +
                            "Bairro: " + endereco.getBairro() + "\n" +
                            "Localidade: " + endereco.getLocalidade() + "\n" +
                            "DDD: " + endereco.getDdd() + "\n";
                    textArea.append(resultado + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    textArea.append("Erro ao consultar o CEP.\n");
                }
            }
        });

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cepLabel)
                        .addComponent(textField)
                        .addComponent(button)
                        .addComponent(resultadoLabel)
                        .addComponent(textArea)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(cepLabel)
                .addComponent(textField)
                .addComponent(button)
                .addComponent(resultadoLabel)
                .addComponent(textArea)
        );

        layout.linkSize(SwingConstants.HORIZONTAL, cepLabel, resultadoLabel);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
