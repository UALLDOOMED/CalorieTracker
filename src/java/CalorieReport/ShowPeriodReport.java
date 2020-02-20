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
public class ShowPeriodReport {

    int totalCalorieConsum;
    int totalCalorieBurn;
    int totalSteps;

    public int getConsum() {
        return totalCalorieConsum;
    }

    public int getBurn() {
        return totalCalorieBurn;
    }

    public int getSteps() {
        return totalSteps;
    }

    public void setConsum(int consum) {
        this.totalCalorieConsum = consum;
    }

    public void setBurn(int burn) {
        this.totalCalorieBurn = burn;
    }

    public void setSteps(int steps) {
        this.totalSteps = steps;
    }

    public ShowPeriodReport() {
    }

    public ShowPeriodReport(int consum, int burn, int steps) {
        this.totalCalorieBurn = burn;
        this.totalCalorieConsum = consum;
        this.totalSteps = steps;
    }
}
