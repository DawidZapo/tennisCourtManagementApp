package com.springboot.tennisCourtManagementApp.dto;

public class SettingManagerDto {
    private String nameDisplay;
    private Boolean showReservationDurationTime;
    private Boolean clientAutoComplete;
    private Boolean nonSummaryReservationsWithDifferentColor;
    private Boolean showPaidIcon;
    private Boolean showCashOrCardIcon;
    private Boolean showNonSummaryIcon;
    private Boolean showCourtsIconInReservationTable;
    private String tileColor;

    public String getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public Boolean getShowReservationDurationTime() {
        return showReservationDurationTime;
    }

    public void setShowReservationDurationTime(Boolean showReservationDurationTime) {
        this.showReservationDurationTime = showReservationDurationTime;
    }

    public Boolean getClientAutoComplete() {
        return clientAutoComplete;
    }

    public void setClientAutoComplete(Boolean clientAutoComplete) {
        this.clientAutoComplete = clientAutoComplete;
    }

    public Boolean getNonSummaryReservationsWithDifferentColor() {
        return nonSummaryReservationsWithDifferentColor;
    }

    public void setNonSummaryReservationsWithDifferentColor(Boolean nonSummaryReservationsWithDifferentColor) {
        this.nonSummaryReservationsWithDifferentColor = nonSummaryReservationsWithDifferentColor;
    }

    public Boolean getShowPaidIcon() {
        return showPaidIcon;
    }

    public void setShowPaidIcon(Boolean showPaidIcon) {
        this.showPaidIcon = showPaidIcon;
    }

    public Boolean getShowCashOrCardIcon() {
        return showCashOrCardIcon;
    }

    public void setShowCashOrCardIcon(Boolean showCashOrCardIcon) {
        this.showCashOrCardIcon = showCashOrCardIcon;
    }

    public Boolean getShowNonSummaryIcon() {
        return showNonSummaryIcon;
    }

    public void setShowNonSummaryIcon(Boolean showNonSummaryIcon) {
        this.showNonSummaryIcon = showNonSummaryIcon;
    }

    public Boolean getShowCourtsIconInReservationTable() {
        return showCourtsIconInReservationTable;
    }

    public void setShowCourtsIconInReservationTable(Boolean showCourtsIconInReservationTable) {
        this.showCourtsIconInReservationTable = showCourtsIconInReservationTable;
    }

    public String getTileColor() {
        return tileColor;
    }

    public void setTileColor(String tileColor) {
        this.tileColor = tileColor;
    }

    @Override
    public String toString() {
        return "SettingManagerDto{" +
                "nameDisplay='" + nameDisplay + '\'' +
                ", showReservationDurationTime=" + showReservationDurationTime +
                ", clientAutoComplete=" + clientAutoComplete +
                ", nonSummaryReservationsWithDifferentColor=" + nonSummaryReservationsWithDifferentColor +
                ", showPaidIcon=" + showPaidIcon +
                ", showCashOrCardIcon=" + showCashOrCardIcon +
                ", showNonSummaryIcon=" + showNonSummaryIcon +
                ", showCourtsIconInReservationTable=" + showCourtsIconInReservationTable +
                ", tileColor='" + tileColor + '\'' +
                '}';
    }
}
