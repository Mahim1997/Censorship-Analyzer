/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author suban
 */
@Entity
@Table(name = "TABLE_EVENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableEvent.findAll", query = "SELECT t FROM TableEvent t")
    , @NamedQuery(name = "TableEvent.findByName", query = "SELECT t FROM TableEvent t WHERE t.tableEventPK.name = :name")
    , @NamedQuery(name = "TableEvent.findByYear", query = "SELECT t FROM TableEvent t WHERE t.tableEventPK.year = :year")
    , @NamedQuery(name = "TableEvent.findBySport", query = "SELECT t FROM TableEvent t WHERE t.sport = :sport")})
public class TableEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TableEventPK tableEventPK;
    @Basic(optional = false)
    @Column(name = "SPORT")
    private String sport;
    @JoinTable(name = "TABLE_TEAM_EVENT_HISTORY", joinColumns = {
        @JoinColumn(name = "EVENT_NAME", referencedColumnName = "NAME")
        , @JoinColumn(name = "year", referencedColumnName = "YEAR")}, inverseJoinColumns = {
        @JoinColumn(name = "TEAM_NAME", referencedColumnName = "NAME")
        , @JoinColumn(name = "year", referencedColumnName = "YEAR")})
    @ManyToMany
    private Collection<TableTeam> tableTeamCollection;
    @JoinTable(name = "TABLE_WINNER_LIST", joinColumns = {
        @JoinColumn(name = "EVENT_NAME", referencedColumnName = "NAME")
        , @JoinColumn(name = "year", referencedColumnName = "YEAR")}, inverseJoinColumns = {
        @JoinColumn(name = "TEAM_NAME", referencedColumnName = "NAME")
        , @JoinColumn(name = "year", referencedColumnName = "YEAR")})
    @ManyToMany
    private Collection<TableTeam> tableTeamCollection1;
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    @ManyToOne
    private TableTeacher teacherId;

    public TableEvent() {
    }

    public TableEvent(TableEventPK tableEventPK) {
        this.tableEventPK = tableEventPK;
    }

    public TableEvent(TableEventPK tableEventPK, String sport) {
        this.tableEventPK = tableEventPK;
        this.sport = sport;
    }

    public TableEvent(String name, long year) {
        this.tableEventPK = new TableEventPK(name, year);
    }

    public TableEventPK getTableEventPK() {
        return tableEventPK;
    }

    public void setTableEventPK(TableEventPK tableEventPK) {
        this.tableEventPK = tableEventPK;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @XmlTransient
    public Collection<TableTeam> getTableTeamCollection() {
        return tableTeamCollection;
    }

    public void setTableTeamCollection(Collection<TableTeam> tableTeamCollection) {
        this.tableTeamCollection = tableTeamCollection;
    }

    @XmlTransient
    public Collection<TableTeam> getTableTeamCollection1() {
        return tableTeamCollection1;
    }

    public void setTableTeamCollection1(Collection<TableTeam> tableTeamCollection1) {
        this.tableTeamCollection1 = tableTeamCollection1;
    }

    public TableTeacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(TableTeacher teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableEventPK != null ? tableEventPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableEvent)) {
            return false;
        }
        TableEvent other = (TableEvent) object;
        if ((this.tableEventPK == null && other.tableEventPK != null) || (this.tableEventPK != null && !this.tableEventPK.equals(other.tableEventPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableEvent[ tableEventPK=" + tableEventPK + " ]";
    }
    
}
