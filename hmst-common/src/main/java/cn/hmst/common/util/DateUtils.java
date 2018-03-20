package cn.hmst.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author xwf
 * @version 2017-4-12
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	// 用来全局控制 上一周，本周，下一周的周数变化
	private static int weeks = 0;
	/**
	 * 日期转换成字符串
	 * @param date
	 * @param formatStr yyyy-MM-dd HH:mm:ss
	 * @return str
	 */
	public static String dateToStr(Date date,String formatStr) {

		if(date == null) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String str = format.format(date);
		return str;
	}

	public static String dateToStr(Date date) {

		if(date == null) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 字符串转换成日期
	 * @param str
	 * @param formatStr 格式
	 * @return date
	 */
	public static Date strToDate(String str,String formatStr) {

		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转换成日期
	 * @param str
	 * @return date
	 */
	public static Date strToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/*
  * 将时间转换为时间戳
  */
	public static String dateToTimeStr(String s) throws ParseException{
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	/*
     * 将时间戳转换为时间
     */
	public static String timeToDateStr(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	private static String[] parsePatterns = { "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
			"yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

	public static final String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss SSS";

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		if (date == null) {
			return null;
		}
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 获取过去的秒数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastSeconds(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / 1000;
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60
				* 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "."
				+ sss;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	public static String formatDate(DateFormat dateFormat, Date date) {
		if (dateFormat == null || date == null) {
			return null;
		}
		return dateFormat.format(date);
	}

	/**
	 * 获得传入日期当月最小的日期
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getStartDateOfMonth(Date dt) {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(dt);
		gcal.set(Calendar.DATE, 1);
		return gcal.getTime();
	}

	/**
	 * 获得传入日期当月最大的日期
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getEndDateOfMonth(Date dt) {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(dt);
		gcal.set(Calendar.DATE, gcal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return gcal.getTime();
	}

	/**
	 * 获取下个月的第一天，如：输入2009-01-31，返回2009-02-01
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getStartDateOfNextMonth(Date dt) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(dt);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 获取下个月的最后一天，如：输入2009-01-31，返回2009-02-28
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getEndDateOfNextMonth(Date dt) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(dt);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 获得传入日期当季最大的日期
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getEndDateOfSeason(Date dt) {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(dt);
		int mon = gcal.get(Calendar.MONTH);
		// 计算需要加多少月才为季度的末月
		int add = ((mon + 1) % 3) == 0 ? 0 : 3 - ((mon + 1) % 3);
		gcal.add(Calendar.MONTH, add);
		gcal.set(Calendar.DATE,
				gcal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return gcal.getTime();
	}

	/**
	 * 获得传入日期当季最小的日期
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getStartDateOfSeason(Date dt) {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(dt);
		int mon = gcal.get(Calendar.MONTH);
		// 计算需要加多少月才为季度的首月
		int add = -(mon % 3);
		gcal.add(Calendar.MONTH, add);
		gcal.set(Calendar.DATE, 1);
		return gcal.getTime();
	}

	/**
	 * 获得传入日期当年最大的日期
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getEndDateOfYear(Date dt) {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(dt);
		gcal.set(Calendar.MONTH, 11);
		gcal.set(Calendar.DATE, 31);
		return gcal.getTime();
	}

	/**
	 * 获得传入日期当年最小的日期
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getStartDateOfYear(Date dt) {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(dt);
		gcal.set(Calendar.MONTH, 0);
		gcal.set(Calendar.DATE, 1);
		return gcal.getTime();
	}

	/**
	 * 获取过去的第N(日|月|年)的日期
	 * 
	 * @param pass
	 *            过去的(日|月|年)
	 * @param type
	 *            DATE,MONTH,YEAR
	 * @return
	 */
	public static Date getPastDate(int pass, String type) {
		return getPastDate(pass, false, null, type);
	}

	/**
	 * TYPE目前只定义日,月,年 如果date不为空,则计算从date当前开始往前偏移pass(日|月|年)期的结果
	 * 如果date为空,则处理当天开始,往前偏移pass(日|月|年)期 如果clearTime为True,则设时分秒为0
	 * 
	 * @param pass
	 * @param clearTime
	 * @param date
	 * @return
	 */
	public static Date getPastDate(int pass, boolean clearTime, Date date,
			String type) {
		GregorianCalendar gcal = new GregorianCalendar();
		if (date != null) {
			gcal.setTime(date);
		} else {
			gcal.setTime(new Date());
		}
		if (StringUtils.equalsIgnoreCase(type, "YEAR")) {
			gcal.add(Calendar.YEAR, pass * (-1));
		} else if (StringUtils.equalsIgnoreCase(type, "MONTH")) {
			gcal.add(Calendar.MONTH, pass * (-1));
		} else {
			gcal.add(Calendar.DATE, pass * (-1));
		}

		if (clearTime) {
			gcal.set(Calendar.HOUR_OF_DAY, 0);
			gcal.set(Calendar.MINUTE, 0);
			gcal.set(Calendar.SECOND, 0);
		}
		return gcal.getTime();
	}

	/**
	 * 获取过去的第N天的日期，若clearTime为TRUE则都将时分秒置为0
	 * 
	 * @param pass
	 * @param clearTime
	 * @return
	 */
	public static Date getPastDate(int pass, boolean clearTime, String type) {
		return getPastDate(pass, clearTime, null, type);
	}

	/**
	 * 指定日期、时间，获取组合后的时间
	 * 
	 * @param date
	 *            日期
	 * @param time
	 *            时间，格式如HH:mm:ss或HH:mm
	 * @return
	 */
	public static Date getDateTime(Date date, String time) {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(date);
		String[] t = time.split(":");
		gcal.set(Calendar.HOUR_OF_DAY,
				t.length > 0 ? NumberUtils.toInt(t[0], 0) : 0);
		gcal.set(Calendar.MINUTE, t.length > 1 ? NumberUtils.toInt(t[1], 0) : 0);
		gcal.set(Calendar.SECOND, t.length > 2 ? NumberUtils.toInt(t[2], 0) : 0);
		return gcal.getTime();
	}





	/**
	 * 将字符串日期转成当天时间等于00:00:00的java.util.Date对象的Long值（毫秒数）
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd格式的日期字符串
	 * @return Long
	 */




	/**
	 * @param time
	 *            时间
	 * @param format
	 *            日期返回的格式 如yyyy-MM-dd hh:mm:ss SSS
	 * @return 字符器类型的日期，日期的格式如参数format
	 */
	public static String getFormatTime(long time, String format) {
		SimpleDateFormat s = new SimpleDateFormat(format);
		return s.format(new Date(time));
	}


	/**
	 * 计时，常用于记录某段代码的执行时间，单位：纳秒
	 * 
	 * @param preTime
	 *            之前记录的时间
	 * @return 时间差，纳秒
	 */
	public static long spendNt(long preTime) {
		return System.nanoTime() - preTime;
	}

	/**
	 * 计时，常用于记录某段代码的执行时间，单位：毫秒
	 * 
	 * @param preTime
	 *            之前记录的时间
	 * @return 时间差，毫秒
	 */
	public static long spendMs(long preTime) {
		return System.currentTimeMillis() - preTime;
	}

	/**
	 * 返回两个日期之间的天数
	 * 
	 * @param early
	 * @param late
	 * @return
	 */
	public static int daysBetween(Date early, Date late) {

		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();
		calst.setTime(early);
		caled.setTime(late);
		// 设置时间为0时
		setTimeZERO(calst);
		setTimeZERO(caled);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 3600 / 24;
		return days;
	}

	/**
	 * 返回两个日期之间的小时数
	 * 
	 * @param early
	 * @param late
	 * @return
	 */
	public static int hoursBetween(Date early, Date late) {
		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();
		calst.setTime(early);
		caled.setTime(late);
		int hours = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 3600;
		return hours;
	}

	/**
	 * 返回两个日期之间的分钟数
	 * 
	 * @param early
	 * @param late
	 * @return
	 */
	public static int minutesBetween(Date early, Date late) {
		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();
		calst.setTime(early);
		caled.setTime(late);
		int minutes = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 60;
		return minutes;
	}

	/**
	 * 设置时间域为0
	 * 
	 * @param cal
	 */
	public static void setTimeZERO(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
	}

	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(7) - 1;
		if (dayOfWeek == 1) {
			return 0;
		}
		return (1 - dayOfWeek);
	}

	public static Date getMondayOFWeek() {
		return getMondayOFWeek(null);
	}

	public static Date getMondayOFWeek(Date date) {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		if (null != date) {
			currentDate.setTime(date);
		}
		currentDate.add(5, mondayPlus);
		return currentDate.getTime();
	}

	public static Date getFirstDayOfMonth() {
		return getFirstDayOfMonth(null);
	}

	public static Date getFirstDayOfMonth(Date date) {
		Calendar lastDate = Calendar.getInstance();
		if (null != date) {
			lastDate.setTime(date);
		}
		lastDate.set(5, 1);
		return lastDate.getTime();
	}

	public static Date getWeekDay(Date date, int firstDay, int weekAmount,
			int weekDay) {
		Calendar ca = Calendar.getInstance();
		if (null != date) {
			ca.setTime(date);
		}
		ca.setFirstDayOfWeek(firstDay);// 设定每周第一天为周几，默认是星期天
		ca.add(Calendar.WEEK_OF_MONTH, weekAmount);// 周数减一，即上周
		ca.set(Calendar.DAY_OF_WEEK, weekDay);// 设定日期
		return ca.getTime();
	}

	public static Date getMonthDay(Date date, int monthAmount, int monthDay) {
		Calendar ca = Calendar.getInstance();
		if (null != date) {
			ca.setTime(date);
		}
		ca.add(Calendar.MONTH, monthAmount);// 月数减一，即上月
		ca.set(Calendar.DAY_OF_MONTH, monthDay);// 设定日期1为当月1号
		return ca.getTime();
	}

	public static Date DateSubtraction(Date date, int minutes) throws Exception {
		Calendar ca = Calendar.getInstance();
		if (null != date) {
			ca.setTime(date);
		}
		ca.add(Calendar.MINUTE, -minutes);// 分钟减法
		return ca.getTime();
	}

	public static Date getBeginOfDate(Date date) {
		if (null == date) {
			date = new Date();
		}
		try {
			Date d = parseDate(formatDate(date, "yyyyMMdd") + " 00:00:00",
					"yyyyMMdd HH:mm:ss");
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date getEndOfDate(Date date) {
		if (null == date) {
			date = new Date();
		}
		try {
			Date d = parseDate(formatDate(date, "yyyyMMdd") + " 23:59:59",
					"yyyyMMdd HH:mm:ss");
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date newDate() {
		return new Date();
	}

	/**
	 * @throws ParseException
	 */




	// 获得上周星期一的日期
	public static Date getPreviousMonday() {
		weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		return monday;
	}


	// 获得上周星期日的日期
	public static Date getPreviousSunday() {
// weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		return monday;
	}


	// 获得本周星期一的日期
	public static Date getCurrentMonday() {
		weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		return monday;
	}


	// 获得下周星期一的日期
	public static Date getNextMonday() {
		weeks++;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		return monday;
	}


	// 获得下周星期一的日期
	public static Date getNextSunday() {
		weeks++;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		return monday;
	}


	// 获得本周的周日的日期
	public static Date getSunday() {
		int mondayPlus =getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		return monday;
	}

	//获取下几个周的周末
	public static  Date getWeekOfDate(int count) {

		int j = -1;

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		for(int i=0;i>-1;i++)
		{

			if(i!=0) {
				cal.add(Calendar.DATE, 1);//每次增加一天
			}
			int week = cal.get(Calendar.DAY_OF_WEEK);//获取星期序号

			int weekcode = week - 1;
			if(weekcode == 0)
			{
				weekcode = weekcode +7;
			}

			if(weekcode == 7)
			{
				j++;//周末加1
			}

			if(j==count)
			{
				break;
			}
		}
		return cal.getTime();
	}



	/***
	 * 返回俩个时间相差的周数
	 * @param startDate
	 * @param endDate
     * @return
     */
	public static  int diffWeeks(Date startDate, Date endDate) {

		int j = 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);

		for(int i=0;i>-1;i++)
		{

			if(i!=0) {
				cal.add(Calendar.DATE, 1);//每次增加一天
			}
			int week = cal.get(Calendar.DAY_OF_WEEK);//获取星期序号

			int weekcode = week - 1;
			if(weekcode == 0)
			{
				weekcode = weekcode +7;
			}

			if(weekcode == 1)
			{
				j++;//周一加1，则认为是新的一周
			}

			if(cal.getTime().after(endDate))
			{
				break;
			}
		}
		return j;
	}

	/***
	 * 计算俩个时间直接相差的周数，按7天算一周计算的方式
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int diffWeeksByOther(Date startDate, Date endDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		Double days = Double.parseDouble(String.valueOf(between_days));
		if ((days / 7) > 0 && (days / 7) <= 1) { // 不满一周的按一周算
			return 1;
		} else if (days / 7 > 1) {
			int day = days.intValue();
			if (day % 7 > 0) {
				return day / 7 + 1;
			} else {
				return day / 7;
			}
		} else if ((days / 7) == 0) {
			return 1;
		} else {
			// 负数返还null
			return 0;
		}
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param startDate
	 *            较小的时间
	 * @param endDate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int diffDays(Date startDate, Date endDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		startDate = sdf.parse(sdf.format(startDate));
		endDate = sdf.parse(sdf.format(endDate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param startDate
	 *            较小的时间
	 * @param endDate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int diffDays(String startDate, String endDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(startDate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(endDate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 *
	 * @return 返回当前固定格式
     */
	public static String getNowData(){
		Date date =  new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date).toString();
	}
	public static void main(String[] args) throws ParseException {
		System.out.println(getNowData());
	}

	/**
	 * 获取最小日期
	 * @return
     */
	public static Date getMinDate(){
		return new Date(0);
	}

	/**
	 * 获取最大日期
	 * @return
     */
	public static Date getMaxDate(){
		return strToDate("9999-12-31","yyyy-MM-dd");
	}

}
