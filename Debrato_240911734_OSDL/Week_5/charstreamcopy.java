// 2. Design and implement a Java application that reads textual data from an
// existing text file using FileReader and writes the same content into another
// text file using FileWriter.

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class charstreamcopy {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("input.txt");
            FileWriter fw = new FileWriter("output.txt");

            int ch;
            while ((ch = fr.read()) != -1) {
                fw.write(ch);
            }

            fr.close();
            fw.close();

            System.out.println("text file copied using character streams.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
