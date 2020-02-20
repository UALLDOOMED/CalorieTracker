/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalorieTracker;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 44931
 */
@Entity
@Table(name = "USERINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userinfo.findAll", query = "SELECT u FROM Userinfo u")
    , @NamedQuery(name = "Userinfo.findByUserid", query = "SELECT u FROM Userinfo u WHERE u.userid = :userid")
    , @NamedQuery(name = "Userinfo.findByName", query = "SELECT u FROM Userinfo u WHERE u.name = :name")
    , @NamedQuery(name = "Userinfo.findBySurename", query = "SELECT u FROM Userinfo u WHERE u.surename = :surename")
    , @NamedQuery(name = "Userinfo.findByEmail", query = "SELECT u FROM Userinfo u WHERE u.email = :email")
    , @NamedQuery(name = "Userinfo.findByDob", query = "SELECT u FROM Userinfo u WHERE u.dob = :dob")
    , @NamedQuery(name = "Userinfo.findByHeight", query = "SELECT u FROM Userinfo u WHERE u.height = :height")
    , @NamedQuery(name = "Userinfo.findByWeight", query = "SELECT u FROM Userinfo u WHERE u.weight = :weight")
    , @NamedQuery(name = "Userinfo.findByGender", query = "SELECT u FROM Userinfo u WHERE u.gender = :gender")
    , @NamedQuery(name = "Userinfo.findByAddress", query = "SELECT u FROM Userinfo u WHERE u.address = :address")
    , @NamedQuery(name = "Userinfo.findByPostcode", query = "SELECT u FROM Userinfo u WHERE u.postcode = :postcode")
    , @NamedQuery(name = "Userinfo.findByLevelOfActivity", query = "SELECT u FROM Userinfo u WHERE u.levelOfActivity = :levelOfActivity")
    , @NamedQuery(name = "Userinfo.findByStepsPerMile", query = "SELECT u FROM Userinfo u WHERE u.stepsPerMile = :stepsPerMile")})
public class Userinfo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USERID")
    private Integer userid;
    @Size(max = 30)
    @Column(name = "NAME")
    private String name;
    @Size(max = 30)
    @Column(name = "SURENAME")
    private String surename;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "HEIGHT")
    private Integer height;
    @Column(name = "WEIGHT")
    private Integer weight;
    @Size(max = 10)
    @Column(name = "GENDER")
    private String gender;
    @Size(max = 80)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 30)
    @Column(name = "POSTCODE")
    private String postcode;
    @Column(name = "LEVEL_OF_ACTIVITY")
    private Integer levelOfActivity;
    @Column(name = "STEPS_PER_MILE")
    private Integer stepsPerMile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Credential> credentialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Report> reportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Consumption> consumptionCollection;

    public Userinfo(){
    
    }

    public Userinfo(Integer userid) {
        this.userid = userid;
    }
    
    public Userinfo(Integer userid,String name, String surename, String email, Date DOB,Integer height,Integer weight,String gender,String address,String postcode,Integer loa,Integer steps) {
        this.userid = userid;
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.dob = DOB;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.levelOfActivity = loa;
        this.stepsPerMile = steps;  
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getLevelOfActivity() {
        return levelOfActivity;
    }

    public void setLevelOfActivity(Integer levelOfActivity) {
        this.levelOfActivity = levelOfActivity;
    }

    public Integer getStepsPerMile() {
        return stepsPerMile;
    }

    public void setStepsPerMile(Integer stepsPerMile) {
        this.stepsPerMile = stepsPerMile;
    }

    @XmlTransient
    public Collection<Credential> getCredentialCollection() {
        return credentialCollection;
    }

    public void setCredentialCollection(Collection<Credential> credentialCollection) {
        this.credentialCollection = credentialCollection;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public Collection<Consumption> getConsumptionCollection() {
        return consumptionCollection;
    }

    public void setConsumptionCollection(Collection<Consumption> consumptionCollection) {
        this.consumptionCollection = consumptionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userinfo)) {
            return false;
        }
        Userinfo other = (Userinfo) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CalorieTracker.Userinfo[ userid=" + userid + " ]";
    }
    
}
