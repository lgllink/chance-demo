package org.example.chance.utils;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempChance {

    private String uniqueId;
    private int dailyChance;
    private int totalChance;
    private int addTimes;
    private int realAddTimes = 1;

}
