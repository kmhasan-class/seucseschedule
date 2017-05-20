/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.ac.seu.seucseschedule;

import java.io.Serializable;

/**
 *
 * @author kmhasan
 */
public enum Day implements Serializable {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    SATURDAY;
    
    public String getShortName() {
        return name().substring(0, 3);
    }
}
