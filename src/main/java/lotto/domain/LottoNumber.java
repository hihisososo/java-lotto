package lotto.domain;

import java.util.Objects;
import java.util.regex.Pattern;
import lotto.exception.InvalidLottoNumberException;

public class LottoNumber {

  private final int value;
  public final static int MIN = 1;
  public final static int MAX = 45;
  private static Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");

  public LottoNumber(int value) {
    if (checkBound(value)) {
      throw new InvalidLottoNumberException(String.valueOf(value));
    }
    this.value = value;
  }

  public LottoNumber(String value) {
    if (!NUMBER_PATTERN.matcher(value).find()) {
      throw new InvalidLottoNumberException(value);
    }
    this.value = Integer.valueOf(value);
  }

  private boolean checkBound(int value) {
    return value < MIN || value > MAX;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LottoNumber that = (LottoNumber) o;
    return value == that.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
