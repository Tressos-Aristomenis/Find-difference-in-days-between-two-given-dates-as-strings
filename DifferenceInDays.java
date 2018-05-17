import java.util.GregorianCalendar;

public class Main {
	public static void main(String[] args) {
		String date1 = "1-1-2016";
		String date2 = "1-1-2017";
		
		int day1 = Integer.parseInt(date1.split("-")[0]);
		int month1 = Integer.parseInt(date1.split("-")[1]);
		int year1 = Integer.parseInt(date1.split("-")[2]);
		
		int day2 = Integer.parseInt(date2.split("-")[0]);
		int month2 = Integer.parseInt(date2.split("-")[1]);
		int year2 = Integer.parseInt(date2.split("-")[2]);
		
		
		long differenceDays = 0;
		
		differenceDays += day1 - day2;
		differenceDays += getDiffDaysBetweenTwoMonths(new MyDate(day1, month1, year1), new MyDate(day2, month2, year2));
		differenceDays += (year1 - year2) * 365;
		
		differenceDays = Math.abs(differenceDays);
		differenceDays += howManyLeapYearsBetweenTwoYears(year1, year2);
		
		System.out.println("Total days difference between " + date1 + " and " + date2 + " : " + differenceDays);
	}
	
	private static int howManyLeapYearsBetweenTwoYears(int year1, int year2) {
		int leap = 0;
		
		int minYear = year1;
		int maxYear = year2;
		
		if (year1 > year2) {
			minYear = year2;
			maxYear = year1;
		}
		
		while (minYear < maxYear) {
			if (isLeapYear(minYear)) {
				leap++;
			}
			
			minYear++;
		}
		
		return leap;
	}
	
	private static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}
	
	private static long getDiffDaysBetweenTwoMonths(MyDate myDate1, MyDate myDate2) {
		if (myDate1.getMonth() == myDate2.getMonth()) {
			return 0;
		}
		
		int totalDays = 0;
		MyDate dateWithSmallerMonth = myDate1;
		MyDate dateWithBiggerMonth = myDate2;
		
		
		if (myDate1.getMonth() > myDate2.getMonth()) {
			dateWithSmallerMonth = myDate2;
			dateWithBiggerMonth = myDate1;
		}
		
		for (int c = dateWithSmallerMonth.getMonth(); c < dateWithBiggerMonth.getMonth(); c++) {
			totalDays += getDaysOfMonth(dateWithSmallerMonth, c);
		}
		
		return (dateWithSmallerMonth == myDate1) ? -totalDays : totalDays;
	}
	
	private static int getDaysOfMonth(MyDate myDate, int nextYear) {
		GregorianCalendar date = new GregorianCalendar(myDate.getYear(), nextYear -1, myDate.getDay());
		
		return date.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	}
}

class MyDate {
	private int day;
	private int month;
	private int year;
	
	public MyDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
}
