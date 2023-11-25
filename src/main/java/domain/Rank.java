package domain;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Rank {
  FIRST(6, 2_000_000_000),
  SECOND(5, 30_000_000, true),
  THIRD(5, 1_500_000),
  FOURTH(4, 50_000),
  FIFTH(3, 5_000),
  MISS(0, 0);


  private static final Map<Integer, Rank> BY_MATCH_COUNT = Stream.of(values())
      .filter(rank -> !rank.matchesBonus)
      .collect(Collectors.toMap(rank -> rank.matchCount, Function.identity()));

  private final int matchCount;
  private final int prizeMoney;
  private final boolean matchesBonus;

  Rank(int matchCount, int prizeMoney) {
    this(matchCount, prizeMoney, false);
  }

  Rank(int matchCount, int prizeMoney, boolean matchesBonus) {
    this.matchCount = matchCount;
    this.prizeMoney = prizeMoney;
    this.matchesBonus = matchesBonus;
  }

  public static Rank valueOf(int matchCount, boolean matchBonus) {
    if (matchCount == SECOND.matchCount && matchBonus) {
      return Rank.SECOND;
    }
    return BY_MATCH_COUNT.getOrDefault(matchCount, MISS);
  }

  public double multiply(long value) {
    return this.prizeMoney * value;
  }
}
