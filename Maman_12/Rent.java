
/**
 * This class represents a Rent object
 *
 * @author Or Kubani
 * @version (2023a)
 */
public class Rent
{
    //instance variables
    private String _name;
    private Car _car;
    private Date _pickDate; 
    private Date _returnDate;

    //finals
    private final int RENT_PRICE_TYPE_A = 100;
    private final int RENT_PRICE_TYPE_B = 150;
    private final int RENT_PRICE_TYPE_C = 180;
    private final int RENT_PRICE_TYPE_D = 240;
    private final double DISCOUNT_FOR_7_DAYS = 0.9; // Multiply the price of 7 days with this final
    private final int WEEK = 7;

    // Constractors
    /**
     * Creates a new Rent object
     * The return date must be at least one day after the pickup date, otherwise set it to one day after the pick up date.
     * @param name - the client's name
     * @param car - the rented car
     * @param pick - the pickup date
     * @param ret - the return date
     */
    public Rent (String name, Car car, Date pick, Date ret)
    {
        _name = name;
        _car = new Car(car);
        _pickDate = new Date(pick);
        _returnDate = new Date(ret);

        // Check that the period of the rent time at least 1 day. if not, set the return to tomorrow.
        if (!((_pickDate.difference(_returnDate)) > 0 && (_pickDate.before(_returnDate))))
        {
            _returnDate = _pickDate.tomorrow();
        }
    }

    /**
     * Copy constructor
     * @param other - the rent to be copied
     */
    public Rent (Rent other)
    {
        _name = other._name;
        _car = new Car(other._car);
        _pickDate = new Date(other._pickDate);
        _returnDate = new Date(other._returnDate);
    }

    //Getters
    /**
     * Gets the car
     * @return the car
     */
    public Car getCar()
    {
        return new Car(_car);   
    }

    /**
     * Gets the name
     * @return the name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Gets the pick up date
     * @return the pick up date
     */
    public Date getPickDate()
    {
        return new Date(_pickDate);
    }

    /**
     * Returns the rent total price
     * @return the return date
     */
    public int getPrice()
    {
        int rentWeeksCounter = howManyDays() / 7; // Check number of weeks to calculate the discount
        int rentDaysCounter = howManyDays() % 7; // Check number of days (Value between 1-6, if 7 it counts as a week
        int price = 0;
        
        if (_car.getType() == 'A')
        {
            price = rentDaysCounter * RENT_PRICE_TYPE_A;
            
            if (rentWeeksCounter >=1)
            {
                // the formula is the number of week, mulitply 7 days to get the days value of a week, 
                // multiply 0.9 which mean 10% discount, multiply the price
                price += (rentWeeksCounter * WEEK * DISCOUNT_FOR_7_DAYS * RENT_PRICE_TYPE_A);
            }
            
            return price;
        }
        
        else if (_car.getType() == 'B')
        {
            price = rentDaysCounter * RENT_PRICE_TYPE_B;
            
            if (rentWeeksCounter >=1)
            {
                price += (rentWeeksCounter * WEEK * DISCOUNT_FOR_7_DAYS * RENT_PRICE_TYPE_B);
            }
            
            return price;
        }
        
        else if (_car.getType() == 'C')
        {
            price = rentDaysCounter * RENT_PRICE_TYPE_C;
            
            if (rentWeeksCounter >=1)
            {
                price += (rentWeeksCounter * WEEK * DISCOUNT_FOR_7_DAYS * RENT_PRICE_TYPE_C);
            }
            
            return price;
        }
        
        else
        {
            price = rentDaysCounter * RENT_PRICE_TYPE_D;
            
            if (rentWeeksCounter >=1)
            {
                price += (rentWeeksCounter * WEEK * DISCOUNT_FOR_7_DAYS * RENT_PRICE_TYPE_D);
            }
            
            return price;
        }
    }

    /**
     * Gets the return date
     * @return the return date
     */
    public Date getReturnDate()
    {
        return new Date(_returnDate);
    }

    //Setters
    /**
     * Sets the rented car
     * @param car - the rented car (You can assume that car is not null)
     */
    public void setCar(Car car)
    {
        _car = new Car(car);
    }

    /**
     * Sets the client name
     * @param name - the client name (You can assume that the name is a valid name)
     */
    public void setName(String name)
    {
        _name = name;
    }

    /**
     * Sets the pick up date
     * The pick up date must be at least one day before the return date, otherwise - don't change the pick up date
     * @param pickDate - the pick up date (You can assume that pick up date is not null)
     */
    public void setPickDate(Date pickDate)
    {
        _pickDate = new Date(pickDate);
    }

    /**
     * Sets the return date
     * The return date must be at least one day after the pick up date, otherwise - don't change the return date
     * @param returnDate - the return date (You can assume that return date is not null)
     */
    public void setReturnDate(Date returnDate)
    {
        _returnDate = new Date(returnDate);
    }

    //Other Methods
    /**
     * Check if 2 rents are the same
     * @param other - the rent to compare this rent to
     * @return true if the rents are the same
     */
    public boolean equals(Rent other)
    {
        if((_name.equals(other._name)) && (_car.equals(other._car)) && (_pickDate.equals(other._pickDate)) && (_returnDate.equals(other._returnDate)))
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    /**
     * Returns the number of rent days
     * @return the number of rent days
     */
    public int howManyDays()
    {
        return (_pickDate.difference(_returnDate));
    }

    /**
     * Try to upgrade the car to a better car
     * If the given car is better than the current car of the rent, upgrade it and return the upgrade additional cost, otherwise - don't upgrade
     * @param car - the car to upgrade to
     * @return the upgrade cost
     */
    public int upgrade (Car newCar)
    {
        int priceBeforeUpgrade = getPrice();
        int priceAfterUpgrade = 0;
        
        if (newCar.better(_car))
        {
            _car = new Car(newCar);
            priceAfterUpgrade = getPrice();
            return (priceAfterUpgrade - priceBeforeUpgrade); // Calculate how much to add
        }
        
        else
        {
            return 0;
        }
    }

    /**
     * Check if there is a double listing of a rent for the same person and car with an overlap in the rental days
     * If there is - return a new rent object with the unified dates, otherwise - return null
     * @param other - the other rent
     * @return the unified rent or null
     */
    public Rent overlap (Rent other)
    {
        if ((_name.equals(other._name)) && (_car.equals(other._car)))
        {
            if ((_returnDate.before(other._pickDate))) // If there is no overlap
            {
                return null;
            }
            
            else if ((_pickDate.after(other._returnDate))) // If there is no overlap
            {
                return null;
            }
            
            else // If there is an overlap
            {
                if (_pickDate.after(other._pickDate))
                {
                    _pickDate = new Date(other._pickDate);
                }
                
                if (_returnDate.before(other._returnDate))
                {
                    _returnDate = new Date(other._returnDate);
                }
                
                return new Rent(_name, _car, _pickDate, _returnDate);
            }
        }
        
        else
        {
            return null;
        }
    }

    /**
     * Returns a String that represents this rent
     * @override toString in class java.lang.Object
     * @return String that represents this rent in the following format:
     * @return Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString()
    {
        
        return ("Name:" + _name + " From:" + _pickDate + " To:" + _returnDate + 
               " Type:" + _car.getType() + " Days:" + howManyDays() + " Price:" + getPrice());
    }
}
