
/**
 * This class represents a Date object
 *
 * @author Or Kubani
 * @version (2023a)
 */
public class Date
{
    // Instance variable
    private int _day;
    private int _month;
    private int _year;

    // Finals
    private final int MAX_MONTH= 12;
    private final int MAX_DAY_FOR_31_MONTH = 31;
    private final int MAX_DAY_FOR_30_MONTH = 30;
    private final int YEAR_NUM_OF_DIGITS = 4;
    private final int FEB_IN_LEAP_YEAR = 29;
    private final int FEB_NOT_IN_LEAP_YEAR = 28;
    private final int JANUARY = 1, FEBRUARY = 2, MARCH = 3, APRIL = 4, MAY = 5, JUNE = 6, 
    JULY = 7, AUGUST = 8, SEPTEMBER = 9, OCTOBER = 10, NOVEMBER = 11, DECEMBER = 12;

    // Default values
    private final int DEFAULT_DAY = 1;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_YEAR = 2000;

    // Constractors
    /**
     * If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000
     * @param day - the day in the month (1-31)
     * @param month - the month in the year (1-12)
     * @param year - the year (4 digits)
     */
    public Date(int day, int month, int year)
    {
        // Check using a private method if the date is legal
        if (isDateLegal(day,month,year)) 
        {
            _day = day;
            _month = month;
            _year = year;
        }

        // If the date isn't legal - set the default parameters
        else
        {
            _day = DEFAULT_DAY;
            _month = DEFAULT_MONTH;
            _year = DEFAULT_YEAR;
        }

    }

