package domain;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lottos {

  private final List<LottoNumbers> values;

  public Lottos(List<LottoNumbers> values) {
    this.values = values;
  }

  public LottoResult calculate(WinningLotto winningLotto) {
    return values.stream()
        .map(winningLotto::getRank)
        .collect(Collectors.collectingAndThen(Collectors.groupingBy(Function.identity(), Collectors.counting()), LottoResult::new));
  }

}
