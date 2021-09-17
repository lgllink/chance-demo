package org.example.chance;

import org.example.chance.domain.ChanceRequest;
import org.example.chance.domain.TempChanceRecord;
import org.example.chance.service.ChanceService;
import org.example.chance.utils.LoggerUtils;
import org.example.chance.utils.TempChance;

import java.util.Scanner;
import java.util.logging.Level;

public class ApplicationMain {

    static ChanceService chanceService = new ChanceService();

    static TempChance buildTempChance(String uniqueId) {
        TempChance tempChance = new TempChance();
        tempChance.setUniqueId(uniqueId);
        tempChance.setTotalChance(5);
        tempChance.setDailyChance(5);
        tempChance.setAddTimes(1);

        return tempChance;
    }

    public static void main(String[] args) {
        while (true) {
            String chooseMsg = "\"1: 查询次数\\tt\" + \"2: 增加次数\\tt\" + \"3: 扣减次数\\tt\"+ \"-1: 退出程序\\tt\"";
            chooseFunction(consoleReder(chooseMsg));
        }
    }

    static int consoleReder(String chooseMsg) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t-----请输入选项------\t\t\t");
        System.out.println(chooseMsg);
        int nextInt = scanner.nextInt();
        return nextInt;
    }

    static void chooseFunction(int choose) {
        switch (choose) {
            case -1:
                System.exit(0);
                break;
            case 1:
                queryChance();
                break;
            case 2:
                incrChanceFunctin(choose);
                break;
            case 3:
                reduceChanceFunctin(choose);
                break;
            default:
                throw new IllegalArgumentException("选项输入错误");
        }
    }

    static void incrChanceFunctin(int choose) {
        System.out.println("----增加次数----");
        String chooseMsg = "请输入addTimes";
        int reder = consoleReder(chooseMsg);
        incrChance(reder);
    }

    static void reduceChanceFunctin(int choose) {
        System.out.println("----扣减次数----");
        String chooseMsg = "请输入reduceTimes";
        int reder = consoleReder(chooseMsg);
        reduceChance(reder);
    }


    static void queryChance() {
        ChanceRequest request = new ChanceRequest("123", 1);
        TempChanceRecord tempChanceRecord = chanceService.queryChance(convert2ChanceContext(request));
//        System.out.println("queryChance: ---" + tempChanceRecord);
        LoggerUtils.getLogger().log(Level.INFO, "queryChance: ---" + tempChanceRecord);
    }

    static void incrChance(int addTimes) {
        ChanceRequest request = new ChanceRequest("123", addTimes);
        TempChanceRecord tempChanceRecord = chanceService.incrChance(convert2ChanceContext(request));
//        System.out.println("incrChance: ---" + tempChanceRecord);
        LoggerUtils.getLogger().log(Level.INFO, "incrChance: ---" + tempChanceRecord);
    }

    static void reduceChance(int reduceTimes) {
        ChanceRequest request = new ChanceRequest("123", reduceTimes);
        TempChanceRecord tempChanceRecord = chanceService.reduceChance(convert2ChanceContext(request));
//        System.out.println("reduceChance: ---" + tempChanceRecord);
        LoggerUtils.getLogger().log(Level.INFO, "reduceChance: ---" + tempChanceRecord);
    }

    static ChanceContext convert2ChanceContext(ChanceRequest request) {
        ChanceContext context = new ChanceContext(request);
        context.buildTempChanceEntity(buildTempChance(request.getUniqueId()));
        return context;
    }

}
