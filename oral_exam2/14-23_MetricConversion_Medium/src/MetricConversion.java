import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * @author Annice Najafi
 * SWD-fall 2019
 * write an application that will assist the user with metric conversions. Your application should allow the user to specify
 * the names of the units as strings (i.e., centimeters, liters, grams, and so on, for the metric system and inches,
 * quarts, pounds, and so on, for the English system) and should respond to simple questions, such as
 * "How many inches are in 2 meters?"
 * Your application should recognize invalid conversions. For example, the question "how many feet are in 5 kilograms"
 * is not meaningful
 */

public class MetricConversion {
    /**
     * instance variable frame is a JFrame to include textfields for requests to be typed and
     * JLabel to show the result
     */
    JFrame frame;

    /**
     * This function asks the user to indicate what is their requested conversion, then it receives the request as a String
     */
    public void testProgram(){
        System.out.println("Please Enter your desired conversion in this format <<5 meters to inches>>"+
                "\n units should be entered in plural form and all lower case " +
                "\n all units should be written in the format below" +
                "\n pounds, kilograms, grams, tonnes, ounces, inches"+
                "\n feet, yards, miles, centimeters, meters, kilometers"+
                "\n square inches, square miles, square yards, square feet" +
                "\n degrees fahrenheit, degrees celsius");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        String unitFrom;
        if(command.split(" ")[1].equals("square") || command.split(" ")[1].equals("degrees")) {
            unitFrom = command.split(" ")[1] + " " + command.split(" ")[2];

        }else{
            unitFrom = command.split(" ")[1];
        }

        if(unitFrom.equals("pounds") || unitFrom.equals("ounces") || unitFrom.equals("inches") || unitFrom.equals("feet")
                || unitFrom.equals("yards") || unitFrom.equals("miles")){

            System.out.println(basicEnglishToMetric(command));
        }
        else if(unitFrom.equals("square inches") || unitFrom.equals("square feet")
                || unitFrom.equals("square yards") || unitFrom.equals("square miles")){
            System.out.println(advEnglishAreaToMetric(command));
        }
        else if(unitFrom.equals("square meters") || unitFrom.equals("square centimeters")
                || unitFrom.equals("square kilometers") || unitFrom.equals("square millimeters")){
            System.out.println(advMetricAreaToEnglish(command));
        }
        else if(unitFrom.equals("meters") || unitFrom =="centimeters" || unitFrom =="kilometers" || unitFrom =="kilograms"
                || unitFrom =="grams" || unitFrom =="tonnes"){

        }
        else if(unitFrom.equals("degrees fahrenheit") || unitFrom.equals("degrees celsius")){
            System.out.println(tempConverter(command));
        }
    }

