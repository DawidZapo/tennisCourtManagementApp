package com.springboot.tennisCourtManagementApp.dto;

public class SettingManagerDto {
    private String nameDisplay;
    private Boolean showReservationDurationTime;
    private Boolean clientAutoComplete;
    private Boolean showNonSummaryReservationsWithDifferentColor;
    private Boolean showPaidIcon;
    private Boolean showIfCashOrCardIcon;
    private Boolean showNonSummaryIcon;
    private Boolean showCourtIconsInReservationTable;
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

    public Boolean getShowNonSummaryReservationsWithDifferentColor() {
        return showNonSummaryReservationsWithDifferentColor;
    }

    public void setShowNonSummaryReservationsWithDifferentColor(Boolean showNonSummaryReservationsWithDifferentColor) {
        this.showNonSummaryReservationsWithDifferentColor = showNonSummaryReservationsWithDifferentColor;
    }

    public Boolean getShowPaidIcon() {
        return showPaidIcon;
    }

    public void setShowPaidIcon(Boolean showPaidIcon) {
        this.showPaidIcon = showPaidIcon;
    }

    public Boolean getShowIfCashOrCardIcon() {
        return showIfCashOrCardIcon;
    }

    public void setShowIfCashOrCardIcon(Boolean showIfCashOrCardIcon) {
        this.showIfCashOrCardIcon = showIfCashOrCardIcon;
    }

    public Boolean getShowNonSummaryIcon() {
        return showNonSummaryIcon;
    }

    public void setShowNonSummaryIcon(Boolean showNonSummaryIcon) {
        this.showNonSummaryIcon = showNonSummaryIcon;
    }

    public Boolean getShowCourtIconsInReservationTable() {
        return showCourtIconsInReservationTable;
    }

    public void setShowCourtIconsInReservationTable(Boolean showCourtIconsInReservationTable) {
        this.showCourtIconsInReservationTable = showCourtIconsInReservationTable;
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
                ", showNonSummaryReservationsWithDifferentColor=" + showNonSummaryReservationsWithDifferentColor +
                ", showPaidIcon=" + showPaidIcon +
                ", showIfCashOrCardIcon=" + showIfCashOrCardIcon +
                ", showNonSummaryIcon=" + showNonSummaryIcon +
                ", showCourtIconsInReservationTable=" + showCourtIconsInReservationTable +
                ", tileColor='" + tileColor + '\'' +
                '}';
    }
}
