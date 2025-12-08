import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Day08 {
  static final int NUM_CONNECTIONS = 10; // set for testing/real input
  static final int NUM_LARGEST = 3;

  public static long p1(List<String> input) {
    long ans = 1;
    ArrayList<Coords3D> connections = new ArrayList<>();
    ArrayList<Coords3D> coords = new ArrayList<>();

    for (String line : input) {
      String[] numbers = line.split(",");

      Coords3D coord = new Coords3D(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]),
          Integer.parseInt(numbers[2]));

      int num = coords.size();
      for (int i = 0; i < num; i++) {
        connections.add(new Coords3D(i, num, dist(coords.get(i), coord)));
      }

      coords.add(coord);
    }

    connections.sort((a, b) -> a.z - b.z);

    // System.out.println(connections);

    // circuitSizes[i] = # of boxes in group i
    int[] circuitSizes = new int[input.size()];

    int groupNum = 0;

    for (int i = 0; i < NUM_CONNECTIONS; i++) {
      Coords3D connection = connections.get(i);
      Coords3D x = coords.get(connection.x);
      Coords3D y = coords.get(connection.y);

      int groupX = x.group;
      int groupY = y.group;
      if (groupX == -1 && groupY == -1) {
        x.group = groupNum;
        y.group = groupNum;

        circuitSizes[groupNum] = 2;
        groupNum++;
      } else if (groupX == -1 && groupY != -1) {
        x.group = groupY;
        circuitSizes[groupY]++;

        x.group = groupY;

      } else if (groupX != -1 && groupY == -1) {
        y.group = groupX;
        circuitSizes[groupX]++;

        y.group = groupX;

      } else if (groupX != groupY) { // two groups combine
        int numGroupX = circuitSizes[groupX];
        int numGroupY = circuitSizes[groupY];

        for (int j = 0; j < coords.size(); j++) {
          Coords3D coord = coords.get(j);
          if (coord.group == groupY)
            coord.group = groupX;
        }

        circuitSizes[groupX] = numGroupX + numGroupY;
        circuitSizes[groupY] = 0;
      }
    }

    Arrays.sort(circuitSizes);

    // System.out.println(Arrays.toString(circuitSizes));

    for (int i = circuitSizes.length - 1; i > circuitSizes.length - 1 - NUM_LARGEST; i--) {
      ans *= circuitSizes[i];
    }

    return ans;
  }

  public static long p2(List<String> input) {
    ArrayList<Coords3D> connections = new ArrayList<>();
    ArrayList<Coords3D> coords = new ArrayList<>();

    for (String line : input) {
      String[] numbers = line.split(",");

      Coords3D coord = new Coords3D(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]),
          Integer.parseInt(numbers[2]));

      int num = coords.size();
      for (int i = 0; i < num; i++) {
        connections.add(new Coords3D(i, num, dist(coords.get(i), coord)));
      }

      coords.add(coord);
    }

    connections.sort((a, b) -> a.z - b.z);

    // System.out.println(connections);

    // circuitSizes[i] = # of boxes in group i
    int[] circuitSizes = new int[input.size()];

    int groupNum = 0;

    for (int i = 0; i < connections.size(); i++) {
      Coords3D connection = connections.get(i);
      Coords3D x = coords.get(connection.x);
      Coords3D y = coords.get(connection.y);

      int groupX = x.group;
      int groupY = y.group;
      if (groupX == -1 && groupY == -1) {
        x.group = groupNum;
        y.group = groupNum;

        circuitSizes[groupNum] = 2;
        groupNum++;
      } else if (groupX == -1 && groupY != -1) {
        x.group = groupY;
        circuitSizes[groupY]++;

        x.group = groupY;

      } else if (groupX != -1 && groupY == -1) {
        y.group = groupX;
        circuitSizes[groupX]++;

        y.group = groupX;

      } else if (groupX != groupY) { // two groups combine
        int numGroupX = circuitSizes[groupX];
        int numGroupY = circuitSizes[groupY];

        for (int j = 0; j < coords.size(); j++) {
          Coords3D coord = coords.get(j);
          if (coord.group == groupY)
            coord.group = groupX;
        }

        circuitSizes[groupX] = numGroupX + numGroupY;
        circuitSizes[groupY] = 0;
      }

      if (circuitSizes[x.group] == input.size() || circuitSizes[y.group] == input.size()) {
        return (long) x.x * y.x;
      }
    }
    return -1;
  }

  public static int dist(Coords3D a, Coords3D b) {
    return (int) (Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2));
  }
}

class Coords3D {
  int x;
  int y;
  int z;
  int group = -1;

  public Coords3D(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public String toString() {
    return "x: " + x + " y: " + y + " z: " + z;
  }
}
