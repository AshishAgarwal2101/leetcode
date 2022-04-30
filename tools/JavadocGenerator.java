package tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class JavadocGenerator {
  public static void main(String[] args) {
    String description = readFile("problem_description.txt");

    String javadocs = "/**\n *" + description.replace("\n", "\n *") + "\n*/";
    System.out.println(javadocs);
  }

  public static String readFile(String filename) {
    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      String res = "";
      while (myReader.hasNextLine()) {
        String newDataLine = myReader.nextLine();
        res += "\n" + newDataLine;
      }
      myReader.close();
      return res;
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      return "";
    }
  }
}