    /**
     * Copy constructor
     * @param other - the date to be copied
     */
    public Date (Date other)
    {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

    // Getters
    /**
     * Gets the day
     * @return the day
     */
    public int getDay()
    {
        return _day;
    }

    /**
     * Gets the month
     * @return the month
     */
    public int getMonth()
    {
        return _month;
    }

    /**
     * Gets the year
     * @return the year
     */
    public int getYear()
    {
        return _year;
    }

    // Setters
    /**
     * Set the day (only if date remains valid)
     * @param dayToSet - the day value to be set
     */
    public void setDay(int dayToSet)
    {
        if(isDateLegal(dayToSet, _month, _year)) // check if the new date keep the date legal
        {
            _day = dayToSet;
        }
    }

    /**
     * Set the month (only if date remains valid)
     * @param monthToSet - the month value to be set
     */
    public void setMonth(int monthToSet)
    {
        if(isDateLegal(_day, monthToSet, _year)) // check if the new date keep the date legal
        {
            _month = monthToSet;
        }
    }

    /**
     * Sets the year (only if date remains valid)
     * @param yearToSet - the year value to be set
     */
    public void setYear(int yearToSet)
    {
        if(isDateLegal(_day, _month, yearToSet)) // check if the new date keep the date legal
        {
            _year = yearToSet;
        }
    }

    // Other public methods

    /**
     * Check if 2 dates are the same
     * @param other - the date to compare this date to
     * @return true if the dates are the same, otherwise false
     */
    public boolean equals (Date other)
    {
        if ((_day == other._day) && (_month == other._month) && (_year == other._year))
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    /**
     * Check if this date is before other date
     * @param other - date to compare this date to
     * @return true if this date is before other date, otherwise false
     */
    public boolean before (Date other)
    {

        if (_year < other._year)
        {
            return true;
        }

        else if ((_month < other._month) && (_year <= other._year))
        {
            return true;
        }

        else if ((_day < other._day) && (_month <= other._month) && (_year <= other._year))
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    /**
     * Check if this date is after other date
     * @param other - date to compare this date to
     * @return true if this date is after other date, otherwise false
     */
    public boolean after (Date other)
    {
        return (!before(other));
    }

    /**
     * Calculates the difference in days between two dates
     * @param other - the date to calculate the difference between
     * @return the number of days between the dates (non negative value)
     */
    public int difference (Date other)
    {
        int differenceAmount = 0; // local variable to represent the dif
        int daysOfOriginalDate = calculateDate(_day, _month, _year);
        int daysOfOtherDate = calculateDate(other._day, other._month, other._year);
        differenceAmount = Math.abs(daysOfOriginalDate - daysOfOtherDate); // using Math.abs to make sure the value is positive 

        return differenceAmount;
    }

    /**
     * Returns a String that represents this date
     * @override toString in class java.lang.Object
     * @return String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString()
    {
        String dayToString = String.valueOf(_day); // convert to string to easly check the length 
        String monthToString = String.valueOf(_month);

        if (String.valueOf(_day).length() == 1)
        {
            dayToString = "0" + dayToString;
        }

        if (String.valueOf(_month).length() == 1)
        {
            monthToString = "0" + monthToString;
        }

        return (dayToString + "/" + monthToString + "/" + _year);
    }

    /**
     * Calculate the date of tomorrow
     * @return the date of tomorrow
     */
    public Date tomorrow()
    {
        if (isDateLegal(_day + 1, _month, _year))
        {
            return new Date(_day + 1, _month, _year);
        }

        else if (isDateLegal(1, _month +1, _year))
        {
            return new Date(1, _month + 1, _year);
        }

        else
        {
            return new Date(1, 1, _year +1);
        }
    }

    //Private methods
    private boolean isLeapYear(int year) 
    {
        if (year % 4 != 0) 
        {
            return false;
        } 

        else if (year % 400 == 0) 
        {
            return true;
        } 

        else if (year % 100 == 0) 
        {
            return false;
        } 

        else 
        {
            return true;
        }
    }

    /**
     * Calculate how much days are passed from specific date
     * @param day - the day in the month (1-31)
     * @param month - the month in the year (1-12)
     * @param year - the year (4 digits)
     * @return Number if days from the begining of the counting until the specific date 
     */
    private int calculateDate ( int day, int month, int year)
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    } 

    /**
     * Private method - Checks if the date is valid.
     * Takes into account leap yearד and months with 30 and 31 days.
     * @param day - the day in the month (1-31)
     * @param month - the month in the year (1-12)
     * @param year - the year (4 digits)
     * @return true if legal and false if illegal 
     */
    private boolean isDateLegal(int day, int month, int year)
    {
        boolean dayLegal = false;
        boolean monthLagel = false;
        boolean yearLegal = false;

        if ((month > 0) && (month <= MAX_MONTH))
        {
            monthLagel = true;
        }

        if ((year > 0) && (String.valueOf(year).length() == YEAR_NUM_OF_DIGITS))
        {
            yearLegal = true;
        }

        //Monthes with 31 days
        if ((month == JANUARY) || (month == MARCH) || (month == MAY) || (month == JULY) 
        || (month == AUGUST) || (month == OCTOBER) || (month == DECEMBER))
        {
            if((day > 0) && (day <= MAX_DAY_FOR_31_MONTH))
            {
                dayLegal = true;
            }
        }

        //Monthes with 30 days
        else if ((month == APRIL) || (month == JUNE) || (month == SEPTEMBER) || (month == NOVEMBER))
        {
            if((day > 0) && (day <= MAX_DAY_FOR_30_MONTH))
            {
                dayLegal = true;
            }
        }

        else if (month == FEBRUARY)
        {
            if (isLeapYear(year)) // Check if leap year using a private method
            {
                if ((day > 0) && (day <= FEB_IN_LEAP_YEAR))
                {
                    dayLegal = true;
                }
            }

            else
            {
                if ((day > 0) && (day <= FEB_NOT_IN_LEAP_YEAR)) // If not a leap 
                {
                    dayLegal = true;
                }
            }
        }

        if (dayLegal && monthLagel && yearLegal)
        {
            return true;
        }

        else
        {
            return false;
        }
    }
}
