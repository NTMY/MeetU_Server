package org.meetu.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Log4j写日志功能扩展： 按天生成日志备份文件，并且根据maxBackupIndex配置值仅保留�?��n天的备份文件�?
 * 扩展自org.apache.log4j.FileAppender类�?
 * 
 * @author dzl 2012.5
 * 
 *         <pre>
 * 使用说明�?
 * 1.在log4j.xml中配置使用该类，配置方式类似于org.jboss.logging.appender.DailyRollingFileAppender�?
 * 2.与DailyRollingFileAppender相比，本类不支持配置datePattern�?备份日志的扩展名)�?
 * 3.与DailyRollingFileAppender相比，本类新增支持配置maxBackupIndex�?保存备份文件的个数，默认�?)�?
 * 4.本类也不支持配置KeepDays项�?
 * 5.示例�?
 *  其中class和maxBackupIndex是配置的关键�?
 *  &lt;appender name=&quot;VPBXLOG&quot; class=&quot;com.zte.component.log.DayRollingFileAppender&quot;&gt;
 *      &lt;param name=&quot;File&quot; value=&quot;../logs/vpbxweblog.log&quot; /&gt;    
 *      &lt;param name=&quot;Append&quot; value=&quot;true&quot;/&gt;
 *      &lt;param name=&quot;maxBackupIndex&quot; value=&quot;5&quot;/&gt;
 *      &lt;layout class=&quot;org.apache.log4j.PatternLayout&quot;&gt;
 *          &lt;param name=&quot;ConversionPattern&quot; value=&quot;%c %d{ISO8601} -- %p -- %m%n &quot;/&gt;
 *      &lt;/layout&gt;
 *   &lt;/appender&gt;
 * </pre>
 */
public class DayRollingFileAppender extends FileAppender {

    // 不允许改写配置datepattern(备份日志的扩展名)，固定为日期格式
    /** The date pattern. */
    private final String datePattern = "'.'yyyy-MM-dd";

    // 未使用该参数
    /** The keep days. */
    private int keepDays = 60;

    // 默认的备份文件保留个�?
    /** The max backup index. */
    private int maxBackupIndex = 2;

    // 文件�?上次�?��更新时间
    /** The scheduled filename. */
    private String scheduledFilename;

    // The next time we estimate a rollover should occur.
    /** The next check. */
    private long nextCheck = System.currentTimeMillis() - 1;

    /** The now. */
    Date now = new Date();

    /** The sdf. */
    SimpleDateFormat sdf;

    /**
     * 默认构�?函数.
     */
    public DayRollingFileAppender() {
    }

    /**
     * 改�?过的构�?函数.
     * 
     * @param layout
     *            layout
     * @param filename
     *            filename
     * @param maxBackupIndex
     *            maxBackupIndex
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public DayRollingFileAppender(Layout layout, String filename, int maxBackupIndex) throws IOException {
        super(layout, filename, true);
        this.maxBackupIndex = maxBackupIndex;
        activateOptions();
    }

    /**
     * 初始化操�?
     * 
     * @version
     */
    public void activateOptions() {
        super.activateOptions();
        if (fileName != null) {
            now.setTime(System.currentTimeMillis());
            sdf = new SimpleDateFormat(datePattern);
            File file = new File(fileName);
            // 获取�?��更新时间拼成的文件名
            scheduledFilename = fileName + sdf.format(new Date(file.lastModified()));
        } else {
            LogLog.error("File is not set for appender [" + name + "].");
        }
        if (maxBackupIndex <= 0) {
            LogLog.error("maxBackupIndex reset to default value[2],orignal value is:" + maxBackupIndex);
            maxBackupIndex = 2;
        }
    }

