package domain;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LottoNumbers {

  public static final int LOTTO_SIZE = 6;

  private final Set<LottoNumber> values;

  public LottoNumbers(Set<LottoNumber> values) {
    validateSize(values);
    this.values = new TreeSet<>(values);
  }

  private void validateSize(Set<LottoNumber> values) {
    if (values.size() != LOTTO_SIZE) {
      throw new IllegalArgumentException(String.format("로또번호는 %d 자리 여야합니다. size: %d", LOTTO_SIZE, values.size()));
    }
  }

  public static LottoNumbers create(Set<Integer> values) {
    return values.stream()
        .map(LottoNumber::valueOf)
        .collect(Collectors.collectingAndThen(Collectors.toSet(), LottoNumbers::new));
  }

  public boolean contains(LottoNumber value) {
    return values.contains(value);
  }

  public int count(LottoNumbers other) {
    Set<LottoNumber> intersection = new TreeSet<>(values);
    intersection.retainAll(other.values);
    return intersection.size();

  }

  @Override
  public String toString() {
    return values.toString();
  }

}
