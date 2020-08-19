// Raphael Genova - 667495961
// Lab 3 - Scrolling Output
// Description - using the LCD screen to scoll through both the top row and bottom row with our name (top row) and favorite quote (bottom row)
// Assumption - it will look sort of like a billboard on the highway
// References - looked up on the Internet and came across the Ardunio discussion board about it, 
//              Arduino Reference sheet on the LiquidCrystalScroll, YouTube video to get an idea on how to get bottom row scroll

// include the library code:
#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(12, 11, 5, 4, 3, 2); //from the LiquidCrystalScroll arduino sample code

//for bottom row
//quote
String quote = "               Go Beyond Your Limit, Plus Ultra!             "; //needed extra padding so that it looks visually nicer especiallly for scrolling
//variables that change for the scrolling
int scrollNum = 16;
int scrollNum2 = 0;

void setup() {
  lcd.begin(16, 2); //just get things set up
  lcd.setCursor(0,0); //sets it up for the first row to be written
  lcd.print("Raphael Genova");
}

void loop() {
  lcd.setCursor(0,1); //moves the cursor to be the beginning of the bottom row
  lcd.print(quote.substring(scrollNum, scrollNum2));//prints out the quote 

  if(scrollNum > quote.length())
  //it resets the scroll numbers
  {
    scrollNum = 16;
    scrollNum2 = 0;
  }
  else
  {
    //"moves" the quote accordingly as the scroll goes through
    scrollNum++;
    scrollNum2++;
  }
  delay(450); //so you're able to see the quote a little smoothly
}
