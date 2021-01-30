package handlers;

import entities.Bus;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TimetableHandler {
    public static List<Bus> effectiveBuses(List<Bus> buses) {

        return buses.stream()
                .filter(bus -> bus.getTravelTimeInMinutes() <= 60)
                .filter(outerBus -> buses.stream()
                        .noneMatch(innerBus ->
                                innerBus.equalsDepartureTime(outerBus) && innerBus.compareTravelTime(outerBus) == -1)
                )
                .filter(outerBus -> buses.stream()
                        .noneMatch(innerBus ->
                                innerBus.equalsArrivalTime(outerBus) && innerBus.compareTravelTime(outerBus) == -1)
                )
                .filter(outerBus -> buses.stream()
                        .noneMatch(innerBus ->
                                innerBus.compareDepartureTime(outerBus) == 1 && innerBus.compareArrivalTime(outerBus) == -1
                                        && innerBus.compareTravelTime(outerBus) == -1)
                )
                .filter(outerBus -> buses.stream()
                        .noneMatch(innerBus ->
                                innerBus.equalsDepartureTime(outerBus) && innerBus.compareTravelTime(outerBus) == 0
                                        && innerBus.equalsCompanyName("Posh") && outerBus.equalsCompanyName("Grotty")))
                .sorted(comparing(Bus::getCompanyName).reversed().thenComparing(Bus::getDepartureTime)
                )
                .collect(toList());
    }
}
