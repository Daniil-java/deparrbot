package com.kuklin.aviabot.models.flightInfo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class FlightInfo {
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private String scheduledDeparture;
    private String scheduledArrival;
    private String actualArrival;
    private String status;
    private String terminal;
    private String gate;

    public static boolean validateFlightNumber(String flightNumber) {
        //TODO
        return true;
    }

    public String getFlightInfoText() {
        StringBuilder sb = new StringBuilder();
        sb.append(airline).append(" (" + flightNumber + ")").append("\n");
        sb.append(departureAirport).append(" -> ").append(arrivalAirport).append("\n");
        sb.append("<b>DEP: </b>").append(scheduledDeparture).append("\n");
        sb.append("<b>ARR: </b>").append(scheduledArrival).append("\n");
        sb.append("<b>STATUS: </b>").append(status).append("\n");
        sb.append("<b>TERMINAL: </b>").append(terminal).append(" ");
        sb.append("<b>GATE: </b>").append(gate).append("\n");

        return sb.toString();
    }

    public static String getFlightInfoListText(List<FlightInfo> flightInfos) {
        StringBuilder sb = new StringBuilder();
        sb.append("<b>Расписание: </b>").append("\n\n");

        for (FlightInfo info: flightInfos) {
            sb.append(info.airline).append(" (" + info.flightNumber + ")").append("\n");
            sb.append(info.departureAirport).append(" -> ").append(info.arrivalAirport).append("\n");
            sb.append("<b>ОТПРАВ АИР: </b>").append(info.departureAirport).append("\n");
            sb.append("<b>ПРИБЫТ АИР: </b>").append(info.arrivalAirport).append("\n");
            sb.append("<b>ОТПРАВ: </b>").append(info.scheduledDeparture).append("\n");
            sb.append("<b>ПРИБЫТ: </b>").append(info.scheduledDeparture).append("\n");
        }

        return sb.toString();
    }

}
