package ru.anani.lesson7.task2;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EncryptedClassLoader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }
    public EncryptedClassLoader(String key, File dir) {
        this.key = key;
        this.dir = dir;
    }

    public Class<?> findClass(String name, String algorithm) throws Exception{
        byte[] b = loadClassData(name, algorithm);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name, String algorithm) throws Exception {
        Path file = Paths.get(dir + "/" + name + ".class");
        byte[] encryptedContent = Files.readAllBytes(file);

        Cipher decryption = Cipher.getInstance(algorithm);
        decryption.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), 0, key.length(), algorithm));
        byte[] decryptedContent = decryption.doFinal(encryptedContent);

        return decryptedContent;
    }
}

