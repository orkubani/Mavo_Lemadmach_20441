
/**
 * This class represents a Car object
 *
 * @version (2023a)
 * @author Or Kubani
 */
public class Car
{
      //Instance variables  
      private int _id;
      private char _type;
      private String _brand;
      private boolean _isManual;
      
      //Finals
      private final int ID_LENGTH = 7;
      private final int MIN_ID_VALUE = 1000000;
      private final int DEFAULT_ID = 9999999;
      private final char TYPE_A = 'A';
      private final char TYPE_B = 'B';
      private final char TYPE_C = 'C';
      private final char TYPE_D = 'D';
      
      //Constractors
      
      /**
       * Creates a new Car object
       * id should be a 7 digits number, otherwise set it to 9999999 
       * type should be 'A','B','C' or 'D', otherwise set it to 'A'
       * @param id - the id of the car (7 digits number)
       * @param type - the type of the car ('A','B','C' or 'D')
       * @param brand - the car's brand
       * @param isManual - flag indicating if the car is manual
       */   
      public Car(int id, char type, String brand, boolean isManual)
      {
          _id = DEFAULT_ID; 
          _type = TYPE_A; // Default value
          _brand = brand;
          _isManual = isManual;
          
          if (id >= MIN_ID_VALUE && String.valueOf(id).length() == ID_LENGTH) // We need to check the id as a string to find the length.  
          {
              _id = id;    
          }
          
          if (type == TYPE_A || type == TYPE_B || type == TYPE_C || type == TYPE_D)
          {
              _type = type;
          }
          
      }
      
      /**
       * Copy constractor
       * @param other - the car to be copied
       */
      public Car(Car other)
      {
          _id = other._id;
          _type  = other._type;
          _brand = other._brand;
          _isManual = other._isManual;
          
      }
      
      //Getters 
      
      /**
       * Gets the id
       * @return the id
       */
      public int getId()
      {
          return _id;
      }
      
      /**
       * Gets the type
       * @return the type
       */
      public char getType()
      {
          return _type;
      }
      
      /**
       * Gets the brand
       * @return the brand
       */
      public String getBrand()
      {
          return _brand;
      }
      
      /**
       * Gets the isManual flag
       * @return the isManual flag
       */
      public boolean isManual()
      {
          return _isManual;
      }
      
      // Setters
      
      /**
       * Sets the id (only if the given id is valid)
       * @param id - the id value to be set
       */
      public void setId(int id)
      {
          if (id >= MIN_ID_VALUE && String.valueOf(id).length() == ID_LENGTH) // We need to check the id as a string to find the length.  
          {
              _id = id;    
          }
      }
      
      /**
       * Sets the type (only if the given type is valid)
       * @param type - the type value to be set
       */
      public void setType(char type)
      {
          if (type == TYPE_A || type == TYPE_B || type == TYPE_C || type == TYPE_D)
          {
              _type = type;
          }      
      }
      
      /**
       * Sets the brand of the car
       * @param brand - the brand value to be set
       */
      public void setBrand(String brand)
      {
          _brand = brand;
      }
      
      
      /**
       * Sets the isManual flag of the car
       * @param isManual - the isManual flag to be set
       */
      public void setIsManual(boolean manual)
      {
          _isManual = manual;
      }
      
      // Other methods
      
      /**
       * Returns a String object that represents this car
       * @override toString in class java.lang.Object
       * @return String that represents this car in the following format:
       * id:1234567 type:B brand:Toyota gear:manual (or auto)
       */
      public String toString()
      {
          String gearType = "auto"; // default value
          if (isManual())
          {
              gearType = "manual";
          }
          
          return ("id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + gearType);
      }
      
      /**
       * Check if two cars are the same
       * Cars are considered the same if they have the same type, brand and gear
       * @param other - the car to compare this car to other car
       * @return true if the cars are the same, otherwise false
       */
      public boolean equals (Car other)
      {
          if ((_type == other._type) && (_brand.equals(other._brand)) && (_isManual == other._isManual))
          {
              return true;
          }
          
          else
          {
              return false;
          }
      }
      
      /**
       * Check if this car is better than the other car
       * A car is considered better than another car if its type is higher.
       * If both cars have the same type, an automated car is better than a manual car.
       * 
       * @param other - car to compare this car to other car
       * @return true if this car is better than the other car, otherwise false
       */
      public boolean better (Car other)
      {
          if (_type > other._type) //  The smaller the letter, the higher the value
          {
              return true;
          }
          
          else if (_type == other._type)
          {
              if (_isManual == true && other._isManual == false)
              {
                  return true;
              }
              
              else
              {
                  return false;
              }
          }
          
          else
          {
              return false;
          }
      }
      
      /**
       * Check if this car is worse than the other car
       * @param other - car to compare this car to
       * @ return true if this car is worse than the other car, otherwise false
       */
      public boolean worse (Car other)
      {
          return !better(other);
      }
}
