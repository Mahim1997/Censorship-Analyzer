/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author suban
 */
@Entity
@Table(name = "VW_EVENT_TEAM_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwEventTeamList.findAll", query = "SELECT v FROM VwEventTeamList v")
    , @NamedQuery(name = "VwEventTeamList.findByEventName", query = "SELECT v FROM VwEventTeamList v WHERE v.eventName = :eventName")
    , @NamedQuery(name = "VwEventTeamList.findByTeamName", query = "SELECT v FROM VwEventTeamList v WHERE v.teamName = :teamName")
    , @NamedQuery(name = "VwEventTeamList.findByYear", query = "SELECT v FROM VwEventTeamList v WHERE v.year = :year")
    , @NamedQuery(name = "VwEventTeamList.findByHallName", query = "SELECT v FROM VwEventTeamList v WHERE v.hallName = :hallName")
    , @NamedQuery(name = "VwEventTeamList.findBySport", query = "SELECT v FROM VwEventTeamList v WHERE v.sport = :sport")
    , @NamedQuery(name = "VwEventTeamList.findBySupervisorName", query = "SELECT v FROM VwEventTeamList v WHERE v.supervisorName = :supervisorName")})
public class VwEventTeamList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "EVENT_NAME")
    private String eventName;
    @Basic(optional = false)
    @Column(name = "TEAM_NAME")
    private String teamName;
    @Basic(optional = false)
    @Column(name = "YEAR")
    private long year;
    @Column(name = "HALL_NAME")
    private String hallName;
    @Basic(optional = false)
    @Column(name = "SPORT")
    private String sport;
    @Column(name = "SUPERVISOR_NAME")
    private String supervisorName;

    public VwEventTeamList() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }
    
}
