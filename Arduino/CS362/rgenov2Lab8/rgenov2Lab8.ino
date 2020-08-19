// Raphael Genova - 667495961
// Lab 8 - Interrupts
// Description - a program for the arduino that utilizes interrupts. When no button has been pressed 
//              your 16x2 display should say something like "We have been waiting for X seconds", where 
//              X will be updated as time goes on.
//              When the first button is pressed, the display should display “Interrupt received! Press button 2 to continue”
//              When the user presses button 2 the display goes back to displaying "We have been waiting for X seconds", and restarts the time a 0 seconds.
// Assumption - like a stopwatch
// References - looked up on the Internet and came across the Ardunio discussion boards about it, 
//              Arduino Reference sheet on

// include the library code:
#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(12, 11, 7, 6, 5, 4); //from the LiquidCrystalScroll arduino sample code

//constant variables
const int btn1Pin = 2; //right button
const int btn2Pin = 3; //left button

int counter = 0;
int currentState = 0; //default state

void setup() {
  lcd.begin(16, 2); //just get things set up
  lcd.setCursor(0,0); //sets it up for the first row to be written
  pinMode(btn1Pin, INPUT_PULLUP);//right button display interupt
  attachInterrupt(digitalPinToInterrupt(btn1Pin), btn1Funct, HIGH);
  pinMode(btn2Pin, INPUT_PULLUP);//left button display how long interupt was
  attachInterrupt(digitalPinToInterrupt(btn2Pin), btn2Funct, HIGH);
  Serial.begin(9600);
  
}

void loop() {
  if(currentState == 0)
  {
    lcd.clear();
    lcd.print("Total wait: ");
    lcd.setCursor(0,1);
    lcd.print(String(counter) + " seconds");
    ++counter;
    delay(1000);
  }
}

void btn1Funct()
{ 
  currentState = 1;
  lcd.clear(); //clears the LCD display
  lcd.print("Interrupt!");
  lcd.setCursor(0,1);
  lcd.print("Press left btn.");
}

void btn2Funct()
{
  currentState = 0;
  counter = 0;
//  lcd.clear(); //clears the LCD display
//  lcd.print("BUTTON 2");
//  lcd.setCursor(0,1);
//  lcd.print("Aloha");
}
