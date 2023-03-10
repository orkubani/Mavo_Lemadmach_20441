/**
 * The Triangle1 class prints calculation of triangle's sides, perimeter and area.
 * 
 * @ Or Kubani
 */

import java.util.Scanner;

public class Triangle1
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculates the area "
            + "and the perimeter of a given triangle. ");
        System.out.println ("Please enter the three lengths"
            + " of the triangle's sides");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        
        // Define tirangle's sides
        int sideA = a;
        int sideB = b;
        int sideC = c;
        
        // Calculate perimeter
        int trianglePerimeter = sideA + sideB + sideC;
        
        // Calculate area using Heron's formula 
        double s = (double)trianglePerimeter / 2; // s = half Perimeter
        double Area = Math.sqrt(s * (s-sideA) * (s-sideB) * (s-sideC));
        
        // print triangle's sides, perimeter and area
        System.out.println ("The lengths of the triangle sides are: " 
                + sideA + ", " + sideB + ", " + sideC + ".");
        System.out.println ("The perimeter of the triangle is: " + trianglePerimeter);
        System.out.println ("The area of the triangle is: " + Area);
    } // end of method main
} //end of class Triangle