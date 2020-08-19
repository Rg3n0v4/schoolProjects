// Raphael Genova - 667495961
// Lab 2 - Input and Output
// Description - it is a binary counter using LEDs from 0 - 7
// Assumption - it will be like Blink but now with buttons that force it to count up and down in binary
// References - looked up on the Internet and came across the Ardunio discussion board about it, 
//              and Arduino Reference sheet on debounce and button reading

// Variables that will change:
int counter = 0; //for which number the counter is currently at
int f_ButtonState; //forward (right) button state
int r_ButtonState; //reverse (left) button state
unsigned long lastButtonPressTimeF = 0; //last time forward button was pressed
unsigned long lastButtonPressTimeR = 0; //last time reverse button was pressed
unsigned long debounceDelay = 100;

void setup() {
  // put your setup code here, to run once:
  // initialize digital pin LED_BUILTIN as an output.
  pinMode(9, OUTPUT);//red
  pinMode(6, OUTPUT);//yellow
  pinMode(3, OUTPUT);//green
  pinMode(11, OUTPUT);//right button forward
  pinMode(12, OUTPUT);//left button reverse

  Serial.begin(9600);
}

void loop() {
  int forwardButton = digitalRead(11);
  int reverseButton = digitalRead(12);

  //for if the forward button was pressed
  if (forwardButton != lastButtonPressTimeF) 
  {
    // reset the debouncing timer
    lastButtonPressTimeF = millis();
  }
  
  if ((millis() - lastButtonPressTimeF) > debounceDelay && counter < 7) 
  {
    // if the button state has changed:
    if (forwardButton != f_ButtonState) 
    {
      f_ButtonState = forwardButton; //if the state of the forward button has changed
      if(f_ButtonState == HIGH) 
      { //this will do the caluclations for the counter into binary
        counter++;
        digitalWrite( 9, counter % 8 > 3);
        digitalWrite( 6, counter % 4 > 1);
        digitalWrite( 3, counter % 2 ); //for dealing with if it's odd 
      }
    }
  }

  //saved for next time the forward button is pressed
  lastButtonPressTimeF = forwardButton;

  //for if the reverse button was pressed
  if (reverseButton != lastButtonPressTimeR) 
  {
    // reset the debouncing timer
    lastButtonPressTimeR = millis();
  }
  
  if ((millis() - lastButtonPressTimeR) > debounceDelay && counter > 0) 
  {
    // if the button state has changed:
    if (reverseButton != r_ButtonState) 
    {
      r_ButtonState = reverseButton;//if the state of the reverse button has changed
      if(r_ButtonState == HIGH) 
      { //this will do the caluclations for the counter into binary
        counter--;
        digitalWrite( 9, counter % 8 > 3);
        digitalWrite( 6, counter % 4 > 1);
        digitalWrite( 3, counter % 2 ); //for dealing with if it's odd 
      }
    }
  }

  //saved for next time the reverse button is pressed
  lastButtonPressTimeR = reverseButton;

  Serial.print("here is counter: ");
  Serial.println(counter);
  
}
