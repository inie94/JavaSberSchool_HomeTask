import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class test {
    private final Path rootDir = Paths.get("D:\\anani\\IdeaProjects\\JavaSberSchool_HomeTask\\javasberschool\\Lesson8\\src\\test\\resources");
    private final Path zipFile = Paths.get(rootDir + "\\arch.zip");
    private Path file /*= Paths.get("D:\\anani\\IdeaProjects\\JavaSberSchool_HomeTask\\javasberschool\\Lesson8\\src\\test\\resources\\test.txt")*/;

    @Test
    public void main() throws IOException {
        createZipArchive();
        file = createTempFile();
        writeDataToTempFile();
        archivedTempFileToZip();
    }

    @Test
    public void createZipArchive() throws IOException {
        if(!Files.exists(zipFile)) {
            Files.createFile(zipFile);
        }
    }


    public Path createTempFile() throws IOException {
        return Files.createTempFile("new Date().toString()", "-temp");
    }

    @Test
    public void writeDataToTempFile() throws IOException {
        String data = "test data 1";
        try(BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(String.valueOf(file)))) {
            writer.write(data.getBytes(StandardCharsets.UTF_8));
        }
    }

    @Test
    public void archivedTempFileToZip() throws IOException {
        try(ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(String.valueOf(zipFile)))){
            outputStream.putNextEntry(new ZipEntry("testNew.txt"));
//            System.out.println(Files.readAllBytes(file).toString());
//            outputStream.write(Files.readAllBytes(file));
            outputStream.closeEntry();
        }
    }

//    @Test
//    public void zipArchive() throws IOException {
//// read war.zip and write to append.zip
//        ZipFile war = new ZipFile("war.zip");
//        ZipOutputStream append = new ZipOutputStream(new FileOutputStream("append.zip"));
//
//        // first, copy contents from existing war
//        Enumeration<? extends ZipEntry> entries = war.entries();
//        while (entries.hasMoreElements()) {
//            ZipEntry e = entries.nextElement();
//            File file = e.clone();
//
//            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(String.valueOf(e)))) {
//
//            };
//
//            System.out.println("copy: " + e.getName());
//            append.putNextEntry(e);
//            if (!e.isDirectory()) {
//                copy(war.getInputStream(e), append);
//            }
//            append.closeEntry();
//        }
//
//        // now append some extra content
//        ZipEntry e = new ZipEntry("answer.txt");
//        System.out.println("append: " + e.getName());
//        append.putNextEntry(e);
//        append.write("42\n".getBytes());
//        append.closeEntry();
//
//        // close
//        war.close();
//        append.close();
//    }

    private void copy(InputStream inputStream, ZipOutputStream append) {
    }

}
