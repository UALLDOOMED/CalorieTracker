/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalorieReport;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
/**
 *
 * @author 44931
 */
public class ShowReport {

    int tcalorieConsum;
    int tcalorieBurn;
    double remainedCalorie;

    public int gettcalorieConsum() {
        return tcalorieConsum;
    }

    public int gettcalorieBurn() {
        return tcalorieBurn;
    }
    
    public double getremainedCalorie(){
        return remainedCalorie;
    }
    
    public void settcalorieConsum(int tcalorieConsum){
        this.tcalorieConsum = tcalorieConsum;
    }
    
    public void settcalorieBurn(int tcalorieBurn){
        this.tcalorieBurn = tcalorieBurn;
    }
    
    public void setremainedCalorie(double remainedCalorie){
        this.remainedCalorie = remainedCalorie;
    }
    
    public ShowReport(){
    }
    
    public ShowReport(int tcalorieConsum, int tcalorieBurn, double remainedCalorie){//, double tcaloriesBurnedAtRest){
        this.tcalorieConsum = tcalorieConsum;
        this.tcalorieBurn = tcalorieBurn;
        this.remainedCalorie = remainedCalorie;//- tcaloriesBurnedAtRest;
    }
}
