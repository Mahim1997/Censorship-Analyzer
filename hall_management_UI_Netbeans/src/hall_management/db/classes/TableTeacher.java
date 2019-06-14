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
@Table(name = "TABLE_TEACHER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableTeacher.findAll", query = "SELECT t FROM TableTeacher t")
    , @NamedQuery(name = "TableTeacher.findById", query = "SELECT t FROM TableTeacher t WHERE t.id = :id")
    , @NamedQuery(name = "TableTeacher.findByFirstName", query = "SELECT t FROM TableTeacher t WHERE t.firstName = :firstName")
    , @NamedQuery(name = "TableTeacher.findByLastName", query = "SELECT t FROM TableTeacher t WHERE t.lastName = :lastName")
    , @NamedQuery(name = "TableTeacher.findByDepartmentId", query = "SELECT t FROM TableTeacher t WHERE t.departmentId = :departmentId")
    , @NamedQuery(name = "TableTeacher.findByDesignation", query = "SELECT t FROM TableTeacher t WHERE t.designation = :designation")
    , @NamedQuery(name = "TableTeacher.findByContactNo", query = "SELECT t FROM TableTeacher t WHERE t.contactNo = :contactNo")
    , @NamedQuery(name = "TableTeacher.findByPassword", query = "SELECT t FROM TableTeacher t WHERE t.password = :password")
    , @NamedQuery(name = "TableTeacher.findByGender", query = "SELECT t FROM TableTeacher t WHERE t.gender = :gender")})
public class TableTeacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "DEPARTMENT_ID")
    private String departmentId;
    @Basic(optional = false)
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "GENDER")
    private String gender;
    @OneToMany(mappedBy = "teacherId")
    private Collection<TableEvent> tableEventCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableTeacher")
    private Collection<TableHallHeadHistory> tableHallHeadHistoryCollection;

    public TableTeacher() {
    }

    public TableTeacher(Long id) {
        this.id = id;
    }

    public TableTeacher(Long id, String firstName, String lastName, String departmentId, String designation, String password, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.designation = designation;
        this.password = password;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlTransient
    public Collection<TableEvent> getTableEventCollection() {
        return tableEventCollection;
    }

    public void setTableEventCollection(Collection<TableEvent> tableEventCollection) {
        this.tableEventCollection = tableEventCollection;
    }

    @XmlTransient
    public Collection<TableHallHeadHistory> getTableHallHeadHistoryCollection() {
        return tableHallHeadHistoryCollection;
    }

    public void setTableHallHeadHistoryCollection(Collection<TableHallHeadHistory> tableHallHeadHistoryCollection) {
        this.tableHallHeadHistoryCollection = tableHallHeadHistoryCollection;
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
        if (!(object instanceof TableTeacher)) {
            return false;
        }
        TableTeacher other = (TableTeacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableTeacher[ id=" + id + " ]";
    }
    
}
