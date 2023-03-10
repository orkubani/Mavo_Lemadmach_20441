/**
 * The Triangle2 class checks if input of three numbers could be 
 * triangle sides and print which kind of a triangle is it.
 * 
 * @ Or Kubani
 */

import java.util.Scanner;

public class Triangle2
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program check if 3 numbers are sides of a triangle.\n" 
        + "If so, the program checks the triangle's kind.");
        System.out.println ("Please enter the three lengths"
            + " of the triangle's sides");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        
        // Define tirangle's sides
        int sideA = a;
        int sideB = b;
        int sideC = c;
        
        /* calculate the squared value of the sides - will help to check 
         * if the triangle is right-angle using Pythagoras' theorem. */
        double squaredSideA = Math.pow(sideA, 2);
        double squaredSideB = Math.pow(sideB, 2);
        double squaredSideC = Math.pow(sideC, 2);
        
        if ((sideA > 0) && (sideB > 0) && (sideC > 0)) // Check that all numbers are natural.
        {
            if ((sideA == sideB) && (sideA == sideC))
            {
                System.out.println("The numbers: " + sideA + ", " + sideB + " and " + sideC 
                        + " represent an equilateral triangle");
            }
            
            else if (((sideA == sideB) && (sideA != sideC)) || ((sideB == sideC) && (sideB != sideA)) 
                        || ((sideA == sideC) && (sideA != sideB)))
            {
                System.out.println("The numbers: " + sideA + ", " + sideB + " and " + sideC 
                        + " represent an isosceles triangle");
            }
            
            // Calulate using Pythagoras' theorem 
            else if ((squaredSideA + squaredSideB == squaredSideC) || (squaredSideB + squaredSideC == squaredSideA)
                        || (squaredSideA + squaredSideC == squaredSideB))
            {
                System.out.println("The numbers: " + sideA + ", " + sideB + " and " + sideC 
                        + " represent a right-angle triangle");
            }
            
            else
            {
                System.out.println("The numbers: " + sideA + ", " + sideB + " and " + sideC 
                        + " represent a common triangle");
            }
        }
        
        else
        {
            System.out.println("The numbers: " + sideA + ", " + sideB + " and " + sideC 
                    + " cannot represent a triangle");
        }
    } // end of method main
} //end of class Triangle