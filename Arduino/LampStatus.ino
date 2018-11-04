int led1 = 13;
int led2 = 8;

void setup()
{
    Serial.begin(9600);
    pinMode(led1, OUTPUT);
    pinMode(led2, OUTPUT);
}

void loop() 
{
    // Zet lamp 1 aan:
    digitalWrite(led1, HIGH);
    // Zet lamp 2 op de waarde die lamp 1 NIET heeft:
    digitalWrite(led2,  !digitalRead(led1));
    // Geeft aan wat de status is van lamp 1:
    Serial.print("De Rode lamp: ");
    Serial.println(digitalRead(led1));
    // Geeft aan wat de status is van lamp 2:
    Serial.print("De Groene lamp: ");
    Serial.println(digitalRead(led2));
    // Wacht een seconden:
    delay(1000);
    
    // Zet lamp 1 uit:
    digitalWrite(led1, LOW);
    // Zet lamp 2 op de waare die lamp 1 NIET heeft:
    digitalWrite(led2,  !digitalRead(led1));
    // Geeft aan wat de status is van lamp 1:
    Serial.print("De Rode lamp: ");
    Serial.println(digitalRead(led1));
    // Geeft aan wat de status is van lamp 2:
    Serial.print("De Groene lamp: ");
    Serial.println(digitalRead(led2));
    // Wacht een seconden:
    delay(1000);  
}