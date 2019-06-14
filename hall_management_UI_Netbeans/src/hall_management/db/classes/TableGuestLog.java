/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author suban
 */
@Entity
@Table(name = "TABLE_GUEST_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableGuestLog.findAll", query = "SELECT t FROM TableGuestLog t")
    , @NamedQuery(name = "TableGuestLog.findByNid", query = "SELECT t FROM TableGuestLog t WHERE t.tableGuestLogPK.nid = :nid")
    , @NamedQuery(name = "TableGuestLog.findByStudentId", query = "SELECT t FROM TableGuestLog t WHERE t.tableGuestLogPK.studentId = :studentId")
    , @NamedQuery(name = "TableGuestLog.findByStartTime", query = "SELECT t FROM TableGuestLog t WHERE t.startTime = :startTime")
    , @NamedQuery(name = "TableGuestLog.findByEndTime", query = "SELECT t FROM TableGuestLog t WHERE t.endTime = :endTime")})
public class TableGuestLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TableGuestLogPK tableGuestLogPK;
    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @JoinColumn(name = "NID", referencedColumnName = "NID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableGuest tableGuest;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableStudent tableStudent;

    public TableGuestLog() {
    }

    public TableGuestLog(TableGuestLogPK tableGuestLogPK) {
        this.tableGuestLogPK = tableGuestLogPK;
    }

    public TableGuestLog(String nid, String studentId) {
        this.tableGuestLogPK = new TableGuestLogPK(nid, studentId);
    }

    public TableGuestLogPK getTableGuestLogPK() {
        return tableGuestLogPK;
    }

    public void setTableGuestLogPK(TableGuestLogPK tableGuestLogPK) {
        this.tableGuestLogPK = tableGuestLogPK;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public TableGuest getTableGuest() {
        return tableGuest;
    }

    public void setTableGuest(TableGuest tableGuest) {
        this.tableGuest = tableGuest;
    }

    public TableStudent getTableStudent() {
        return tableStudent;
    }

    public void setTableStudent(TableStudent tableStudent) {
        this.tableStudent = tableStudent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableGuestLogPK != null ? tableGuestLogPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableGuestLog)) {
            return false;
        }
        TableGuestLog other = (TableGuestLog) object;
        if ((this.tableGuestLogPK == null && other.tableGuestLogPK != null) || (this.tableGuestLogPK != null && !this.tableGuestLogPK.equals(other.tableGuestLogPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableGuestLog[ tableGuestLogPK=" + tableGuestLogPK + " ]";
    }
    
}