    /**
     * makes a frame and adds a textfield to the frame for the request to be typed then it will print the result of
     * the conversion to a JLabel. The typed request will be passed to the appropriate function based on the command
     */
    public void runProgram(){
        ///Make a frame
        frame = new JFrame("Unit converter");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ///Set the size of the frame
        frame.setSize(640, 480);
        ///Set the color of the frame
        frame.setBackground(Color.ORANGE);
        ///Add a textfield
        JTextField convCommand = new JTextField("Enter command here");
        ///Add a label to show instructions
        JLabel instructions = new JLabel("Please Enter your desired conversion in this format <<5 meters to inches>>");
        ///Add an ActionListener to the textfield
        convCommand.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent E){
                ///receive the text from the textfield
                String command = convCommand.getText();
                String unitFrom;
                double res=0;
                ///if the command is requesting temperature conversion or area conversion
                ///the string will be parsed in a different way than it would be if it is a simple metric to
                ///English or other way for length, mass or etc
                if(command.split(" ")[1].equals("square") || command.split(" ")[1].equals("degrees")) {
                    unitFrom = command.split(" ")[1] + " " + command.split(" ")[2];

                }else{
                    unitFrom = command.split(" ")[1];
                }
                ///give the command to the related function
                if(unitFrom.equals("pounds") || unitFrom.equals("ounces") || unitFrom.equals("inches") || unitFrom.equals("feet")
                        || unitFrom.equals("yards") || unitFrom.equals("miles")){
                    res = basicEnglishToMetric(command);
                    System.out.println(basicEnglishToMetric(command));

                }
                else if(unitFrom.equals("square inches") || unitFrom.equals("square feet")
                        || unitFrom.equals("square yards") || unitFrom.equals("square miles")){
                    res = advEnglishAreaToMetric(command);
                    System.out.println(advEnglishAreaToMetric(command));
                }
                else if(unitFrom.equals("square meters") || unitFrom.equals("square centimeters")
                        || unitFrom.equals("square kilometers") || unitFrom.equals("square millimeters")){
                    res = advMetricAreaToEnglish(command);
                    System.out.println(advMetricAreaToEnglish(command));
                }
                else if(unitFrom.equals("meters") || unitFrom.equals("centimeters") || unitFrom.equals("kilometers") || unitFrom.equals("kilograms")
                        || unitFrom.equals("grams") || unitFrom.equals("tonnes")){
                    res = basicMetricToEnglish(command);
                    System.out.println(basicMetricToEnglish(command));
                }
                else if(unitFrom.equals("degrees fahrenheit") || unitFrom.equals("degrees celsius")){
                    res = tempConverter(command);
                    System.out.println(tempConverter(command));
                }
                double hold =  Double.parseDouble(command.split(" ")[0]);
                if(hold!=0 && res ==0 && !unitFrom.equals("degrees celsius") || !unitFrom.equals("degrees fahrenheit")){
                    instructions.setText("invalid conversion");
                }else {
                    String result = res + " ";
                    instructions.setText(result);
                }
            }
        });
        frame.add(instructions);
        frame.add(convCommand);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }

    /**
     * this function can handle Metric to English conversion for mass and length
     * @param numWithUnits is the command handed to the function for example 5 meters to inches
     * @return the number of equivalent English units
     */
    public static double basicMetricToEnglish(String numWithUnits){
        double num = Double.parseDouble(numWithUnits.split(" ")[0]);
        String unitFrom = numWithUnits.split(" ")[1];
        String unitTo = numWithUnits.split(" ")[3];
        double result=0;
        ///MASS CONVERSION from milligram
        if(unitFrom.equals("milligrams")){
            if(unitTo.equals("pounds")){
                result = num * 0.00220461999989109/1000;

            }
            else if(unitTo.equals("ounces")){
                result = num * 0.035274/1000;
            }
            else{System.out.println("Error, invalid conversion!");
            }
        }
        ///MASS CONVERSION from kilograms
        if(unitFrom.equals("kilograms")){
            if(unitTo.equals("pounds")){
                result = num * 2.20462;
            }
            else if(unitTo.equals("ounces")){
                result = num * 35.274;
            }
            else{System.out.println("Error, invalid conversion!");
            }
        }
        ///MASS CONVERSION from grams
        if(unitFrom.equals("grams")){
            if(unitTo.equals("pounds")){
                result = num * 0.00220461999989109;
            }
            else if(unitTo.equals("ounces")){
                result = num * 0.035274;
            }
            else{System.out.println("Error, invalid conversion!");
                }
        }
        ///MASS CONVERSION from tonnes
        if(unitFrom.equals("tonnes")){
            if(unitTo.equals("pounds")){
                result = num * 2204.6249999751998985;
            }
            else if(unitTo.equals("ounces")){
                result = num * 35273.9999996032;
            }
            else{System.out.println("Error, invalid conversion!");
            }
        }
        ///LENGTH CONVERSION from meters
        if(unitFrom.equals("meters")){
            if(unitTo.equals("inches")){
                result = num * 39.3701;
            }
            else if(unitTo.equals("feet")){
                result = num * 3.280841666667;
            }
            else if(unitTo.equals("yards")){
                result = num * 1.09361;
            }
            else if(unitTo.equals("miles")){
                result = num * 0.00062137152777784086452;
            }
            else{System.out.println("Error, invalid conversion!");
            }
        }
        ///LENGTH CONVERSION from millimeters
        if(unitFrom.equals("millimeters")){
            if(unitTo.equals("inches")){
                result = num * 39.3701/1000;
            }
            else if(unitTo.equals("feet")){
                result = num * 3.280841666667/1000;
            }
            else if(unitTo.equals("yards")){
                result = num * 1.09361/1000;
            }
            else if(unitTo.equals("miles")){
                result = num * 0.00062137152777784086452/1000;
            }
            else{System.out.println("Error, invalid conversion!");
            }
        }
        ///LENGTH CONVERSION from kilometers
        if(unitFrom.equals("kilometers")){
            if(unitTo.equals("inches")){
                result = num * 39.3701*1000;
            }
            else if(unitTo.equals("feet")){
                result = num * 3.280841666667*1000;
            }
            else if(unitTo.equals("yards")){
                result = num * 1.09361*1000;
            }
            else if(unitTo.equals("miles")){
                result = num * 0.00062137152777784086452*1000;
            }
            else{System.out.println("Error, invalid conversion!");
          }
        }
        if(unitFrom.equals("centimeters")){
            if(unitTo.equals("inches")){
                result = num * 39.3701/100;
            }
            else if(unitTo.equals("feet")){
                result = num * 3.280841666667/100;
            }
            else if(unitTo.equals("yards")){
                result = num * 1.09361/100;
            }
            else if(unitTo.equals("miles")){
                result = num * 0.00062137152777784086452/100;
            }
            else{System.out.println("Error, invalid conversion!");
           }
        }

        return result;
    }
    /**
     * this function can handle English to Metric conversion for mass and length
     * @param numWithUnits is the command handed to the function for example 5 inches to meters
     * @return the number of equivalent metric units
     */
    public static double basicEnglishToMetric(String numWithUnits){
        double num = Double.parseDouble(numWithUnits.split(" ")[0]);
        String unitFrom = numWithUnits.split(" ")[1];
        String unitTo = numWithUnits.split(" ")[3];
        double result=0;

        ///MASS CONVERSION from POUND
        //sample question: How many kilograms is in 3 pounds
        if(unitFrom.equals("pounds")){
            if(unitTo.equals("kilograms")){
                result = num/2.205;
            }
            else if(unitTo.equals("grams")){
                result = num*453.592;
            }
            else if(unitTo.equals("tonnes")){
                result = num*2204.623;
            }
            else{System.out.println("Error, invalid conversion!");
            }
        }
        ///MASS CONVERSION from OUNCE
        if(unitFrom.equals("ounces")){
            if(unitTo.equals("kilograms")){
                result = num/35.274;
            }
            else if(unitTo.equals("grams")){
                result = num* 28.35;
            }
            else if(unitTo.equals("tonnes")){
                result = num/35273.962;
            }
            else{System.out.println("Error, invalid conversion!");
               }
        }
        ///LENGTH CONVERSION from INCH
        if(unitFrom.equals("inches")){
            if(unitTo.equals("centimeters")){
                result = num*2.54;
            }
            else if(unitTo.equals("meters")){
                result = num*0.0254;
            }
            else if(unitTo.equals("kilometers")){
                result = num*num*0.0000254;
            }
            else{System.out.println("Error, invalid conversion!");
            }
        }
        ///LENGTH CONVERSION from FOOT
        if(unitFrom.equals("feet")){
            if(unitTo.equals("centimeters")){
                result=num*30.48;
            }
            else if(unitTo.equals("meters")){
                result=num*0.3048;
            }
            else if(unitTo.equals("kilometers")){
                result = num*0.0003048;
            }else{System.out.println("Error, invalid conversion!");
               }
        }
        ///LENGTH CONVERSION from YARD
        if(unitFrom.equals("yards")){
            if(unitTo.equals("centimeters")){
                result=num*91.44;
            }
            else if(unitTo.equals("meters")){
                result=num*0.9144;
            }
            else if(unitTo.equals("kilometers")){
                result = num*0.0009144;
            }else{System.out.println("Error, invalid conversion!");
               }
        }
        ///LENGTH CONVERSION from MILE
        if(unitFrom.equals("miles")){
            if(unitTo.equals("centimeters")){
                result=num*160934;
            }
            else if(unitTo.equals("meters")){
                result=num*1609.34;
            }
            else if(unitTo.equals("kilometers")){
                result = num*1.60934;
            }else{System.out.println("Error, invalid conversion!");
             }
        }

    return result;
    }

    /**
     * This function can convert english area to Metric area, it receives a String and parses the string
     * finds the number and converts the requested unit, function is static because there is no need for an object
     * to be instantiated for the function to be used. function uses another function to convert
     * @param command is a String that includes information about the number and the initial unit and the unit we want
     * to convert to
     * @return double converted number
     */
    public static double advEnglishAreaToMetric(String command){
        String unitFrom = command.split(" ")[2];
        double num = Double.parseDouble(command.split(" ")[0]);
        if(!command.split(" ")[4].equals("square")) {
            System.out.println("invalid conversion");
            return 0;
        }
        String unitTo = command.split(" ")[5];
        double result = basicEnglishToMetric(num+" "+unitFrom+" to "+unitTo);
        result = result*basicEnglishToMetric(1+" "+unitFrom+" to "+unitTo);

        return result;
    }

    /**
     * This function can convert metric area to english area, it receives a String and parses the string
     * finds the number and converts the requested unit, function is static because there is no need for an object
     * to be instantiated for the function to be used. function uses another function to convert
     * @param command is a String that includes information about the number and the initial unit and the unit we want
     * to convert to
     * @return double converted number
     */
    public static double advMetricAreaToEnglish(String command){
        String unitFrom = command.split(" ")[2];
        double num = Double.parseDouble(command.split(" ")[0]);
        String unitTo = command.split(" ")[5];
        if(!command.split(" ")[4].equals("square")) {
            System.out.println("invalid conversion");
            return 0;
        }
        double result = basicMetricToEnglish(num+" "+unitFrom+" to "+unitTo);
        result = result*basicMetricToEnglish(1+" "+unitFrom+" to "+unitTo);

        return result;
    }

    /**
     * This function is intended to be used for temperature conversion if given temperature in fahrenheit
     * it converts it to celsius or the other way
     * @param command is a String that will be parsed and processed
     * @return double temperature in the other system
     */
    public static double tempConverter(String command){
        double result;
        String unitFrom = command.split(" ")[2];
        double num = Double.parseDouble(command.split(" ")[0]);
        String unitTo = command.split(" ")[5];
        if(!command.split(" ")[4].equals("degrees")) {
            System.out.println("invalid conversion");
            return 0;
        }
        if(unitTo.equals("fahrenheit")){
            result = num*9/5 + 32;
        }
        else if(unitTo.equals("celsius")){
            result = (num - 32) * 5/9;
        }
        else{
            result=0;
        }
        return result;
    }
}
