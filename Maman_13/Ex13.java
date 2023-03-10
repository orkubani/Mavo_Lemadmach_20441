/**
 * Maman 13
 *
 * @author Or Kubani
 */
public class Ex13
{
    // ------------------------------ Q1 start ----------------------------------------------------------------------------
    /**
     * The Time Complexity of this method is O(n).
     * First, we run for n chars to convert the string into an Array of chars.
     * Second, we run for n - 1 chars and check if every char located currectly or should be replaced.
     * Eventually we check the first index. so we run for 2n which means Time Complexity of O(n)
     * 
     * The Space Complexity of this method is O(n)
     * We create a new Array of n indexes - which means Space Complexity of O(n) 
     * 
     * @param s -  String of 2n bits ('0' or '1') that contain n times '0' and n times '1'.
     * @return The minimum required replacments to create an Alternating Sequence 
     */
    public static int alternating (String s)
    {
        char[] stringAsArray = new char[s.length()]; 

        int replaceCounter = 0; // Count each time a num changes his value

        for (int i = 0; i < s.length(); i++) // Convert the string into an Array of chars to easly iterate the indexes.
        {
            stringAsArray[i] = s.charAt(i);
        }

        /* 
         * This for loop checks if two close numbers (one after one) are equal.
         * If so and the number is '0' set it as '1' and if the number is '1' set it as '0'.
         * If two close numbers aren't equal (expected result) - leave there indexes the same and continue to the next index.
         */
        for (int i = 1 ; i < stringAsArray.length; i++) // The value of index - [0] will be checked after this for loop
        {   
            if ((i < stringAsArray.length - 1)  && (stringAsArray[i] == stringAsArray[i + 1]))
            {
                if (stringAsArray[i + 1] == '0')
                {
                    stringAsArray[i + 1] = '1';
                    replaceCounter += 1; // increase the index by one because replacement has been done 
                }

                else
                {
                    stringAsArray[i + 1] = '0';
                    replaceCounter += 1; // increase the index by one because replacement has been done 
                }
            }
        }

        // After we checked all chars in the string beside the first, we can look at the last char.

        if (stringAsArray[stringAsArray.length - 1] == '0') // If the last char is '0' we know for sure the first char is '1'
        {
            if (stringAsArray[0] != '1') // If the first char isn't '1', we need to perfom replacment
            {
                stringAsArray[0] = '1';
                replaceCounter += 1; // increase the index by one because replacement has been done 
            }
        }

        else // If the last char is '1' we know for sure the first char is '0'
        {
            if (stringAsArray[0] != '0') // If the first char isn't '0', we need to perfom replacment
            {
                stringAsArray[0] = '0';
                replaceCounter += 1; // increase the index by one because replacement has been done 
            }
        }

        /* 
         * We return the couner divided by 2 because the counter counts number of replacments.
         * Every number is replaced by another number which means - 2 replacments == two numbers that were swaped.
         */
        return replaceCounter / 2;
    }
    // ------------------------------ Q1 End ----------------------------------------------------------------------------

