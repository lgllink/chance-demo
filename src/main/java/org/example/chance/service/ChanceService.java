package org.example.chance.service;

import org.apache.commons.lang3.StringUtils;
import org.example.chance.ChanceContext;
import org.example.chance.cache.ChanceConstantCache;
import org.example.chance.domain.TempChanceRecord;
import org.example.chance.utils.LoggerUtils;

import java.util.Objects;

public class ChanceService {

    public TempChanceRecord queryChance(ChanceContext context) {
        checkParams(context);

        return handleTempChanceRecord(context);
    }

    public TempChanceRecord incrChance(ChanceContext context) {
        TempChanceRecord tempChanceRecord = queryChance(context);

        context.checkChance(tempChanceRecord);

        return doIncrChance(context, tempChanceRecord);
    }

    private TempChanceRecord doIncrChance(ChanceContext context, TempChanceRecord tempChanceRecord){
        TempChanceRecord cacheTempChanceRecord = ChanceConstantCache.incrChance(context.getUniqueId(), tempChanceRecord, context.obtainTempChance().getRealAddTimes());
        tempChanceRecord.setChance(cacheTempChanceRecord.getChance());
        tempChanceRecord.setTodayChance(cacheTempChanceRecord.getTodayChance());
        return tempChanceRecord;
    }

    public TempChanceRecord reduceChance(ChanceContext context) {
        TempChanceRecord tempChanceRecord = queryChance(context);
        return doReduceChance(context, tempChanceRecord);
    }

    private TempChanceRecord doReduceChance(ChanceContext context, TempChanceRecord tempChanceRecord){
        if(tempChanceRecord.getChance() <= tempChanceRecord.getConsumeChance()){
            LoggerUtils.getLogger().info("doReduceChance: ---次数不足: tempChanceRecord: " +tempChanceRecord);
            return tempChanceRecord;
        }
        TempChanceRecord cacheTempChanceRecord = ChanceConstantCache.reduceChance(context.getUniqueId(), tempChanceRecord, context.obtainTempChance().getRealAddTimes());
        tempChanceRecord.setConsumeChance(cacheTempChanceRecord.getConsumeChance());
        return tempChanceRecord;
    }

    private TempChanceRecord handleTempChanceRecord(ChanceContext context) {
        return ChanceConstantCache.getTempChanceRecord(context.getUniqueId());
    }

    private void checkParams(ChanceContext context) {
        if (StringUtils.isBlank(context.getUniqueId())) {
            throw new NullPointerException("checkParams params uiniqueId is null");
        }

        if (Objects.isNull(context.obtainTempChance())) {
            throw new NullPointerException("checkParams tempChance is null");
        }
    }


}
