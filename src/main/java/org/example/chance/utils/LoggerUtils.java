package org.example.chance.utils;


import org.example.chance.log.LoggerFormatter;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoggerUtils {

    public static Logger getLogger() {
        Logger logger = Logger.getLogger("chance-context");
        // logger.setLevel(Level.INFO);
        //如果需要将日志文件写到文件系统中，需要创建一个FileHandler对象
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler();
            //追加写入日志文件
            //FileHandler fileHandler = new FileHandler("d:" + File.separator + "java" + File.separator + "logger.log" , true);
            //创建日志格式文件：本次采用自定义的Formatter
            fileHandler.setFormatter(new LoggerFormatter());
            //将FileHandler对象添加到Logger对象中
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logger;
    }
}
