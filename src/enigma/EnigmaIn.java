package enigma;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class EnigmaIn {

    public static void main(String[] args) {
        try {
            // Mensagem a ser criptografada
            String mensagemOriginal = JOptionPane.showInputDialog("Digite a mensagem a ser criptografada:");

            // Solicitar o tamanho da chave em bytes
            int tamanhoChave = Integer.parseInt(JOptionPane.showInputDialog("Digite o tamanho da chave em bytes (16, 24 ou 32):"));
            if (tamanhoChave != 16 && tamanhoChave != 24 && tamanhoChave != 32) {
                JOptionPane.showMessageDialog(null, "O tamanho da chave deve ser 16, 24 ou 32 bytes.");
                return;
            }

            // Solicitar a chave de criptografia
            String chave = JOptionPane.showInputDialog("Digite a chave de criptografia (deve ter " + tamanhoChave + " bytes):");
            if (chave.length() != tamanhoChave) {
                JOptionPane.showMessageDialog(null, "A chave de criptografia deve ter " + tamanhoChave + " bytes.");
                return;
            }

            // Criptografar a mensagem
            byte[] mensagemCriptografada = criptografar(mensagemOriginal, chave);

            // Gravar a mensagem criptografada em um arquivo
            FileOutputStream fileOutputStream = new FileOutputStream("c:\\temp\\mensagemcriptografada.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(mensagemCriptografada);
            objectOutputStream.close();

            JOptionPane.showMessageDialog(null, "Mensagem criptografada e salva com sucesso no arquivo 'mensagemcriptografada.txt'.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage());
        }
    }

    public static byte[] criptografar(String mensagem, String chave) throws Exception {
        // Criar um objeto de chave AES
        SecretKeySpec chaveAES = new SecretKeySpec(chave.getBytes(), "AES");

        // Criar um objeto Cipher para AES
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chaveAES);

        // Criptografar a mensagem
        return cipher.doFinal(mensagem.getBytes());
    }
}
