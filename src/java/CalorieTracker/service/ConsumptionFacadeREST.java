/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalorieTracker.service;

import CalorieTracker.Consumption;
import CalorieTracker.Food;
import java.sql.Date;
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
@Path("calorietracker.consumption")
public class ConsumptionFacadeREST extends AbstractFacade<Consumption> {

    @PersistenceContext(unitName = "5046_AssignmentPU")
    private EntityManager em;

    public ConsumptionFacadeREST() {
        super(Consumption.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Consumption entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Consumption entity) {
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
    public Consumption find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findByConid/{conid}")
    @Produces({"application/json"})
    public List<Consumption> findByConid(@PathParam("conid") Integer conid) {
        Query query = em.createNamedQuery("Consumption.findByConid");
        query.setParameter("conid", conid);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDate/{date}")
    @Produces({"application/json"})
    public List<Consumption> findByDate(@PathParam("date") Date date) {
        Query query = em.createNamedQuery("Consumption.findByDate");
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @GET
    @Path("findByQuantity/{quantity}")
    @Produces({"application/json"})
    public List<Consumption> findByQuantity(@PathParam("quantity") Integer quantity) {
        Query query = em.createNamedQuery("Consumption.findByQuantity");
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUserGenderAndFoodCategory/{gender}/{category}") 
    @Produces({"application/json"}) 
    public List<Consumption> findByUserGenderAndFoodCategory(@PathParam("gender") String gender, @PathParam("category") String category){
        TypedQuery<Consumption> q = em.createQuery("SELECT c FROM Consumption c WHERE c.userid.gender = :gender AND c.foodid.category = :category", Consumption.class); 
        q.setParameter("gender", gender); 
        q.setParameter("category", category); 
        return q.getResultList();
    }
    
    @GET
    @Path("findByUserLoaAndFoodFat/{levelOfActivity}/{fat}")
    @Produces({"application/json"})
    public List<Consumption> findByUserLoaAndFoodFat(@PathParam("levelOfActivity") Integer levelOfActivity, @PathParam("fat") Integer fat) {
        Query query = em.createNamedQuery("Consumption.findByUserLoaAndFoodFat");
        query.setParameter("levelOfActivity", levelOfActivity);
        query.setParameter("fat", fat);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFoodid/{foodid}")
    @Produces({"application/json"})
    public List<Consumption> findByFoodid(@PathParam("foodid") Integer foodid) {
        TypedQuery<Consumption> q = em.createQuery("SELECT c FROM Consumption c WHERE c.foodid.foodid = :foodid", Consumption.class); 
        q.setParameter("foodid", foodid); 
        return q.getResultList();
    }
    
    @GET
    @Path("findByUserid/{userid}")
    @Produces({"application/json"})
    public List<Consumption> findByUserid(@PathParam("userid") Integer userid) {
        TypedQuery<Consumption> q = em.createQuery("SELECT c FROM Consumption c WHERE c.userid.userid = :userid", Consumption.class); 
        q.setParameter("userid", userid); 
        return q.getResultList();
    }
    
    @GET
    @Path("Total Calories Consumed/{userid}/{date}")
    @Produces(MediaType.TEXT_PLAIN)
    public int totalCaloriesConsumed(@PathParam("userid") Integer userid, @PathParam("date") Date date) {
        int tcc = 0;
        TypedQuery<Consumption> q = em.createQuery("SELECT c FROM Consumption c WHERE c.userid.userid = :userid AND c.date = :date", Consumption.class);
        q.setParameter("userid", userid); 
        q.setParameter("date", date); 
        List<Consumption> con = q.getResultList();
        for(int i = 0;i < con.size();i ++){
            Food food = con.get(i).getFoodid();
            int calorie = food.getCalorieAmount() * con.get(i).getQuantity();
            tcc = tcc + calorie; 
        }
        return tcc;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
