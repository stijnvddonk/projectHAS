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
  digitalWrite(led1, HIGH); 
  digitalWrite(led2,  !digitalRead(led1));
  Serial.print("De Rode lamp: ");
  Serial.println(digitalRead(led1));
  Serial.print("De Groene lamp: ");
  Serial.println(digitalRead(led2));
  delay(1000);        
       
  digitalWrite(led1, LOW); 
  digitalWrite(led2,  !digitalRead(led1));
  Serial.print("De Rode lamp: ");
  Serial.println(digitalRead(led1));
  Serial.print("De Groene lamp: ");
  Serial.println(digitalRead(led2));
  delay(1000);  
}
