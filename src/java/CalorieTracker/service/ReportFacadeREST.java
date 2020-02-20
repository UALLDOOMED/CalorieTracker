/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalorieTracker.service;

import CalorieReport.ShowPeriodReport;
import CalorieReport.ShowReport;
import CalorieTracker.Report;
import CalorieTracker.Userinfo;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author 44931
 */
@Stateless
@Path("calorietracker.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "5046_AssignmentPU")
    private EntityManager em;

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Report entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Report entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Report find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("findByUserid/{userid}")
    @Produces({"application/json"})
    public List<Report> findByUserid(@PathParam("userid") Integer userid) {
        TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid = :userid", Report.class);
        q.setParameter("userid", userid);
        return q.getResultList();
    }

    @GET
    @Path("findByReportid/{reportid}")
    @Produces({"application/json"})
    public List<Report> findByReportid(@PathParam("reportid") Integer reportid) {
        Query query = em.createNamedQuery("Report.findByReportid");
        query.setParameter("reportid", reportid);
        return query.getResultList();
    }

    @GET
    @Path("findByDate/{date}")
    @Produces({"application/json"})
    public List<Report> findByDate(@PathParam("date") Date date) {
        Query query = em.createNamedQuery("Report.findByDate");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @GET
    @Path("findByTcalorieConsum/{tcalorieConsum}")
    @Produces({"application/json"})
    public List<Report> findByTcalorieConsum(@PathParam("tcalorieConsum") Integer tcalorieConsum) {
        Query query = em.createNamedQuery("Report.findByTcalorieConsum");
        query.setParameter("tcalorieConsum", tcalorieConsum);
        return query.getResultList();
    }

    @GET
    @Path("findByTcalorieBurn/{tcalorieBurn}")
    @Produces({"application/json"})
    public List<Report> findByTcalorieBurn(@PathParam("tcalorieBurn") Integer tcalorieBurn) {
        Query query = em.createNamedQuery("Report.findByTcalorieBurn");
        query.setParameter("tcalorieBurn", tcalorieBurn);
        return query.getResultList();
    }

    @GET
    @Path("findByTsteps/{tsteps}")
    @Produces({"application/json"})
    public List<Report> findByTsteps(@PathParam("tsteps") Integer tsteps) {
        Query query = em.createNamedQuery("Report.findByTsteps");
        query.setParameter("tsteps", tsteps);
        return query.getResultList();
    }

    @GET
    @Path("findByCalorieGoal/{calorieGoal}")
    @Produces({"application/json"})
    public List<Report> findByCalorieGoal(@PathParam("calorieGoal") Integer calorieGoal) {
        Query query = em.createNamedQuery("Report.findByCalorieGoal");
        query.setParameter("calorieGoal", calorieGoal);
        return query.getResultList();
    }

    @GET
    @Path("Calories Burned Per Step/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public double caloriesBurnedPerStep(@PathParam("userid") Integer userid) {
        Userinfo user = em.getReference(Userinfo.class, userid);
        int weight = user.getWeight();
        int stepsPerMile = user.getStepsPerMile();
        double cbps = 0;
        cbps = (weight * 2.205 * 0.49) / stepsPerMile;
        return cbps;
    }

    @GET
    @Path("Basal Metabolic Rate/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public double basalMetabolicRate(@PathParam("userid") Integer userid) {
        Calendar cal = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        Userinfo user = em.getReference(Userinfo.class, userid);
        int age = 0;
        age = cal.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (cal.get(Calendar.DAY_OF_YEAR) > dob.get(Calendar.DAY_OF_YEAR)) {
            age += 1;
        }
        double bmr = 0;
        if ("male" == user.getGender()) {
            bmr = (13.75 * user.getWeight()) + (5.003 * user.getHeight() - (6.755 * age) + 66.5);
        } else {
            bmr = (9.563 * user.getWeight()) + (1.85 * user.getHeight() - (4.676 * age) + 655.1);
        }
        return bmr;
    }

    @GET
    @Path("Total Calories Burned/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public double totalCaloriesBurned(@PathParam("userid") Integer userid) {
        Userinfo user = em.getReference(Userinfo.class, userid);
        double tcb = 0;
        double bmr = basalMetabolicRate(userid);
        int loa = user.getLevelOfActivity();
        switch (loa) {
            case 1:
                tcb = bmr * 1.2;
                break;
            case 2:
                tcb = bmr * 1.375;
                break;
            case 3:
                tcb = bmr * 1.55;
                break;
            case 4:
                tcb = bmr * 1.725;
                break;
            case 5:
                tcb = bmr * 1.9;
                break;
        }
        return tcb;
    }

    @GET
    @Path("Show the report/{userid}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public ShowReport showTheReport(@PathParam("userid") Integer userid, @PathParam("date") Date date) {
        TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid = :userid AND r.date = :date", Report.class);
        q.setParameter("userid", userid);
        q.setParameter("date", date);
        Report rep = q.getSingleResult();
        int calorieGoal = rep.getCalorieGoal();
        int tcalorieConsum = rep.getTcalorieConsum();
        int tcalorieBurn = rep.getTcalorieBurn();
        double remainedCalorie = 0;
        remainedCalorie = calorieGoal - tcalorieConsum + tcalorieBurn;
        if(remainedCalorie <= 0){
            remainedCalorie = 0;
        }
        //double tcalorieBurnedAtRest = totalCaloriesBurned(userid);
        ShowReport sr = new ShowReport(tcalorieConsum, tcalorieBurn, remainedCalorie);// tcalorieBurnedAtRest);
        return sr;
    }

    @GET
    @Path("Show the report for a period of time/{userid}/{startingDate}/{endingDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public ShowPeriodReport showThePeriodReport(@PathParam("userid") Integer userid, @PathParam("startingDate") Date startingdate, @PathParam("endingDate") Date endingdate) {
        TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid = :userid AND r.date >= :startingdate AND r.date <= :endingdate", Report.class);
        q.setParameter("userid", userid);
        q.setParameter("startingdate", startingdate);
        q.setParameter("endingdate", endingdate);
        List<Report> rep = q.getResultList();
        int totalCalorieConsum = 0;
        int totalCalorieBurn = 0;
        int totalSteps = 0;
        for(int i = 0; i < rep.size(); i++){
            totalCalorieConsum = totalCalorieConsum + rep.get(i).getTcalorieConsum();
            totalCalorieBurn = totalCalorieBurn + rep.get(i).getTcalorieBurn();
            totalSteps = totalSteps + rep.get(i).getTsteps();
        }
        ShowPeriodReport spr = new ShowPeriodReport(totalCalorieConsum, totalCalorieBurn, totalSteps);
        return spr;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
