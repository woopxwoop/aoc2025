import java.util.List;

class Day01 {
  public static int p1(List<String> input) {
    int ans = 0;
    int pointer = 50;

    for (String line : input) {
      char direction = line.charAt(0);
      int num = Integer.parseInt(line.substring(1));

      pointer = direction == 'L' ? (pointer - num + 100) % 100 : (pointer + num) % 100;
      if (pointer == 0)
        ans++;
    }

    return ans;
  }

  public static double p2(List<String> input) {
    int ans = 0;
    int prev;
    int pointer = 50;

    for (String line : input) {
      char direction = line.charAt(0);
      int num = Integer.parseInt(line.substring(1));

      ans += num / 100;
      num %= 100;

      prev = pointer;

      if (num == 0)
        continue;

      pointer = direction == 'L' ? (pointer - num) : (pointer + num);

      if (direction == 'L' && pointer <= 0 && prev != 0) {
        ans++;
        System.out.println(line + " " + pointer);
      }

      else if (direction == 'R' && pointer >= 100 && prev != 0) {
        ans++;
        System.out.println(line + " " + pointer);
      }

      pointer = (pointer + 100) % 100;
    }

    return ans;
  }
}
