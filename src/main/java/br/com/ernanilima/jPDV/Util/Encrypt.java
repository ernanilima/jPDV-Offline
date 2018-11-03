package br.com.ernanilima.jPDV.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * CLasse de criptografar senhas
 * @author Ernani Lima
 */
public class Encrypt {

    /**
     * Metodo que criptografa String em sha256
     * @param password
     * @return String sha256
     */
    public static String sha256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02X", 0xff & b));
            }
            password = sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.out.println("ERRO AO CRIPTOGRAFAR SHA-256");
            e.printStackTrace();
        }
        return password;
    }
}
