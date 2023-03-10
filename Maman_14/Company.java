
/**
 * Write a description of class Company here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Company
{
    private RentNode _head;

    // Constractors
    /**
     * Empty Constractor - Set the head to be null.
     */
    public Company ()
    {
        _head = null;
    }

    /**
     * Add a new Rent to the list according to the sorting rules - to the pickup date.
     * If the list is empty, the head will be the new Rent.
     * 
     * @param name - The name of the owner
     * @param car - the rented car
     * @param pickupDate - The pickup date of the rent
     * @param returnDate - The return date of the rent
     * 
     * @Return true - if the new Rent was added to the list or false if not.
     */
    public boolean addRent(String name, Car car, Date pickDate, Date returnDate)
    {
        Rent newRent = new Rent(name, car, pickDate, returnDate);
        RentNode newRentNode = new RentNode(newRent);

        // ---------- Head Checks --------------
        if (_head == null)
        {
            _head = newRentNode;
            return true;
        }

        // Check if the head is equal to the rent that want to add. if yes, return false and don't add it
        if (_head.getRent().equals(newRent) == true)
        {
            return false;
        }

        // Check if the pickup date of the new rent is the same as the head
        if (_head.getRent().getPickDate().equals(newRent.getPickDate()))
        {
            /// If there is a rent with the same pickup date - the rent with the longest period of time will be first between the two
            if (_head.getRent().howManyDays() > newRent.howManyDays()) 
            {
                RentNode temp = _head.getNext();
                _head.setNext(newRentNode);
                newRentNode.setNext(temp);
                return true;
            }

            else
            {
                RentNode temp = _head;
                _head = newRentNode;
                _head.setNext(temp);
                return true;
            }
        }

        // If the new rent according the rules is the last in the list, set it to be the head
        if (_head.getRent().getPickDate().after(newRent.getPickDate())) 
        {
            RentNode temp = _head;
            _head = newRentNode;
            _head.setNext(temp);
            return true;
        }

        // ------- Rest of Rents checks ----------

        //Helpers pointers for the while loop
        RentNode ptr1 = _head;
        RentNode ptr2 = _head.getNext();

        while (ptr2 != null)
        {
            // If the same Rent is already in the list, don't enter it again and return false.
            if (ptr2.getRent().equals(newRent) == true)
            {
                return false;
            }

            // If there is a rent with the same pickup date - the rent with the longest period of time will be first between the two
            if (ptr2.getRent().getPickDate().equals(newRent.getPickDate()))
            {
                if (ptr2.getRent().howManyDays() > newRent.howManyDays())
                {
                    newRentNode.setNext(ptr2.getNext());
                    ptr2.setNext(newRentNode);
                    return true;
                }

                else
                {
                    newRentNode.setNext(ptr2);
                    ptr1.setNext(newRentNode);
                    return true;
                }
            }

            // If the new rent between should be between ptr1 and ptr2
            if (ptr1.getRent().getPickDate().before(newRent.getPickDate()) && ptr2.getRent().getPickDate().after(newRent.getPickDate()))
            {
                newRentNode.setNext(ptr2);
                ptr1.setNext(newRentNode);
                return true;
            }

            // Continue with the rest of the rents
            ptr2 = ptr2.getNext();
            ptr1 = ptr1.getNext();
        }

        // If the new rent has the earliest pickup date, set it to the end of the list
        ptr1.setNext(newRentNode);
        return true;
    }

    /**
     * The method gets a date and if there is a Rent in the list with the same return date - remove it
     * Note that, if there are several rents with the same date - it removes the first one
     * 
     * @param d - the date to check for
     * @return - true if the remove was performed or false if wasn't.
     */
    public boolean removeRent(Date d)
    {
        // If the list is empty
        if (_head == null)
        {
            return false;
        }

        //If the Head is with the same date as the "d"'s date - remove the head, and set "d" to be the head 
        if (_head.getRent().getReturnDate().equals(d))
        {
            _head = _head.getNext();
            return true;
        }

        //Pointers for the while loop
        RentNode ptr1 = _head;
        RentNode ptr2 = _head.getNext();

        if (ptr2.getRent() != null)
        {
            while (ptr2 != null)
            {
                //Check from the second because the head was checked before the loop
                if (ptr2.getRent().getReturnDate().equals(d))
                {
                    ptr1.setNext(ptr2.getNext());
                    ptr2.setNext(null);
                    return true;
                }

                //Continue the check with the rest of the rents
                ptr2 = ptr2.getNext();
                ptr1 = ptr1.getNext();
            }
        }

        //If there is no Rent with the same Return Date - return "false"
        return false;
    }

    /**
     * Returns the number of rents in the list
     * 
     * @return Num of rents in the list
     */
    public int getNumOfRents()
    {
        int rentCounter = 0;

        if (_head == null)
        {
            return 0;
        }

        for(RentNode temp = _head; temp != null; temp = temp.getNext())
        {
            rentCounter += 1;
        }

        return rentCounter;
    }

    /**
     * Calculates and returns the sum of all rents' payments
     * 
     * @return - total payments from all rents
     */
    public int getSumOfPrices()
    {
        int sum = 0;

        if (_head == null)
        {
            return 0;
        }

        for(RentNode temp = _head; temp != null; temp = temp.getNext())
        {
            sum += temp.getRent().getPrice();
        }

        return sum;
    }

    /**
     * Calculates and returns all rents' days
     * 
     * @return - total days of all rents
     */
    public int getSumOfDays()
    {
        int sum = 0;

        if (_head == null)
        {
            return 0;
        }

        for(RentNode temp = _head; temp != null; temp = temp.getNext())
        {
            sum += temp.getRent().howManyDays();
        }

        return sum;
    }

    /**
     * Calculates and returns the average rents' days
     * 
     * @return - the average rents' days
     */
    public double averageRent()
    {
        double sum = 0;

        if (_head == null)
        {
            return 0;
        }

        for(RentNode temp = _head; temp != null; temp = temp.getNext())
        {
            sum += temp.getRent().howManyDays();
        }

        return sum / getNumOfRents();
    }

    /**
     * Retruns the car with the latest return date
     * @return Retruns the car with the latest return date from the list
     */
    public Car lastCarRent()
    {
        if (_head == null)
        {
            return null;
        }

        else
        {
            Rent rentWithLastReturnDay = _head.getRent();

            for(RentNode temp = _head.getNext(); temp != null; temp = temp.getNext()) // Default is head - check the loop from head.getNext()
            {
                if (temp.getRent().getReturnDate().after(rentWithLastReturnDay.getReturnDate()))
                {
                    rentWithLastReturnDay = temp.getRent();
                }
            }

            return rentWithLastReturnDay.getCar();
        }
    }

    /**
     * Return the rent with the longest rent's days
     * @return - the rent with the longest rent's days from the list
     */
    public Rent longestRent()
    {
        if (_head == null)
        {
            return null;
        }

        else
        {
            Rent rentWithLongestRentTime = _head.getRent();

            for(RentNode temp = _head.getNext(); temp != null; temp = temp.getNext()) // Default is head - check the loop from head.getNext()
            {
                if (temp.getRent().howManyDays() > rentWithLongestRentTime.howManyDays())
                {
                    rentWithLongestRentTime = temp.getRent();
                }
            }

            return rentWithLongestRentTime;
        }    
    }

    /**
     * Return the popular car's type from all rents
     * @return - the popular car's type from all rents
     */
    public char mostCommonRate()
    {
        int typeACounter = 0;
        int typeBCounter = 0;
        int typeCCounter = 0;
        int typeDCounter = 0;

        if (_head == null)
        {
            return 'N'; // Default if no rents
        }

        for(RentNode temp = _head; temp != null; temp = temp.getNext()) // Count all types
        {
            if (temp.getRent().getCar().getType() == 'A')
                typeACounter += 1;

            if (temp.getRent().getCar().getType() == 'B')
                typeBCounter += 1;

            if (temp.getRent().getCar().getType() == 'C')
                typeCCounter += 1;

            if (temp.getRent().getCar().getType() == 'D')
                typeDCounter += 1;    
        }

        // Return the most common rate (type).
        // If 2 Types (Counters) are equal - return the higher

        if(typeDCounter >= typeCCounter && typeDCounter >= typeCCounter && typeDCounter >= typeACounter)
        {
            return 'D';
        }

        if(typeCCounter >= typeBCounter && typeCCounter >= typeACounter)
        {
            return 'C';
        }

        if(typeBCounter >= typeACounter)
        {
            return 'B';
        }

        else
        {
            return 'A';
        }
    }

    /**
     * Check if all the rents of the "other" company are in the current company
     * @return true - if all the rents of the other company are exist in the current company, and true if not.
     */
    public boolean includes(Company other)
    {   
        if (other._head == null)
        {
            return true; // Default if the other list is empty
        }

        if (_head == null)
        {
            return false; // If the current list is empty but the other not empty
        }
        
        // Check for every car from the other company if it exists in the current company.
        for(RentNode tempOther = other._head; tempOther != null; tempOther = tempOther.getNext())
        {
            boolean rentIncludesInBothCompanies = false; // Start the flag as false.
            
            for(RentNode temp = _head; temp != null; temp = temp.getNext())
            {
                if (tempOther.getRent().equals(temp.getRent()))
                {
                    rentIncludesInBothCompanies = true; // If the car from the othe company was found in the current company, change flag to be true
                    break; // If the car was found, head over to check the next other company's car.
                }
            }
            
            // If this condition is true, it means that the current car from the other company
            // isn't exist in the current company - return false, not all the cars from the other cars are in the current.
            if (rentIncludesInBothCompanies == false) 
            {
                return false;
            }
        }
        
        return true; // If all cars are exist, return true
    }

    /**
     * Merge two companies together
     */
    public void merge(Company other)
    {
        // Loop through every rent from the other company and insert the relevant parm to the addRent(). All the rules E.G "aviod duplications" will be checked
        // as part of the method.
        for(RentNode tempOther = other._head; tempOther != null; tempOther = tempOther.getNext())
        {
            addRent(tempOther.getRent().getName(),
                    tempOther.getRent().getCar(),
                    tempOther.getRent().getPickDate(),
                    tempOther.getRent().getReturnDate());
        }
    }

    /**
     * Retrun all rents and their details.
     * If the list is empty - return this information
     * 
     * @return the number of rents and the details about them
     */
    public String toString()
    {
        String str = "";

        for(RentNode temp = _head; temp != null; temp = temp.getNext())
        {
            str += (temp.getRent().toString() + "\n");
        }

        if (!str.equals(""))
            return ("The company has " + getNumOfRents() + " rents:\n" + str);

        else
            return("The company has 0 rents.");
    }
}
