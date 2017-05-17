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
public enum TimeSlot implements Serializable {
    _0830 ("08:30 AM - 09:50 AM"),
    _1000 ("10:00 AM - 11:20 AM"),
    _1130 ("11:30 AM - 12:50 PM"),
    _0100 ("01:00 PM - 02:20 PM"),
    _0230 ("02:30 PM - 03:50 PM"),
    _0400 ("04:00 PM - 05:20 PM"),
    _0530 ("05:30 PM - 06:50 PM");
    
    private String output;
    
    private TimeSlot(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return output;
    }

    public String getOutput() {
        return output;
    }
    
}
