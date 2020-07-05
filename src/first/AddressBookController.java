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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = tv.getItems();
        list.add(new Person("Max","Mustermann","mm@mm.de"));
        Person selectedPerson = new Person("","","");
        tf_email.textProperty().bindBidirectional(selectedPerson.emailProperty());
        tf_firstName.textProperty().bind(selectedPerson.emailProperty());
        tf_lastName.textProperty().bind(selectedPerson.nachnameProperty());

        tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            public void changed(ObservableValue<? extends Person> p, Person oldVal, Person newVal)
            {
                if (newVal != null)
                {
                    selectedPerson.setEmail(newVal.getEmail());
                    selectedPerson.setNachname(newVal.getNachname());
                    selectedPerson.setName(newVal.getName());
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

    }

}