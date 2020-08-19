// Raphael Genova - 667495961
// Lab 5 - Multiple Inputs and Outputs
// Description - be able to have my Arduino have two unconnected things at te same time, using multiple inputs 
//               and outputs. Also be able to read and write analog inputs and outputs
// Assumption - being able to adjust the volume on a tuner and checking the lighting in the room
// References - looked up on the Internet and came across the Ardunio discussion boards about it, 
//              Arduino Reference sheet on

int value; //for storing the value of the photoresistor

int sensorVal; //for storing the value of the potentiometer

int volume; //for the volume of the buzzer

//for storing the values of the LEDs
int led1 = LOW;
int led2 = LOW;
int led3 = LOW;
int led4 = LOW;

void setup() {
  
    pinMode(2, OUTPUT);
    pinMode(3, OUTPUT);
    pinMode(4, OUTPUT);
    pinMode(5, OUTPUT);

    pinMode(11, OUTPUT);
    pinMode(A1, INPUT);
    pinMode(A2, INPUT);

    //Serial.begin(9600);
}
void loop() {
  // put your main code here, to run repeatedly:

    value = analogRead(A2); //for reading the photoresistor value

    sensorVal = analogRead(A1); //for reading the potentiometer
    //Serial.println(sensorVal);

    volume = map(sensorVal, 0, 1023, 100, 2000);

    tone(11, volume); //analog output on a digital pin
    //delay(1);

    if(value <= 370) //for if the photoresistor is reading it's dark
    {
      led1 = HIGH;
      led2 = HIGH;
      led3 = HIGH;
      led4 = HIGH;
    }
    else if(value > 370 && value <= 500)//for if the photoresistor is reading it's partially dark
    {
      led1 = LOW;
      led2 = HIGH;
      led3 = HIGH;
      led4 = HIGH;
    }
    else if(value > 500 && value <= 700)//for if the photoresistor is reading it's half-dark and half-lit
    {
      led1 = LOW;
      led2 = LOW;
      led3 = HIGH;
      led4 = HIGH;
    }
    else if(value > 700 && value <= 831)//for if the photoresistor is reading it's partially lit
    {
      led1 = LOW;
      led2 = LOW;
      led3 = LOW;
      led4 = HIGH;
    }
    else //for if the photoresistor is reading it's fully lit
    {
      led1 = LOW;
      led2 = LOW;
      led3 = LOW;
      led4 = LOW;
    }

    //updates the LEDs accordingly
    digitalWrite(2, led1);
    digitalWrite(3, led2);
    digitalWrite(4, led3);
    digitalWrite(5, led4);

}
