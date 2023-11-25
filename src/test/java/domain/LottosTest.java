package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class LottosTest {

  @Test
  void calculate() {
    // given
    Lottos lottos = new Lottos(List.of(
        LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 6)),
        LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 45)),
        LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 44)),
        LottoNumbers.create(Set.of(1, 2, 3, 4, 44, 45)),
        LottoNumbers.create(Set.of(1, 2, 3, 43, 44, 45)),
        LottoNumbers.create(Set.of(40, 41, 42, 43, 44, 45)))
    );

    WinningLotto winningLotto = new WinningLotto(
        LottoNumbers.create(Set.of(1, 2, 3, 4, 5, 6)),
        LottoNumber.valueOf(45)
    );

    // when
    LottoResult lottoResult = lottos.calculate(winningLotto);

    // then
    assertAll(
        () -> assertThat(lottoResult.getRankCount(Rank.FIRST)).isEqualTo(1),
        () -> assertThat(lottoResult.getRankCount(Rank.SECOND)).isEqualTo(1),
        () -> assertThat(lottoResult.getRankCount(Rank.THIRD)).isEqualTo(1),
        () -> assertThat(lottoResult.getRankCount(Rank.FOURTH)).isEqualTo(1),
        () -> assertThat(lottoResult.getRankCount(Rank.FIFTH)).isEqualTo(1),
        () -> assertThat(lottoResult.getRankCount(Rank.MISS)).isEqualTo(1)
    );
  }

}
