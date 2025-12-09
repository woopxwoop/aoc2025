import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.List;

class Day09 {
  public static long p1(List<String> input) {
    Coords2D[] points = new Coords2D[input.size()];

    for (int i = 0; i < input.size(); i++) {
      String[] str = input.get(i).split(",");
      points[i] = new Coords2D(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
    }

    long max = -1;
    for (int i = 0; i < points.length - 1; i++) {
      for (int j = i + 1; j < points.length; j++) {
        long area = area(points[i], points[j]);
        max = Math.max(max, area);
      }
    }
    return max;
  }

  public static long p2(List<String> input) {
    Coords2D[] points = new Coords2D[input.size()];
    int[] x = new int[input.size()];
    int[] y = new int[input.size()];

    for (int i = 0; i < input.size(); i++) {
      String[] str = input.get(i).split(",");
      int xc = Integer.parseInt(str[0]);
      int yc = Integer.parseInt(str[1]);
      points[i] = new Coords2D(xc, yc);
      x[i] = xc;
      y[i] = yc;
    }

    Polygon polygon = new Polygon(x, y, input.size());
    Area polygonArea = new Area(polygon);

    long max = -1;
    for (int i = 0; i < points.length - 1; i++) {
      for (int j = i + 1; j < points.length; j++) {
        if (polygonArea.contains(getRect(points[i], points[j]))) {

          long area = area(points[i], points[j]);
          max = Math.max(max, area);
        }
      }
    }
    return max;
  }

  private static long area(Coords2D a, Coords2D b) {
    return (long) (Math.abs(a.x - b.x) + 1) * (Math.abs(a.y - b.y) + 1);
  }

  private static Rectangle getRect(Coords2D a, Coords2D b) {
    int left = Math.min(a.x, b.x);
    int top = Math.min(a.y, b.y);
    int height = (Math.abs(a.y - b.y));
    int length = (Math.abs(a.x - b.x));

    return new Rectangle(left, top, length, height);
  }

}

class Coords2D {
  int x;
  int y;

  public Coords2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "x: " + x + " y: " + y;
  }
}
