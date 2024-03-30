package enigma;

//Exemplo de Symetric Encryption - criptografar / descriptografar usando chave secreta

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class EnigmaOut {

    public static void main(String[] args) {
        try {
            // Ler a mensagem criptografada do arquivo
            FileInputStream fileInputStream = new FileInputStream("c:\\temp\\mensagemcriptografada.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            byte[] mensagemCriptografada = (byte[]) objectInputStream.readObject();
            objectInputStream.close();

            // Solicitar a chave de descriptografia
            String chave = JOptionPane.showInputDialog("Digite a chave de descriptografia:");

            // Descriptografar a mensagem
            String mensagemDescriptografada = descriptografar(mensagemCriptografada, chave);

            // Gravar a mensagem descriptografada em um arquivo
            FileOutputStream fileOutputStream = new FileOutputStream("c:\\temp\\mensagemdescriptografada.txt");
            fileOutputStream.write(mensagemDescriptografada.getBytes());
            fileOutputStream.close();

            JOptionPane.showMessageDialog(null, "Mensagem descriptografada e salva com sucesso no arquivo 'mensagemdescriptografada.txt'.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage());
        }
    }

    public static String descriptografar(byte[] mensagemCriptografada, String chave) throws Exception {
        // Criar um objeto de chave AES
        SecretKeySpec chaveAES = new SecretKeySpec(chave.getBytes(), "AES");

        // Criar um objeto Cipher para AES
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chaveAES);

        // Descriptografar a mensagem
        byte[] mensagemDescriptografada = cipher.doFinal(mensagemCriptografada);

        return new String(mensagemDescriptografada);
    }
}
