package hall_management.ui.student.applySeat;

import hall_management.db.queries.Application_Queries;
import hall_management.db.queries.Query;
import hall_management.ui.startPage.Main;
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
import javafx.stage.Stage;

public class AvailableRoomList {
    static Stage stage = new Stage();
    @Override
    public String toString() {
        return "AvailableRoomList{" + "hall_id=" + hall_id + ", room_no=" + room_no + ", current_no_ppl=" + current_no_ppl + ", room_capacity=" + room_capacity + '}';
    }

    private static ObservableList<AvailableRoomList> getAvailableRoomList(String id) throws Exception {
        ObservableList<AvailableRoomList> availableRoomList = FXCollections.observableArrayList();
        List<AvailableRoomList> list = Application_Queries.getListOfAvailableRooms(id);
        availableRoomList.addAll(list);
        return availableRoomList;
    }

    private String hall_id;
    private String room_no;
    private String current_no_ppl;
    private String room_capacity;

    public String getRoom_capacity() {
        return room_capacity;
    }

    public void setRoom_capacity(String room_capacity) {
        this.room_capacity = room_capacity;
    }
    
    public String getCurrent_no_ppl() {
        return current_no_ppl;
    }

    public void setCurrent_no_ppl(String current_no_ppl) {
        this.current_no_ppl = current_no_ppl;
    }

    public AvailableRoomList() {

    }

    public String getHall_id() {
        return hall_id;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    static void openAvailableRoomList()throws Exception {
        Scene scene = AvailableRoomList.createSeeHallStudentsScene(Main.studentID);
        stage.setScene(scene);
        int height = 700;
        int width  = 1000;
        stage.setHeight(height);
        stage.setWidth(width);
        stage.show();
    }

    public static Scene createSeeHallStudentsScene(String id) {
        TableView<AvailableRoomList> table;
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
        text.setText("Student ID : " + Main.studentID + "\t\tHALL ID : " + str[0] + "\t\tHall Name: " + str[1]);
        int x = 200;
        int delx = 50;

        TableColumn<AvailableRoomList, String> room_no_col = new TableColumn<>("Room No");
        room_no_col.setMinWidth(x + delx);
        room_no_col.setCellValueFactory(new PropertyValueFactory<>("room_no"));

        TableColumn<AvailableRoomList, String> no_of_ppl_now_col = new TableColumn<>("Current No. Of People");
        no_of_ppl_now_col.setMinWidth(x + delx);
        no_of_ppl_now_col.setCellValueFactory(new PropertyValueFactory<>("current_no_ppl"));
        
        TableColumn<AvailableRoomList, String> capacity_col = new TableColumn<>("Capacity");
        capacity_col.setMinWidth(x + delx);
        capacity_col.setCellValueFactory(new PropertyValueFactory<>("room_capacity"));

        
        try {
            table.setItems(getAvailableRoomList(id));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getColumns().addAll(room_no_col, no_of_ppl_now_col, capacity_col);
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
            stage.close();
        });
        return scene;
    }
}