    // ------------------------------ Q2 start ----------------------------------------------------------------------------
    /**
     * A. This explanation is about the original "What" method (Before my changes)! 
     * The method "WHAT" receives an array "a" and returns the maximum amount of array's elements 
     * whose sum is equal to an even number.
     * 
     * B. This explanation is about the original "What" method (Before my changes)! 
     * The Time Complexity of "What" method is O(n^3) (Calculated by using the worst case and --> n = a.length)
     * The Place Complexity of "What" method is O(1).
     * 
     * D. This explanation is about the New "What" method (After my changes)! 
     * The Time Complexity of "What" method is O(n). 
     * We run once for all over the Array and the calcualtion was made with --> n = a.length.
     * The place Complexity of "What" method is O(1). 
     * We don't create a new Array, just get it. The creation isn't in the responsibility of the method.
     * 
     * @param a - Array of Whole numbers(positive, negative and zeros).
     * @return Maximum amount of array's elements whose sum is equal to an even number.
     */
    public static int what (int [] a)
    {
        int evenCounter = 0;
        int oddCounter = 0;

        for (int i = 0; i < a.length; i++)
        {
            if (a[i] % 2 == 0) // if the num is even, increase the eventCounter by 1.
            {
                evenCounter += 1;
            }

            else // if the number is odd, increase the oddCount by 1.
            {
                oddCounter += 1;
            }
        }

        /*
         * Here, we return the counter of the even numbers beacuse any combination of even numbers equals to event number.
         * We need to add the counter of odd numbers that the combination of them, keeps the result an even number.
         * 
         * We know that a combination of two odd numbers always return an even number.
         * We check here if the counter of the odd numbers is even or odd. 
         */

        if (oddCounter % 2 == 0)  
        {
            /*
             * If the counter is even, it means that the combination of all odd numbers equals to an even number.
             * In this senario we want to count all the odd numbers as elemnts that combains an even number.
             */
            return evenCounter + oddCounter;
        }

        else
        {
            /*
             * If the counter is odd, it means that the combination of all odd numbers equals to an odd number.
             * In this senario we want to count all the odd numbers minus one as elemnts that combains an even number.
             */
            return evenCounter + (oddCounter - 1);
        }
    }

    // ------------------------------ Q2 end ----------------------------------------------------------------------------

    // ------------------------------ Q3 start --------------------------------------------------------------------------
    /**
     * The regresive method "isWay" gets an Array of Whole numbers (positive only) and checks if There is a legal way
     * to get from the first index to the last index.
     * Legal way is a way to move Left / Right as long as the index exists in the Array.
     * Every step is accoridng to the value of the current index.
     * 
     * @param a - Array of Whole numbers (positive only).
     * @return - 'True' if there is a legal way or 'False' if there is no a legal way.
     */
    public static boolean isWay(int[] a)
    {
        // Create new array to mark the indexs that were checked
        int[] b = new int[a.length];

        // Call to an overloading method
        return isWay(a,b,0);
    }

    /**
     * This regresive Overloading method gets the required array, an array to mark the indexs that were checked 
     * and the current index to check for.
     * 
     * @param a - Array of Whole numbers (positive only).
     * @param b - Helper array - to mark the indexs that were checked 
     * @return - 'True' if there is a legal way or 'False' if there is no a legal way.
     */
    public static boolean isWay(int[] a, int[] b, int curIndex)
    {   
        if (a.length == 1) // stop condition
        {
            return true;
        }

        if (curIndex == a.length - 1) // stop condition 
        {
            return true;
        }

        if (curIndex < 0 || curIndex > a.length - 1) // stop condition
        {
            return false;
        }

        // Using helpers methods - check always right and left steps.
        return isWay(a,b, left(a,b, curIndex)) || isWay(a,b, right(a,b, curIndex));

    }

    /**
     * This helper method - Left, perform single step left if the step is legal.
     * In addition - marks the index as checked using an helper array.
     * 
     * @param a - Array to perform the left step on (positive only).
     * @param b - Helper array - to mark the indexs that were checked 
     * @return - The new index after the step or '-1' if the step is illegal.
     */
    private static int left(int[] a,int[] b, int curIndex)
    {
        curIndex -= a[curIndex];

        if (curIndex < 0 || curIndex > a.length - 1)
        {
            return -1; // If the step is illegal
        }

        if (b[curIndex] != -2) // Checks if the index was already checked
        {
            b[curIndex] = -2; // Mark the index as checked using an helper method.
            return curIndex;
        }

        return -1;
    }

    /**
     * This helper method - Right, perform single step Right if the step is legal.
     * In addition - marks the index as checked using an helper array.
     * 
     * @param a - Array to perform the right step on (positive only).
     * @param b - Helper array - to mark the indexs that were checked 
     * @return - The new index after the step or '-1' if the step is illegal.
     */
    private static int right(int[] a,int[] b, int curIndex)
    {
        curIndex += a[curIndex];

        if (curIndex < 0 || curIndex > a.length - 1)
        {
            return -1; // If the step is illegal
        }

        if (b[curIndex] != -2) // Checks if the index was already checked 
        {
            b[curIndex] = -2; // Mark the index as checked using an helper method.
            return curIndex;
        }

        return -1;
    }
    // ------------------------------ Q3 end ----------------------------------------------------------------------------

