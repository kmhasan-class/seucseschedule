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
public class Section implements Serializable, Comparable {
    private String courseCode;
    private int section;
    private String facultyInitials;
    private Day day;
    private TimeSlot timeSlot;
    private String room;

    public Section(String courseCode, int section, String facultyInitials, Day day, TimeSlot timeSlot, String room) {
        this.courseCode = courseCode;
        this.section = section;
        this.facultyInitials = facultyInitials;
        this.day = day;
        this.timeSlot = timeSlot;
        if (room.startsWith("(AR)"))
            this.room = room.substring(4);
        else this.room = room;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getSectionLabel() {
        return courseCode + "." + section;
    }
    
    public int getSection() {
        return section;
    }

    public String getFacultyInitials() {
        return facultyInitials;
    }

    public Day getDay() {
        return day;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return courseCode + "." + section + " [" + facultyInitials + "] (" + room + ")";
    }

    @Override
    public int compareTo(Object o) {
        Section section1 = this;
        Section section2 = (Section) o;
        return section1.getRoom().compareTo(section2.getRoom());
    }
    
}
