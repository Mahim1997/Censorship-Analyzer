/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "VW_STUDENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwStudent.findAll", query = "SELECT v FROM VwStudent v")
    , @NamedQuery(name = "VwStudent.findById", query = "SELECT v FROM VwStudent v WHERE v.id = :id")
    , @NamedQuery(name = "VwStudent.findByName", query = "SELECT v FROM VwStudent v WHERE v.name = :name")
    , @NamedQuery(name = "VwStudent.findByDepartmentId", query = "SELECT v FROM VwStudent v WHERE v.departmentId = :departmentId")
    , @NamedQuery(name = "VwStudent.findByAddress", query = "SELECT v FROM VwStudent v WHERE v.address = :address")
    , @NamedQuery(name = "VwStudent.findByBloodGroup", query = "SELECT v FROM VwStudent v WHERE v.bloodGroup = :bloodGroup")
    , @NamedQuery(name = "VwStudent.findByBirthDate", query = "SELECT v FROM VwStudent v WHERE v.birthDate = :birthDate")
    , @NamedQuery(name = "VwStudent.findByType", query = "SELECT v FROM VwStudent v WHERE v.type = :type")
    , @NamedQuery(name = "VwStudent.findByHallId", query = "SELECT v FROM VwStudent v WHERE v.hallId = :hallId")
    , @NamedQuery(name = "VwStudent.findByHallName", query = "SELECT v FROM VwStudent v WHERE v.hallName = :hallName")
    , @NamedQuery(name = "VwStudent.findByReligion", query = "SELECT v FROM VwStudent v WHERE v.religion = :religion")
    , @NamedQuery(name = "VwStudent.findByGender", query = "SELECT v FROM VwStudent v WHERE v.gender = :gender")
    , @NamedQuery(name = "VwStudent.findByPassword", query = "SELECT v FROM VwStudent v WHERE v.password = :password")
    , @NamedQuery(name = "VwStudent.findByFatherName", query = "SELECT v FROM VwStudent v WHERE v.fatherName = :fatherName")
    , @NamedQuery(name = "VwStudent.findByMotherName", query = "SELECT v FROM VwStudent v WHERE v.motherName = :motherName")
    , @NamedQuery(name = "VwStudent.findByContactNo", query = "SELECT v FROM VwStudent v WHERE v.contactNo = :contactNo")})
public class VwStudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;
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
    @Column(name = "HALL_ID")
    private Long hallId;
    @Column(name = "HALL_NAME")
    private String hallName;
    @Basic(optional = false)
    @Column(name = "RELIGION")
    private String religion;
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

    public VwStudent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
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
    
}
