package cn.siques.mangocore.utils;

public class PasswordUtils {
    public static boolean matches(String salt, String rawPass, String encPass) {
        return new PasswordEncoder(salt).matches(encPass, rawPass);
    }
}
