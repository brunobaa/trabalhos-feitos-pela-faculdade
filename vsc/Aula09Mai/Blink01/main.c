#include <stdint.h>
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>


int main (void) 
{
  DDRB |= (1 << PB5); // define GPIO PB5 como saída 
  
  int periodo = 1000; // 1000 ms = 1 seg
  int perSec = periodo/4; //nxpor segundo
  
  while(1){
	  
    PORTB |= 1 << PB5;     // liga o led
    _delay_ms(perSec);
    
    PORTB &= ~(1 << PB5);  // desliga o led
    _delay_ms(perSec);
    
  }
}
