import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Day02 {
  public static long p1(List<String> input) {
    long ans = 0;

    String[] ranges = input.get(0).split(",");
    for (String range : ranges) {
      String[] bounds = range.split("-");

      long lower = Long.parseLong(bounds[0]);
      long upper = Long.parseLong(bounds[1]);

      // set lower bound to be closest bigger even digit number
      if (bounds[0].length() % 2 == 1) {
        long temp = 10;
        while (temp < lower) {
          temp *= 100;
        }
        lower = temp;
      }

      // set upper bound to be closest smaller even digit number
      if (bounds[1].length() % 2 == 1) {
        long temp = 1;
        while (temp < upper) {
          temp *= 100;
        }
        upper = temp / 100 - 1;
      }

      ans += numInvalid(lower, upper);

    }
    return ans;
  }

  public static long p2(List<String> input) {
    Pattern pattern = Pattern.compile("^(.+?)\\1+$");

    long ans = 0;
    String[] ranges = input.get(0).split(",");
    for (String range : ranges) {
      String[] bounds = range.split("-");

      long lower = Long.parseLong(bounds[0]);
      long upper = Long.parseLong(bounds[1]);

      for (long i = lower; i <= upper; i++) {
        Matcher matcher = pattern.matcher(i + "");
        if (matcher.find()) {
          // System.out.println(ans);
          ans += i;
        }
      }

    }
    return ans;
  }

  private static long numInvalid(long lower, long upper) {
    long split = 100;
    long halfSplit = 10;
    long ans = 0;

    if (upper < lower)
      return 0;

    while (split < lower) {
      split *= 100;
      halfSplit *= 10;
    }

    long lowerFirstHalf = lower / halfSplit;
    long lowerSecondHalf = lower % halfSplit;

    while (split < upper) {
      split *= 100;
      halfSplit *= 10;
    }

    long upperFirstHalf = upper / halfSplit;
    long upperSecondHalf = upper % halfSplit;

    for (long i = lowerFirstHalf + 1; i < upperFirstHalf; i++) {
      int numDigits = (int) (Math.log10(i) + 1);
      double add = i * Math.pow(10, numDigits) + i;
      ans += add;
    }

    if (upperFirstHalf != lowerFirstHalf && upperFirstHalf <= upperSecondHalf) {
      int numDigits = (int) (Math.log10(upperFirstHalf) + 1);
      double add = upperFirstHalf * Math.pow(10, numDigits) + upperFirstHalf;
      ans += add;
    }

    if (upperFirstHalf != lowerFirstHalf && lowerFirstHalf >= lowerSecondHalf) {
      int numDigits = (int) (Math.log10(lowerFirstHalf) + 1);
      double add = lowerFirstHalf * Math.pow(10, numDigits) + lowerFirstHalf;
      ans += add;
    }

    if (upperFirstHalf == lowerFirstHalf && upperSecondHalf >= lowerFirstHalf && lowerFirstHalf >= lowerSecondHalf) {
      int numDigits = (int) (Math.log10(lowerFirstHalf) + 1);
      double add = lowerFirstHalf * Math.pow(10, numDigits) + lowerFirstHalf;
      ans += add;
    }

    return ans;
  }
}
