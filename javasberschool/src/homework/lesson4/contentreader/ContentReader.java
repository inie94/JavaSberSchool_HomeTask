package homework.lesson4.contentreader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class ContentReader {

    private static void readContent(String url) throws IOException {
        Object content = new URL(url).getContent();

        if (content instanceof InputStream) {
            InputStream contentStream = (InputStream) content;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (BufferedInputStream stream = new BufferedInputStream(contentStream)) {
                while (stream.available() > 0) {
                    byteArrayOutputStream.write(stream.read());
                }
            }
            System.out.println(new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8));
        } else {
            throw new IOException("Unsupported content kind");
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Write URL: ");
            while (true) {
                if (reader.ready()) {
                    String request = reader.readLine();
                    try {
                        if (request.toLowerCase(Locale.ROOT).equals("exit")) {
                            System.out.println("Working is stop. Thank you for collaborations!");
                            break;
                        }
                        readContent(request);
                    } catch (MalformedURLException e) {
                        System.out.println("Malformed URL specified!");
                    } catch (UnknownHostException e) {
                        System.out.println("Unknown host!");
                    } catch (IOException e) {
                        System.out.println("Unable to get site content. " + e.getMessage());
                        throw e;
                    }

                    System.out.print("Try again write URL: ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
