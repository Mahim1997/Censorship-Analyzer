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
@Table(name = "TABLE_HALL_HEAD_HISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableHallHeadHistory.findAll", query = "SELECT t FROM TableHallHeadHistory t")
    , @NamedQuery(name = "TableHallHeadHistory.findByTeacherId", query = "SELECT t FROM TableHallHeadHistory t WHERE t.tableHallHeadHistoryPK.teacherId = :teacherId")
    , @NamedQuery(name = "TableHallHeadHistory.findByHallId", query = "SELECT t FROM TableHallHeadHistory t WHERE t.tableHallHeadHistoryPK.hallId = :hallId")
    , @NamedQuery(name = "TableHallHeadHistory.findByStartDate", query = "SELECT t FROM TableHallHeadHistory t WHERE t.tableHallHeadHistoryPK.startDate = :startDate")
    , @NamedQuery(name = "TableHallHeadHistory.findByEndDate", query = "SELECT t FROM TableHallHeadHistory t WHERE t.endDate = :endDate")})
public class TableHallHeadHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TableHallHeadHistoryPK tableHallHeadHistoryPK;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @JoinColumn(name = "HALL_ID", referencedColumnName = "HALL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableHall tableHall;
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableTeacher tableTeacher;

    public TableHallHeadHistory() {
    }

    public TableHallHeadHistory(TableHallHeadHistoryPK tableHallHeadHistoryPK) {
        this.tableHallHeadHistoryPK = tableHallHeadHistoryPK;
    }

    public TableHallHeadHistory(long teacherId, long hallId, Date startDate) {
        this.tableHallHeadHistoryPK = new TableHallHeadHistoryPK(teacherId, hallId, startDate);
    }

    public TableHallHeadHistoryPK getTableHallHeadHistoryPK() {
        return tableHallHeadHistoryPK;
    }

    public void setTableHallHeadHistoryPK(TableHallHeadHistoryPK tableHallHeadHistoryPK) {
        this.tableHallHeadHistoryPK = tableHallHeadHistoryPK;
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

    public TableTeacher getTableTeacher() {
        return tableTeacher;
    }

    public void setTableTeacher(TableTeacher tableTeacher) {
        this.tableTeacher = tableTeacher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableHallHeadHistoryPK != null ? tableHallHeadHistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableHallHeadHistory)) {
            return false;
        }
        TableHallHeadHistory other = (TableHallHeadHistory) object;
        if ((this.tableHallHeadHistoryPK == null && other.tableHallHeadHistoryPK != null) || (this.tableHallHeadHistoryPK != null && !this.tableHallHeadHistoryPK.equals(other.tableHallHeadHistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableHallHeadHistory[ tableHallHeadHistoryPK=" + tableHallHeadHistoryPK + " ]";
    }
    
}
