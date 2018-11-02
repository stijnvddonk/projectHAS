/*
Lamp ON.
*/

void setup() {
    // initialize digital pin LED_BUILTIN as an output.
    pinMode(LED_BUILTIN, OUTPUT);
    digitalWrite(LED_BUILTIN, HIGH);
}

// There is no loop because it only has to activate once.
void loop() {
}

/**-----Diferent option:-----**/

/*
Lamp OFF.
*/

/*
void setup() {
    // initialize digital pin LED_BUILTIN as an output.
    pinMode(LED_BUILTIN, OUTPUT);
    digitalWrite(LED_BUILTIN, LOW);
}

// There is no loop because it only has to activate once.
void loop() {
}
*/