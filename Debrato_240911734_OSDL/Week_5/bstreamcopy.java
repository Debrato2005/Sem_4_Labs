// 1. Design and implement a Java application that copies the contents of one file
// to another using byte streams. The program must use FileInputStream to
// read data from a source file and FileOutputStream to write the same data to
// a destination file.
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class bstreamcopy {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream("source.dat");
            fos = new FileOutputStream("destination.dat");

            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }

            System.out.println("file copied using bytestream.");

        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                System.out.println("error closing file.");
            }
        }
    }
}

