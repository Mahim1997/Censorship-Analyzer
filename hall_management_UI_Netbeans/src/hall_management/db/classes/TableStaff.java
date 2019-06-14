/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author suban
 */
@Entity
@Table(name = "TABLE_STAFF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableStaff.findAll", query = "SELECT t FROM TableStaff t")
    , @NamedQuery(name = "TableStaff.findById", query = "SELECT t FROM TableStaff t WHERE t.id = :id")
    , @NamedQuery(name = "TableStaff.findByStaffName", query = "SELECT t FROM TableStaff t WHERE t.staffName = :staffName")
    , @NamedQuery(name = "TableStaff.findByJobType", query = "SELECT t FROM TableStaff t WHERE t.jobType = :jobType")
    , @NamedQuery(name = "TableStaff.findByContactNo", query = "SELECT t FROM TableStaff t WHERE t.contactNo = :contactNo")
    , @NamedQuery(name = "TableStaff.findByStaffPassword", query = "SELECT t FROM TableStaff t WHERE t.staffPassword = :staffPassword")})
public class TableStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "STAFF_NAME")
    private String staffName;
    @Basic(optional = false)
    @Column(name = "JOB_TYPE")
    private String jobType;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Basic(optional = false)
    @Column(name = "STAFF_PASSWORD")
    private String staffPassword;
    @ManyToMany(mappedBy = "tableStaffCollection")
    private Collection<TableHall> tableHallCollection;

    public TableStaff() {
    }

    public TableStaff(Long id) {
        this.id = id;
    }

    public TableStaff(Long id, String staffName, String jobType, String staffPassword) {
        this.id = id;
        this.staffName = staffName;
        this.jobType = jobType;
        this.staffPassword = staffPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    @XmlTransient
    public Collection<TableHall> getTableHallCollection() {
        return tableHallCollection;
    }

    public void setTableHallCollection(Collection<TableHall> tableHallCollection) {
        this.tableHallCollection = tableHallCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableStaff)) {
            return false;
        }
        TableStaff other = (TableStaff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableStaff[ id=" + id + " ]";
    }
    
}
