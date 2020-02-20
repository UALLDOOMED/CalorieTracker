/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalorieTracker;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 44931
 */
@Entity
@Table(name = "CREDENTIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credential.findAll", query = "SELECT c FROM Credential c")
    , @NamedQuery(name = "Credential.findByCreid", query = "SELECT c FROM Credential c WHERE c.creid = :creid")
    , @NamedQuery(name = "Credential.findByUsername", query = "SELECT c FROM Credential c WHERE c.username = :username")
    , @NamedQuery(name = "Credential.findByPasswordHash", query = "SELECT c FROM Credential c WHERE c.passwordHash = :passwordHash")
    , @NamedQuery(name = "Credential.findBySignUpDate", query = "SELECT c FROM Credential c WHERE c.signUpDate = :signUpDate")})
public class Credential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CREID")
    private Integer creid;
    @Size(max = 30)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PASSWORD_HASH")
    private String passwordHash;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIGN_UP_DATE")
    @Temporal(TemporalType.DATE)
    private Date signUpDate;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Userinfo userid;

    public Credential() {
    }

    public Credential(Integer creid) {
        this.creid = creid;
    }

    public Credential(Integer creid, String passwordHash, Date signUpDate) {
        this.creid = creid;
        this.passwordHash = passwordHash;
        this.signUpDate = signUpDate;
    }

    public Integer getCreid() {
        return creid;
    }

    public void setCreid(Integer creid) {
        this.creid = creid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Userinfo getUserid() {
        return userid;
    }

    public void setUserid(Userinfo userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creid != null ? creid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credential)) {
            return false;
        }
        Credential other = (Credential) object;
        if ((this.creid == null && other.creid != null) || (this.creid != null && !this.creid.equals(other.creid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CalorieTracker.Credential[ creid=" + creid + " ]";
    }
    
}
