/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author suban
 */
@Embeddable
public class TableHallHeadHistoryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TEACHER_ID")
    private long teacherId;
    @Basic(optional = false)
    @Column(name = "HALL_ID")
    private long hallId;
    @Basic(optional = false)
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    public TableHallHeadHistoryPK() {
    }

    public TableHallHeadHistoryPK(long teacherId, long hallId, Date startDate) {
        this.teacherId = teacherId;
        this.hallId = hallId;
        this.startDate = startDate;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) teacherId;
        hash += (int) hallId;
        hash += (startDate != null ? startDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableHallHeadHistoryPK)) {
            return false;
        }
        TableHallHeadHistoryPK other = (TableHallHeadHistoryPK) object;
        if (this.teacherId != other.teacherId) {
            return false;
        }
        if (this.hallId != other.hallId) {
            return false;
        }
        if ((this.startDate == null && other.startDate != null) || (this.startDate != null && !this.startDate.equals(other.startDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableHallHeadHistoryPK[ teacherId=" + teacherId + ", hallId=" + hallId + ", startDate=" + startDate + " ]";
    }
    
}
