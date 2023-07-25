package xyl.me.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

    private static final int SALT_LENGTH = 16; // 盐的字节长度

    /**
     * 对密码进行加密
     * @param password 原始密码
     * @return 包含加密后的密码和盐的字符串
     */
    public static String encryptPassword(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // 将密码和盐进行Base64编码
            String encodedPassword = Base64.getEncoder().encodeToString(hashedPassword);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);

            // 返回包含加密后的密码和盐的字符串，可以将该字符串存储到数据库中
            return encodedPassword + "$" + encodedSalt;
        } catch (NoSuchAlgorithmException e) {
            // 处理加密算法不可用的异常
            e.printStackTrace();
            // 可根据实际情况返回一个默认密码或抛出异常
            return null;
        }
    }

    /**
     * 验证密码是否匹配
     * @param password      原始密码
     * @param hashedPassword   存储的加密后的密码
     * @return true表示密码匹配，false表示密码不匹配
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        try {
            String[] parts = hashedPassword.split("\\$");
            String encodedPassword = parts[0];
            String encodedSalt = parts[1];

            byte[] salt = Base64.getDecoder().decode(encodedSalt);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedInput = md.digest(password.getBytes());

            String encodedInput = Base64.getEncoder().encodeToString(hashedInput);

            return encodedInput.equals(encodedPassword);
        } catch (NoSuchAlgorithmException e) {
            // 处理加密算法不可用的异常
            e.printStackTrace();
            return false;
        }
    }
}
