import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Day10 {
  public static long p1(List<String> input) {
    long ans = 0;

    for (String line : input) {
      Pattern lightPattern = Pattern.compile("\\[(.*?)\\]");
      Matcher matcher = lightPattern.matcher(line);

      int goal = 0;
      String light = "";
      matcher.find();

      light = matcher.group(1);

      for (int i = 0; i < light.length(); i++) {
        if (light.charAt(i) == '#') {
          // light on at switch i
          goal |= 1 << i;
        }
      }

      Pattern buttonPattern = Pattern.compile("\\((.*?)\\)");
      List<String> buttonsList = new ArrayList<>();

      matcher = buttonPattern.matcher(line);
      while (matcher.find()) {
        buttonsList.add(matcher.group(1));
      }

      int numButtons = buttonsList.size();

      int[] buttons = new int[numButtons];
      for (int i = 0; i < numButtons; i++) {
        String[] button = buttonsList.get(i).split(",");
        int btnMask = 0;

        for (String b : button) {
          btnMask |= 1 << Integer.parseInt(b);
        }
        buttons[i] = btnMask;
      }

      ans += solve1(goal, buttons);
    }

    return ans;
  }

  public static long p2(List<String> input) {

    long ans = 0;

    for (String line : input) {
      Pattern lightPattern = Pattern.compile("\\{(.*?)\\}");
      Matcher matcher = lightPattern.matcher(line);

      String[] joltage;
      matcher.find();

      joltage = matcher.group(1).split(",");
      Integer[] goal = new Integer[joltage.length];

      for (int i = 0; i < joltage.length; i++) {
        goal[i] = Integer.parseInt(joltage[i]);
      }

      Pattern buttonPattern = Pattern.compile("\\((.*?)\\)");
      List<String> buttonsList = new ArrayList<>();

      matcher = buttonPattern.matcher(line);
      while (matcher.find()) {
        buttonsList.add(matcher.group(1));
      }

      int numButtons = buttonsList.size();

      // Build matrix with one row per light (goal entry) and one column per button.
      int[][] buttons = new int[joltage.length][numButtons];
      for (int col = 0; col < numButtons; col++) {
        String[] button = buttonsList.get(col).split(",");

        for (String b : button) {
          int idx = Integer.parseInt(b);
          if (idx < 0 || idx >= joltage.length) {
            throw new IllegalArgumentException(
                "Button index out of range: " + idx + " for goal length " + joltage.length);
          }
          buttons[idx][col] = 1;
        }
      }
      // ans increment TODO
    }

    return ans;

  }

  public static int solve1(int goal, int[] buttons) {
    Queue<Integer> q = new LinkedList<>();
    Map<Integer, Integer> m = new HashMap<>();

    // initial all 0s
    q.add(0);
    m.put(0, 0);

    while (!q.isEmpty()) {
      int curr = q.poll();
      int d = m.get(curr);
      for (int button : buttons) {
        int result = curr ^ button;

        if (!m.containsKey(result)) {
          // increase dist by 1
          m.put(result, d + 1);
          q.add(result);
        }

        if (result == goal) {
          return d + 1;
        }
      }
    }
    return -1;

  }

}
