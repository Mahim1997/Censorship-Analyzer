/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author suban
 */
@Entity
@Table(name = "TABLE_HALL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableHall.findAll", query = "SELECT t FROM TableHall t")
    , @NamedQuery(name = "TableHall.findByHallId", query = "SELECT t FROM TableHall t WHERE t.hallId = :hallId")
    , @NamedQuery(name = "TableHall.findByHallName", query = "SELECT t FROM TableHall t WHERE t.hallName = :hallName")
    , @NamedQuery(name = "TableHall.findByHallLocation", query = "SELECT t FROM TableHall t WHERE t.hallLocation = :hallLocation")
    , @NamedQuery(name = "TableHall.findByHallGender", query = "SELECT t FROM TableHall t WHERE t.hallGender = :hallGender")})
public class TableHall implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HALL_ID")
    private Long hallId;
    @Basic(optional = false)
    @Column(name = "HALL_NAME")
    private String hallName;
    @Basic(optional = false)
    @Column(name = "HALL_LOCATION")
    private String hallLocation;
    @Basic(optional = false)
    @Column(name = "HALL_GENDER")
    private String hallGender;
    @JoinTable(name = "TABLE_INTER_HALL_TEAM", joinColumns = {
        @JoinColumn(name = "HALL_ID", referencedColumnName = "HALL_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "NAME", referencedColumnName = "NAME")
        , @JoinColumn(name = "YEAR", referencedColumnName = "YEAR")})
    @ManyToMany
    private Collection<TableTeam> tableTeamCollection;
    @JoinTable(name = "TABLE_STAFF_WORKSAT_HALL", joinColumns = {
        @JoinColumn(name = "HALL_ID", referencedColumnName = "HALL_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "STAFF_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<TableStaff> tableStaffCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableHall")
    private Collection<TableStudentHallHistory> tableStudentHallHistoryCollection;
    @OneToMany(mappedBy = "hallId")
    private Collection<TableStudent> tableStudentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableHall")
    private Collection<TableRoom> tableRoomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableHall")
    private Collection<TableHallHeadHistory> tableHallHeadHistoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hallId")
    private Collection<TableApplication> tableApplicationCollection;

    public TableHall() {
    }

    public TableHall(Long hallId) {
        this.hallId = hallId;
    }

    public TableHall(Long hallId, String hallName, String hallLocation, String hallGender) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.hallLocation = hallLocation;
        this.hallGender = hallGender;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getHallLocation() {
        return hallLocation;
    }

    public void setHallLocation(String hallLocation) {
        this.hallLocation = hallLocation;
    }

    public String getHallGender() {
        return hallGender;
    }

    public void setHallGender(String hallGender) {
        this.hallGender = hallGender;
    }

    @XmlTransient
    public Collection<TableTeam> getTableTeamCollection() {
        return tableTeamCollection;
    }

    public void setTableTeamCollection(Collection<TableTeam> tableTeamCollection) {
        this.tableTeamCollection = tableTeamCollection;
    }

    @XmlTransient
    public Collection<TableStaff> getTableStaffCollection() {
        return tableStaffCollection;
    }

    public void setTableStaffCollection(Collection<TableStaff> tableStaffCollection) {
        this.tableStaffCollection = tableStaffCollection;
    }

    @XmlTransient
    public Collection<TableStudentHallHistory> getTableStudentHallHistoryCollection() {
        return tableStudentHallHistoryCollection;
    }

    public void setTableStudentHallHistoryCollection(Collection<TableStudentHallHistory> tableStudentHallHistoryCollection) {
        this.tableStudentHallHistoryCollection = tableStudentHallHistoryCollection;
    }

    @XmlTransient
    public Collection<TableStudent> getTableStudentCollection() {
        return tableStudentCollection;
    }

    public void setTableStudentCollection(Collection<TableStudent> tableStudentCollection) {
        this.tableStudentCollection = tableStudentCollection;
    }

    @XmlTransient
    public Collection<TableRoom> getTableRoomCollection() {
        return tableRoomCollection;
    }

    public void setTableRoomCollection(Collection<TableRoom> tableRoomCollection) {
        this.tableRoomCollection = tableRoomCollection;
    }

    @XmlTransient
    public Collection<TableHallHeadHistory> getTableHallHeadHistoryCollection() {
        return tableHallHeadHistoryCollection;
    }

    public void setTableHallHeadHistoryCollection(Collection<TableHallHeadHistory> tableHallHeadHistoryCollection) {
        this.tableHallHeadHistoryCollection = tableHallHeadHistoryCollection;
    }

    @XmlTransient
    public Collection<TableApplication> getTableApplicationCollection() {
        return tableApplicationCollection;
    }

    public void setTableApplicationCollection(Collection<TableApplication> tableApplicationCollection) {
        this.tableApplicationCollection = tableApplicationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hallId != null ? hallId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableHall)) {
            return false;
        }
        TableHall other = (TableHall) object;
        if ((this.hallId == null && other.hallId != null) || (this.hallId != null && !this.hallId.equals(other.hallId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableHall[ hallId=" + hallId + " ]";
    }
    
}
