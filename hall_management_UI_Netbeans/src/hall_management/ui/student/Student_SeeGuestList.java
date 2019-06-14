package hall_management.ui.student;

import hall_management.db.queries.Query;
import hall_management.ui.startPage.Main;
import hall_management.ui.student.seeGuestList.AllowedGuest;
import hall_management.util.Interface.Scenes;
import hall_management.util.SceneLoader;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Student_SeeGuestList {

    public static Scene createSeeHallStudentsScene(String id) {
        TableView<AllowedGuest> table;
        table = new TableView<>();
        TextFlow textFlow = new TextFlow();
        Button btn = new Button("BACK");

        Text text = new Text();
        String[] str = new String[2];

        try {
            str = Query.findHallIDAndNameOfStudent(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        text.setText("Teacher ID : " + Main.teacherID + "  HALL ID : " + str[0] );//+ "  Hall Name: " + str[1]);
//        text.setText("Teacher ID : " + "YOUR TEACHER_ID" + "\t\tHALL ID : " + "YOUR HALL ID" + "\t\tHall Name: " + "YOUR HALL NAME");
        text.setText("Student ID : " + Main.studentID + "\t\tHALL ID : " + str[0] + "\t\tHall Name: " + str[1]);
        int x = 200;
        int delx = 50;

        TableColumn<AllowedGuest, String> nidCol = new TableColumn<>("Guest NID");
        nidCol.setMinWidth(x + delx);
        nidCol.setCellValueFactory(new PropertyValueFactory<>("NID"));

        TableColumn<AllowedGuest, String> nameCol = new TableColumn<>("Full Name");
        nameCol.setMinWidth(x + delx);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("guestFullName"));

        TableColumn<AllowedGuest, String> addCol = new TableColumn<>("Address");
        addCol.setMinWidth(x + delx);
        addCol.setCellValueFactory(new PropertyValueFactory<>("guestAddress"));

        TableColumn<AllowedGuest, String> contactCol = new TableColumn<>("Contact No.");
        contactCol.setMinWidth(x + delx);
        contactCol.setCellValueFactory(new PropertyValueFactory<>("guestContactNumber"));
        
        TableColumn<AllowedGuest, String> relationCol = new TableColumn<>("Relation.");
        relationCol.setMinWidth(x + delx);
        relationCol.setCellValueFactory(new PropertyValueFactory<>("relationWithStudent"));
        
        try {
            table.setItems(getGuests(id));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getColumns().addAll(nidCol, nameCol, addCol, contactCol, relationCol);
//        table.getColumns().addAll(idColumn, nameColumn, deptColumn, contactCol, birthColumn, fatherCol, motherCol, addColumn, bloodColumn, religionCol, genderCol);
        textFlow.getChildren().addAll(text);

        double scale = 1;
        btn.setScaleX(scale);
        btn.setScaleY(scale);

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        Text text2 = new Text();

        hBox.getChildren().addAll(btn);
        vBox.getChildren().addAll(textFlow, table, hBox);

        Scene scene = null;

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(table);
        borderPane.setTop(text);

        borderPane.setBottom(btn);

        scene = new Scene(borderPane);

        // load the old one not new
        btn.setOnAction((event) -> {
//            Student std = new Student();
//            StudentController prevController = (StudentController) SceneLoader.loadScene(SceneLoader.student_ui, std);
//            try {
//                prevController.refreshInfo();
//            goBack();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            SceneLoader.closeScene(SceneLoader.CurrentScene());
            SceneLoader.loadPreviousScene(Scenes.student_ui, event);
        });
        return scene;
    }

    private static ObservableList<AllowedGuest> getGuests(String id) throws Exception {
        ObservableList<AllowedGuest> allowedGuestList = FXCollections.observableArrayList();
        List<AllowedGuest> list = Query.getAllowedGuests(id);
        allowedGuestList.addAll(list);
        return allowedGuestList;
    }

}
