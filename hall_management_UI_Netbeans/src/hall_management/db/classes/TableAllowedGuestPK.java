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
public class TableAllowedGuestPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NID")
    private String nid;
    @Basic(optional = false)
    @Column(name = "STUDENT_ID")
    private String studentId;

    public TableAllowedGuestPK() {
    }

    public TableAllowedGuestPK(String nid, String studentId) {
        this.nid = nid;
        this.studentId = studentId;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
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
        hash += (nid != null ? nid.hashCode() : 0);
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableAllowedGuestPK)) {
            return false;
        }
        TableAllowedGuestPK other = (TableAllowedGuestPK) object;
        if ((this.nid == null && other.nid != null) || (this.nid != null && !this.nid.equals(other.nid))) {
            return false;
        }
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableAllowedGuestPK[ nid=" + nid + ", studentId=" + studentId + " ]";
    }
    
}
