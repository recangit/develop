#language: sv
Egenskap: Validera
  Som Administratör
  Vill jag vara säker på att all inlaggd information är validerad
  För att garantera databasens konsistens

  Bakgrund: En användare ska kunna lägga till personuppgifter

  Abstrakt Scenario: Lägg till en post
    Givet att jag navigerat till personsidan
    När jag fyller i fältet förnamn med '<fornamn>'
    Och jag fyller i fältet efternamn med '<efternamn>'
    Och jag fyller i fältet personnummer med '<pnumb>'
    Och jag klickar på Kvinna
    Och jag klickar på Spara
    Så ska meddelandet vara '<message1>'
    När jag klickar på Man
    Och jag fyller i fältet användarnamn med '<username>'
    Och jag fyller i fältet lösenord med '<pw>'
    Och jag fyller i fältet lösenord igen med '<pw2>'
    Och jag klickar på Spara
    Så ska meddelandet vara '<message2>'

    Exempel:
      | fornamn | efternamn | pnumb       | message1                        | username | pw    | pw2   | message2          |
      | Anders  | Recksén   | 621002-4318 | Du har angett kvinna men är man | recan    | malla | malla | Lägg till en post |
