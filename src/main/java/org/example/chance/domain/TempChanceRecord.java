package org.example.chance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TempChanceRecord {
    private int chance;
    private int consumeChance;
    private int todayChance;

}
