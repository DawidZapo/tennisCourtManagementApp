package com.springboot.tennisCourtManagementApp.entity;

import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "court_reservation")
public class CourtReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "court_number")
    private Integer courtNumber;
    @Column(name = "reservation_date")
    private LocalDate reservationDate;
    @Column(name = "time_start")
    private LocalTime timeStart;
    @Column(name = "time_end")
    private LocalTime timeEnd;
    @Column(name = "duration")
    private Double duration;
    @Column(name = "price_schedule")
    private Integer priceSchedule;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "is_doubles_match")
    private Boolean isDoublesMatch;
    @Column(name = "is_paid")
    private Boolean isPaid;
    @Column(name = "is_cash")
    private Boolean isCash;
    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="customer_id")
    private Customer customer;
    @Column(name = "accepted_by")
    private String acceptedBy;
    @Column(name = "accepted_at")
    private String acceptedAt;

    @Column(name = "valid_for_finance_summary")
    private Boolean validForFinanceSummary;
    public CourtReservation() {
    }

    public CourtReservation(Long id, Integer courtNumber, LocalDate reservationDate, LocalTime timeStart, LocalTime timeEnd, Double duration, Integer priceSchedule, Double totalPrice, Boolean isDoublesMatch, Boolean isPaid, Boolean isCash, String comments, Customer customer, String acceptedBy, String acceptedAt, Boolean validForFinanceSummary) {
        this.id = id;
        this.courtNumber = courtNumber;
        this.reservationDate = reservationDate;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.duration = duration;
        this.priceSchedule = priceSchedule;
        this.totalPrice = totalPrice;
        this.isDoublesMatch = isDoublesMatch;
        this.isPaid = isPaid;
        this.isCash = isCash;
        this.comments = comments;
        this.customer = customer;
        this.acceptedBy = acceptedBy;
        this.acceptedAt = acceptedAt;
        this.validForFinanceSummary = validForFinanceSummary;
    }

    // constructor to create possible reservation (not saved yet) to pass as argument in addReservation form
    public CourtReservation(Integer courtNumber, LocalDate reservationDate, LocalTime timeStart, LocalTime timeEnd) {
        this.courtNumber = courtNumber;
        this.reservationDate = reservationDate;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public CourtReservation(Integer courtNumber, LocalDate reservationDate, LocalTime timeStart, LocalTime timeEnd, Integer priceSchedule, Double totalPrice, Boolean isDoublesMatch, Boolean isPaid, String acceptedBy, Boolean validForFinanceSummary) {
        this.courtNumber = courtNumber;
        this.reservationDate = reservationDate;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.duration = (double) Duration.between(timeStart, timeEnd).toMinutes();
        this.priceSchedule = priceSchedule;
        this.totalPrice = totalPrice;
        this.isDoublesMatch = isDoublesMatch;
        this.isPaid = isPaid;
        this.isCash = null;
        this.acceptedBy = acceptedBy;
        this.acceptedAt = LocalDate.now().toString() + " " + LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.validForFinanceSummary = validForFinanceSummary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourtNumber() {
        return courtNumber;
    }

    public void setCourtNumber(Integer courtNumber) {
        this.courtNumber = courtNumber;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservation_date) {
        this.reservationDate = reservation_date;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getPriceSchedule() {
        return priceSchedule;
    }

    public void setPriceSchedule(Integer priceSchedule) {
        this.priceSchedule = priceSchedule;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean isDoublesMatch() {
        return isDoublesMatch;
    }

    public void setDoublesMatch(Boolean doublesMatch) {
        isDoublesMatch = doublesMatch;
    }

    public Boolean getDoublesMatch() {
        return isDoublesMatch;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public Boolean getCash() {
        return isCash;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Boolean isCash() {
        return isCash;
    }

    public void setCash(Boolean cash) {
        isCash = cash;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public String getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(String acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public Boolean getValidForFinanceSummary() {
        return validForFinanceSummary;
    }

    public void setValidForFinanceSummary(Boolean validForFinanceSummary) {
        this.validForFinanceSummary = validForFinanceSummary;
    }

    public String getDiscountNameForHTML() {
        return switch (priceSchedule) {
            case 1 -> "Cena regularna";
            case 2 -> "Dzieci, młodzież szkolna do lat 16";
            case 3 -> "Karta Rodzina 3+ (osoby dorosłe)";
            case 4 -> "Karta Rodzina 3+ (dzieci, młodzież szkolna do lat 16)";
            case 5 -> "Karta Gliwicki Senior 60+";
            case 6 -> "Karta Gliwicki Senior 75+";
            case 7 -> "Gliwicka Karta Mieszkańca";
            case 8 -> "Gliwicka Karta Mieszkańca (dzieci, młodzież szkolna do lat 16)";
            case 9 -> "Gliwickie stowarzyszenia i kluby sportowe";
            case 10 -> "Inne kluby sportowe i podmioty gospodarcze";
            default -> "Błąd";
        };
    }

    @Override
    public String toString() {
        return "CourtReservation{" +
                "id=" + id +
                ", courtNumber=" + courtNumber +
                ", reservationDate=" + reservationDate +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", duration=" + duration +
                ", priceSchedule=" + priceSchedule +
                ", totalPrice=" + totalPrice +
                ", isDoublesMatch=" + isDoublesMatch +
                ", isPaid=" + isPaid +
                ", isCash=" + isCash +
                ", comments='" + comments + '\'' +
                ", customer=" + customer +
                ", acceptedBy='" + acceptedBy + '\'' +
                ", acceptedAt='" + acceptedAt + '\'' +
                ", validForFinanceSummary=" + validForFinanceSummary +
                '}';
    }

    public String convertMinutesToHoursAndMinutes(Double minutes) {
        if (minutes < 0) {
            return "Błędna wartość";
        }

        int hours = (int) (minutes / 60);
        int remainingMinutes = (int) (minutes % 60);

        if (hours == 0) {
            return remainingMinutes + "min";
        } else if (remainingMinutes == 0) {
            return hours + "h";
        } else {
            return hours + "h" + remainingMinutes + "min";
        }
    }
    public String getPricePerHourForHTML(PriceSchedule priceSchedule){
        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.POLAND));
        boolean isHoliday = holidayManager.isHoliday(reservationDate);
        boolean isSaturday = DayOfWeek.SATURDAY.equals(reservationDate.getDayOfWeek());
        boolean isSunday = DayOfWeek.SUNDAY.equals(reservationDate.getDayOfWeek());
        LocalTime time15 = LocalTime.of(15,0);
        Double morningPrice = 0.0;
        Double afternoonPrice = 0.0;

        if(isHoliday || isSaturday || isSunday){
            if(!isDoublesMatch){
                morningPrice = priceSchedule.getOffdayMorningSingles();
                afternoonPrice = priceSchedule.getOffdayAfternoonSingles();
            }
            else{
                morningPrice = priceSchedule.getOffdayMorningDoubles();
                afternoonPrice = priceSchedule.getOffdayAfternoonDoubles();
            }
        }
        else{
            if(!isDoublesMatch){
                morningPrice = priceSchedule.getWorkdayMorningSingles();
                afternoonPrice = priceSchedule.getWorkdayAfternoonSingles();
            }
            else{
                morningPrice = priceSchedule.getWorkdayMorningDoubles();
                afternoonPrice = priceSchedule.getWorkdayAfternoonDoubles();
            }
        }

        if (timeStart.isBefore(time15)) {
            if (timeEnd.isAfter(time15)) {
                //oba cenniki
                return morningPrice + " PLN/h oraz " + afternoonPrice + " PLN/h";
            } else {
                //cennik poranny
                return morningPrice + " PLN/h";
            }
        }
        else {
            //cennik popołudniowy
            return afternoonPrice + " PLN/h";
        }

    }
    public static double getCalculatedPrice(LocalDate reservationDate, LocalTime timeStart, LocalTime timeEnd, Boolean isDoubles, PriceSchedule priceSchedule){
        double calculatedPrice = 0.0;
        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.POLAND));
        boolean isHoliday = holidayManager.isHoliday(reservationDate);
        boolean isSaturday = DayOfWeek.SATURDAY.equals(reservationDate.getDayOfWeek());
        boolean isSunday = DayOfWeek.SUNDAY.equals(reservationDate.getDayOfWeek());
        LocalTime time15 = LocalTime.of(15,0);
        Double morningPrice = 0.0;
        Double afternoonPrice = 0.0;

        if(isHoliday || isSaturday || isSunday){
            if(!isDoubles){
                morningPrice = priceSchedule.getOffdayMorningSingles();
                afternoonPrice = priceSchedule.getOffdayAfternoonSingles();
            }
            else{
                morningPrice = priceSchedule.getOffdayMorningDoubles();
                afternoonPrice = priceSchedule.getOffdayAfternoonDoubles();
            }
        }
        else{
            if(!isDoubles){
                morningPrice = priceSchedule.getWorkdayMorningSingles();
                afternoonPrice = priceSchedule.getWorkdayAfternoonSingles();
            }
            else{
                morningPrice = priceSchedule.getWorkdayMorningDoubles();
                afternoonPrice = priceSchedule.getWorkdayAfternoonDoubles();
            }
        }

        if (timeStart.isBefore(time15)) {
            if (timeEnd.isAfter(time15)) {
                //oba cenniki
                Double before15 = Duration.between(timeStart,time15).toMinutes()/60.0;
                Double after15 = Duration.between(time15,timeEnd).toMinutes()/60.0;
                calculatedPrice += (before15*morningPrice);
                calculatedPrice += (after15*afternoonPrice);
            } else {
                //cennik poranny
                Double duration = Duration.between(timeStart,timeEnd).toMinutes()/60.0;
                calculatedPrice += (duration*morningPrice);
            }
        }
        else {
            //cennik popołudniowy
            Double duration = Duration.between(timeStart,timeEnd).toMinutes()/60.0;
            calculatedPrice += (duration*afternoonPrice);
        }


        return calculatedPrice;
    }

    public static void sortReservations(List<CourtReservation> reservations) {
        Comparator<CourtReservation> byCourtNumber = Comparator.comparingInt(CourtReservation::getCourtNumber);
        Comparator<CourtReservation> byTimeStart = Comparator.comparing(CourtReservation::getTimeStart);

        reservations.sort(byCourtNumber.thenComparing(byTimeStart));
    }
}
