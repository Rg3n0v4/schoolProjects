// Raphael Genova - 667495961
// Lab 4 - More complicated input
// Description - using the LCD screen to display relevent information about a light sensitive photo resistor
// Assumption - it will act like a "proximity" sensor
// References - looked up on the Internet and came across the Ardunio discussion boards about it, 
//              Arduino Reference sheet on

// include the library code:
#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(12, 11, 5, 4, 3, 2); //from the LiquidCrystalScroll arduino sample code


//Constants
const int resistorVal = A0; // photoresistor at pin A0

//Variables
int value;          // Store value from photoresistor (0-1023)
String printOut; //prints out value that should be in the LCD screen
String oldPrint; //keeps old printOut so that it only prints out once on the LCD screen

void setup() {
  lcd.begin(16, 2); //just get things set up
  lcd.setCursor(0,0); //sets it up for the first row to be written
}

void loop() {
    value = analogRead(resistorVal); //for reading the photoresistor value

    if(value <= 370) //for if the photoresistor is reading it's dark
    {
      printOut = "Dark";
    }
    else if(value > 370 && value <= 500)//for if the photoresistor is reading it's partially dark
    {
      printOut = "Partially Dark";
    }
    else if(value > 500 && value <= 700)//for if the photoresistor is reading it's half-dark and half-lit
    {
      printOut = "Medium";
    }
    else if(value > 700 && value <= 831)//for if the photoresistor is reading it's partially lit
    {
      printOut = "Partially Lit";
    }
    else //for if the photoresistor is reading it's fully lit
    {
      printOut = "Fully Lit";
    }

    //to check if the new printOut is different than the oldPrint 
    if(printOut != oldPrint)
    {
      lcd.clear(); //clears the LCD display
      lcd.print(printOut); //prints out printOut on LCD display
    }
    
    oldPrint = printOut;//"saves" old printOut so that it doesn't continuously print printOut on the LCD display
}
