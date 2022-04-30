import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

class SolutionGenerator {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter java file name: ");
    String filename = scan.nextLine();

    String fileContents = readFile("solution_placeholder.txt");
    writeToFileSub(filename, fileContents);
    pushToGithub(filename);
    //System.out.println(fileContents);
  }

  public static String readFile(String filename) {
    boolean isJavaDoc = true;
    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      String res = "/**\n *";
      while (myReader.hasNextLine()) {
        String newDataLine = myReader.nextLine();
        if(newDataLine.contains("*****")){
          isJavaDoc = false;
          res += "\n*/\n\n";
        }
        else if(isJavaDoc) {
          res += "\n *" + newDataLine;
        }
        else {
          res += "\n" + newDataLine;
        }
      }
      myReader.close();
      return res;
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred during file read.");
      e.printStackTrace();
      return "";
    }
  }

  public static void writeToFile(String filename, String fileContents){
    try {
      FileWriter myWriter = new FileWriter(filename);
      myWriter.write(fileContents);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred during file write.");
      e.printStackTrace();
    }
  }

  public static void writeToFileSub(String filename, String fileContents){
    try {
      PrintWriter myWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream("solutions/" + filename)));
      myWriter.println(fileContents);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred during file write to sub directory.");
      e.printStackTrace();
    }
  }

  public static void pushToGithub(String filename) {
    try {
      ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "push_to_git.bat");
      Map<String, String> env = builder.environment();
      env.put("filename", filename);
      builder.redirectErrorStream(true);

      Process p = builder.start();
      BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      while (true) {
          line = r.readLine();
          if (line == null) { break; }
          System.out.println(line);
      }
    }catch(Exception e) {
      System.out.println("An error occurred during push to github.");
      e.printStackTrace();
    }
  }
}