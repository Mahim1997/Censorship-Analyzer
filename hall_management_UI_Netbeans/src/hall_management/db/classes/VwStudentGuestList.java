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
@Table(name = "VW_STUDENT_GUEST_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwStudentGuestList.findAll", query = "SELECT v FROM VwStudentGuestList v")
    , @NamedQuery(name = "VwStudentGuestList.findByStudentId", query = "SELECT v FROM VwStudentGuestList v WHERE v.studentId = :studentId")
    , @NamedQuery(name = "VwStudentGuestList.findByStudentName", query = "SELECT v FROM VwStudentGuestList v WHERE v.studentName = :studentName")
    , @NamedQuery(name = "VwStudentGuestList.findByStudentAddress", query = "SELECT v FROM VwStudentGuestList v WHERE v.studentAddress = :studentAddress")
    , @NamedQuery(name = "VwStudentGuestList.findByStudentType", query = "SELECT v FROM VwStudentGuestList v WHERE v.studentType = :studentType")
    , @NamedQuery(name = "VwStudentGuestList.findByHallName", query = "SELECT v FROM VwStudentGuestList v WHERE v.hallName = :hallName")
    , @NamedQuery(name = "VwStudentGuestList.findByStudentGender", query = "SELECT v FROM VwStudentGuestList v WHERE v.studentGender = :studentGender")
    , @NamedQuery(name = "VwStudentGuestList.findByStudentContactNo", query = "SELECT v FROM VwStudentGuestList v WHERE v.studentContactNo = :studentContactNo")
    , @NamedQuery(name = "VwStudentGuestList.findByGuestName", query = "SELECT v FROM VwStudentGuestList v WHERE v.guestName = :guestName")
    , @NamedQuery(name = "VwStudentGuestList.findByGuestContactNo", query = "SELECT v FROM VwStudentGuestList v WHERE v.guestContactNo = :guestContactNo")
    , @NamedQuery(name = "VwStudentGuestList.findByGuestAddress", query = "SELECT v FROM VwStudentGuestList v WHERE v.guestAddress = :guestAddress")
    , @NamedQuery(name = "VwStudentGuestList.findByRelationWithGuest", query = "SELECT v FROM VwStudentGuestList v WHERE v.relationWithGuest = :relationWithGuest")})
public class VwStudentGuestList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "STUDENT_ID")
    private String studentId;
    @Column(name = "STUDENT_NAME")
    private String studentName;
    @Basic(optional = false)
    @Column(name = "STUDENT_ADDRESS")
    private String studentAddress;
    @Column(name = "STUDENT_TYPE")
    private String studentType;
    @Column(name = "HALL_NAME")
    private String hallName;
    @Column(name = "STUDENT_GENDER")
    private String studentGender;
    @Column(name = "STUDENT_CONTACT_NO")
    private String studentContactNo;
    @Column(name = "GUEST_NAME")
    private String guestName;
    @Column(name = "GUEST_CONTACT_NO")
    private String guestContactNo;
    @Basic(optional = false)
    @Column(name = "GUEST_ADDRESS")
    private String guestAddress;
    @Column(name = "RELATION_WITH_GUEST")
    private String relationWithGuest;

    public VwStudentGuestList() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentContactNo() {
        return studentContactNo;
    }

    public void setStudentContactNo(String studentContactNo) {
        this.studentContactNo = studentContactNo;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestContactNo() {
        return guestContactNo;
    }

    public void setGuestContactNo(String guestContactNo) {
        this.guestContactNo = guestContactNo;
    }

    public String getGuestAddress() {
        return guestAddress;
    }

    public void setGuestAddress(String guestAddress) {
        this.guestAddress = guestAddress;
    }

    public String getRelationWithGuest() {
        return relationWithGuest;
    }

    public void setRelationWithGuest(String relationWithGuest) {
        this.relationWithGuest = relationWithGuest;
    }
    
}
