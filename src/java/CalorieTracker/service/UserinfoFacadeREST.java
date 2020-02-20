/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalorieTracker.service;

import CalorieTracker.Userinfo;
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
@Path("calorietracker.userinfo")
public class UserinfoFacadeREST extends AbstractFacade<Userinfo> {

    @PersistenceContext(unitName = "5046_AssignmentPU")
    private EntityManager em;

    public UserinfoFacadeREST() {
        super(Userinfo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Userinfo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Userinfo entity) {
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
    public Userinfo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userinfo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userinfo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("findByName/{name}")
    @Produces({"application/json"})
    public List<Userinfo> findByName(@PathParam("name") String name) {
        Query query = em.createNamedQuery("Userinfo.findByName");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @GET
    @Path("findBySurename/{surename}")
    @Produces({"application/json"})
    public List<Userinfo> findBySurename(@PathParam("surename") String surename) {
        Query query = em.createNamedQuery("Userinfo.findBySurename");
        query.setParameter("surename", surename);
        return query.getResultList();
    }

    @GET
    @Path("findByEmail/{email}")
    @Produces({"application/json"})
    public List<Userinfo> findByEmail(@PathParam("email") String email) {
        Query query = em.createNamedQuery("Userinfo.findByEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }

    @GET
    @Path("findByDob/{dob}")
    @Produces({"application/json"})
    public List<Userinfo> findByDob(@PathParam("dob") Date dob) {
        Query query = em.createNamedQuery("Userinfo.findByDob");
        query.setParameter("dob", dob);
        return query.getResultList();
    }

    @GET
    @Path("findByHeight/{height}")
    @Produces({"application/json"})
    public List<Userinfo> findByHeight(@PathParam("height") Integer height) {
        Query query = em.createNamedQuery("Userinfo.findByHeight");
        query.setParameter("height", height);
        return query.getResultList();
    }

    @GET
    @Path("findByWeight/{weight}")
    @Produces({"application/json"})
    public List<Userinfo> findByWeight(@PathParam("weight") Integer weight) {
        Query query = em.createNamedQuery("Userinfo.findByWeight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }

    @GET
    @Path("findByGender/{gender}")
    @Produces({"application/json"})
    public List<Userinfo> findByGender(@PathParam("gender") String gender) {
        Query query = em.createNamedQuery("Userinfo.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }

    @GET
    @Path("findByAddress/{address}")
    @Produces({"application/json"})
    public List<Userinfo> findByAddress(@PathParam("address") String address) {
        Query query = em.createNamedQuery("Userinfo.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }

    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({"application/json"})
    public List<Userinfo> findByPostcode(@PathParam("postcode") String postcode) {
        Query query = em.createNamedQuery("Userinfo.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }

    @GET
    @Path("findByLevelOfActivity/{levelOfActivity}")
    @Produces({"application/json"})
    public List<Userinfo> findByLevelOfActivity(@PathParam("levelOfActivity") Integer levelOfActivity) {
        Query query = em.createNamedQuery("Userinfo.findByLevelOfActivity");
        query.setParameter("levelOfActivity", levelOfActivity);
        return query.getResultList();
    }

    @GET
    @Path("findByStepsPerMile/{stepsPerMile}")
    @Produces({"application/json"})
    public List<Userinfo> findByStepsPerMile(@PathParam("stepsPerMile") Integer stepsPerMile) {
        Query query = em.createNamedQuery("Userinfo.findByStepsPerMile");
        query.setParameter("stepsPerMile", stepsPerMile);
        return query.getResultList();
    }

    @GET
    @Path("findByHeightAndWeight/{height}/{weight}")
    @Produces({"application/json"})
    public List<Userinfo> findByHeightAndWeight(@PathParam("height") Integer height, @PathParam("weight") Integer weight) {
        TypedQuery<Userinfo> query = em.createQuery(
        "SELECT u FROM Userinfo u WHERE u.height >= :height AND u.weight >= :weight", Userinfo.class); 
        query.setParameter("height", height);
        query.setParameter("weight", weight);
        return query.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
