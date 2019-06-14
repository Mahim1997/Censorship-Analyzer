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
@Table(name = "TABLE_STUDENT_HALL_HISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableStudentHallHistory.findAll", query = "SELECT t FROM TableStudentHallHistory t")
    , @NamedQuery(name = "TableStudentHallHistory.findByHallId", query = "SELECT t FROM TableStudentHallHistory t WHERE t.tableStudentHallHistoryPK.hallId = :hallId")
    , @NamedQuery(name = "TableStudentHallHistory.findByStudentId", query = "SELECT t FROM TableStudentHallHistory t WHERE t.tableStudentHallHistoryPK.studentId = :studentId")
    , @NamedQuery(name = "TableStudentHallHistory.findByStartDate", query = "SELECT t FROM TableStudentHallHistory t WHERE t.startDate = :startDate")
    , @NamedQuery(name = "TableStudentHallHistory.findByEndDate", query = "SELECT t FROM TableStudentHallHistory t WHERE t.endDate = :endDate")})
public class TableStudentHallHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TableStudentHallHistoryPK tableStudentHallHistoryPK;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @JoinColumn(name = "HALL_ID", referencedColumnName = "HALL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableHall tableHall;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableStudent tableStudent;

    public TableStudentHallHistory() {
    }

    public TableStudentHallHistory(TableStudentHallHistoryPK tableStudentHallHistoryPK) {
        this.tableStudentHallHistoryPK = tableStudentHallHistoryPK;
    }

    public TableStudentHallHistory(long hallId, String studentId) {
        this.tableStudentHallHistoryPK = new TableStudentHallHistoryPK(hallId, studentId);
    }

    public TableStudentHallHistoryPK getTableStudentHallHistoryPK() {
        return tableStudentHallHistoryPK;
    }

    public void setTableStudentHallHistoryPK(TableStudentHallHistoryPK tableStudentHallHistoryPK) {
        this.tableStudentHallHistoryPK = tableStudentHallHistoryPK;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TableHall getTableHall() {
        return tableHall;
    }

    public void setTableHall(TableHall tableHall) {
        this.tableHall = tableHall;
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
        hash += (tableStudentHallHistoryPK != null ? tableStudentHallHistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableStudentHallHistory)) {
            return false;
        }
        TableStudentHallHistory other = (TableStudentHallHistory) object;
        if ((this.tableStudentHallHistoryPK == null && other.tableStudentHallHistoryPK != null) || (this.tableStudentHallHistoryPK != null && !this.tableStudentHallHistoryPK.equals(other.tableStudentHallHistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableStudentHallHistory[ tableStudentHallHistoryPK=" + tableStudentHallHistoryPK + " ]";
    }
    
}
