package org.moonzhou.validation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * @author moon zhou
 * @version 1.0
 * @description: id num util, true is valid
 * @date 2022/9/21 17:00
 */
public class IdNumUtil {

    /**
     * 数字
     */
    private final static Pattern NUMBERS = Pattern.compile("\\d+");

    /**
     * 中国公民身份证号码最小长度。
     */
    private static final int CHINA_ID_MIN_LENGTH = 15;

    /**
     * 中国公民身份证号码最大长度。
     */
    private static final int CHINA_ID_MAX_LENGTH = 18;

    /**
     * 校验码
     */
    private static final int[] PARITYBIT = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };

    /**
     * 加权因子wi
     */
    private static final int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
    
    public static boolean isValid(String idNum) {
        // 号码长度应为15位或18位
        if (idNum == null || (idNum.length() != 15 && idNum.length() != 18)) {
            return false;
        }

        // 校验年份
        String year = idNum.length() == 15 ? "19" + idNum.substring(6, 8) : idNum.substring(6, 10);
        final int iyear = Integer.parseInt(year);
        if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR)) {
            // 1900年的PASS，超过今年的PASS
            return false;
        }
        // 校验月份
        String month = idNum.length() == 15 ? idNum.substring(8, 10) : idNum.substring(10, 12);
        final int imonth = Integer.parseInt(month);
        if (imonth < 1 || imonth > 12) {
            return false;
        }
        // 校验天数
        String day = idNum.length() == 15 ? idNum.substring(10, 12) : idNum.substring(12, 14);
        final int iday = Integer.parseInt(day);
        if (iday < 1 || iday > 31) {
            return false;
        }

        // 校验一个合法的年月日
        if (!isValidDate(year + month + day)) {
            return false;
        }

        // 校验位数
        int power = 0;
        final char[] cs = idNum.toUpperCase().toCharArray();
        for (int i = 0; i < cs.length; i++) {// 循环比正则表达式更快
            if (i == cs.length - 1 && cs[i] == 'X') {
                break;// 最后一位可以是X或者x
            }
            if (cs[i] < '0' || cs[i] > '9') {
                return false;
            }
            if (i < cs.length - 1) {
                power += (cs[i] - '0') * POWER_LIST[i];
            }
        }
        // 校验“校验码”
        if (idNum.length() == 15) {
            return true;
        }
        return cs[cs.length - 1] == PARITYBIT[power % 11];
    }

    /**
     * 判断字符串是否为日期格式(合法)
     *
     * @param inDate:字符串时间
     * @return true/false
     */
    public static boolean isValidDate(String inDate) {
        if (inDate == null) {
            return false;
        }
        // 或yyyy-MM-dd
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");
        if (inDate.trim().length() != dataFormat.toPattern().length()) {
            return false;
        }
        // 该方法用于设置Calendar严格解析字符串;默认为true,宽松解析
        dataFormat.setLenient(false);
        try {
            dataFormat.parse(inDate.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    
}
