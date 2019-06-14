/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author suban
 */
@Entity
@Table(name = "TABLE_ROOM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableRoom.findAll", query = "SELECT t FROM TableRoom t")
    , @NamedQuery(name = "TableRoom.findByHallId", query = "SELECT t FROM TableRoom t WHERE t.tableRoomPK.hallId = :hallId")
    , @NamedQuery(name = "TableRoom.findByRoomNo", query = "SELECT t FROM TableRoom t WHERE t.tableRoomPK.roomNo = :roomNo")
    , @NamedQuery(name = "TableRoom.findByRoomCapacity", query = "SELECT t FROM TableRoom t WHERE t.roomCapacity = :roomCapacity")
    , @NamedQuery(name = "TableRoom.findByCurrentNoOfResidents", query = "SELECT t FROM TableRoom t WHERE t.currentNoOfResidents = :currentNoOfResidents")})
public class TableRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TableRoomPK tableRoomPK;
    @Basic(optional = false)
    @Column(name = "ROOM_CAPACITY")
    private long roomCapacity;
    @Basic(optional = false)
    @Column(name = "current no of residents")
    private long currentNoOfResidents;
    @ManyToMany(mappedBy = "tableRoomCollection")
    private Collection<TableApplication> tableApplicationCollection;
    @JoinColumn(name = "HALL_ID", referencedColumnName = "HALL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TableHall tableHall;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableRoom")
    private Collection<TableRoomHistory> tableRoomHistoryCollection;

    public TableRoom() {
    }

    public TableRoom(TableRoomPK tableRoomPK) {
        this.tableRoomPK = tableRoomPK;
    }

    public TableRoom(TableRoomPK tableRoomPK, long roomCapacity, long currentNoOfResidents) {
        this.tableRoomPK = tableRoomPK;
        this.roomCapacity = roomCapacity;
        this.currentNoOfResidents = currentNoOfResidents;
    }

    public TableRoom(long hallId, long roomNo) {
        this.tableRoomPK = new TableRoomPK(hallId, roomNo);
    }

    public TableRoomPK getTableRoomPK() {
        return tableRoomPK;
    }

    public void setTableRoomPK(TableRoomPK tableRoomPK) {
        this.tableRoomPK = tableRoomPK;
    }

    public long getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(long roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public long getCurrentNoOfResidents() {
        return currentNoOfResidents;
    }

    public void setCurrentNoOfResidents(long currentNoOfResidents) {
        this.currentNoOfResidents = currentNoOfResidents;
    }

    @XmlTransient
    public Collection<TableApplication> getTableApplicationCollection() {
        return tableApplicationCollection;
    }

    public void setTableApplicationCollection(Collection<TableApplication> tableApplicationCollection) {
        this.tableApplicationCollection = tableApplicationCollection;
    }

    public TableHall getTableHall() {
        return tableHall;
    }

    public void setTableHall(TableHall tableHall) {
        this.tableHall = tableHall;
    }

    @XmlTransient
    public Collection<TableRoomHistory> getTableRoomHistoryCollection() {
        return tableRoomHistoryCollection;
    }

    public void setTableRoomHistoryCollection(Collection<TableRoomHistory> tableRoomHistoryCollection) {
        this.tableRoomHistoryCollection = tableRoomHistoryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableRoomPK != null ? tableRoomPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableRoom)) {
            return false;
        }
        TableRoom other = (TableRoom) object;
        if ((this.tableRoomPK == null && other.tableRoomPK != null) || (this.tableRoomPK != null && !this.tableRoomPK.equals(other.tableRoomPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableRoom[ tableRoomPK=" + tableRoomPK + " ]";
    }
    
}
