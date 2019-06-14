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
@Table(name = "VW_EVENT_PARTICIPATION_HISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwEventParticipationHistory.findAll", query = "SELECT v FROM VwEventParticipationHistory v")
    , @NamedQuery(name = "VwEventParticipationHistory.findById", query = "SELECT v FROM VwEventParticipationHistory v WHERE v.id = :id")
    , @NamedQuery(name = "VwEventParticipationHistory.findByName", query = "SELECT v FROM VwEventParticipationHistory v WHERE v.name = :name")
    , @NamedQuery(name = "VwEventParticipationHistory.findBySport", query = "SELECT v FROM VwEventParticipationHistory v WHERE v.sport = :sport")
    , @NamedQuery(name = "VwEventParticipationHistory.findByEventName", query = "SELECT v FROM VwEventParticipationHistory v WHERE v.eventName = :eventName")
    , @NamedQuery(name = "VwEventParticipationHistory.findByYear", query = "SELECT v FROM VwEventParticipationHistory v WHERE v.year = :year")
    , @NamedQuery(name = "VwEventParticipationHistory.findByTeamName", query = "SELECT v FROM VwEventParticipationHistory v WHERE v.teamName = :teamName")
    , @NamedQuery(name = "VwEventParticipationHistory.findByDesignation", query = "SELECT v FROM VwEventParticipationHistory v WHERE v.designation = :designation")
    , @NamedQuery(name = "VwEventParticipationHistory.findByHallName", query = "SELECT v FROM VwEventParticipationHistory v WHERE v.hallName = :hallName")
    , @NamedQuery(name = "VwEventParticipationHistory.findBySupervisorName", query = "SELECT v FROM VwEventParticipationHistory v WHERE v.supervisorName = :supervisorName")
    , @NamedQuery(name = "VwEventParticipationHistory.findByRank", query = "SELECT v FROM VwEventParticipationHistory v WHERE v.rank = :rank")})
public class VwEventParticipationHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "SPORT")
    private String sport;
    @Basic(optional = false)
    @Column(name = "EVENT_NAME")
    private String eventName;
    @Basic(optional = false)
    @Column(name = "YEAR")
    private long year;
    @Basic(optional = false)
    @Column(name = "TEAM_NAME")
    private String teamName;
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "HALL_NAME")
    private String hallName;
    @Column(name = "SUPERVISOR_NAME")
    private String supervisorName;
    @Column(name = "RANK")
    private Long rank;

    public VwEventParticipationHistory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }
    
}
