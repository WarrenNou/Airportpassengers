 /**
 * Warren Noubi
 * September 05, 2022
 * CSE 17 : ALA 2

 * 
 * This class inherits the Java class 'Exception' and will
 * output an error based on its given argument on its
 * one-arg constructor.
 */

public class InvalidSeatException extends Exception{
    /**
     * No arg constructor
     */
    public InvalidSeatException(){}

    /**
     * One-arg constructor
     * Uses a method in its parent to output an error.
     * 
     * @param exception The exception to print(String)
     */
    public InvalidSeatException(String exception)
    {
        super(exception);
    }
}
