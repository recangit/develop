# language: sv

Egenskap: Samband mellan fart, distans och tid
  Som nyfiken
  Vill jag koppla ihop Cucumber med Java och SoapUI
  För att se om detta är ett bra sätt att jobba på

  Bakgrund: Jag är inget vidare på Web Services

  Abstrakt Scenario: Tid
    Givet farten '<speed>'
    Och distansen '<distance>'
    Så är tiden '<time>'

    Exempel: 
      | speed | distance | time |
      | 120   | 160      | 80   |
      | 90    | 150      | 100  |

  Abstrakt Scenario: Fart
    Givet distansen '<distance>'
    Och tiden '<time>'
    Så är farten '<speed>'

    Exempel: 
      | speed | distance | time |
      | 120   | 160      | 80   |
      | 75    | 150      | 120  |

  Abstrakt Scenario: Distans
    Givet tiden '<time>'
    Och farten '<speed>'
    Så är distansen '<distance>'

    Exempel: 
      | speed | distance | time |
      | 120   | 160      | 80   |
      | 110   | 330      | 180  |
