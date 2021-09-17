package org.example.chance;


import lombok.NoArgsConstructor;
import org.example.chance.utils.CglibBeanCopierUtils;
import org.example.chance.utils.LoggerUtils;
import org.example.chance.utils.TempChance;

import java.util.Objects;

@NoArgsConstructor
public class TempChanceEntity {

    private TempChance tempChance;

    public TempChanceEntity(TempChance tempChance) {
        this.tempChance = tempChance;
    }

    public static TempChanceEntity build(TempChance tempChance) {
        if (Objects.isNull(tempChance)) {
            throw new NullPointerException("build tempChance can not null");
        }
        TempChance newTempChance = new TempChance();
//        BeanCopier beanCopier = BeanCopier.create(tempChance.getClass(), TempChance.class, false);
//        beanCopier.copy(tempChance, newTempChance, null);

        CglibBeanCopierUtils.copyProperties(tempChance, newTempChance);
        return new TempChanceEntity(newTempChance);
    }

    public TempChance obtainTempChance() {
        return this.tempChance;
    }

    public void checkChance(int totalChance, int todayChance, int addTimes) {
        int realAddTimes = checkLimitChance(obtainTempChance(), totalChance, todayChance, addTimes);
        this.tempChance.setRealAddTimes(realAddTimes);
    }

    private int checkLimitChance(TempChance tempChance, int totalChance, int todayChance, int addTimes) {
        if (totalChance >= tempChance.getTotalChance()) {
            LoggerUtils.getLogger().info("totalChance is full...");
            throw new NullPointerException("totalChance is full...");
        }
        if (todayChance >= tempChance.getDailyChance()) {
            LoggerUtils.getLogger().info("dailyChance is full...");
            throw new NullPointerException("dailyChance is full...");

        }
        return addTimes;
    }


}
