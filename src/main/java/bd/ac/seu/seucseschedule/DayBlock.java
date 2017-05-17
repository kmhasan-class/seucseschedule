/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.ac.seu.seucseschedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author kmhasan
 */
public class DayBlock implements Serializable {

    private Day day;
    private List<Section> s0830;
    private List<Section> s1000;
    private List<Section> s1130;
    private List<Section> s0100;
    private List<Section> s0230;
    private List<Section> s0400;
    private List<Section> s0530;

    private List<Section> filteredS0830;
    private List<Section> filteredS1000;
    private List<Section> filteredS1130;
    private List<Section> filteredS0100;
    private List<Section> filteredS0230;
    private List<Section> filteredS0400;
    private List<Section> filteredS0530;

    private List<List<Section>> listOfLists;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.day);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DayBlock other = (DayBlock) obj;
        if (this.day != other.day) {
            return false;
        }
        return true;
    }

    public DayBlock(Day day) {
        this.day = day;
        s0830 = new ArrayList<>();
        s1000 = new ArrayList<>();
        s1130 = new ArrayList<>();
        s0100 = new ArrayList<>();
        s0230 = new ArrayList<>();
        s0400 = new ArrayList<>();
        s0530 = new ArrayList<>();

        filteredS0830 = new ArrayList<>();
        filteredS1000 = new ArrayList<>();
        filteredS1130 = new ArrayList<>();
        filteredS0100 = new ArrayList<>();
        filteredS0230 = new ArrayList<>();
        filteredS0400 = new ArrayList<>();
        filteredS0530 = new ArrayList<>();

        listOfLists = new ArrayList<>();
        listOfLists.add(s0830);
        listOfLists.add(s1000);
        listOfLists.add(s1130);
        listOfLists.add(s0100);
        listOfLists.add(s0230);
        listOfLists.add(s0400);
        listOfLists.add(s0530);
        listOfLists.add(filteredS0830);
        listOfLists.add(filteredS1000);
        listOfLists.add(filteredS1130);
        listOfLists.add(filteredS0100);
        listOfLists.add(filteredS0230);
        listOfLists.add(filteredS0400);
        listOfLists.add(filteredS0530);
    }

    public List<Section> getS0830() {
        return s0830;
    }

    public List<Section> getS1000() {
        return s1000;
    }

    public List<Section> getS1130() {
        return s1130;
    }

    public Day getDay() {
        return day;
    }

    public List<Section> getS0100() {
        return s0100;
    }

    public List<Section> getS0230() {
        return s0230;
    }

    public List<Section> getS0400() {
        return s0400;
    }

    public List<Section> getS0530() {
        return s0530;
    }

    public List<Section> getFilteredS0830() {
        return filteredS0830;
    }

    public boolean getDataFoundS0830() {
        return filteredS0830.size() != 0;
    }

    public boolean getDataFoundS1000() {
        return filteredS1000.size() != 0;
    }

    public boolean getDataFoundS1130() {
        return filteredS1130.size() != 0;
    }

    public boolean getDataFoundS0100() {
        return filteredS0100.size() != 0;
    }

    public boolean getDataFoundS0230() {
        return filteredS0230.size() != 0;
    }

    public boolean getDataFoundS0400() {
        return filteredS0400.size() != 0;
    }

    public boolean getDataFoundS0530() {
        return filteredS0530.size() != 0;
    }

    public void updateRoomFacultyFilter(String courseFilter, String roomFilter, String facultyFilter) {
        if (courseFilter == null) {
            courseFilter = "";
        }
        if (roomFilter == null) {
            roomFilter = "";
        }
        if (facultyFilter == null) {
            facultyFilter = "";
        }

        filteredS0830.clear();
        filteredS1000.clear();
        filteredS1130.clear();
        filteredS0100.clear();
        filteredS0230.clear();
        filteredS0400.clear();
        filteredS0530.clear();

        for (Section section : s0830) {
            if (courseFilter.length() == 0 && roomFilter.length() == 0 && facultyFilter.length() == 0) {
                filteredS0830.add(section);
            } else if (section.getSectionLabel().contains(courseFilter) && section.getRoom().contains(roomFilter) && section.getFacultyInitials().contains(facultyFilter)) {
                filteredS0830.add(section);
            }
        }

        for (Section section : s1000) {
            if (courseFilter.length() == 0 && roomFilter.length() == 0 && facultyFilter.length() == 0) {
                filteredS1000.add(section);
            } else if (section.getSectionLabel().contains(courseFilter) && section.getRoom().contains(roomFilter) && section.getFacultyInitials().contains(facultyFilter)) {
                filteredS1000.add(section);
            }
        }

        for (Section section : s1130) {
            if (courseFilter.length() == 0 && roomFilter.length() == 0 && facultyFilter.length() == 0) {
                filteredS1130.add(section);
            } else if (section.getSectionLabel().contains(courseFilter) && section.getRoom().contains(roomFilter) && section.getFacultyInitials().contains(facultyFilter)) {
                filteredS1130.add(section);
            }
        }

        for (Section section : s0100) {
            if (courseFilter.length() == 0 && roomFilter.length() == 0 && facultyFilter.length() == 0) {
                filteredS0100.add(section);
            } else if (section.getSectionLabel().contains(courseFilter) && section.getRoom().contains(roomFilter) && section.getFacultyInitials().contains(facultyFilter)) {
                filteredS0100.add(section);
            }
        }

        for (Section section : s0230) {
            if (courseFilter.length() == 0 && roomFilter.length() == 0 && facultyFilter.length() == 0) {
                filteredS0230.add(section);
            } else if (section.getSectionLabel().contains(courseFilter) && section.getRoom().contains(roomFilter) && section.getFacultyInitials().contains(facultyFilter)) {
                filteredS0230.add(section);
            }
        }

        for (Section section : s0400) {
            if (courseFilter.length() == 0 && roomFilter.length() == 0 && facultyFilter.length() == 0) {
                filteredS0400.add(section);
            } else if (section.getSectionLabel().contains(courseFilter) && section.getRoom().contains(roomFilter) && section.getFacultyInitials().contains(facultyFilter)) {
                filteredS0400.add(section);
            }
        }

        for (Section section : s0530) {
            if (courseFilter.length() == 0 && roomFilter.length() == 0 && facultyFilter.length() == 0) {
                filteredS0530.add(section);
            } else if (section.getSectionLabel().contains(courseFilter) && section.getRoom().contains(roomFilter) && section.getFacultyInitials().contains(facultyFilter)) {
                filteredS0530.add(section);
            }
        }

        for (List<Section> sectionList : listOfLists) {
            Collections.sort(sectionList);
        }
        System.out.println("Done updating");
    }

    public List<Section> getFilteredS1000() {
        return filteredS1000;
    }

    public List<Section> getFilteredS1130() {
        return filteredS1130;
    }

    public List<Section> getFilteredS0100() {
        return filteredS0100;
    }

    public List<Section> getFilteredS0230() {
        return filteredS0230;
    }

    public List<Section> getFilteredS0400() {
        return filteredS0400;
    }

    public List<Section> getFilteredS0530() {
        return filteredS0530;
    }

    public List<List<Section>> getListOfLists() {
        return listOfLists;
    }
}
