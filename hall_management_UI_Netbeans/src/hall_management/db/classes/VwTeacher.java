/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hall_management.db.classes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author suban
 */
@Entity
@Table(name = "VW_TEACHER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwTeacher.findAll", query = "SELECT v FROM VwTeacher v")
    , @NamedQuery(name = "VwTeacher.findById", query = "SELECT v FROM VwTeacher v WHERE v.id = :id")
    , @NamedQuery(name = "VwTeacher.findByPassword", query = "SELECT v FROM VwTeacher v WHERE v.password = :password")
    , @NamedQuery(name = "VwTeacher.findByName", query = "SELECT v FROM VwTeacher v WHERE v.name = :name")
    , @NamedQuery(name = "VwTeacher.findByContactNo", query = "SELECT v FROM VwTeacher v WHERE v.contactNo = :contactNo")
    , @NamedQuery(name = "VwTeacher.findByDepartmentId", query = "SELECT v FROM VwTeacher v WHERE v.departmentId = :departmentId")
    , @NamedQuery(name = "VwTeacher.findByDesignation", query = "SELECT v FROM VwTeacher v WHERE v.designation = :designation")
    , @NamedQuery(name = "VwTeacher.findByGender", query = "SELECT v FROM VwTeacher v WHERE v.gender = :gender")})
public class VwTeacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ID")
    private long id;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Basic(optional = false)
    @Column(name = "DEPARTMENT_ID")
    private String departmentId;
    @Basic(optional = false)
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "GENDER")
    private String gender;

    public VwTeacher() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
