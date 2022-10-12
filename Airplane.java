 /**
 * Warren Noubi
 * september 5, 2022
 * CSE 17  2022: ALA 2
 *
 * 
 * This class reserves/frees seats on a 9 by 8 grid.
 * It will also copy its grid onto a text file.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Airplane {
    private char[][] seatMap;

    /**
     * No arg constructor
     * Initializes all indexes in seatMap as '.'
     */
    public Airplane()
    {
        seatMap = new char[9][8];
        for(int i=0;i<9;i++)
            for(int j=0;j<8;j++)
                seatMap[i][j] = '.';
    }

    /**
     * Args constructor
     * Calls the no arg constructor to initializes all indexes in seatMap as '.'
     * Calls readMap(filename) to set elements in seatMap as '.' or 'X' based on
     * what is written in filename.
     * 
     * @param filename The name of the file to read/write(String)
     */
    public Airplane(String filename)
    {
        this();
        readMap(filename);
    }



    //Miscellaneous
    /**
     * Sets elements in seatMap as '.' or 'X' based on what is written in filename.
     * If filename does not exist, throws a FileNotFoundException and initializes
     * all indexes in seatMap as '.'
     * 
     * @param filename The name of the file to read/write(String)
     */
    private void readMap(String filename)
    {
        try{
            seatMap = new char[9][8];
            FileInputStream file = new FileInputStream(filename);
            Scanner scan = new Scanner(file);

            scan.nextLine();
            for(int i=0;i<9;i++)
            {
                scan.next();
                for(int j=0;j<8;j++)
                    seatMap[i][j] = scan.next().charAt(0);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println(filename + " is not found!");
            for(int i=0;i<9;i++)
                for(int j=0;j<8;j++)
                    seatMap[i][j] = '.';
        }
    }

    /**
     * Checks whether seatNumber is valid - within 1 to 9 and A to H.
     * Returns true if seatNumber is valid, otherwise it throws an
     * exception of type InvalidSeatException. The method should use
     * regular expressions to check the seat number.
     * 
     * @param seatNumber The seat number that will be determined whether
     * it is valid(String)
     * @return Returns true if seatNumber is valid
     */
    private boolean checkSeatNumber(String seatNumber)
    {
        try{
            //if(seatNumber.length() > 2 ||
            // !(seatNumber.charAt(0) > 48 && seatNumber.charAt(0) < 58) || //1-9
            // !(seatNumber.charAt(1) > 64 && seatNumber.charAt(1) < 73)) //a-h
            if(!seatNumber.matches("[1-9][A-H]"))
                throw new InvalidSeatException("Invalid seat number (row[1-9]column[A-H]). Please try again.");
        }
        catch(InvalidSeatException e)
        {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Reserves seats on the 9 by 8 grid. If a seat is '.' it will reserve
     * the seat. If it is 'X' it will not reserve the seat.
     * 
     * @param seatNumber The seat number to reserve(String)
     * @return Returns true if seatNumber is reserved successfully, false if
     * the seat is already occupied
     */
    public boolean reserveSeat(String seatNumber)
    {
        seatNumber.toUpperCase();
        if(checkSeatNumber(seatNumber))
        {
            if(seatMap[seatNumber.charAt(0) - 49][seatNumber.charAt(1) - 65] == '.')
            {
                seatMap[seatNumber.charAt(0) - 49][seatNumber.charAt(1) - 65] = 'X';
                System.out.println("Seat " + seatNumber + " successfully reserved.");
                return true;
            }
            else if(seatMap[seatNumber.charAt(0) - 49][seatNumber.charAt(1) - 65] == 'X')
                System.out.println("Seat " + seatNumber + " already reserved.");
        }
        
        return false;
    }

    /**
     * Frees seats on the 9 by 8 grid. If a seat is 'X' it will free
     * the seat. If it is '.' it will not free the seat.
     * 
     * @param seatNumber The seat number to free(String)
     * @return Returns true if seatNumber is freed successfully, false if
     * the seat is already free.
     */
    public boolean freeSeat(String seatNumber)
    {
        seatNumber.toUpperCase();
        if(checkSeatNumber(seatNumber))
        {
            if(seatMap[seatNumber.charAt(0) - 49][seatNumber.charAt(1) - 65] == 'X')
            {
                seatMap[seatNumber.charAt(0) - 49][seatNumber.charAt(1) - 65] = '.';
                System.out.println("Seat " + seatNumber + " successfully freed.");
                return true;
            }
            else if(seatMap[seatNumber.charAt(0) - 49][seatNumber.charAt(1) - 65] == '.')
                System.out.println("Seat " + seatNumber + " already freed.");
        }
        
        return false;
    }

    /**
     * Writes the contents of the array seatMap to the file
     * filename (one row per line)
     * 
     * @param filename The name of the file to write(String)
     */
    public void saveMap(String filename)
    {
        try{
            FileOutputStream file = new FileOutputStream(filename);
            PrintWriter output = new PrintWriter(filename);

            output.println(toString());
            output.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the content of the array seatMap in a formatted string
     */
    public String toString()
    {
        String res = "\tA\tB\tC\tD\tE\tF\tG\tH\n";

        for(int i=0;i<seatMap.length;i++)
        {
            res += i+1;
            for(int j=0;j<seatMap[0].length;j++)
                res += "\t" + seatMap[i][j];
            res += "\n";
        }
        return res;
    }
}
