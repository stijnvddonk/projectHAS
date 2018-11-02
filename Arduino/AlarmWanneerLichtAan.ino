//set pin numbers

const int ledPin = 13;
const int buzzerPin = 12;
const int ldrPin = A0;

void setup() {
    //put the setup code here, to run once:
    Serial.begin(9600);
    
    pinMode(ledPin, OUTPUT);
    pinMode(buzzerPin, OUTPUT);
    pinMode(ldrPin, INPUT);
}

void loop(){
    //read the state of the LDR Value
    int ldrStatus = analogRead(ldrPin);

/* Let op dat de waarde (nu 10) afhankelijk is van de hoeveelheid licht in een 
ruimte. Indien het niet werkt kun je de comment bij println ldrStatus activeren
om na te gaan wat de normale waarde in de ruimte is.*/
    if(ldrStatus >= 10){
        tone(buzzerPin, 100);
        digitalWrite(ledPin, HIGH);
        delay(100);
        
        noTone(buzzerPin);
        digitalWrite(ledPin, LOW);
        delay(100);
        
        //Serial.println(ldrStatus);
        Serial.println("-------Alarm Geactiveerd!!!-------");
    }else{
        noTone(buzzerPin);
        digitalWrite(ledPin,LOW);
        
        //Serial.println(ldrStatus);
        Serial.println("Alarm niet geactiveerd.");
    }
}