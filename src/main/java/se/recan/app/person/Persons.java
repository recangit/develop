package se.recan.app.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Denna klass finns endast för att användas i olika testtutorials. Används
 * alltså inte i Applikationen.
 *
 * @date 2014-maj-14
 * @author Anders Recksén (recan)
 */
public class Persons {

    private final List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        persons.add(person);
    }

    public Person getPerson(int position) {
        return persons.get(position);
    }

    public int getSize() {
        if(persons.size() < 1) {
            throw new IllegalArgumentException("Listan måste innehålla någon post: " + persons.size());
        }
        return persons.size();
    }
}
