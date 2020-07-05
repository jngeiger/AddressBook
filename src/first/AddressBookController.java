package first;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.net.URL;
import java.util.ResourceBundle;

public class AddressBookController implements Initializable {
    @FXML private TableView tv;
    private ObservableList<Person> list;
    @FXML private GridPane root;
    @FXML private TextField tf_firstName;
    @FXML private TextField tf_lastName;
    @FXML private TextField tf_email;
    @FXML private Label lb_mode;
    private Person selectedPerson;
    SimpleBooleanProperty isSelected;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = tv.getItems();
        isSelected = new SimpleBooleanProperty(false);
        isSelected.bind(tv.getSelectionModel().selectedIndexProperty().greaterThan(-1));
        list.add(new Person("Max","Mustermann","mm@mm.de"));
        selectedPerson = new Person("","","");
        selectedPerson.emailProperty().bind(tf_email.textProperty());
        selectedPerson.nameProperty().bind(tf_firstName.textProperty());
        selectedPerson.nachnameProperty().bind(tf_lastName.textProperty());
        lb_mode.textProperty().bind(Bindings.when(isSelected).then("Edit").otherwise("Create"));
        tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            public void changed(ObservableValue<? extends Person> p, Person oldVal, Person newVal)
            {
                if (newVal != null)
                {
                    tf_firstName.setText(newVal.getName());
                    tf_lastName.setText(newVal.getNachname());
                    tf_email.setText(newVal.getEmail());
                }
                else {
                    tf_firstName.clear();
                    tf_lastName.clear();
                    tf_email.clear();
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
    tv.getSelectionModel().clearSelection();
    }
    @FXML
    public void save(ActionEvent e)
    {
        list.add(new Person(selectedPerson.getName(),selectedPerson.getNachname(),selectedPerson.getEmail()));
    }

}
