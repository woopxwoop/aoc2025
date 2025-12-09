import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class Main {
  // This function gets called when the program starts
  // The 'throws' is to simplify error handling if a file read fails.
  // If you get a big error and stack trace, you probably misspelled the filename
  public static void main(String[] args) throws java.io.IOException {

    // Load the data file, then call problem 1 & 2 for this day
    List<String> d1 = Files.readAllLines(Paths.get("09.txt"));
    System.out.println("01.1: " + Day09.p1(d1));
    System.out.println("01.2: " + Day09.p2(d1));
  }
}
