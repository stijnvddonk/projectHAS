int lamp1 = 0;
int lamp2 = 1;
int lamp3 = 2;
int lamp4 = 3;
int lamp5 = 4;
int door1 = 5;
int door2 = 6;
int door3 = 7;
int door4 = 8;
int door5 = 9;
int temp = 10;
int moist = 11;

int nr;




void setup() {
Serial.begin(9600);
}

void loop() {
  if (Serial.available() >0)
  {
    nr = Serial.read();
    if (nr == "1") {
      LightOn(lamp1);
      ReadAnalog(lamp1);
    } else {
      LightOff(lamp1);
      ReadAnalog(lamp1);
    }
  }
}

void LightOn(int light){
  // set pin:
  pinMode(light, OUTPUT);
  // change status:
  digitalWrite(light, HIGH);
}

void LightOff(int light){
  // set pin:
  pinMode(light, OUTPUT);
  // change status:
  digitalWrite(light, LOW);
}

int ReadDigital(int digitalNr){
  pinMode(digitalNr, OUTPUT);
  return Serial.println(digitalRead(digitalNr));
}

int ReadAnalog(int pinNr){
  // Read the input on specified pin number:
  int valuePin = analogRead(pinNr);
  // Print the value:
  Serial.println(valuePin);
  return valuePin;
  // Delay for stability:
  delay(1);
}

void recvOneChar() {
 if (Serial.available() > 0) {
 receivedChar = Serial.read();
 newData = true;
 }
}

void showNewData() {
 if (newData == true) {
 Serial.print("This just in ... ");
 Serial.println(receivedChar);
 newData = false;
 }
}

void door(doorSensor){
  int state;
  pinMode(doorSensor, INPUT_PULLUP);
  state = digitalRead(doorSensor);
  Serial.println(state);
}
