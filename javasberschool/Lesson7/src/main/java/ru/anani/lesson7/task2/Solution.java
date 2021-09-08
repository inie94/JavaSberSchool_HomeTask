package ru.anani.lesson7.task2;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {
    public static void main(String[] args) throws Exception {
        File dir = new File("D:/anani/IdeaProjects/JavaSberSchool_HomeTask/javasberschool/Lesson7/testClassForEncrypt");
        String classname = "Test";
        String algorithm = "AES";
        String key = "asfrbhdft;oxnkht";

//        encrypt(dir, classname, algorithm, key);

        EncryptedClassLoader myClassLoader = new EncryptedClassLoader(key, dir);
        Class<?> dynamicClass = myClassLoader.findClass(classname, algorithm);

        Method m = dynamicClass.getMethod("main", String[].class);
        m.invoke(null, new Object[] {null});
    }

    public static void encrypt(File dir, String classname, String algorithm, String key) throws Exception{
        Path file = Paths.get(dir + "/" + classname + ".class");
        byte[] content = Files.readAllBytes(file);
        Cipher encryption = Cipher.getInstance(algorithm);
        encryption.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), 0, key.length(), algorithm));
        byte[] encryptedContent = encryption.doFinal(content);
        writeToFile(dir, classname, encryptedContent);
    }
    public static void writeToFile(File dir, String filename, byte[] content) throws Exception{
        FileOutputStream out = new FileOutputStream(dir + "/" + filename + ".class");
        out.write(content);
        out.close();
    }
}
