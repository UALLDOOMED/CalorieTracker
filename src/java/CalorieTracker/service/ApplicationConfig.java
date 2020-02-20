/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalorieTracker.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author 44931
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(CalorieTracker.service.ConsumptionFacadeREST.class);
        resources.add(CalorieTracker.service.CredentialFacadeREST.class);
        resources.add(CalorieTracker.service.FoodFacadeREST.class);
        resources.add(CalorieTracker.service.ReportFacadeREST.class);
        resources.add(CalorieTracker.service.UserinfoFacadeREST.class);
    }
    
}
