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
import javax.persistence.ManyToMany;
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
@Table(name = "TABLE_TEAM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableTeam.findAll", query = "SELECT t FROM TableTeam t")
    , @NamedQuery(name = "TableTeam.findByName", query = "SELECT t FROM TableTeam t WHERE t.tableTeamPK.name = :name")
    , @NamedQuery(name = "TableTeam.findByYear", query = "SELECT t FROM TableTeam t WHERE t.tableTeamPK.year = :year")
    , @NamedQuery(name = "TableTeam.findBySport", query = "SELECT t FROM TableTeam t WHERE t.sport = :sport")})
public class TableTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TableTeamPK tableTeamPK;
    @Basic(optional = false)
    @Column(name = "SPORT")
    private String sport;
    @ManyToMany(mappedBy = "tableTeamCollection")
    private Collection<TableHall> tableHallCollection;
    @ManyToMany(mappedBy = "tableTeamCollection")
    private Collection<TableEvent> tableEventCollection;
    @ManyToMany(mappedBy = "tableTeamCollection1")
    private Collection<TableEvent> tableEventCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableTeam")
    private Collection<TableStudentFormsTeam> tableStudentFormsTeamCollection;

    public TableTeam() {
    }

    public TableTeam(TableTeamPK tableTeamPK) {
        this.tableTeamPK = tableTeamPK;
    }

    public TableTeam(TableTeamPK tableTeamPK, String sport) {
        this.tableTeamPK = tableTeamPK;
        this.sport = sport;
    }

    public TableTeam(String name, long year) {
        this.tableTeamPK = new TableTeamPK(name, year);
    }

    public TableTeamPK getTableTeamPK() {
        return tableTeamPK;
    }

    public void setTableTeamPK(TableTeamPK tableTeamPK) {
        this.tableTeamPK = tableTeamPK;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @XmlTransient
    public Collection<TableHall> getTableHallCollection() {
        return tableHallCollection;
    }

    public void setTableHallCollection(Collection<TableHall> tableHallCollection) {
        this.tableHallCollection = tableHallCollection;
    }

    @XmlTransient
    public Collection<TableEvent> getTableEventCollection() {
        return tableEventCollection;
    }

    public void setTableEventCollection(Collection<TableEvent> tableEventCollection) {
        this.tableEventCollection = tableEventCollection;
    }

    @XmlTransient
    public Collection<TableEvent> getTableEventCollection1() {
        return tableEventCollection1;
    }

    public void setTableEventCollection1(Collection<TableEvent> tableEventCollection1) {
        this.tableEventCollection1 = tableEventCollection1;
    }

    @XmlTransient
    public Collection<TableStudentFormsTeam> getTableStudentFormsTeamCollection() {
        return tableStudentFormsTeamCollection;
    }

    public void setTableStudentFormsTeamCollection(Collection<TableStudentFormsTeam> tableStudentFormsTeamCollection) {
        this.tableStudentFormsTeamCollection = tableStudentFormsTeamCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableTeamPK != null ? tableTeamPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableTeam)) {
            return false;
        }
        TableTeam other = (TableTeam) object;
        if ((this.tableTeamPK == null && other.tableTeamPK != null) || (this.tableTeamPK != null && !this.tableTeamPK.equals(other.tableTeamPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableTeam[ tableTeamPK=" + tableTeamPK + " ]";
    }
    
}
