package domain;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

  public static final int MIN_LOTTO_NUMBER = 1;
  public static final int MAX_LOTTO_NUMBER = 45;
  private static final Map<Integer, LottoNumber> CACHE = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
      .mapToObj(LottoNumber::new)
      .collect(Collectors.toMap(lottoNumber -> lottoNumber.value, Function.identity()));

  private final int value;

  private LottoNumber(int value) {
    validateRange(value);
    this.value = value;
  }

  private static void validateRange(int value) {
    if (value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
      throw new IllegalArgumentException(String.format("로또번호 범위가 아닙니다, value: %d", value));
    }
  }

  public static LottoNumber valueOf(int value) {
    validateRange(value);
    return CACHE.get(value);
  }

  @Override
  public int compareTo(LottoNumber other) {
    return Integer.compare(this.value, other.value);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    LottoNumber that = (LottoNumber) other;
    return value == that.value;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

}
