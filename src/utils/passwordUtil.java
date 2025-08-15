package utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class passwordUtil {
    /**
     * Generates a random salt to hash the password
     * @return generated salt
     */
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) throws Exception {
        int iterations = 65536;
        int keyLength = 256;
        char[] convertedPassword = password.toCharArray();

        PBEKeySpec spec = new PBEKeySpec(convertedPassword, salt, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();

        Arrays.fill(convertedPassword, '\0'); // clear password from memory
        return Base64.getEncoder().encodeToString(hash);
    }

}
