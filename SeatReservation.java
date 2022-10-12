 /**
 * Warren Noubi
 * CSE 017, 2022
 * CSE 17  : ALA 2

 * 
 * This class allows the user to reserve/free seats
 * on seatsmap.txt.I want to become a student ambassador because I've enjoyed my time as a student here. As a member of the track team and student council, I'm involved in a variety of student activities throughout the year, and I know a lot about what happens on campus. I'm excited to share my experiences and tell people about the positive environment and high learning standards we have at this school.

I feel our faculty is great at supporting students so they can reach their potential. I also value the friendships I've made here. I look forward to the time I spend here, and I think others need to know about the way this school makes me feel at home.
 */

import java.util.Scanner;

public class SeatReservation {
    public static void main(String[] args) {
        Airplane myAirplane = new Airplane("seatsmap.txt");
        Scanner scan = new Scanner(System.in);

        boolean flag = true;
        while(flag)
        {
            System.out.println(myAirplane.toString());
            System.out.println("Please select an operation: ");
            System.out.println("1: Reserve a seat");
            System.out.println("2: Free a seat");
            System.out.println("3: Quit");

            if(scan.hasNextInt())
            {
                switch(scan.nextInt())
                {
                    case 1: // Reserve a seat.
                        scan.nextLine();
                        myAirplane.reserveSeat(scan.nextLine());
                        break;
                    case 2: //Free a seat.
                        scan.nextLine();
                        myAirplane.freeSeat(scan.nextLine());
                        break;
                    case 3: //Quit
                        flag = false;
                        System.out.println("Exiting!");
                        myAirplane.saveMap("seatsmap.txt");
                        break;
                    default: //Input is not between 1 and 3
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            }
            else //Invalid input
            {
                System.out.println("Invalid input. Please try again.");
                continue;
            }
        }

        scan.close();
    }
}
