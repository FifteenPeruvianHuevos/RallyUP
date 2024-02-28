package com.example.rallyup.attendeeList;

public class AttendeeStatsClass {
    private String attName;
    private Integer checkInCount;

    public AttendeeStatsClass(String attName, Integer checkInCount) {
        this.attName = attName;
        this.checkInCount = checkInCount;
    }

    public String getAttName() {
        return attName;
    }

    public void setAttName(String attName) {
        this.attName = attName;
    }

    public Integer getCheckInCount() {
        return checkInCount;
    }

    public void setCheckInCount(Integer checkInCount) {
        this.checkInCount = checkInCount;
    }
}