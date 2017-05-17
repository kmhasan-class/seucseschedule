/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.ac.seu.seucseschedule;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author kmhasan
 */
@ManagedBean(eager = true)
@ViewScoped
public class ScheduleView implements Serializable {

    private String message;
    private int semesterNumber;
    private List<Section> sectionList;
    private List<DayBlock> dayBlockList;
    private Map<Day, DayBlock> dayBlockMap;
    private String roomFilter;
    private String facultyFilter;
    private String courseFilter;
    private String semesterLabel;
    private String semesterLabels[] = {"Spring", "Summer", "Fall"};
    private int yearOffset;
    private int currentYear;
    
    public ScheduleView() {
        semesterNumber = 45;
        semesterLabel = "45 - Spring 2017";
//        semesterNumber = 43;
//        semesterLabel = "43 - Summer 2016";
        yearOffset = 2002;
        onSemesterChange();
    }

    public void setSemesterNumber(int semesterNumber) {
        this.semesterNumber = semesterNumber;
        onSemesterChange();
    }

    public void onSemesterChange() {
        semesterLabel = semesterNumber + " - " + semesterLabels[semesterNumber % 3] + " " + (semesterNumber / 3 + yearOffset);
        sectionList = new ArrayList<>();
        dayBlockList = new ArrayList<>();
        dayBlockMap = new HashMap<>();

        for (Day day : Day.values()) {
//            if (day.equals(Day.THURSDAY))
//                continue;
            DayBlock dayBlock = new DayBlock(day);
            dayBlockList.add(dayBlock);
            dayBlockMap.put(day, dayBlock);
        }

//        semesterNumber = Integer.parseInt(semesterLabel.split("\\ ")[0]);

        try {
            URL url = new URL("http://my.seu.ac.bd/~kmhasan/__WebServices/get_schedule_json.php?semester=" + semesterNumber);
            readJson(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ScheduleView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PostConstruct
    public void init() {
        System.out.println("init got called");
    }

    void readJson(URL url) {
        //Collections collections = new TimetablingCollections();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String jsonLine = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                jsonLine += line;
            }

            JsonArray entries = (JsonArray) new JsonParser().parse(jsonLine);
            for (JsonElement entry : entries) {
                String courseCode = ((JsonObject) entry).get("courseCode").getAsString();
                int sectionNumber = ((JsonObject) entry).get("section").getAsInt();
                String facultyInitials = ((JsonObject) entry).get("facultyInitials").getAsString();
                String dayString = ((JsonObject) entry).get("day").getAsString().toUpperCase();
                String timeString = ((JsonObject) entry).get("startTime").getAsString();
                String room = ((JsonObject) entry).get("room").getAsString();
                Day day = null;
                TimeSlot timeSlot = null;

                if (dayString.equals("THU")) {
                    continue;
                }

                for (Day d : Day.values()) {
                    if (d.name().startsWith(dayString)) {
                        day = d;
                    }
                }
                for (TimeSlot t : TimeSlot.values()) {
                    if (t.name().substring(1).equals(timeString.replaceAll(":", ""))) {
                        timeSlot = t;
                    }
                }
                Section section = new Section(courseCode, sectionNumber, facultyInitials, day, timeSlot, room);
                sectionList.add(section);

                DayBlock dayBlock = dayBlockMap.get(section.getDay());
                if (dayBlock == null) {
                    System.err.println("day block is null");
                    continue;
                    //System.exit(0);
                }
                switch (section.getTimeSlot()) {
                    case _0830:
                        dayBlock.getS0830().add(section);
                        dayBlock.getFilteredS0830().add(section);
                        break;
                    case _1000:
                        dayBlock.getS1000().add(section);
                        dayBlock.getFilteredS1000().add(section);
                        break;
                    case _1130:
                        dayBlock.getS1130().add(section);
                        dayBlock.getFilteredS1130().add(section);
                        break;
                    case _0100:
                        dayBlock.getS0100().add(section);
                        dayBlock.getFilteredS0100().add(section);
                        break;
                    case _0230:
                        dayBlock.getS0230().add(section);
                        dayBlock.getFilteredS0230().add(section);
                        break;
                    case _0400:
                        dayBlock.getS0400().add(section);
                        dayBlock.getFilteredS0400().add(section);
                        break;
                    case _0530:
                        dayBlock.getS0530().add(section);
                        dayBlock.getFilteredS0530().add(section);
                        break;
                    default:
                        break;
                }

                //System.out.println(section);
            }

            for (DayBlock dayBlock : dayBlockList) {
                for (List<Section> sectionList : dayBlock.getListOfLists()) {
                    Collections.sort(sectionList);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public List<DayBlock> getDayBlockList() {
        return dayBlockList;
    }

    public Map<Day, DayBlock> getDayBlockMap() {
        return dayBlockMap;
    }

    public String getRoomFilter() {
        return roomFilter;
    }

    public void setRoomFilter(String roomFilter) {
        this.roomFilter = roomFilter;
    }

    public String getFacultyFilter() {
        return facultyFilter;
    }

    public void setFacultyFilter(String facultyFilter) {
        this.facultyFilter = facultyFilter;
    }

    public void filterAction(ActionEvent actionEvent) {
        //onSemesterChange();
        for (DayBlock dayBlock : dayBlockList) {
            dayBlock.updateRoomFacultyFilter(courseFilter, roomFilter, facultyFilter);
        }
    }

    public String getCourseFilter() {
        return courseFilter;
    }

    public void setCourseFilter(String courseFilter) {
        this.courseFilter = courseFilter;
    }

    public String getSemesterLabel() {
        return semesterLabel;
    }

    public void setSemesterLabel(String semesterLabel) {
        this.semesterLabel = semesterLabel;
        System.out.println("semester label " + semesterLabel);
    }
}
