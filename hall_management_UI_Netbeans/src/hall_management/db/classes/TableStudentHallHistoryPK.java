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
public class TableStudentHallHistoryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "HALL_ID")
    private long hallId;
    @Basic(optional = false)
    @Column(name = "STUDENT_ID")
    private String studentId;

    public TableStudentHallHistoryPK() {
    }

    public TableStudentHallHistoryPK(long hallId, String studentId) {
        this.hallId = hallId;
        this.studentId = studentId;
    }

    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
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
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableStudentHallHistoryPK)) {
            return false;
        }
        TableStudentHallHistoryPK other = (TableStudentHallHistoryPK) object;
        if (this.hallId != other.hallId) {
            return false;
        }
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableStudentHallHistoryPK[ hallId=" + hallId + ", studentId=" + studentId + " ]";
    }
    
}
