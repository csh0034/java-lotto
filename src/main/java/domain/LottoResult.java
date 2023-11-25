package domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

  private final Map<Rank, Long> value;
  private final double profileRatio;

  public LottoResult(Map<Rank, Long> value) {
    this.value = new EnumMap<>(value);
    this.profileRatio = calculateProfitRatio();
  }

  public double calculateProfitRatio() {
    double sum = 0;
    for (Map.Entry<Rank, Long> entry : value.entrySet()) {
      sum += entry.getKey().multiply(entry.getValue());
    }
    return sum / 1000;
  }

  public long getRankCount(Rank rank) {
    return value.getOrDefault(rank, 0L);
  }

  @Override
  public String toString() {
    return value.toString();
  }

}
