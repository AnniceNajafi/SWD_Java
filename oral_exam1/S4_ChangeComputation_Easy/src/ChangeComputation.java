/**
@Author: Annice Najafi
Program Name: S4_ChangeComputation
ECE:3330
Fall 2019
*/

//scanner class is used in order to get an input from the user.
import java.util.Scanner;

public class ChangeComputation {
    public static void main(String args[]){
        //
    Scanner itemPriceSc = new Scanner(System.in);
    System.out.println("Please enter the price of the item");
    Double itemPrice = itemPriceSc.nextDouble();
    System.out.println("item price is :"+itemPrice);
    /////
     Scanner customerPaidSc = new Scanner(System.in);
     System.out.println("Please enter the amount the customer paid in the following format: 20 dollar bills 10 dollar bills \n" +
             "5 dollar bills 1 dollar bill Quarters Dimes Nickels Pennies");
     double customerPaidTwentyBills = customerPaidSc.nextDouble();
     double customerPaidTenBills = customerPaidSc.nextDouble();
     double customerPaidFiveBills = customerPaidSc.nextDouble();
     double customerPaidOneBills = customerPaidSc.nextDouble();
     double customerPaidQuarters = customerPaidSc.nextDouble();
     double customerPaidDimes = customerPaidSc.nextDouble();
     double customerPaidNickels = customerPaidSc.nextDouble();
     double customerPaidPennies = customerPaidSc.nextDouble();
     double sumPaid = customerPaidTwentyBills*20 + customerPaidTenBills*10 + customerPaidFiveBills*5 + customerPaidOneBills +
             customerPaidQuarters*0.25 + customerPaidDimes*0.1 + customerPaidNickels*0.05 +customerPaidPennies*0.01;
     System.out.println("The amount of money the customer paid is equal to "+ customerPaidTwentyBills+" Twenty dollar bills\n"
             +customerPaidTenBills+ " Ten dollar bills "+ customerPaidFiveBills+" Five dollar bills"+customerPaidOneBills+"\n" +
             " One dollar bills"+customerPaidQuarters+" Quarters"+customerPaidDimes+" Dimes"+customerPaidNickels+" Nickels\n" +
             customerPaidPennies+" Pennies which is equal to: " +sumPaid);
     change(sumPaid, itemPrice);


    }
    ///Function name: change
    ///function: prints the amount of change in dollars to the console
    ///input: double - amount of money paid in dollars/ price of the item in dollars
    ///output: void no output - amount of change in dollars is printed to the screen
    public static void change (double sumPaid, double itemPrice){
////////Calculate the change////////
        double crudeChange = sumPaid - itemPrice;
        if(crudeChange<0){
            System.out.println("Failure. The price of the item is more than the amount paid");

        }
        else{
            double change20 = Math.floor(crudeChange/20);
            double changeRem = crudeChange%20;
            double change10 = Math.floor(changeRem/10);
            double changeRem2 = changeRem%10;
            double change5 = Math.floor(changeRem2/5);
            double changeRem3 = changeRem2%5;
            double change1 = Math.floor((changeRem3*100)/100);
            double changeRem4 = changeRem3 - change1;
            double changeQuart = Math.floor((changeRem4*100)/25);
            double changeRem5 = changeRem4 - 0.25*changeQuart;
            double changeDime = Math.floor(changeRem5*10);
            double changeRem6 = changeRem5 - changeDime*0.1;
            double changePenny  = Math.floor(changeRem6 *100);
            System.out.println("The change is equal to "+ change20 +" Twenty dollar bills "+change10+ " Ten dollar bills "+ change5 +"\n" +
                    " Five dollar bills "+change1 + " One dollar bills "+changeQuart+" Quarters "+changeDime+" Dimes "+changePenny+" Pennies ");
            System.out.println("The equivalent amount of change in dollar is "+crudeChange);
        }

    }
}
