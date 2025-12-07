import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Day05 {
  public static long p1(List<String> input) {
    long ans = 0;

    int idx;

    ArrayList<Bounds> set = new ArrayList<>();
    // ranges
    for (idx = 0; idx < input.size(); idx++) {
      String line = input.get(idx);
      if (line.length() == 0) {
        break;
      }

      String[] bounds = line.split("-");
      long lower = Long.parseLong(bounds[0]);
      long upper = Long.parseLong(bounds[1]);

      set.add(new Bounds(lower, upper));
    }

    idx++;

    while (idx < input.size()) {
      String line = input.get(idx);
      Long val = Long.parseLong(line);

      for (Bounds bound : set) {
        if (val >= bound.lower && val <= bound.upper) {
          ans++;
          break;
        }
      }

      idx++;
    }

    return ans;
  }

  public static long p2(List<String> input) {
    long ans = 0;

    int idx;

    ArrayList<Bounds> set = new ArrayList<>();
    // ranges
    for (idx = 0; idx < input.size(); idx++) {
      String line = input.get(idx);
      if (line.length() == 0) {
        break;
      }

      String[] bounds = line.split("-");
      long lower = Long.parseLong(bounds[0]);
      long upper = Long.parseLong(bounds[1]);

      set.add(new Bounds(lower, upper));
    }

    idx++;

    set.sort(new BoundsComparator());

    for (int i = set.size() - 1; i > 0; i--) {
      Bounds curr = set.get(i);
      Bounds prev = set.get(i - 1);

      if (curr.lower <= prev.upper) {
        Bounds newBound = new Bounds(prev.lower, Math.max(curr.upper, prev.upper));
        set.remove(i);
        set.remove(i - 1);
        set.add(i - 1, newBound);
      }
    }

    for (int i = 0; i < set.size(); i++) {
      Bounds curr = set.get(i);
      ans += curr.upper - curr.lower + 1;
    }

    return ans;
  }
}

class Bounds {
  long lower;
  long upper;

  public Bounds(long x, long y) {
    this.lower = x;
    this.upper = y;
  }

  @Override
  public String toString() {
    return this.lower + "-" + this.upper;
  }
}

class BoundsComparator implements Comparator<Bounds> {
  @Override
  public int compare(Bounds o1, Bounds o2) {

    return Long.compare(o1.lower, o2.lower) != 0 ? Long.compare(o1.lower, o2.lower) : Long.compare(o1.upper, o2.upper);

  }
}