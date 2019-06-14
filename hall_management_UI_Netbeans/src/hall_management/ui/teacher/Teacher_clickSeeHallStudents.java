package hall_management.ui.teacher;

import hall_management.ui.startPage.Main;
import hall_management.util.SceneLoader;
import hall_management.ui.student.Student;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import hall_management.db.queries.Query;
import hall_management.util.Interface.Scenes;

public class Teacher_clickSeeHallStudents {

public static Scene createSeeHallStudentsScene() {
        TableView<Student> table;
        table = new TableView<>();
        TextFlow textFlow = new TextFlow();
        Button btn = new Button("BACK");
//        textFlow.setScaleX(2); textFlow.setScaleY(2);
        Text text = new Text();
        String[] str = new String[2];

        try{
            str = Query.findHallIDAndName(Main.teacherID);
        }catch(Exception e){
            e.printStackTrace();
        }
//        text.setText("Teacher ID : " + Main.teacherID + "  HALL ID : " + str[0] );//+ "  Hall Name: " + str[1]);
//        text.setText("Teacher ID : " + "YOUR TEACHER_ID" + "\t\tHALL ID : " + "YOUR HALL ID" + "\t\tHall Name: " + "YOUR HALL NAME");
        text.setText("Teacher ID : " + Main.teacherID + "\t\tHALL ID : " + str[0] + "\t\tHall Name: " + str[1]);
        int x = 200;
        int delx = 50;
        //ID column
        TableColumn<Student, String> idColumn = new TableColumn<>("Student ID");
        idColumn.setMinWidth(x - 2 * delx);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        //NAME column
        TableColumn<Student, String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setMinWidth(x);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        //DEPT column
        TableColumn<Student, String> deptColumn = new TableColumn<>("Department ID");
        deptColumn.setMinWidth(x - delx);
        deptColumn.setCellValueFactory(new PropertyValueFactory<>("dept_Id"));

        //Address column
        TableColumn<Student, String> addColumn = new TableColumn<>("Address");
        addColumn.setMinWidth(x + delx);
        addColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        //Blood Group Column
        TableColumn<Student, String> bloodColumn = new TableColumn<>("Blood Group");
        bloodColumn.setMinWidth(x - delx);
        bloodColumn.setCellValueFactory(new PropertyValueFactory<>("bloodGrp"));

        //Birthdate Column
        TableColumn<Student, String> birthColumn = new TableColumn<>("Date Of Birth");
        birthColumn.setMinWidth(x);
        birthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        //Religion Column
        TableColumn<Student, String> religionCol = new TableColumn<>("Religion");
        religionCol.setMinWidth(x - delx);
        religionCol.setCellValueFactory(new PropertyValueFactory<>("religion"));

        //Gender Column
        TableColumn<Student, String> genderCol = new TableColumn<>("Gender");
        genderCol.setMinWidth(x - delx);
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        //Contact 
        TableColumn<Student, String> contactCol = new TableColumn<>("Contact Number");
        contactCol.setMinWidth(x - delx);
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        //Father Name
        TableColumn<Student, String> fatherCol = new TableColumn<>("Father Name");
        fatherCol.setMinWidth(x - delx);
        fatherCol.setCellValueFactory(new PropertyValueFactory<>("fatherName"));

        TableColumn<Student, String> motherCol = new TableColumn<>("Mother Name");
        motherCol.setMinWidth(x - delx);
        motherCol.setCellValueFactory(new PropertyValueFactory<>("motherName"));

        try {
            table.setItems(getStudents());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        table.getColumns().addAll(idColumn, nameColumn, deptColumn, contactCol, birthColumn, fatherCol, motherCol, addColumn, bloodColumn, religionCol, genderCol);
        textFlow.getChildren().addAll(text);

        double scale = 1;
        btn.setScaleX(scale);
        btn.setScaleY(scale);

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        Text text2 = new Text();
//        btn.setLayoutX(x + 4 * delx);
        hBox.getChildren().addAll(btn);
        vBox.getChildren().addAll(textFlow, table, hBox);

        Scene scene = null;
//        vBox.setLayoutX(x* 5);
//        vBox.setLayoutY(x * 7);
//        scene = new Scene(vBox);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(table);
        borderPane.setTop(text);

        borderPane.setBottom(btn);
//        borderPane.getChildren().addAll(vBox);
        scene = new Scene(borderPane);
//        Main.windowStage.setScene(scene);
//        Main.windowStage.show();
        btn.setOnAction((event) -> {
//            Student std = new Student();
//            Teacher_windowController prevController = (Teacher_windowController)SceneLoader.loadScene(Scenes.teacher_ui, std);
//            try {
//                prevController.refreshInfo();
////            goBack();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            SceneLoader.closeScene(SceneLoader.CurrentScene());
            SceneLoader.loadPreviousScene(Scenes.teacher_ui, event);
        });
        return scene;
    }

    private static ObservableList<Student> getStudents() throws Exception {
        ObservableList<Student> students = FXCollections.observableArrayList();
        List<Student> list = Query.getStudentsOfThisHall();
        students.addAll(list);
        return students;
    }

    private static void goBack() {
        
    }
}
