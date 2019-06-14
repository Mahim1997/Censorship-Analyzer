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
public class TableStudentFormsTeamPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "STUDENT_ID")
    private String studentId;
    @Basic(optional = false)
    @Column(name = "TEAM_NAME")
    private String teamName;
    @Basic(optional = false)
    @Column(name = "YEAR")
    private long year;

    public TableStudentFormsTeamPK() {
    }

    public TableStudentFormsTeamPK(String studentId, String teamName, long year) {
        this.studentId = studentId;
        this.teamName = teamName;
        this.year = year;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        hash += (teamName != null ? teamName.hashCode() : 0);
        hash += (int) year;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableStudentFormsTeamPK)) {
            return false;
        }
        TableStudentFormsTeamPK other = (TableStudentFormsTeamPK) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        if ((this.teamName == null && other.teamName != null) || (this.teamName != null && !this.teamName.equals(other.teamName))) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableStudentFormsTeamPK[ studentId=" + studentId + ", teamName=" + teamName + ", year=" + year + " ]";
    }
    
}
