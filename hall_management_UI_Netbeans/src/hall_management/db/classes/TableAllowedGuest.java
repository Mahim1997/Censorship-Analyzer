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
@Table(name = "TABLE_ALLOWED_GUEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableAllowedGuest.findAll", query = "SELECT t FROM TableAllowedGuest t")
    , @NamedQuery(name = "TableAllowedGuest.findByNid", query = "SELECT t FROM TableAllowedGuest t WHERE t.tableAllowedGuestPK.nid = :nid")
    , @NamedQuery(name = "TableAllowedGuest.findByStudentId", query = "SELECT t FROM TableAllowedGuest t WHERE t.tableAllowedGuestPK.studentId = :studentId")
    , @NamedQuery(name = "TableAllowedGuest.findByRelation", query = "SELECT t FROM TableAllowedGuest t WHERE t.relation = :relation")})
public class TableAllowedGuest implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TableAllowedGuestPK tableAllowedGuestPK;
    @Column(name = "RELATION")
    private String relation;
    @JoinColumn(name = "NID", referencedColumnName = "NID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableGuest tableGuest;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableStudent tableStudent;

    public TableAllowedGuest() {
    }

    public TableAllowedGuest(TableAllowedGuestPK tableAllowedGuestPK) {
        this.tableAllowedGuestPK = tableAllowedGuestPK;
    }

    public TableAllowedGuest(String nid, String studentId) {
        this.tableAllowedGuestPK = new TableAllowedGuestPK(nid, studentId);
    }

    public TableAllowedGuestPK getTableAllowedGuestPK() {
        return tableAllowedGuestPK;
    }

    public void setTableAllowedGuestPK(TableAllowedGuestPK tableAllowedGuestPK) {
        this.tableAllowedGuestPK = tableAllowedGuestPK;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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
        hash += (tableAllowedGuestPK != null ? tableAllowedGuestPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableAllowedGuest)) {
            return false;
        }
        TableAllowedGuest other = (TableAllowedGuest) object;
        if ((this.tableAllowedGuestPK == null && other.tableAllowedGuestPK != null) || (this.tableAllowedGuestPK != null && !this.tableAllowedGuestPK.equals(other.tableAllowedGuestPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableAllowedGuest[ tableAllowedGuestPK=" + tableAllowedGuestPK + " ]";
    }
    
}
