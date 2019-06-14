/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.util.Interface;

/**
 *
 * @author suban
 */
public interface Scenes {



    //    public String login_ui = "ui_front.fxml";
    String student_ui = "/hall_management/ui/student/student.fxml";
    String loginPage_ui = "/hall_management/ui/login/login.fxml";

//    String teacher_ui_old = "/hall_management/ui/teacher/teacher_window.fxml";
    String startPage_ui = "/hall_management/ui/startPage/StartPage.fxml";
//    String student_ui_old = "/hall_management/ui/student/student_window_latest.fxml";
    //    public String student_addGuestForm_ui = "/hall_management/ui/student/student_addGuestForm.fxml";
    String student_newGuestForm = "/hall_management/ui/student/addNewGuest/newGuestForm.fxml";

    String student_application_ui = "/hall_management/ui/student/applySeat/ApplySeatForm.fxml";
    String student_see_application_ui = "/hall_management/ui/student/seeApplicationLog/see_Applications.fxml";

    String student_see_application_room_list = "/hall_management/ui/student/seeRoomList/see_Application_RoomList.fxml";
    
    String student_see_guest_list = "/hall_management/ui/student/seeGuestList/see_GuestList.fxml";
    
    
    String student_see_guestLog = "/hall_management/ui/student/seeGuestList/see_GuestLog.fxml";
    
    String settings_panel = "/hall_management/ui/settings/settings.fxml";
    
    String student_form_a_team = "/hall_management/ui/student/formingTeamApply/teamFormation_Apply.fxml";
    
    String student_seeOthersOfThisHall = "/hall_management/ui/student/formingTeamApply/loadStudentsOfHall.fxml";
    String student_see_upcoming_events = "/hall_management/ui/student/events/seeUpcomingEvents.fxml";
    
    String students_events_participated = "/hall_management/ui/student/events/participatedEvents.fxml";
    String student_see_teams_participated = "/hall_management/ui/student/events/see_teams.fxml";
    
    
    String student_see_teams_upcoming_events = "/hall_management/ui/student/events/seeTeamsOfUpcomingEvents.fxml";
    
    
    
    //TEACHER
    String teacher_ui = "/hall_management/ui/teacher/teacher.fxml";
    String normalTeacher_ui = "/hall_management/ui/teacher/normalTeacher.fxml";
    String teacher_see_Applications = "/hall_management/ui/teacher/applications/teacher_seeApplications.fxml";
    String teacher_application_AcceptForm = "/hall_management/ui/teacher/applications/Application_AcceptedForm.fxml";    
    
    String teacher_seeEvents_teams = "/hall_management/ui/teacher/events/seeTeams.fxml";
    
    String teacher_manage_upcoming_events = "/hall_management/ui/teacher/upcomingEvents/manageUpcomingEvents.fxml";
    String teacher_load_teams_for_events = "/hall_management/ui/teacher/upcomingEvents/manage_teams.fxml";
    String teacher_acceptOrReject_Teams = "/hall_management/ui/teacher/upcomingEvents/team_student_relations.fxml";
    String teacher_event_winnerSelector = "/hall_management/ui/teacher/upcomingEvents/winnerAndRunnerUpSelectro.fxml";
    
    String teacher_seeEvents_teams_students = "/hall_management/ui/teacher/events/see_teams_students.fxml";
    String teacher_see_students_hall = "/hall_management/ui/teacher/see_students_hall.fxml";
    
    //STAFF
    String staff_ui = "/hall_management/ui/staff/staff.fxml";
    String staff_SecurityGuard = "/hall_management/ui/staff/staff_Security_Guard.fxml";
    String staff_SecurityGuard_Guest_Log = "/hall_management/ui/staff/staff_Security_SeeGuestLog.fxml";
    String staff_SecurityGuard_Guest_List = "/hall_management/ui/staff/staff_Security_SeeGuestList.fxml";
    
    String teacher_seeEvents = "/hall_management/ui/teacher/events/seeEventsSupervied.fxml";
    
    //Guest
    String guest_ui = "/hall_management/ui/guest/guest.fxml";
    
    
    String admin_ui = "/hall_management/ui/admin/admin.fxml";
    String admin_deleteLogs = "/hall_management/ui/admin/deleteLogs.fxml";
    String admin_addPerson = "/hall_management/ui/admin/insertPerson.fxml";
    String admin_updatePerson = "/hall_management/ui/admin/updatePerson.fxml";
    String admin_addEvent = "/hall_management/ui/admin/addEvent.fxml";
    String admin_modifyEvent = "/hall_management/ui/admin/modifyEvent.fxml";
    String admin_modifyHall = "/hall_management/ui/admin/modifyHall.fxml";
    
    String admin_delete_particular_guest = "/hall_management/ui/admin/delete_particular_guest.fxml";
    
    String credits = "/hall_management/ui/credits/credits.fxml";

}
