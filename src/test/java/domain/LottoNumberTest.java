package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

  @DisplayName("로또 숫자 허용 범위가 아닐 경우 예외 발생")
  @ParameterizedTest
  @ValueSource(ints = {LottoNumber.MIN_LOTTO_NUMBER - 1, LottoNumber.MAX_LOTTO_NUMBER + 1})
  void createFail(int value) {
    // when then
    assertThatIllegalArgumentException().isThrownBy(() -> LottoNumber.valueOf(value));
  }

  @DisplayName("로또 숫자는 캐싱처리되어 동일한 인스턴스 반환")
  @Test
  void caching() {
    // given
    LottoNumber lottoNumber = LottoNumber.valueOf(1);
    LottoNumber other = LottoNumber.valueOf(1);

    // when then
    assertThat(lottoNumber).isSameAs(other);
  }

}
