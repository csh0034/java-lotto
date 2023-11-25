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
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {

  @DisplayName("로또 번호에 보너스번호를 포함하면 예외 발생")
  @Test
  void createFail() {
    // given
    LottoNumbers lottoNumbers = LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 6));
    LottoNumber lottoNumber = LottoNumber.valueOf(1);

    // when then
    assertThatIllegalArgumentException().isThrownBy(() -> new WinningLotto(lottoNumbers, lottoNumber));
  }

  @DisplayName("당첨 등수를 가져온다")
  @ParameterizedTest
  @MethodSource("getRankArguments")
  void getRank(LottoNumbers lottoNumbers, Rank rank) {
    // given
    WinningLotto winningLotto = new WinningLotto(LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(45));

    // when
    Rank result = winningLotto.getRank(lottoNumbers);

    // then
    assertThat(result).isEqualTo(rank);
  }

  static Stream<Arguments> getRankArguments() {
    return Stream.of(
        arguments(LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 6)), Rank.FIRST),
        arguments(LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 45)), Rank.SECOND),
        arguments(LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 44)), Rank.THIRD),
        arguments(LottoNumbers.create(Set.of(1, 2, 3, 4, 44, 45)), Rank.FOURTH),
        arguments(LottoNumbers.create(Set.of(1, 2, 3, 43, 44, 45)), Rank.FIFTH)
    );
  }

}
