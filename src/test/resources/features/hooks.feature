#language: sv
Egenskap: Testa Hooks
  Som testare
  Vill jag kunna exekvera kod före och efter metoder och klasser
  För att förbereda och rensa

  Bakgrund: 
    Givet att applikationen förberetts
    Och jag heter Anders
    Så är vi redo att exekvera scenarios

  @init
  Scenario: initApp

  Scenario: NO 1
    Och metod1 exekveras
    Så ska jag vara i validera1

  @twice
  Scenario: NO 2
    Och metod2 exekveras
    Så ska jag vara i validera2

  @once @twice
  Scenario: NO 3
    Och metod3 exekveras
    Så ska jag vara i validera3

  @clean
  Scenario: cleanApp
