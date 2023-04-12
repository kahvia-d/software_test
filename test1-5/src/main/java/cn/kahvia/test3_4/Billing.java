package cn.kahvia.test3_4;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Billing {

    //这个函数用来判断电话的拨通时间或者结束时间需不需要转换
    long getConversationTime(final ZonedDateTime time, final int fastConversionCount, final int delayConversionCount, final boolean hasConversion) {
        int month = time.getMonthValue();
        int day = time.getDayOfMonth();
        int hour = time.getHour();
        int dayOfWeek = time.getDayOfWeek().getValue();

        boolean isNeedSunday = false;

        //三月的某个(fastConversionCount)星期日凌晨两点快进到三点，真实时间要回退3600秒
        if (month == 3 && dayOfWeek == 7 && day - 7 * fastConversionCount < 1 && hour >= 3 && hasConversion) {
            //1,2,3,4,5,6,7
            //1,2,3,4,5,6,7,1,2,3,4,5,6,7
            isNeedSunday = true;
            return time.toEpochSecond() - 60 * 60;
        }
        //十一月的某个（常为第一个，delayConversionCount）星期日凌晨三点回退到两点，真实时间要快进3600秒
        if (month == 11 && dayOfWeek == 7 && day - 7 * delayConversionCount < 1 && hasConversion) {
            return time.toEpochSecond() + 60 * 60;
        }

        return time.toEpochSecond();
    }


    // 计算通话费用
    public double calculateCost(final String startTimeStr, final String endTimeStr, final int fastConversionCount, final int delayConversionCount, final boolean startHasConversion, final boolean endHasConversion) {
        double cost = 0;
        // 将字符串类型的开始时间转换为LocalDateTime类型
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 将开始时间转换为ZonedDateTime类型，以便考虑时区
        ZonedDateTime startZonedTime = ZonedDateTime.of(startTime, ZoneId.systemDefault());
        ZonedDateTime endZonedTime = ZonedDateTime.of(endTime, ZoneId.systemDefault());

        //获得真实时间（即，如果是夏令时，就得到转换到夏令时前的时间）
        long realStartTime = getConversationTime(startZonedTime, fastConversionCount, delayConversionCount, startHasConversion);
        long realEndTime = getConversationTime(endZonedTime, fastConversionCount, delayConversionCount, endHasConversion);
        //计算通话时间,不足一分钟则计一分钟（即向上取整）
        double duration = Math.ceil((realEndTime - realStartTime) / 60.0);
        if (duration > 0 && duration <= 20.0) {
            cost = duration * 0.05;
        } else if (duration > 20.0) {
            cost = 1.0 + (duration - 20.0) * 0.10;
        }
        return cost;
    }

}

