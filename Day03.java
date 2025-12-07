import java.util.List;

class Day03 {
  public static int p1(List<String> input) {
    int ans = 0;

    for (String bank : input) {
      int leftVal = -1;
      int leftIndex = -1;
      int rightVal = -1;
      for (int i = 0; i < bank.length() - 1; i++) {
        int val = bank.charAt(i) - '0';
        if (val > leftVal) {
          leftVal = val;
          leftIndex = i;
        }
      }

      for (int i = leftIndex + 1; i < bank.length(); i++) {
        int val = bank.charAt(i) - '0';
        if (val > rightVal) {
          rightVal = val;
        }
      }

      ans += leftVal * 10 + rightVal;
    }

    return ans;
  }

  public static long p2(List<String> input) {
    long ans = 0;

    for (String bank : input) {
      long val = Long.parseLong(getMaxNum(bank, 12));
      ans += val;
    }

    return ans;
  }

  private static String getMaxNum(String bank, int choose) {
    if (choose == 0) {
      return "";
    }

    if (bank.length() == choose) {
      return bank;
    }

    int max = -1;
    int maxIndex = -1;
    for (int i = 0; i < bank.length() - choose + 1; i++) {
      int val = bank.charAt(i) - '0';
      if (val > max) {
        max = val;
        maxIndex = i;
      }
    }

    if (maxIndex + 1 == bank.length()) {
      return bank.substring(maxIndex);
    }

    return bank.substring(maxIndex, maxIndex + 1) + getMaxNum(bank.substring(maxIndex + 1), choose - 1);
  }
}
