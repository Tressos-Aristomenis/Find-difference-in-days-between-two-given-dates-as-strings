import java.util.GregorianCalendar;

public class DifferenceInDays {
	public static void main(String[] args) {
		String date1 = "1-1-2016";		// Dates input.
		String date2 = "1-1-2017";
		
		// Splitting dates to get their day, month and year values.
		
		int day1 = Integer.parseInt(date1.split("-")[0]);
		int month1 = Integer.parseInt(date1.split("-")[1]);
		int year1 = Integer.parseInt(date1.split("-")[2]);
		
		int day2 = Integer.parseInt(date2.split("-")[0]);
		int month2 = Integer.parseInt(date2.split("-")[1]);
		int year2 = Integer.parseInt(date2.split("-")[2]);
		
		// Total days counter that will be printed in the end.
		
		long differenceDays = 0;
		
		// Below I substract the 2nd date from the 1st date, we could do the opposite as well. It' s the same thing.
		// Find the difference of days of the dates.
		
		differenceDays += day1 - day2; // We could write day2 - day1 as well.
		
		// Find how many days the months of the dates differ (not taking leap years under consideration).
		
		differenceDays += getDiffDaysBetweenTwoMonths(new MyDate(day1, month1, year1), new MyDate(day2, month2, year2));
		
		// Find the difference of years of the dates.
		
		differenceDays += (year1 - year2) * 365; // We could write year2 - year1 as well.
		
		// We don't care about the negative number, just find the absolute value.
		
		differenceDays = Math.abs(differenceDays);
		
		// Add the number of leap years to the counter. Every leap year is +1 day.
		
		differenceDays += howManyLeapYearsBetweenTwoYears(year1, year2);
		
		// Print the result.
		
		System.out.println("Total days difference between " + date1 + " and " + date2 + " : " + differenceDays);
	}
	
	
	
	private static int howManyLeapYearsBetweenTwoYears(int year1, int year2) {
		int leap = 0;	// Counts leap years.
		
		// Find min and max year.
		
		int minYear = year1;
		int maxYear = year2;
		
		if (year1 > year2) {
			minYear = year2;
			maxYear = year1;
		}
		
		while (minYear < maxYear) {
			if (isLeapYear(minYear)) {	// If current year is a leap year:
				leap++;
			}
			
			minYear++;
		}
		
		return leap;		// Return number of leap years.
	}
	
	
	
	private static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;	// Algorithm that checks if a year is a leap year.
	}
	
	
	
	private static long getDiffDaysBetweenTwoMonths(MyDate myDate1, MyDate myDate2) {
		if (myDate1.getMonth() == myDate2.getMonth()) {		// If it's the same month there is no difference in days.
			return 0;
		}
		
		int totalDays = 0;
		
		// Find smaller month and bigger month.
		
		MyDate dateWithSmallerMonth = myDate1;
		MyDate dateWithBiggerMonth = myDate2;
		
		
		if (myDate1.getMonth() > myDate2.getMonth()) {
			dateWithSmallerMonth = myDate2;
			dateWithBiggerMonth = myDate1;
		}
		
		
		for (int c = dateWithSmallerMonth.getMonth(); c < dateWithBiggerMonth.getMonth(); c++) {
			// Count the days between these two months.
			totalDays += getDaysOfMonth(dateWithSmallerMonth, c);	
		}
		
		// Here I'm taking myDate1 as the standard date. If myDate1 is the smaller one, return the negative value of totalDays.
		
		return (dateWithSmallerMonth == myDate1) ? -totalDays : totalDays;
	}
	
	
	// Using the GregorianCalendar, get the days of the given month.
	
	private static int getDaysOfMonth(MyDate myDate, int nextYear) {
		GregorianCalendar date = new GregorianCalendar(myDate.getYear(), nextYear -1, myDate.getDay());
		
		return date.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	}
}



/////////////	MyDate Class	/////////////

class MyDate {
	private int day;
	private int month;
	private int year;
	
	// Constructor.
	
	public MyDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	// Getters.
	
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
