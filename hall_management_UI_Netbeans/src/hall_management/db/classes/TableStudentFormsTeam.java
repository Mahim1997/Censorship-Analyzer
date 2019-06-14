/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author suban
 */
@Entity
@Table(name = "TABLE_STUDENT_FORMS_TEAM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableStudentFormsTeam.findAll", query = "SELECT t FROM TableStudentFormsTeam t")
    , @NamedQuery(name = "TableStudentFormsTeam.findByStudentId", query = "SELECT t FROM TableStudentFormsTeam t WHERE t.tableStudentFormsTeamPK.studentId = :studentId")
    , @NamedQuery(name = "TableStudentFormsTeam.findByTeamName", query = "SELECT t FROM TableStudentFormsTeam t WHERE t.tableStudentFormsTeamPK.teamName = :teamName")
    , @NamedQuery(name = "TableStudentFormsTeam.findByYear", query = "SELECT t FROM TableStudentFormsTeam t WHERE t.tableStudentFormsTeamPK.year = :year")
    , @NamedQuery(name = "TableStudentFormsTeam.findByDesignation", query = "SELECT t FROM TableStudentFormsTeam t WHERE t.designation = :designation")})
public class TableStudentFormsTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TableStudentFormsTeamPK tableStudentFormsTeamPK;
    @Column(name = "DESIGNATION")
    private String designation;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableStudent tableStudent;
    @JoinColumns({
        @JoinColumn(name = "TEAM_NAME", referencedColumnName = "NAME", insertable = false, updatable = false)
        , @JoinColumn(name = "YEAR", referencedColumnName = "YEAR", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TableTeam tableTeam;

    public TableStudentFormsTeam() {
    }

    public TableStudentFormsTeam(TableStudentFormsTeamPK tableStudentFormsTeamPK) {
        this.tableStudentFormsTeamPK = tableStudentFormsTeamPK;
    }

    public TableStudentFormsTeam(String studentId, String teamName, long year) {
        this.tableStudentFormsTeamPK = new TableStudentFormsTeamPK(studentId, teamName, year);
    }

    public TableStudentFormsTeamPK getTableStudentFormsTeamPK() {
        return tableStudentFormsTeamPK;
    }

    public void setTableStudentFormsTeamPK(TableStudentFormsTeamPK tableStudentFormsTeamPK) {
        this.tableStudentFormsTeamPK = tableStudentFormsTeamPK;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public TableStudent getTableStudent() {
        return tableStudent;
    }

    public void setTableStudent(TableStudent tableStudent) {
        this.tableStudent = tableStudent;
    }

    public TableTeam getTableTeam() {
        return tableTeam;
    }

    public void setTableTeam(TableTeam tableTeam) {
        this.tableTeam = tableTeam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableStudentFormsTeamPK != null ? tableStudentFormsTeamPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableStudentFormsTeam)) {
            return false;
        }
        TableStudentFormsTeam other = (TableStudentFormsTeam) object;
        if ((this.tableStudentFormsTeamPK == null && other.tableStudentFormsTeamPK != null) || (this.tableStudentFormsTeamPK != null && !this.tableStudentFormsTeamPK.equals(other.tableStudentFormsTeamPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableStudentFormsTeam[ tableStudentFormsTeamPK=" + tableStudentFormsTeamPK + " ]";
    }
    
}
