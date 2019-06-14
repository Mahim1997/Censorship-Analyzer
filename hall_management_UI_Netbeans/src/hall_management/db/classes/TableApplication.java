/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author suban
 */
@Entity
@Table(name = "TABLE_APPLICATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableApplication.findAll", query = "SELECT t FROM TableApplication t")
    , @NamedQuery(name = "TableApplication.findByApplicationId", query = "SELECT t FROM TableApplication t WHERE t.applicationId = :applicationId")
    , @NamedQuery(name = "TableApplication.findByApplicationDate", query = "SELECT t FROM TableApplication t WHERE t.applicationDate = :applicationDate")
    , @NamedQuery(name = "TableApplication.findByVerdictDate", query = "SELECT t FROM TableApplication t WHERE t.verdictDate = :verdictDate")
    , @NamedQuery(name = "TableApplication.findByCurrentRoomid", query = "SELECT t FROM TableApplication t WHERE t.currentRoomid = :currentRoomid")
    , @NamedQuery(name = "TableApplication.findByHasRoom", query = "SELECT t FROM TableApplication t WHERE t.hasRoom = :hasRoom")
    , @NamedQuery(name = "TableApplication.findByStatus", query = "SELECT t FROM TableApplication t WHERE t.status = :status")
    , @NamedQuery(name = "TableApplication.findByAllottedRoomNo", query = "SELECT t FROM TableApplication t WHERE t.allottedRoomNo = :allottedRoomNo")})
public class TableApplication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "APPLICATION_ID")
    private Long applicationId;
    @Column(name = "APPLICATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationDate;
    @Column(name = "VERDICT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date verdictDate;
    @Column(name = "CURRENT_ROOMID")
    private Long currentRoomid;
    @Basic(optional = false)
    @Column(name = "HAS_ROOM")
    private String hasRoom;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ALLOTTED_ROOM_NO")
    private Long allottedRoomNo;
    @JoinTable(name = "TABLE_APPLICATION_ROOM_LIST", joinColumns = {
        @JoinColumn(name = "APPLICATION_ID", referencedColumnName = "APPLICATION_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "HALL_ID", referencedColumnName = "HALL_ID")
        , @JoinColumn(name = "ROOM_NO", referencedColumnName = "ROOM_NO")})
    @ManyToMany
    private Collection<TableRoom> tableRoomCollection;
    @JoinColumn(name = "HALL_ID", referencedColumnName = "HALL_ID")
    @ManyToOne(optional = false)
    private TableHall hallId;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TableStudent studentId;

    public TableApplication() {
    }

    public TableApplication(Long applicationId) {
        this.applicationId = applicationId;
    }

    public TableApplication(Long applicationId, String hasRoom, String status) {
        this.applicationId = applicationId;
        this.hasRoom = hasRoom;
        this.status = status;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getVerdictDate() {
        return verdictDate;
    }

    public void setVerdictDate(Date verdictDate) {
        this.verdictDate = verdictDate;
    }

    public Long getCurrentRoomid() {
        return currentRoomid;
    }

    public void setCurrentRoomid(Long currentRoomid) {
        this.currentRoomid = currentRoomid;
    }

    public String getHasRoom() {
        return hasRoom;
    }

    public void setHasRoom(String hasRoom) {
        this.hasRoom = hasRoom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAllottedRoomNo() {
        return allottedRoomNo;
    }

    public void setAllottedRoomNo(Long allottedRoomNo) {
        this.allottedRoomNo = allottedRoomNo;
    }

    @XmlTransient
    public Collection<TableRoom> getTableRoomCollection() {
        return tableRoomCollection;
    }

    public void setTableRoomCollection(Collection<TableRoom> tableRoomCollection) {
        this.tableRoomCollection = tableRoomCollection;
    }

    public TableHall getHallId() {
        return hallId;
    }

    public void setHallId(TableHall hallId) {
        this.hallId = hallId;
    }

    public TableStudent getStudentId() {
        return studentId;
    }

    public void setStudentId(TableStudent studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationId != null ? applicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableApplication)) {
            return false;
        }
        TableApplication other = (TableApplication) object;
        if ((this.applicationId == null && other.applicationId != null) || (this.applicationId != null && !this.applicationId.equals(other.applicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableApplication[ applicationId=" + applicationId + " ]";
    }
    
}
