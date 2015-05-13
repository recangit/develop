<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Git</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1>Git</h1>

        <table class="mainCanvas">
            <tr>
                <td class="anteckning">                
                    <h2>Om...git</h2>
git pull origin master
git push -u origin master

GITHUB
recangit:maLLa_62

SSH
Kontrollera först om det redan finns ssh-nycklar
cd ~/.ssh
ls -l
Filen id_rsa.pub är en ssh-nyckel

Om inte:
ssh-keygen -t rsa -C "info@recksen.se"  
ENTER
Lösenord
Lösenord

Skriv sedan: ssh-add ~/.ssh/id_rsa

Kopiera innehållet i id_rsa.pub till GitHub

Testa att detta fungerat:
ssh -T git@github.com
Resultatet bör vara: Hi username! You've successfully authenticated, but GitHub does not # provide shell access.

REPOSITORY
https://github.com/recangit/

Skapa ett repository i GitHub, ex: Sudoku.
Behöver inte klicka i Initialize this repository...
Efter att ha klickat på "Create repository" visas information som är bra att ha

LOKALT REPOSITORY
cd <projektet>
git init
git add *.java *.xml *.properties
git status visar nu att README inte inkluderats
git add README

git commit -m "Första commit"

Fortfarande har jag bara påverkat mina lokala filer, inte gitrepot på github.
The first part is familiar; we’ve used git add already with files. 
We’ve tacked the word origin onto it to indicate a new place from 
which files will originate. remote is a descriptor of origin, to 
indicate the origin is not on the computer, but somewhere online.
git remote add origin https://github.com/recangit/Sudoku.git
Kontrollera att detta fungerade: git remote -v
git push -u origin master

Gör nu en ändring i en fil och skicka upp den till repot
git add *Helper.java
git commit -m "Lagt till text i Helper"
git push -u origin master

HÄMTA FRÅN REPOSITORYT
// DETTA FUNGERAR
git clone git@github.com:recangit/Sudoku.git
Gör en ändring:
git add *Helper.java
git commit -m "Lagt till text i Helper PÅ JOBBET"
git push -u origin master

==========================================================
FRÅN BÖRJAN (på jobbet)

git init
git add *.java *.xml *.conf *.properties *.jsp *.graphml *.xlsx *.feature *.css *.txt *.template
git commit -m "Första commit av SimpleWebApp"
git remote add origin https://github.com/recangit/SimpleWebApp.git
git push -u origin master
Användarnamn: recangit
Lösenord: maLLa_62

Gå till ett annat directory för att testa
git clone git@github.com:recangit/SimpleWebApp.git

Av något skäl följde inte alla filer med och måste därför läggas in från original directoryt
git add src/main/resources/log4j.xml
git commit -m "Filer som inte kom med första gången"
git push -u origin master

Gå nu tillbaka till test directoryt och uppdatera det
git pull origin master

För att slippa texten om ospårade filer när jag kör git status vill jag exkludera onödiga filer.
Skriv ex: docs/Model-Based-Testing1.ppt, spara filen som .gitignore
==========================================================

Radera från github:
Ta först bort filer lokalt
git commit -m 'Jag har raderat filer'
git pull origin master
==========================================================
git clone git@github.com:recangit/SimpleWebApp.git
git clone git@github.com:recangit/BuildingBlocks.git
git clone git@github.com:recangit/recan-util.git

                </td>
            </tr>
        </table>

    </body>
</html>