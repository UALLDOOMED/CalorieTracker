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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 44931
 */
@Entity
@Table(name = "REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findByReportid", query = "SELECT r FROM Report r WHERE r.reportid = :reportid")
    , @NamedQuery(name = "Report.findByDate", query = "SELECT r FROM Report r WHERE r.date = :date")
    , @NamedQuery(name = "Report.findByTcalorieConsum", query = "SELECT r FROM Report r WHERE r.tcalorieConsum = :tcalorieConsum")
    , @NamedQuery(name = "Report.findByTcalorieBurn", query = "SELECT r FROM Report r WHERE r.tcalorieBurn = :tcalorieBurn")
    , @NamedQuery(name = "Report.findByTsteps", query = "SELECT r FROM Report r WHERE r.tsteps = :tsteps")
    , @NamedQuery(name = "Report.findByCalorieGoal", query = "SELECT r FROM Report r WHERE r.calorieGoal = :calorieGoal")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REPORTID")
    private Integer reportid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "TCALORIE_CONSUM")
    private Integer tcalorieConsum;
    @Column(name = "TCALORIE_BURN")
    private Integer tcalorieBurn;
    @Column(name = "TSTEPS")
    private Integer tsteps;
    @Column(name = "CALORIE_GOAL")
    private Integer calorieGoal;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Userinfo userid;

    public Report(){
    
    }
    
    public Report(Integer reportid) {
        this.reportid = reportid;
    }
    
    public Report(Integer reportid,Date date,Userinfo user, Integer consum, Integer burn, Integer tSteps,Integer goal) {
        this.reportid = reportid;
        this.date = date;
        this.userid = user;
        this.tcalorieConsum = consum;
        this.tcalorieBurn = burn;
        this.tsteps = tSteps;
        this.calorieGoal = goal;
    }

    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTcalorieConsum() {
        return tcalorieConsum;
    }

    public void setTcalorieConsum(Integer tcalorieConsum) {
        this.tcalorieConsum = tcalorieConsum;
    }

    public Integer getTcalorieBurn() {
        return tcalorieBurn;
    }

    public void setTcalorieBurn(Integer tcalorieBurn) {
        this.tcalorieBurn = tcalorieBurn;
    }

    public Integer getTsteps() {
        return tsteps;
    }

    public void setTsteps(Integer tsteps) {
        this.tsteps = tsteps;
    }

    public Integer getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(Integer calorieGoal) {
        this.calorieGoal = calorieGoal;
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
        hash += (reportid != null ? reportid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportid == null && other.reportid != null) || (this.reportid != null && !this.reportid.equals(other.reportid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CalorieTracker.Report[ reportid=" + reportid + " ]";
    }
    
}
