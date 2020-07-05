package first;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.net.URL;
import java.util.ResourceBundle;

public class AddressBookController implements Initializable {
    @FXML TableView tv;
    ObservableList<Person> list;
    @FXML GridPane root;
    @FXML TextField tf_firstName;
    @FXML TextField tf_lastName;
    @FXML TextField tf_email;
    Person selectedPerson;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = tv.getItems();
        list.add(new Person("Max","Mustermann","mm@mm.de"));
        selectedPerson = new Person("","","");
        selectedPerson.emailProperty().bind(tf_email.textProperty());
        selectedPerson.nameProperty().bind(tf_firstName.textProperty());
        selectedPerson.nachnameProperty().bind(tf_lastName.textProperty());

        tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            public void changed(ObservableValue<? extends Person> p, Person oldVal, Person newVal)
            {
                if (newVal != null)
                {
                    tf_firstName.setText(newVal.getName());
                    tf_lastName.setText(newVal.getNachname());
                    tf_email.setText(newVal.getEmail());
                }
            }
        });

    }
    @FXML
    public void delete(ActionEvent e)
    {
        if(tv.getSelectionModel().getSelectedIndex() != -1)
            list.remove(tv.getSelectionModel().getSelectedIndex());
        else {
            return;
        }
    }
    @FXML
    public void newMode(ActionEvent e)
    {

    }
    @FXML
    public void save(ActionEvent e)
    {
        list.add(new Person(selectedPerson.getName(),selectedPerson.getNachname(),selectedPerson.getEmail()));
    }

}
