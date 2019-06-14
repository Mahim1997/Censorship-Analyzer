/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.ui.student.formingTeamApply;

import com.jfoenix.controls.JFXTextField;
import hall_management.db.queries.Query;
import hall_management.db.queries.more_queries.Team_Queries;
import hall_management.ui.popup.PopUpWindow;
import hall_management.ui.pushNotification.Notification;

import hall_management.ui.startPage.Main;
import hall_management.util.Interface.Controller;
import hall_management.util.Interface.Scenes;
import hall_management.util.Interface.Table;
import hall_management.util.SceneLoader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author esfs
 */
public class TeamFormation_ApplyController implements Initializable, Controller {

    private String sportType;
    @FXML
    private JFXTextField textField_ID_1;
    @FXML
    private JFXTextField textField_ID_2;
    @FXML
    private JFXTextField textField_ID_3;
    @FXML
    private JFXTextField textField_ID_10;
    @FXML
    private JFXTextField textField_ID_9;
    @FXML
    private JFXTextField textField_ID_8;
    @FXML
    private JFXTextField textField_ID_7;
    @FXML
    private JFXTextField textField_ID_6;
    @FXML
    private JFXTextField textField_ID_5;
    @FXML
    private JFXTextField textField_ID_4;
    @FXML
    private JFXTextField textField_position_5;
    @FXML
    private JFXTextField textField_position_4;
    @FXML
    private JFXTextField textField_position_3;
    @FXML
    private JFXTextField textField_position_2;
    @FXML
    private JFXTextField textField_position_1;
    @FXML
    private JFXTextField textField_ID_11;
    @FXML
    private JFXTextField textField_position_8;
    @FXML
    private JFXTextField textField_position_7;
    @FXML
    private JFXTextField textField_position_6;
    @FXML
    private JFXTextField textField_position_9;
    @FXML
    private JFXTextField textField_position_10;
    @FXML
    private JFXTextField textField_position_11;
    @FXML
    private Text text_UporerPart;

    private String[] player_positions;
    private String[] player_ids;
    private String team_name;
    private String team_yr;
    private String hallID;
    private String captainID;
    @FXML
    private Text text_AlertForDuplicateTeamName;

