import java.util.ArrayList;
import java.util.List;

class Day04 {
  static boolean[][] grid;

  public static int p1(List<String> input) {
    int ans = 0;
    grid = new boolean[input.size()][input.get(0).length()];

    for (int i = 0; i < input.size(); i++) {
      String line = input.get(i);
      for (int j = 0; j < line.length(); j++) {
        if (line.charAt(j) == '@') {
          grid[i][j] = true;
        }
      }
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] && checkAdjacent(i, j)) {
          ans++;
        }
      }
    }

    return ans;
  }

  public static int p2(List<String> input) {
    int ans = 0;
    grid = new boolean[input.size()][input.get(0).length()];

    for (int i = 0; i < input.size(); i++) {
      String line = input.get(i);
      for (int j = 0; j < line.length(); j++) {
        if (line.charAt(j) == '@') {
          grid[i][j] = true;
        }
      }
    }

    while (true) {
      ArrayList<Coords> remove = new ArrayList<>();

      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          if (grid[i][j] && checkAdjacent(i, j)) {
            remove.add(new Coords(i, j));
            ans++;
          }
        }
      }

      if (remove.isEmpty()) {
        break;
      }

      for (Coords coord : remove) {
        grid[coord.x][coord.y] = false;
      }
    }

    return ans;
  }

  public static boolean checkAdjacent(int i, int j) {
    int[][] adj = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

    int numAdj = 0;
    for (int[] pair : adj) {
      int x = pair[0];
      int y = pair[1];

      if (i + x < grid.length && i + x >= 0 && j + y < grid[0].length && j + y >= 0) {
        if (grid[i + x][j + y]) {
          numAdj++;
        }
      }
    }
    return numAdj < 4;
  }

}

class Coords {
  int x;
  int y;

  public Coords(int x, int y) {
    this.x = x;
    this.y = y;
  }
}