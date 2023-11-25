package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoNumbersTest {

  @DisplayName("로또가 정해진 숫자가 아닐 경우 예외 발생")
  @Test
  void createFail() {
    // given
    Set<Integer> values = Set.of(1, 2, 3);

    // when then
    assertThatIllegalArgumentException().isThrownBy(() -> LottoNumbers.create(values));
  }

  @DisplayName("로또 숫자를 포함하는지 반환")
  @ParameterizedTest
  @CsvSource({
      "1,true",
      "2,true",
      "3,true",
      "4,true",
      "5,true",
      "6,true",
      "7,false",
  })
  void contains(int number, boolean expected) {
    // given
    LottoNumbers lottoNumbers = LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 6));

    // when
    boolean result = lottoNumbers.contains(LottoNumber.valueOf(number));

    // then
    assertThat(result).isEqualTo(expected);
  }

  @DisplayName("일치하는 개수를 반환")
  @ParameterizedTest
  @MethodSource("countArguments")
  void count(LottoNumbers other, int expected) {
    // given
    LottoNumbers lottoNumbers = LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 6));

    // when
    int result = lottoNumbers.count(other);

    // then
    assertThat(result).isEqualTo(expected);
  }

  static Stream<Arguments> countArguments() {
    return Stream.of(
        arguments(LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 6)), 6),
        arguments(LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 45)), 5),
        arguments(LottoNumbers.create(Set.of(1, 2, 3, 4, 44, 45)), 4),
        arguments(LottoNumbers.create(Set.of(1, 2, 3, 43, 44, 45)), 3),
        arguments(LottoNumbers.create(Set.of(1, 2, 42, 43, 44, 45)), 2),
        arguments(LottoNumbers.create(Set.of(1, 41, 42, 43, 44, 45)), 1),
        arguments(LottoNumbers.create(Set.of(40, 41, 42, 43, 44, 45)), 0)
    );
  }

}