    @FXML
    private JFXTextField textField_teamleaderID;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private Text text_For_Chess;
    @FXML
    private JFXTextField textField_TeamName_Football;
    @FXML
    private JFXTextField textField_teamYear_Football;
    @FXML
    private Text text_WhatTypeTeam;
    @FXML
    private ChoiceBox choiceBox_teamType;
    String teamType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("=->>> INSIDE TeamFormation_ApplyController.initialize()... ");
        sportType = "FOOTBALL";
        setInitialStringArray();
        initialiseChoiceBox();
        initialiseInitialInfo();
        textField_ID_1.setEditable(false);
        textField_teamYear_Football.setEditable(false);

//
        setInitialDebugMode();

    }

    private void initialiseChoiceBox() {
        choiceBox.setItems(FXCollections.observableArrayList(
                "FOOTBALL", "CRICKET"));
        choiceBox.setValue((String) "FOOTBALL");
        choiceBox.setOnAction((event) -> {
            this.sportType = (String) choiceBox.getValue();
            text_UporerPart.setText("Sport : " + this.sportType);
        });

        choiceBox_teamType.setItems(FXCollections.observableArrayList(
                "LOCAL", "GLOBAL"));
        choiceBox_teamType.setValue((String) "LOCAL");
        choiceBox_teamType.setOnAction((event) -> {
            this.teamType = (String) choiceBox_teamType.getValue();
            text_UporerPart.setText(this.teamType.toUpperCase() + " TEAM");
        });

    }

    @FXML
    private void goBack() {
        SceneLoader.closeScene(SceneLoader.CurrentScene());
        SceneLoader.loadPreviousScene(Scenes.student_ui, this);
    }

    @FXML
    private void submitInfoOfTeam() throws Exception {
        boolean flag = checkThisName_IsAlreadyTaken();
        if (flag == true) {
            Notification.push("WARNING", "THIS TEAM NAME ALREADY EXISTS!!", Notification.WARNING);
            return;
        }
        String s = this.allInformationIsCorrect();
        if (s.equals("SUCCESS") == false) {
            Notification.push("ERROR", s, Notification.FAILURE);
            return;
        }

        ///TO SEND
        retrieveStringDataFromTextField();
        if (teamType.equals("LOCAL")) {
            Team_Queries.insertTeam_interHall(team_name, team_yr, sportType, hallID, captainID);
            Team_Queries.insertStudentFormsTeam(team_name, team_yr, player_ids, player_positions, hallID, sportType, teamType);
        } else {
            Team_Queries.insertTeam_Global(team_name, team_yr, sportType,hallID ,captainID);
            Team_Queries.insertStudentFormsTeam(team_name, team_yr, player_ids, player_positions, hallID, sportType, teamType);
        }
        Notification.push("SUCCESS!", "Add Another Team maybe?", Notification.SUCCESS);
//            PopUpWindow.displaySuccess("SUCCESS", "Team is Added", "Going To Main Window");
        this.clearAll();
//            SceneLoader.closeScene(SceneLoader.CurrentScene());
//            SceneLoader.loadPreviousScene(Scenes.student_ui, this);

    }

    @FXML
    private boolean checkThisName_IsAlreadyTaken() throws Exception {
//        TeamQuery.isThisTeamAlreadyIn();

        boolean flag = true;
        retrieveStringDataFromTextField();
        if (team_name.trim().equals("") == true) {
            Notification.push("ERROR!", "Team Name Cant Be Empty", Notification.FAILURE);
            return false;
        }
        if (teamType.equals("LOCAL")) {
            flag = Team_Queries.checkIfTeamExists_interHall(team_name, team_yr, sportType, hallID);
        } else {
            flag = Team_Queries.checkIfTeamExists_Global(team_name, team_name, sportType);
        }
//        boolean flag = Team_Queries.checkIfTeamExists_interHall(makeTeamName(team_name), team_yr, sportType);
        if (flag == true) {
            ///ALREADY EXISTS
            text_AlertForDuplicateTeamName.setText("Already Taken Name.");
            textField_TeamName_Football.clear();
            return true;
        } else {
            text_AlertForDuplicateTeamName.setText("NAME AVAILABLE!");
            return false;
        }
    }

    private void initialiseInitialInfo() {
        try {
            this.hallID = Query.getFieldInfoFromTable(Table.Student, Main.studentID, "HALL_ID");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String text = "Sport: " + this.sportType;
        text_UporerPart.setText(text);
        textField_teamleaderID.setText(Main.studentID);
        textField_ID_1.setText(Main.studentID);
//        textField_teamYear.setText(getCurrentYear());
        player_ids[1] = textField_ID_1.getText();
        team_name = textField_TeamName_Football.getText();

        text = "Student ID : " + Main.studentID + " Applying for Chess, Year : " + getCurrentYear();
        text_For_Chess.setText(text);
    }

    private String getCurrentYear() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();
        String s = dateFormat.format(cal.getTime());
        return s;
    }

    private void setInitialDebugMode() {
        player_positions[1] = "GoalKeeper";
        for (int i = 2; i <= 6; i++) {
            player_positions[i] = "Defender";
        }
        for (int i = 7; i <= 9; i++) {
            player_positions[i] = "Midfielder";
        }
        for (int i = 9; i <= 11; i++) {
            player_positions[i] = "Striker";
        }
        player_ids[1] = textField_ID_1.getText();
        player_ids[2] = String.valueOf(7001);
        player_ids[3] = String.valueOf(7004);
        player_ids[4] = String.valueOf(7005);
        player_ids[5] = String.valueOf(7007);
        player_ids[6] = String.valueOf(7009);
        player_ids[7] = String.valueOf(7010);
        player_ids[8] = String.valueOf(7011);
        player_ids[9] = String.valueOf(7013);
        player_ids[10] = String.valueOf(7015);
        player_ids[11] = String.valueOf(7019);
        System.out.println("=-->>Inside debugInfo.. player_ids.length = " + player_ids.length + " , plauer_pos.length = " + player_positions.length);
        System.out.println("==>>PRINTING THEM .. ");
        for (int i = 1; i < player_positions.length; i++) {
            System.out.println(player_positions[i]);
        }
        for (int i = 1; i < player_ids.length; i++) {
            System.out.println(player_ids[i]);
        }
        textField_teamYear_Football.setText(getCurrentYear());
//        textField_teamYear_Football.setText("2017");
//        printArray(player_ids);
//        printArray(player_positions);
        makeTextFieldAppearWithRespectToString();
    }

    private void setInitialStringArray() {
        int numOfPlayers = 11;
        this.player_ids = new String[numOfPlayers + 1];
        this.player_positions = new String[numOfPlayers + 1];
        player_ids[1] = this.textField_ID_1.getText();
    }

    private void retrieveStringDataFromTextField() {
        this.captainID = textField_teamleaderID.getText();
        player_ids[1] = textField_ID_1.getText();
        player_ids[2] = textField_ID_2.getText();
        player_ids[3] = textField_ID_3.getText();
        player_ids[4] = textField_ID_4.getText();
        player_ids[5] = textField_ID_5.getText();
        player_ids[6] = textField_ID_6.getText();
        player_ids[7] = textField_ID_7.getText();
        player_ids[8] = textField_ID_8.getText();
        player_ids[9] = textField_ID_9.getText();
        player_ids[10] = textField_ID_10.getText();
        player_ids[11] = textField_ID_11.getText();
        player_positions[1] = textField_position_1.getText();
        player_positions[2] = textField_position_2.getText();
        player_positions[3] = textField_position_3.getText();
        player_positions[4] = textField_position_4.getText();
        player_positions[5] = textField_position_5.getText();
        player_positions[6] = textField_position_6.getText();
        player_positions[7] = textField_position_7.getText();
        player_positions[8] = textField_position_8.getText();
        player_positions[9] = textField_position_9.getText();
        player_positions[10] = textField_position_10.getText();
        player_positions[11] = textField_position_11.getText();
        this.team_name = textField_TeamName_Football.getText().trim();
        this.team_yr = textField_teamYear_Football.getText().trim();
        this.sportType = (String) choiceBox.getValue();
        this.teamType = (String) choiceBox_teamType.getValue();
    }

    @FXML
    private void clearAll() {
        textField_ID_2.clear();
        textField_ID_3.clear();
        textField_ID_4.clear();
        textField_ID_5.clear();
        textField_ID_6.clear();
        textField_ID_7.clear();
        textField_ID_8.clear();
        textField_ID_9.clear();
        textField_ID_10.clear();
        textField_ID_11.clear();
        textField_position_1.clear();
        textField_position_2.clear();
        textField_position_3.clear();
        textField_position_4.clear();
        textField_position_5.clear();
        textField_position_6.clear();
        textField_position_7.clear();
        textField_position_8.clear();
        textField_position_9.clear();
        textField_position_10.clear();
        textField_position_11.clear();
        textField_TeamName_Football.clear();
        textField_teamleaderID.clear();
    }

    private void makeTextFieldAppearWithRespectToString() {
        textField_position_1.setText(player_positions[1]);
        textField_position_2.setText(player_positions[2]);
        textField_position_3.setText(player_positions[3]);
        textField_position_4.setText(player_positions[4]);
        textField_position_5.setText(player_positions[5]);
        textField_position_6.setText(player_positions[6]);
        textField_position_7.setText(player_positions[7]);
        textField_position_8.setText(player_positions[8]);
        textField_position_9.setText(player_positions[9]);
        textField_position_10.setText(player_positions[10]);
        textField_position_11.setText(player_positions[11]);
        textField_ID_2.setText(player_ids[2]);
        textField_ID_3.setText(player_ids[3]);
        textField_ID_4.setText(player_ids[4]);
        textField_ID_5.setText(player_ids[5]);
        textField_ID_6.setText(player_ids[6]);
        textField_ID_7.setText(player_ids[7]);
        textField_ID_8.setText(player_ids[8]);
        textField_ID_9.setText(player_ids[9]);
        textField_ID_10.setText(player_ids[10]);
        textField_ID_11.setText(player_ids[11]);
    }

    private String allInformationIsCorrect() {
        String hallID;
        try {
            for (int i = 2; i <= 11; i++) {
                hallID = Query.getFieldInfoFromTable(Table.Student, player_ids[i], "HALL_ID");
                if (hallID == null || hallID.trim().equals(this.hallID) == false) {
                    return "Player ID: " + player_ids[i] + " Is not from same hall as " + Main.studentID;
//                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean flag = false;
        for (int i = 1; i <= 11; i++) {
            if (player_ids[i].equals(this.captainID) == true) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
//            Notification.push("ERROR!", "Captain Must be a Player", Notification.FAILURE);
            return "Captain Must be a Player";
        }
        if (team_name.trim().equals("") == true) {
//            Notification.push("ERROR", "Team Name Cant Be Empty", Notification.FAILURE);
            return "Team Name Cant Be Empty";
        }
        //TODO!!
        return "SUCCESS";

    }

    @FXML
    private void loadStudentsOfThisHall() {
        SceneLoader.loadSceneInADifferentWindow(Scenes.student_seeOthersOfThisHall, this);
    }

    @FXML
    private void registerForChess() throws Exception {
        boolean flag = Team_Queries.didThisPersonRegisterForChess(Main.studentID, this.hallID, this.getCurrentYear(), "CHESS");
        if (flag == true) {
            Notification.push("ERROR", "YOU ARE ALREADY REGISTERED FOR CHESS!!" + this.getCurrentYear(), Notification.FAILURE);
            return;
        }
        Team_Queries.insertTeam_interHall(Team_Queries.makeSingleTeamNameForChess(Main.studentID), this.getCurrentYear(),
                "CHESS", hallID, Main.studentID);
        Team_Queries.insertStudentFormsTeam(Team_Queries.makeSingleTeamNameForChess(Main.studentID), this.getCurrentYear(),
                Main.studentID, "SINGLE PLAYER", hallID, "CHESS", "LOCAL");

        Notification.push("SUCCESS!!", "ALL THE BEST! APPLY NEXT YEAR TOO!", Notification.SUCCESS);

//        Team_Queries.insertForChess(Main.studentID, this.hallID, "CHESS", getCurrentYear());
    }

    @FXML
    private void registerForUniversityChess(ActionEvent event)  throws Exception {
        boolean flag = Team_Queries.didThisPersonRegisterForChess(Main.studentID, this.hallID, this.getCurrentYear(), "CHESS", "GLOBAL");
        if (flag == true) {
            Notification.push("ERROR", "YOU ARE ALREADY REGISTERED FOR CHESS!!" + this.getCurrentYear(), Notification.FAILURE);
            return;
        }
        Team_Queries.insertTeam_Global(Team_Queries.makeSingleTeamNameForChess(Main.studentID), this.getCurrentYear(),
                "CHESS", hallID, Main.studentID);
        Team_Queries.insertStudentFormsTeam(Team_Queries.makeSingleTeamNameForChess(Main.studentID), this.getCurrentYear(),
                Main.studentID, "SINGLE PLAYER", hallID, "CHESS", "GLOBAL");

        Notification.push("SUCCESS!!", "ALL THE BEST! APPLY NEXT YEAR TOO!", Notification.SUCCESS);
    }

}
