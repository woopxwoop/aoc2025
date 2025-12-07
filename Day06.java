import java.util.List;

class Day06 {
  public static long p1(List<String> input) {
    long ans = 0;

    int length = input.get(0).trim().split("\\s+").length;

    int[][] nums = new int[input.size() - 1][length];

    for (int i = 0; i < input.size() - 1; i++) {
      String[] lineNums = input.get(i).trim().split("\\s+");
      for (int j = 0; j < lineNums.length; j++) {
        nums[i][j] = Integer.parseInt(lineNums[j]);
      }
    }

    String[] ops = input.get(input.size() - 1).trim().split("\\s+");

    for (int j = 0; j < length; j++) {
      long sum = nums[0][j];
      for (int i = 1; i < input.size() - 1; i++) {
        if (ops[j].equals("+")) {
          sum += nums[i][j];
        } else if (ops[j].equals("*")) {
          sum *= nums[i][j];
        }
      }
      ans += sum;
    }

    return ans;
  }

  public static long p2(List<String> input) {
    long ans = 0;

    int length = input.get(0).length();

    char[][] nums = new char[input.size()][length];

    for (int i = 0; i < input.size(); i++) {
      String line = input.get(i);
      for (int j = 0; j < length; j++) {
        nums[i][j] = line.charAt(j);
      }
    }

    int opIdx = 0;
    char op = nums[input.size() - 1][0];

    while (opIdx < length) {
      char newOp = nums[input.size() - 1][opIdx];
      if (newOp == '+' || newOp == '*') {
        op = newOp;
      }

      long sum = op == '+' ? 0 : 1;
      while (opIdx < length) {
        for (int j = opIdx; j < length; j++) {
          long columnNum = 0;
          for (int i = 0; i < input.size() - 1; i++) {
            if (!Character.isDigit(nums[i][j])) {
              continue;
            }
            if (columnNum == 0) {
              columnNum = nums[i][j] - '0';
              continue;
            }
            char curr = nums[i][j];
            if (Character.isDigit(curr)) {
              columnNum = columnNum * 10 + (curr - '0');
            } else {
              break;
            }
          }
          if (columnNum == 0) {
            opIdx++;
            break;
          }
          if (op == '+') {
            sum += columnNum;
          } else if (op == '*') {
            sum *= columnNum;
          }
        }

        opIdx++;
        if (opIdx >= length) {
          break;
        }
        char opChar = nums[input.size() - 1][opIdx];

        while (opIdx < length && opChar != '*' && opChar != '+') {
          opIdx++;
          if (opIdx == length)
            break;
          opChar = nums[input.size() - 1][opIdx];
        }
        break;
      }
      System.out.println(sum);
      ans += sum;
    }

    return ans;
  }
}
