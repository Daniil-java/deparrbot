package com.kuklin.aviabot.entities;

import com.kuklin.aviabot.models.FlightDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightCode;
    private String number;

    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private String scheduledDeparture;
    private String scheduledArrival;
    private String actualArrival;
    private String status;
    private String terminal;
    private String gate;

    public static Flight toEntity(FlightDto dto) {
        if (dto == null) {
            return null;
        }

        Flight flight = new Flight();
        flight.setFlightCode(dto.getFlight());
        flight.setNumber(dto.getNumber());
        flight.setAirline(dto.getAirline());
        flight.setDepartureAirport(dto.getDepartureAirport());
        flight.setArrivalAirport(dto.getArrivalAirport());
        flight.setScheduledDeparture(dto.getScheduledDeparture());
        flight.setScheduledArrival(dto.getScheduledArrival());
        flight.setActualArrival(dto.getActualArrival());
        flight.setStatus(dto.getStatus());
        flight.setTerminal(dto.getTerminal());
        flight.setGate(dto.getGate());

        return flight;
    }

    public boolean equalsDto(FlightDto dto) {
        if (dto == null) return false;

        return Objects.equals(airline, dto.getAirline()) &&
                Objects.equals(departureAirport, dto.getDepartureAirport()) &&
                Objects.equals(arrivalAirport, dto.getArrivalAirport()) &&
                Objects.equals(scheduledDeparture, dto.getScheduledDeparture()) &&
                Objects.equals(scheduledArrival, dto.getScheduledArrival()) &&
                Objects.equals(actualArrival, dto.getActualArrival()) &&
                Objects.equals(status, dto.getStatus()) &&
                Objects.equals(terminal, dto.getTerminal()) &&
                Objects.equals(gate, dto.getGate());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(airline, flight.airline) &&
                Objects.equals(departureAirport, flight.departureAirport) &&
                Objects.equals(arrivalAirport, flight.arrivalAirport) &&
                Objects.equals(scheduledDeparture, flight.scheduledDeparture) &&
                Objects.equals(scheduledArrival, flight.scheduledArrival) &&
                Objects.equals(actualArrival, flight.actualArrival) &&
                Objects.equals(status, flight.status) &&
                Objects.equals(terminal, flight.terminal) &&
                Objects.equals(gate, flight.gate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                airline,
                departureAirport,
                arrivalAirport,
                scheduledDeparture,
                scheduledArrival,
                actualArrival,
                status,
                terminal,
                gate
        );
    }


}
