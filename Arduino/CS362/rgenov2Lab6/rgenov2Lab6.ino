// Raphael Genova - 667495961
// Lab 6 - Communication
// Description - being able to display date and time on LCD display based on user input
// Assumption - its going to be similar to a clock
// References - looked up on the Internet and came across the Ardunio discussion boards about it, 
//              Arduino Reference sheet on

// include the library code:
#include <LiquidCrystal.h>
#include <Time.h>
#include <TimeLib.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(12, 11, 7, 6, 5, 4); //from the LiquidCrystalScroll arduino sample code
time_t t = now();

int state = 0;

String timeOutput;
String hourNum;
String minuteNum;
String secondNum;

int p_secondNum;

String dateOutput;
String dayNum;
String monthNum;
String yearNum;

String o_timeOutput;
String o_dateOutput;

bool validDate = true; //always assume they have the correct date
bool validTime = true; //always assume they have the correct time
bool instDate = true; //always assume they have the correct date
bool instTime = false; //always assume they have the correct time

void readingDate(String s)
{
    monthNum = s.substring(0,2);
    dayNum = s.substring(3,5);
    yearNum = s.substring(6,10);

    Serial.print("Month: " + monthNum + "Day: " + dayNum + "Year:" + yearNum);
}


void readingTime(String s)
{
    hourNum = s.substring(11,13);
    minuteNum = s.substring(14,16);
    secondNum = s.substring(17,19);
    Serial.print("Hour: " + hourNum + "Min: " + minuteNum + "Second:" + secondNum);
}

void validateDate()
{
  //checks if the month and day entered in are valid
  if(monthNum == "1" && (dayNum >= "1" && dayNum <= "31") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "2" && (dayNum >= "1" && dayNum <="28") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "3" && (dayNum >= "1" && dayNum <="31") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "4" && (dayNum >= "1" && dayNum <="30") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "5" && (dayNum >= "1" && dayNum <="31") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "6" && (dayNum >= "1" && dayNum <="30") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "7" && (dayNum >= "1" && dayNum <="31") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "8" && (dayNum >= "1" && dayNum <="31") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "9" && (dayNum >= "1" && dayNum <="30") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "10" && (dayNum >= "1" && dayNum <="31") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "11" && (dayNum >= "1" && dayNum <="30") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else if(monthNum == "12" && (dayNum >= "1" && dayNum <="31") && (yearNum >= "1000" && yearNum <="99999"))
  {
    //dateOutput = monthNum + "/" + dayNum + "/" + yearNum;
    validDate = true;
  }
  else
  {
    validDate = false;
  }
}

void validateTime()
{
  if(hourNum >= "0" && hourNum <="23")
  {
    if(minuteNum >= "0" && minuteNum <= "59")
    {
      if(secondNum >= "0" && secondNum <= "59")
      {
         //timeOutput = hourNum + ":" + minuteNum + ":" + secondNum;
         //p_secondNum = secondNum.toInt();
         validTime = true;
      }
      else
      {
        validTime = false;
      }
    }
    else
    {
      validTime = false;
    }
  }
  else
  {
    validTime = false;
  }
}

void printOut(time_t t)
{
  lcd.clear();
  t = now();
    if(validDate == true)
    {
      String sdayNum = String(day(t));
      String smonthNum = String(month(t));
      String syearNum = String(year(t));
      dateOutput = "Date: " + smonthNum + "/" + sdayNum + "/" + syearNum;
    }
    else
    {
      dateOutput = "Date: Invalid";
    }
  
    
    if(validTime == true)
    { 
      String shourNum = String(hour(t));
      String sminuteNum = String(minute(t));
      String ssecondNum = String(second(t));
      //p_secondNum = second(t);
      timeOutput = "Time: " + shourNum + ":" + sminuteNum + ":" + ssecondNum;
    }
    else
    {
      timeOutput = "Time: Invalid";
    } 
    
  lcd.setCursor(0,0);
//    if(dateOutput != o_dateOutput)
//    {
      lcd.print(dateOutput);
//    }
    lcd.setCursor(0,1);
//    if(timeOutput != o_timeOutput)
//    {
      lcd.print(timeOutput);
//    }
//    
//    
//    o_timeOutput = timeOutput;
//    o_dateOutput = dateOutput;
   p_secondNum = secondNum.toInt();
   
   setTime(hourNum.toInt(), minuteNum.toInt(), p_secondNum, dayNum.toInt(), monthNum.toInt(), yearNum.toInt());
   p_secondNum++;
   secondNum = String(p_secondNum);
   
   delay(1000);
}

void setup() {
  lcd.begin(16, 2); //just get things set up
  lcd.setCursor(0,0); //sets it up for the first row to be written
  
  Serial.begin(9600);
  Serial.println("Please enter: mm/dd/yyyy hh:mm:ss; ");
}

void loop() {
  //t = now();
    printOut(t);
    
  while(Serial.available()) 
  {
    //Serial.println("Please enter: mm/dd/yyyy hh:mm:ss; ");
    String s = Serial.readStringUntil(';');
    Serial.println(s);
    readingDate(s); 
    readingTime(s); 
//    validateDate();
//    validateTime();
  
  }
  validateDate();
  validateTime();

}