    /**
     * 滚动文件
     * 
     * <pre>
     * 1.对文件名带的时间戳进行比较，确定是否更新
     * 2.如果�?��更新，当前文件重命名为：原文件名+日期，重新开始写新的日志文件
     * 3.根据配置的maxBackupIndex，删除过期的文件
     * </pre>
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @version
     */
    void rollOver() throws IOException {

        String datedFilename = fileName + sdf.format(now);
        // 如果上次写的日期跟当前日期相同，不需要换文件
        if (scheduledFilename.equals(datedFilename)) {
            return;
        }

        // 关闭当前文件，并重命�?
        this.closeFile();

        File target = new File(scheduledFilename);
        if (target.exists()) {
            target.delete();
        }

        File file = new File(fileName);
        boolean result = file.renameTo(target);
        if (result) {
            LogLog.debug(fileName + " -> " + scheduledFilename);
        } else {
            LogLog.error("Failed to rename [" + fileName + "] to [" + scheduledFilename + "].");
        }

        // 删除过期文件
        if (maxBackupIndex > 0) {
            File folder = new File(file.getParent());
            List<String> maxBackupIndexDates = getMaxBackupIndexDates();
            for (File ff : folder.listFiles()) { // 遍历目录，将日期不在备份范围内的日志删掉
                if (ff.getName().startsWith(file.getName()) && !ff.getName().equals(file.getName())) {
                    // 获取文件名带的日期时间戳
                    String markedDate = ff.getName().substring(file.getName().length());
                    if (!maxBackupIndexDates.contains(markedDate)) {
                        result = ff.delete();
                    }
                    if (result) {
                        LogLog.debug(ff.getName() + " -> deleted ");
                    } else {
                        LogLog.error("Failed to deleted old DayRollingFileAppender file :" + ff.getName());
                    }
                }
            }
        }

        try {
            // 关闭文件，多个关闭操作并行时安装�?
            this.setFile(fileName, false, this.bufferedIO, this.bufferSize);
        } catch (IOException e) {
            errorHandler.error("setFile(" + fileName + ", false) call failed.");
        }
        // 更新�?��更新日期�?
        scheduledFilename = datedFilename;
    }

    /**
     * 写操�?
     * 
     * @param event
     *            event
     * @version
     */
    protected void subAppend(LoggingEvent event) {
        long n = System.currentTimeMillis();
        if (n >= nextCheck) { // 在每次写操作前判断一下是否需要滚动文�?
            now.setTime(n);
            nextCheck = getNextDayCheckPoint(now);
            try {
                rollOver();
            } catch (IOException ioe) {
                LogLog.error("rollOver() failed.", ioe);
            }
        }
        super.subAppend(event);
    }

    /**
     * 获取下一天的时间变更�?
     * 
     * @param now
     *            now
     * @return long
     */
    long getNextDayCheckPoint(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0); // 注意MILLISECOND,毫秒也要�?.
        calendar.add(Calendar.DATE, 1);
        return calendar.getTimeInMillis();
    }

    /**
     * 根据maxBackupIndex配置值，获取要保留log文件的日期范围集�?
     * 
     * @return list
     */
    List<String> getMaxBackupIndexDates() {
        List<String> result = new ArrayList<String>();

        if (maxBackupIndex > 0) {
            for (int i = 1; i <= maxBackupIndex; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0); // 注意MILLISECOND,毫秒也要�?.
                calendar.add(Calendar.DATE, -i);
                result.add(sdf.format(calendar.getTime()));
            }
        }
        return result;
    }

    /**
     * Gets the max backup index.
     * 
     * @return int
     */
    public int getMaxBackupIndex() {
        return maxBackupIndex;
    }

    /**
     * Sets the max backup index.
     * 
     * @param maxBackupIndex
     *            maxBackupIndex
     */
    public void setMaxBackupIndex(int maxBackupIndex) {
        this.maxBackupIndex = maxBackupIndex;
    }

    /**
     * Gets the date pattern.
     * 
     * @return String
     */
    public String getDatePattern() {
        return datePattern;
    }

    /**
     * Gets the keep days.
     * 
     * @return int
     */
    public int getKeepDays() {
        return keepDays;
    }

    /**
     * Sets the keep days.
     * 
     * @param keepDays
     *            keepDays
     */
    public void setKeepDays(int keepDays) {
        this.keepDays = keepDays;
    }

    /**
     * The main method.
     * 
     * @param args
     *            args
     */
    public static void main(String[] args) {
        DayRollingFileAppender da = new DayRollingFileAppender();
        da.setMaxBackupIndex(5);
        da.sdf = new SimpleDateFormat(da.getDatePattern());
        System.out.println(da.getMaxBackupIndexDates());

        File f = new File("C:/vpbxlog/vpbxweblog.log");
        System.out.println("f.name=" + f.getName());
        File p = new File(f.getParent());
        for (File ff : p.listFiles()) {
            System.out.println(ff);
        }
    }

}
