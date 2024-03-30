package com.viagemvirtual.apps.descriptografar;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

public class EnigmaOutGUI extends JFrame {

	private JTextArea mensagemTextArea;
	private JButton lerNovaMensagemButton;
	private JButton limparMensagemButton;
	private JButton sairButton;

	public EnigmaOutGUI() {
		setTitle("Decrypt Message");
		setSize(400, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		initComponents();
		addComponentsToFrame();

		setVisible(true);
	}

	private void initComponents() {
		mensagemTextArea = new JTextArea();
		mensagemTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(mensagemTextArea);

		lerNovaMensagemButton = new JButton("Read New Message");
		limparMensagemButton = new JButton("Clear Message");
		sairButton = new JButton("Exit");

		lerNovaMensagemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				descriptografarMensagem();
			}
		});

		limparMensagemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparMensagem();
			}
		});

		sairButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
				dispose();
			}
		});

		// Implementação para inibir a função de print screen
		mensagemTextArea.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN || e.getKeyCode() == KeyEvent.VK_SNAPSHOT) {
				if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}

	private void addComponentsToFrame() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
		buttonPanel.add(lerNovaMensagemButton);
		buttonPanel.add(limparMensagemButton);
		buttonPanel.add(sairButton);

		add(new JScrollPane(mensagemTextArea), BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void descriptografarMensagem() {
		try {
			// Ler a mensagem criptografada do arquivo
			FileInputStream fileInputStream = new FileInputStream("c:\\temp\\msgcrypt.txt");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			byte[] mensagemCriptografada = (byte[]) objectInputStream.readObject();
			objectInputStream.close();

			// Solicitar a chave de descriptografia
			String chave = JOptionPane.showInputDialog("Enter the decryption key:");
			if (chave == null || chave.length() != 32) {
				JOptionPane.showMessageDialog(this, "The decryption key must be 32 bytes long.");
				return;
			}

			// Descriptografar a mensagem
			String mensagemDescriptografada = descriptografar(mensagemCriptografada, chave);

			// Exibir a mensagem descriptografada
			mensagemTextArea.setText(mensagemDescriptografada);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "ERROR:\n" + e.getMessage());
		}
	}

	private String descriptografar(byte[] mensagemCriptografada, String chave) throws Exception {
		// Criar um objeto de chave AES
		SecretKeySpec chaveAES = new SecretKeySpec(chave.getBytes(), "AES");

		// Criar um objeto Cipher para AES
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, chaveAES);

		// Descriptografar a mensagem
		byte[] mensagemDescriptografada = cipher.doFinal(mensagemCriptografada);

		return new String(mensagemDescriptografada);
	}

	private void limparMensagem() {
		mensagemTextArea.setText("");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EnigmaOutGUI();
			}
		});
	}
}
