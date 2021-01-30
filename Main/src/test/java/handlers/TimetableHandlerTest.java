package handlers;

import entities.Bus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static handlers.TimetableHandler.effectiveBuses;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TimetableHandlerTest {

    @Test
    public void effectiveBus_firstCondition() {
        List<Bus> buses = Arrays.asList(Bus.of("Posh 00:00 01:00")
                , Bus.of("Posh 00:00 01:10")
                , Bus.of("Posh 00:00 01:20"));

        List<Bus> effectiveBuses = effectiveBuses(buses);

        //that 'effectiveBuses' has only 1 element which is equals 'Posh 00:00 01:00'
        assertEquals(effectiveBuses.size(), 1);
        assertEquals(effectiveBuses.get(0), buses.get(0));
    }

    @Test
    public void effectiveBus_secondCondition() {
        List<Bus> buses = Arrays.asList(Bus.of("Posh 00:00 00:10")
                , Bus.of("Posh 00:00 00:20")
                , Bus.of("Posh 00:00 00:30"));

        List<Bus> effectiveBuses = effectiveBuses(buses);

        //that 'effectiveBuses' has only 1 element which is equals 'Posh 00:00 00:10'
        assertEquals(effectiveBuses.size(), 1);
        assertEquals(effectiveBuses.get(0), buses.get(0));
    }

    @Test
    public void effectiveBus_thirdCondition() {
        List<Bus> buses = Arrays.asList(Bus.of("Posh 00:30 01:30")
                , Bus.of("Posh 00:40 01:30")
                , Bus.of("Posh 00:50 01:30"));

        List<Bus> effectiveBuses = effectiveBuses(buses);

        //that 'effectiveBuses' has only 1 element which is equals 'Posh 00:50 01:30'
        assertEquals(effectiveBuses.size(), 1);
        assertEquals(effectiveBuses.get(0), buses.get(2));
    }

    @Test
    public void effectiveBus_fourthCondition() {
        List<Bus> buses = Arrays.asList(Bus.of("Posh 00:00 01:00")
                , Bus.of("Posh 00:10 00:50")
                , Bus.of("Posh 00:20 00:40"));

        List<Bus> effectiveBuses = effectiveBuses(buses);

        //that 'effectiveBuses' has only 1 element which is equals 'Posh 00:20 00:40'
        assertEquals(effectiveBuses.size(), 1);
        assertEquals(effectiveBuses.get(0), buses.get(2));
    }

    @Test
    public void effectiveBus_fifthCondition() {
        List<Bus> buses = Arrays.asList(Bus.of("Posh 00:30 01:30")
                , Bus.of("Posh 00:30 01:30")
                , Bus.of("Grotty 00:30 01:30"));

        List<Bus> effectiveBuses = effectiveBuses(buses);

        //that 'effectiveBuses' has only 1 element which is equals 'Posh 00:30 01:30'
        assertEquals(effectiveBuses.size(), 1);
        assertEquals(effectiveBuses.get(0), buses.get(0));
    }
}