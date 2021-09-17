package org.example.chance.cache;


import org.example.chance.domain.TempChanceRecord;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChanceConstantCache {

    private static final Map<String, TempChanceRecord> CHANCE_RECORD_CACHE = new ConcurrentHashMap<>();

    private static Lock lock = new ReentrantLock();


    public static TempChanceRecord getTempChanceRecord(String uniqueId) {
        return CHANCE_RECORD_CACHE.computeIfAbsent(uniqueId, t -> new TempChanceRecord());
    }

    public static TempChanceRecord putTempChanceRecord(String uniqueId, TempChanceRecord tempChanceRecord) {
        return CHANCE_RECORD_CACHE.put(uniqueId, tempChanceRecord);
    }

    public static TempChanceRecord incrChance(String uniqueId, final TempChanceRecord record, int addTimes) {
        lock.tryLock();
        TempChanceRecord tempChanceRecord = getTempChanceRecord(uniqueId);
        System.out.println("incr before tempChanceRecord: " + tempChanceRecord);
        tempChanceRecord.setChance(tempChanceRecord.getChance() + addTimes);
        tempChanceRecord.setTodayChance(tempChanceRecord.getTodayChance() + addTimes);
        System.out.println("incr after tempChanceRecord: " + tempChanceRecord);
        TempChanceRecord chanceRecord = CHANCE_RECORD_CACHE.put(uniqueId, tempChanceRecord);
        lock.unlock();
        return chanceRecord;
    }

    public static TempChanceRecord reduceChance(String uniqueId,  final TempChanceRecord record, int reduceTimes) {
        lock.tryLock();
        TempChanceRecord tempChanceRecord = getTempChanceRecord(uniqueId);
        System.out.println("reduce before tempChanceRecord: " + tempChanceRecord);
        tempChanceRecord.setConsumeChance(tempChanceRecord.getConsumeChance() + reduceTimes);
        System.out.println("reduce after tempChanceRecord: " + tempChanceRecord);
        TempChanceRecord chanceRecord = CHANCE_RECORD_CACHE.put(uniqueId, tempChanceRecord);
        lock.unlock();
        return chanceRecord;
    }
}
