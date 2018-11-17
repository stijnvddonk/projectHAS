/*
Lamp aan.
*/

// the setup function runs once when you press reset or power the board
void setup() {
  // initialize digital pin LED_BUILTIN as an output.
  pinMode(LED_BUILTIN, OUTPUT);
  // Om de lamp aan te zetten:
  digitalWrite(LED_BUILTIN, HIGH);
  // Om de lamp uit te zetten:
//  digitalWrite(LED_BUILTIN, LOW);
}

// the loop function runs over and over again forever
void loop() {}
