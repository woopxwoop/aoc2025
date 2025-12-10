import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class Main {
  // This function gets called when the program starts
  // The 'throws' is to simplify error handling if a file read fails.
  // If you get a big error and stack trace, you probably misspelled the filename
  public static void main(String[] args) throws java.io.IOException {
    // Load the data file, then call problem 1 & 2 for this day
    List<String> d1 = readInput("10.txt");
    System.out.println("01.1: " + Day10.p1(d1));
    System.out.println("01.2: " + Day10.p2(d1));
  }

  private static List<String> readInput(String fileName) throws java.io.IOException {
    // Try common locations so running from repo root works
    String[] candidates = new String[] { fileName, Paths.get("src", fileName).toString(),
        Paths.get("bin", fileName).toString() };
    for (String path : candidates) {
      if (Files.exists(Paths.get(path))) {
        return Files.readAllLines(Paths.get(path));
      }
    }
    throw new java.io.FileNotFoundException("Could not find input file: " + fileName);
  }
}
