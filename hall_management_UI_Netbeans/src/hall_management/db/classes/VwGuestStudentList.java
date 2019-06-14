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
@Table(name = "VW_GUEST_STUDENT_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwGuestStudentList.findAll", query = "SELECT v FROM VwGuestStudentList v")
    , @NamedQuery(name = "VwGuestStudentList.findByGuestNid", query = "SELECT v FROM VwGuestStudentList v WHERE v.guestNid = :guestNid")
    , @NamedQuery(name = "VwGuestStudentList.findByGuestName", query = "SELECT v FROM VwGuestStudentList v WHERE v.guestName = :guestName")
    , @NamedQuery(name = "VwGuestStudentList.findByGuestContactNo", query = "SELECT v FROM VwGuestStudentList v WHERE v.guestContactNo = :guestContactNo")
    , @NamedQuery(name = "VwGuestStudentList.findByGuestAddress", query = "SELECT v FROM VwGuestStudentList v WHERE v.guestAddress = :guestAddress")
    , @NamedQuery(name = "VwGuestStudentList.findByStudentId", query = "SELECT v FROM VwGuestStudentList v WHERE v.studentId = :studentId")
    , @NamedQuery(name = "VwGuestStudentList.findByStudentName", query = "SELECT v FROM VwGuestStudentList v WHERE v.studentName = :studentName")
    , @NamedQuery(name = "VwGuestStudentList.findByHallName", query = "SELECT v FROM VwGuestStudentList v WHERE v.hallName = :hallName")
    , @NamedQuery(name = "VwGuestStudentList.findByStudentGender", query = "SELECT v FROM VwGuestStudentList v WHERE v.studentGender = :studentGender")
    , @NamedQuery(name = "VwGuestStudentList.findByStudentContactNo", query = "SELECT v FROM VwGuestStudentList v WHERE v.studentContactNo = :studentContactNo")
    , @NamedQuery(name = "VwGuestStudentList.findByRelationWithGuest", query = "SELECT v FROM VwGuestStudentList v WHERE v.relationWithGuest = :relationWithGuest")})
public class VwGuestStudentList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "GUEST_NID")
    private String guestNid;
    @Column(name = "GUEST_NAME")
    private String guestName;
    @Column(name = "GUEST_CONTACT_NO")
    private String guestContactNo;
    @Basic(optional = false)
    @Column(name = "GUEST_ADDRESS")
    private String guestAddress;
    @Column(name = "STUDENT_ID")
    private String studentId;
    @Column(name = "STUDENT_NAME")
    private String studentName;
    @Column(name = "HALL_NAME")
    private String hallName;
    @Column(name = "STUDENT_GENDER")
    private String studentGender;
    @Column(name = "STUDENT_CONTACT_NO")
    private String studentContactNo;
    @Column(name = "RELATION_WITH_GUEST")
    private String relationWithGuest;

    public VwGuestStudentList() {
    }

    public String getGuestNid() {
        return guestNid;
    }

    public void setGuestNid(String guestNid) {
        this.guestNid = guestNid;
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

    public String getRelationWithGuest() {
        return relationWithGuest;
    }

    public void setRelationWithGuest(String relationWithGuest) {
        this.relationWithGuest = relationWithGuest;
    }
    
}
