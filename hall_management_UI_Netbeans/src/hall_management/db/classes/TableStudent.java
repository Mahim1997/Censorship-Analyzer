/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author suban
 */
@Entity
@Table(name = "TABLE_STUDENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableStudent.findAll", query = "SELECT t FROM TableStudent t")
    , @NamedQuery(name = "TableStudent.findById", query = "SELECT t FROM TableStudent t WHERE t.id = :id")
    , @NamedQuery(name = "TableStudent.findByFirstName", query = "SELECT t FROM TableStudent t WHERE t.firstName = :firstName")
    , @NamedQuery(name = "TableStudent.findByLastName", query = "SELECT t FROM TableStudent t WHERE t.lastName = :lastName")
    , @NamedQuery(name = "TableStudent.findByDepartmentId", query = "SELECT t FROM TableStudent t WHERE t.departmentId = :departmentId")
    , @NamedQuery(name = "TableStudent.findByAddress", query = "SELECT t FROM TableStudent t WHERE t.address = :address")
    , @NamedQuery(name = "TableStudent.findByBloodGroup", query = "SELECT t FROM TableStudent t WHERE t.bloodGroup = :bloodGroup")
    , @NamedQuery(name = "TableStudent.findByBirthDate", query = "SELECT t FROM TableStudent t WHERE t.birthDate = :birthDate")
    , @NamedQuery(name = "TableStudent.findByType", query = "SELECT t FROM TableStudent t WHERE t.type = :type")
    , @NamedQuery(name = "TableStudent.findByReligion", query = "SELECT t FROM TableStudent t WHERE t.religion = :religion")
    , @NamedQuery(name = "TableStudent.findByGender", query = "SELECT t FROM TableStudent t WHERE t.gender = :gender")
    , @NamedQuery(name = "TableStudent.findByPassword", query = "SELECT t FROM TableStudent t WHERE t.password = :password")
    , @NamedQuery(name = "TableStudent.findByFatherName", query = "SELECT t FROM TableStudent t WHERE t.fatherName = :fatherName")
    , @NamedQuery(name = "TableStudent.findByMotherName", query = "SELECT t FROM TableStudent t WHERE t.motherName = :motherName")
    , @NamedQuery(name = "TableStudent.findByContactNo", query = "SELECT t FROM TableStudent t WHERE t.contactNo = :contactNo")})
public class TableStudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
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
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "BLOOD_GROUP")
    private String bloodGroup;
    @Basic(optional = false)
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Column(name = "RELIGION")
    private String religion;
    @Basic(optional = false)
    @Column(name = "GENDER")
    private String gender;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "FATHER_NAME")
    private String fatherName;
    @Basic(optional = false)
    @Column(name = "MOTHER_NAME")
    private String motherName;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableStudent")
    private Collection<TableStudentHallHistory> tableStudentHallHistoryCollection;
    @JoinColumn(name = "HALL_ID", referencedColumnName = "HALL_ID")
    @ManyToOne
    private TableHall hallId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableStudent")
    private Collection<TableAllowedGuest> tableAllowedGuestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableStudent")
    private Collection<TableStudentFormsTeam> tableStudentFormsTeamCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableStudent")
    private Collection<TableRoomHistory> tableRoomHistoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<TableApplication> tableApplicationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableStudent")
    private Collection<TableGuestLog> tableGuestLogCollection;

    public TableStudent() {
    }

    public TableStudent(String id) {
        this.id = id;
    }

    public TableStudent(String id, String firstName, String lastName, String departmentId, String address, Date birthDate, String type, String religion, String gender, String password, String fatherName, String motherName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.address = address;
        this.birthDate = birthDate;
        this.type = type;
        this.religion = religion;
        this.gender = gender;
        this.password = password;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @XmlTransient
    public Collection<TableStudentHallHistory> getTableStudentHallHistoryCollection() {
        return tableStudentHallHistoryCollection;
    }

    public void setTableStudentHallHistoryCollection(Collection<TableStudentHallHistory> tableStudentHallHistoryCollection) {
        this.tableStudentHallHistoryCollection = tableStudentHallHistoryCollection;
    }

    public TableHall getHallId() {
        return hallId;
    }

    public void setHallId(TableHall hallId) {
        this.hallId = hallId;
    }

    @XmlTransient
    public Collection<TableAllowedGuest> getTableAllowedGuestCollection() {
        return tableAllowedGuestCollection;
    }

    public void setTableAllowedGuestCollection(Collection<TableAllowedGuest> tableAllowedGuestCollection) {
        this.tableAllowedGuestCollection = tableAllowedGuestCollection;
    }

    @XmlTransient
    public Collection<TableStudentFormsTeam> getTableStudentFormsTeamCollection() {
        return tableStudentFormsTeamCollection;
    }

    public void setTableStudentFormsTeamCollection(Collection<TableStudentFormsTeam> tableStudentFormsTeamCollection) {
        this.tableStudentFormsTeamCollection = tableStudentFormsTeamCollection;
    }

    @XmlTransient
    public Collection<TableRoomHistory> getTableRoomHistoryCollection() {
        return tableRoomHistoryCollection;
    }

    public void setTableRoomHistoryCollection(Collection<TableRoomHistory> tableRoomHistoryCollection) {
        this.tableRoomHistoryCollection = tableRoomHistoryCollection;
    }

    @XmlTransient
    public Collection<TableApplication> getTableApplicationCollection() {
        return tableApplicationCollection;
    }

    public void setTableApplicationCollection(Collection<TableApplication> tableApplicationCollection) {
        this.tableApplicationCollection = tableApplicationCollection;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableStudent)) {
            return false;
        }
        TableStudent other = (TableStudent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hall_management.db.classes.TableStudent[ id=" + id + " ]";
    }
    
}
