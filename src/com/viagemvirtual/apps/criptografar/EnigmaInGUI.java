/**
 * Enigma Project 
 * EnigmaInGUI.java
 * v.1.0 - Initial Version
 * v.1.1 - Use JInternalFrame 
 */
package com.viagemvirtual.apps.criptografar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

//public class EnigmaInGUI extends JFrame {
public class EnigmaInGUI extends JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 101L;
	private JTextArea mensagemTextArea;
    private JButton okButton;
    private JButton limparButton;
    private JButton sairButton;

    public EnigmaInGUI() {
        //setTitle("Encrypt Message");
        super("Encrypt Message", true, true, true, true);
        
        setSize(800, 600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setLocationRelativeTo(null);
        setLocation(10, 10);
//        setLayout(new BorderLayout());
        
        initComponents();
        addComponentsToFrame();

//        setVisible(true);
    }

    private void initComponents() {
        mensagemTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(mensagemTextArea);

        okButton = new JButton("OK");
        limparButton = new JButton("Erase");
        sairButton = new JButton("Close");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criptografarMensagem();
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.exit(0);
            	dispose();
            	
            }
        });
    }

    private void addComponentsToFrame() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(okButton);
        panel.add(limparButton);
        panel.add(sairButton);

        add(new JScrollPane(mensagemTextArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    private void criptografarMensagem() {
        try {
            // Mensagem a ser criptografada
            String mensagemOriginal = mensagemTextArea.getText();

            // Solicitar a chave de criptografia
            String chave = JOptionPane.showInputDialog("Enter the encryption key (must be 32 bytes):");
            if (chave == null || chave.length() != 32) {
                JOptionPane.showMessageDialog(this, "The encryption key must be 32 bytes long.");
                return;
            }

            // Criptografar a mensagem
            byte[] mensagemCriptografada = criptografar(mensagemOriginal, chave);

            // Gravar a mensagem criptografada em um arquivo
            FileOutputStream fileOutputStream = new FileOutputStream("c:\\temp\\msgcrypt.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(mensagemCriptografada);
            objectOutputStream.close();

            JOptionPane.showMessageDialog(this, "Message successfully encrypted and saved to file 'msgcrypt.txt'.");
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR:\n" + e.getMessage());
        }
    }

    private byte[] criptografar(String mensagem, String chave) throws Exception {
        // Criar um objeto de chave AES
        SecretKeySpec chaveAES = new SecretKeySpec(chave.getBytes(), "AES");

        // Criar um objeto Cipher para AES
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chaveAES);

        // Criptografar a mensagem
        return cipher.doFinal(mensagem.getBytes());
    }

    private void limparCampos() {
        mensagemTextArea.setText("");
    }

/*  public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EnigmaInGUI();
            }
        });
    }
*/

}
