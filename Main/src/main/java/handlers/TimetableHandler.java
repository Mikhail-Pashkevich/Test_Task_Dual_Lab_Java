package handlers;

import entities.Bus;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TimetableHandler {
    public static List<Bus> effectiveBuses(List<Bus> buses) {

        return buses.stream()
                // If buses travel more than 60 minutes
                .filter(bus -> bus.getTravelTimeInMinutes() <= 60)
                // If buses starts at the same time and one of them reaches earlier
                .filter(outerBus -> buses.stream()
                        .noneMatch(innerBus ->
                                innerBus.equalsDepartureTime(outerBus) && innerBus.compareTravelTime(outerBus) == -1)
                )
                // If buses starts at different time and reaches at the same time
                .filter(outerBus -> buses.stream()
                        .noneMatch(innerBus ->
                                innerBus.equalsArrivalTime(outerBus) && innerBus.compareTravelTime(outerBus) == -1)
                )
                // If bus starts later and reaches earlier
                .filter(outerBus -> buses.stream()
                        .noneMatch(innerBus ->
                                innerBus.compareDepartureTime(outerBus) == 1 && innerBus.compareArrivalTime(outerBus) == -1
                                        && innerBus.compareTravelTime(outerBus) == -1)
                )
                // If buses has same departure and arrival times, but different company name
                .filter(outerBus -> buses.stream()
                        .noneMatch(innerBus ->
                                innerBus.equalsDepartureTime(outerBus) && innerBus.compareTravelTime(outerBus) == 0
                                        && innerBus.equalsCompanyName("Posh") && outerBus.equalsCompanyName("Grotty"))
                )
                .sorted(comparing(Bus::getCompanyName).reversed().thenComparing(Bus::getDepartureTime))
                .distinct()
                .collect(toList());
    }
}
