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
import javax.persistence.JoinColumns;
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
@Table(name = "TABLE_ROOM_HISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableRoomHistory.findAll", query = "SELECT t FROM TableRoomHistory t")
    , @NamedQuery(name = "TableRoomHistory.findByHallId", query = "SELECT t FROM TableRoomHistory t WHERE t.tableRoomHistoryPK.hallId = :hallId")
    , @NamedQuery(name = "TableRoomHistory.findByRoomNo", query = "SELECT t FROM TableRoomHistory t WHERE t.tableRoomHistoryPK.roomNo = :roomNo")
    , @NamedQuery(name = "TableRoomHistory.findByStudentId", query = "SELECT t FROM TableRoomHistory t WHERE t.tableRoomHistoryPK.studentId = :studentId")
    , @NamedQuery(name = "TableRoomHistory.findByStartDate", query = "SELECT t FROM TableRoomHistory t WHERE t.startDate = :startDate")
    , @NamedQuery(name = "TableRoomHistory.findByEndDate", query = "SELECT t FROM TableRoomHistory t WHERE t.endDate = :endDate")})
public class TableRoomHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TableRoomHistoryPK tableRoomHistoryPK;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @JoinColumns({
        @JoinColumn(name = "HALL_ID", referencedColumnName = "HALL_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "ROOM_NO", referencedColumnName = "ROOM_NO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TableRoom tableRoom;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableStudent tableStudent;

    public TableRoomHistory() {
    }

    public TableRoomHistory(TableRoomHistoryPK tableRoomHistoryPK) {
        this.tableRoomHistoryPK = tableRoomHistoryPK;
    }

    public TableRoomHistory(long hallId, long roomNo, String studentId) {
        this.tableRoomHistoryPK = new TableRoomHistoryPK(hallId, roomNo, studentId);
    }

    public TableRoomHistoryPK getTableRoomHistoryPK() {
        return tableRoomHistoryPK;
    }

    public void setTableRoomHistoryPK(TableRoomHistoryPK tableRoomHistoryPK) {
        this.tableRoomHistoryPK = tableRoomHistoryPK;
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

    public TableRoom getTableRoom() {
        return tableRoom;
    }

    public void setTableRoom(TableRoom tableRoom) {
        this.tableRoom = tableRoom;
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
        hash += (tableRoomHistoryPK != null ? tableRoomHistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableRoomHistory)) {
            return false;
        }
        TableRoomHistory other = (TableRoomHistory) object;
        if ((this.tableRoomHistoryPK == null && other.tableRoomHistoryPK != null) || (this.tableRoomHistoryPK != null && !this.tableRoomHistoryPK.equals(other.tableRoomHistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableRoomHistory[ tableRoomHistoryPK=" + tableRoomHistoryPK + " ]";
    }
    
}
