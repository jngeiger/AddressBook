package first;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty nachname = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();

    public Person(String f, String n, String e)
    {
        name.set(f);
        nachname.set(n);
        email.set(e);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty nachnameProperty() {
        return nachname;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setNachname(String nachname)
    {
        this.nachname.set(nachname);
    }

    public void setEmail(String email)
    {
        this.email.set(email);
    }

    public String getName()
    {
      return name.get();
    }
    public String getNachname()
    {
        return nachname.get();
    }
    public String getEmail()
    {
        return email.get();
    }
}