    // ------------------------------ Q4 start -------------------------------------------------------------------------
    
    /**
     * The  regressive method - "prince" gets a "Digital Roof Map" and return the nummber of roofs that 
     * the prince needs to go through, until he catches the evil (Who is marked with -1)
     * 
     * @param "drm" - "Digital Roof Map"- Matrix
     * @param i - rows
     * @param j - columns
     * @return The Min Way to get the evil
     */
    public static int prince(int[][] drm, int i, int j)
    {
        return prince(drm,i,j,drm[i][j],0);
    }

    /**
     * Overloading regressive method of "prince" - Helps us to calculate the way using more parameters.
     * 
     * @param "drm" - "Digital Roof Map"- Matrix
     * @param i - rows
     * @param j - columns 
     * @param prev - the previous roof
     * @param stepCounter - - Step Counter
     * @return The Min Way to get the evil
     */
    public static int prince(int[][] drm, int i, int j, int prev, int stepCounter)
    {
        if(outOfBoundaries(drm, i, j)) // Invalid path
        {
            return -1;
        }

        if(drm[i][j] == -1) // Got the evil
        {
            return stepCounter + 1;
        }

        if (!isValidStep(drm, i, j, prev)) //Invalid step
        {
            return -1;
        }

        int numHold = drm[i][j]; // Keep the original value of the current roof
        drm[i][j] = -2; // Mark the roof 
        int min = findMin(prince(drm, i + 1, j, numHold, stepCounter + 1), 
                prince(drm, i - 1, j, numHold, stepCounter + 1), 
                prince(drm, i, j + 1, numHold, stepCounter + 1), 
                prince(drm, i, j - 1, numHold, stepCounter + 1));

        drm[i][j] = numHold; // Return the original value
        return min;
    }

    /**
     * Checks we are in the matrix indexes
     * @param "drm" - "Digital Roof Map"- Matrix
     * @param i - rows
     * @param j - columns 
     * @return 'True' - if Out Of Boundaries ; 'False' if In Boundaries
     */
    private static boolean outOfBoundaries(int[][] drm, int i, int j)
    {
        return (i < 0 || i >= drm.length || j < 0 || j >= drm[i].length);
    }

    /**
     * Check if the step is Legal.
     * Legal step is "1" floor rise at most or "2" floor drop at most.
     * 
     * @param "drm" - "Digital Roof Map"- Matrix
     * @param i - rows
     * @param j - columns 
     * @param prev - the previous roof
     * @return 'True' - if Legal step ; 'False' if Illegal step
     */
    private static boolean isValidStep(int[][] drm, int i, int j, int prev)
    {
        return (drm[i][j] != -2 && (drm[i][j] == prev + 1 || drm[i][j] == prev || drm[i][j] == prev - 2 || drm[i][j] == prev - 1));
    }

    /**
     * Find the Min way between two ways
     * Takes into account scenario of "No Way" (-1) and returns the relevant way.
     * 
     * @param w1 - First Way
     * @param w2 - Second Way
     * @return - Min Way or "-1" if there is no way.
     */
    private static int findMin(int w1, int w2)
    {
        if (w1 == -1 && w2 >= 0)
        {
            return w2;
        }

        if (w2 == -1 && w1 >= 0)
        {
            return w1;
        }

        if (w1 == -1 && w2 == -1)
        {
            return -1;
        }

        return Math.min(w1, w2);
    }
    
    /**
     * Using the "findMin(int w1, int w2)" method - chekcs 4 way in once
     * 
     * @param w1 - First Way
     * @param w2 - Second Way
     * @param w3 - Third way
     * @param w4 - Fourth way
     * @return - Min Way or "-1" if there is no way.
     */
    private static int findMin(int w1, int w2, int w3, int w4)
    {
        return findMin(findMin(w1,w2), findMin(w3,w4));
    }
    // ------------------------------ Q4 end ----------------------------------------------------------------------------
}
