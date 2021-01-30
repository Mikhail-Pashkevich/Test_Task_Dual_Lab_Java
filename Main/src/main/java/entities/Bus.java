package entities;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalTime;

@Slf4j
@Getter
public class Bus implements Serializable {
    private final String companyName;
    private final LocalTime departureTime;
    private final LocalTime arrivalTime;
    private final int travelTimeInMinutes;

    public Bus(String companyName, LocalTime departureTime, LocalTime arrivalTime, int travelTimeInMinutes) {
        this.companyName = companyName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.travelTimeInMinutes = travelTimeInMinutes;
    }

    public static Bus of(String busString) {
        try {
            String[] strings = busString.split("[ :]");

            String companyName = strings[0];
            LocalTime departureTime = LocalTime.of(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
            LocalTime arrivalTime = LocalTime.of(Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));
            LocalTime travelTime = arrivalTime
                    .minusHours(departureTime.getHour())
                    .minusMinutes(departureTime.getMinute());
            int travelTimeInMinutes = travelTime.getHour() * 60 + travelTime.getMinute();

            return new Bus(companyName, departureTime, arrivalTime, travelTimeInMinutes);

        } catch (ArrayIndexOutOfBoundsException exception) {

            log.info("No enough elements in string", exception.fillInStackTrace());
            return new Bus(null, null, null, 0);
        }
    }

    @Override
    public String toString() {
        return companyName + " " + departureTime + " " + arrivalTime;
    }

    public int compareTravelTime(Bus bus) {
        return Integer.compare(this.travelTimeInMinutes, bus.getTravelTimeInMinutes());
    }

    public int compareDepartureTime(Bus bus) {
        int thisTime = this.departureTime.getHour() * 60 + this.departureTime.getMinute();
        int busTime = bus.getDepartureTime().getHour() * 60 + bus.getDepartureTime().getMinute();

        return Integer.compare(thisTime, busTime);
    }

    public int compareArrivalTime(Bus bus) {
        int thisTime = this.arrivalTime.getHour() * 60 + this.arrivalTime.getMinute();
        int busTime = bus.getArrivalTime().getHour() * 60 + bus.getArrivalTime().getMinute();

        return Integer.compare(thisTime, busTime);
    }


    public boolean equalsDepartureTime(Bus bus) {
        return this.departureTime.equals(bus.getDepartureTime());
    }

    public boolean equalsArrivalTime(Bus bus) {
        return this.arrivalTime.equals(bus.getArrivalTime());
    }

    public boolean equalsCompanyName(String str) {
        return this.companyName.equals(str);
    }
}