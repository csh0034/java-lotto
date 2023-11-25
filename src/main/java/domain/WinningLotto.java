package domain;

public class WinningLotto {

  private final LottoNumbers lottoNumbers;
  private final LottoNumber bonus;

  public WinningLotto(LottoNumbers lottoNumbers, LottoNumber bonus) {
    validateBonus(lottoNumbers, bonus);
    this.lottoNumbers = lottoNumbers;
    this.bonus = bonus;
  }

  private void validateBonus(LottoNumbers lottoNumbers, LottoNumber bonus) {
    if (lottoNumbers.contains(bonus)) {
      throw new IllegalArgumentException(String.format("보너스볼은 로또번호에 포함될 수 없습니다. bonus: %s", bonus));
    }
  }

  public Rank getRank(LottoNumbers lottoNumbers) {
    int matchCount = lottoNumbers.count(this.lottoNumbers);
    boolean matchesBonus = lottoNumbers.contains(this.bonus);
    return Rank.valueOf(matchCount, matchesBonus);
  }

}

