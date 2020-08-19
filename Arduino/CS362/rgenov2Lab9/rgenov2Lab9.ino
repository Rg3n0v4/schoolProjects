// Raphael Genova - 667495961
// Lab 9 - Graphing sensor data on a PC
// Description - Connect two analog devices to the arduino and plot the data received on the computer using processing.
//               Data could be displayed in two separate graphs  or both on one graph .
// Assumption - should look like live readings of sensors that are out of reach
// References - looked up on the Internet and came across the Ardunio discussion boards about it, 
//              Arduino Reference sheet on. Main reference for graphing: https://www.instructables.com/id/Double-Sensor-Graphs-With-Processing/
void setup() {
  // initialize the serial communication: (this is more for me to see what's being printed and read onto processing
  Serial.begin(9600);
}

void loop() {
  // send the value of analog input A0 (photoresistor) and A1 (potentiometer):
  Serial.print(analogRead(A0));
  Serial.print("|");
  Serial.print(analogRead(A1));
  Serial.println("");
  // reading:
  delay(2);
}
