import java.util.List;

class Day07 {
  public static long p1(List<String> input) {
    long ans = 0;

    String line = input.get(0);
    int source = line.indexOf("S");

    boolean[] currBeams = new boolean[line.length()];

    currBeams[source] = true;

    for (int i = 1; i < input.size(); i++) {
      line = input.get(i);
      boolean[] newBeams = new boolean[line.length()];

      for (int j = 1; j < line.length() - 1; j++) {
        if (line.charAt(j) == '^' && currBeams[j]) {
          // System.out.println(i + " " + j);
          newBeams[j - 1] = true;
          newBeams[j + 1] = true;
          ans++;
        } else {
          newBeams[j] = newBeams[j] || currBeams[j];
        }
      }
      // for (boolean b : newBeams) {
      // System.out.print(b);
      // }
      // System.out.println();

      currBeams = newBeams;
    }
    return ans;
  }

  public static long p2(List<String> input) {
    long ans = 0;

    String line = input.get(0);
    int source = line.indexOf("S");

    long[] currBeams = new long[line.length()];

    currBeams[source] = 1;

    for (int i = 1; i < input.size(); i++) {
      line = input.get(i);
      long[] newBeams = new long[line.length()];

      for (int j = 0; j < line.length(); j++) {
        if (line.charAt(j) == '^' && currBeams[j] > 0) {
          // System.out.println(i + " " + j);
          newBeams[j - 1] += currBeams[j];
          newBeams[j + 1] += currBeams[j];
        } else {
          newBeams[j] = newBeams[j] + currBeams[j];
        }
      }
      // for (boolean b : newBeams) {
      // System.out.print(b);
      // }
      // System.out.println();

      currBeams = newBeams;
    }

    for (long i : currBeams) {
      ans += i;
    }

    return ans;
  }
}
