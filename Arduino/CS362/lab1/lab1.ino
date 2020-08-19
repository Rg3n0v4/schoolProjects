// Raphael Genova - 667495961
// Lab 1 - Getting to know your Arduino
// Description -  Have the 3 LEDS blink take turns turning on and off
// 
// References - looked up on the Internet and came across the Ardunio discussion board about it

// Variables will change:
unsigned long previousMillis = 0;        // will store last time LED was updated

// the follow variables is a long because the time, measured in miliseconds,
// will quickly become a bigger number than can be stored in an int.
unsigned long interval = 1000;           // interval at which to blink (milliseconds)

void setup() {
  // put your setup code here, to run once:
  // initialize digital pin LED_BUILTIN as an output.
  pinMode(9, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(3, OUTPUT);
}

void loop() {
  unsigned long currentMillis = millis();
 
  if(currentMillis - previousMillis > interval) 
  {
    if(currentMillis - previousMillis < interval *2)
    {
      digitalWrite(9, HIGH);
    }
    if(currentMillis - previousMillis >= interval *2 && currentMillis - previousMillis < interval *3) 
    {
      digitalWrite(9, LOW);
      digitalWrite(6, HIGH);
    }
    if(currentMillis - previousMillis >= interval *3 && currentMillis - previousMillis < interval *4) 
    {
      digitalWrite(6, LOW);
      digitalWrite(3, HIGH); 
    }
    if(currentMillis - previousMillis >= interval *4)
    {
      digitalWrite(3, LOW);
      previousMillis = currentMillis;  
    }
  }
}
