void setup() {
  Serial.begin(9600);
  while(!Serial){
    ;
  }
}

void loop() {
  if (Serial.available() >0){
    byte incomingByte = Serial.read();
    if (incomingByte != -1){
      Serial.print("I received: ");
      Serial.println(incomingByte);
    }
  }
}
