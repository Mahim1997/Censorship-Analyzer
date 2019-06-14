/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author suban
 */
@Embeddable
public class TableRoomHistoryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "HALL_ID")
    private long hallId;
    @Basic(optional = false)
    @Column(name = "ROOM_NO")
    private long roomNo;
    @Basic(optional = false)
    @Column(name = "STUDENT_ID")
    private String studentId;

    public TableRoomHistoryPK() {
    }

    public TableRoomHistoryPK(long hallId, long roomNo, String studentId) {
        this.hallId = hallId;
        this.roomNo = roomNo;
        this.studentId = studentId;
    }

    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
    }

    public long getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(long roomNo) {
        this.roomNo = roomNo;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) hallId;
        hash += (int) roomNo;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableRoomHistoryPK)) {
            return false;
        }
        TableRoomHistoryPK other = (TableRoomHistoryPK) object;
        if (this.hallId != other.hallId) {
            return false;
        }
        if (this.roomNo != other.roomNo) {
            return false;
        }
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableRoomHistoryPK[ hallId=" + hallId + ", roomNo=" + roomNo + ", studentId=" + studentId + " ]";
    }
    
}
