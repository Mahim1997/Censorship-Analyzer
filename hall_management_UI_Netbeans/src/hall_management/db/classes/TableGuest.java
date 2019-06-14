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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "TABLE_GUEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableGuest.findAll", query = "SELECT t FROM TableGuest t")
    , @NamedQuery(name = "TableGuest.findByNid", query = "SELECT t FROM TableGuest t WHERE t.nid = :nid")
    , @NamedQuery(name = "TableGuest.findByFirstName", query = "SELECT t FROM TableGuest t WHERE t.firstName = :firstName")
    , @NamedQuery(name = "TableGuest.findByLastName", query = "SELECT t FROM TableGuest t WHERE t.lastName = :lastName")
    , @NamedQuery(name = "TableGuest.findByContactNo", query = "SELECT t FROM TableGuest t WHERE t.contactNo = :contactNo")
    , @NamedQuery(name = "TableGuest.findByAddress", query = "SELECT t FROM TableGuest t WHERE t.address = :address")})
public class TableGuest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NID")
    private String nid;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Basic(optional = false)
    @Column(name = "ADDRESS")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableGuest")
    private Collection<TableAllowedGuest> tableAllowedGuestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableGuest")
    private Collection<TableGuestLog> tableGuestLogCollection;

    public TableGuest() {
    }

    public TableGuest(String nid) {
        this.nid = nid;
    }

    public TableGuest(String nid, String firstName, String lastName, String address) {
        this.nid = nid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public Collection<TableAllowedGuest> getTableAllowedGuestCollection() {
        return tableAllowedGuestCollection;
    }

    public void setTableAllowedGuestCollection(Collection<TableAllowedGuest> tableAllowedGuestCollection) {
        this.tableAllowedGuestCollection = tableAllowedGuestCollection;
    }

    @XmlTransient
    public Collection<TableGuestLog> getTableGuestLogCollection() {
        return tableGuestLogCollection;
    }

    public void setTableGuestLogCollection(Collection<TableGuestLog> tableGuestLogCollection) {
        this.tableGuestLogCollection = tableGuestLogCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nid != null ? nid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableGuest)) {
            return false;
        }
        TableGuest other = (TableGuest) object;
        if ((this.nid == null && other.nid != null) || (this.nid != null && !this.nid.equals(other.nid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableGuest[ nid=" + nid + " ]";
    }
    
}
