package com.viagemvirtual.apps;

import javax.swing.*;

import com.viagemvirtual.apps.criptografar.EnigmaInGUI;
import com.viagemvirtual.apps.descriptografar.EnigmaOutGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Enigma Cryptography");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        
        
        JMenuItem criptografarItem = new JMenuItem("Encrypt message");
        criptografarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EnigmaInGUI.main(new String[0]);
            }
        });
        
        JMenuItem descriptografarItem = new JMenuItem("Decrypt message");
        descriptografarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EnigmaOutGUI.main(new String[0]);
            }
        });
        
        JMenuItem ajudaItem = new JMenuItem("Help");
        ajudaItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Select 'Encrypt Message' to encrypt a message.\nSelect 'Decrypt Message' to decrypt a message.\nSelect 'About' for information about the program.");
            }
        });
        
        JMenuItem sobreItem = new JMenuItem("About");
        sobreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Enigma - Cryptography Program\nVersion 1.0\nDeveloped by\n Wesley Mendonca\nwmdeoliveira@cursodecobol.com.br");
            }
        });
        
        //menu.add(criptografarItem);
        //menu.add(descriptografarItem);
        //menu.addSeparator();
        //menu.add(ajudaItem);
        //menu.add(sobreItem);
        //menuBar.add(menu);
        

        menuBar.add(criptografarItem);
        menuBar.add(descriptografarItem);
        menuBar.add(ajudaItem);
        menuBar.add(sobreItem);

        frame.setJMenuBar(menuBar);
        frame.setPreferredSize(new Dimension(1024, 768));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
