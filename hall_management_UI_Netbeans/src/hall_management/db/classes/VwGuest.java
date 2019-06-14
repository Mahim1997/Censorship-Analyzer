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
@Table(name = "VW_GUEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwGuest.findAll", query = "SELECT v FROM VwGuest v")
    , @NamedQuery(name = "VwGuest.findByNid", query = "SELECT v FROM VwGuest v WHERE v.nid = :nid")
    , @NamedQuery(name = "VwGuest.findByName", query = "SELECT v FROM VwGuest v WHERE v.name = :name")
    , @NamedQuery(name = "VwGuest.findByContactNo", query = "SELECT v FROM VwGuest v WHERE v.contactNo = :contactNo")
    , @NamedQuery(name = "VwGuest.findByAddress", query = "SELECT v FROM VwGuest v WHERE v.address = :address")})
public class VwGuest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NID")
    private String nid;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Basic(optional = false)
    @Column(name = "ADDRESS")
    private String address;

    public VwGuest() {
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
