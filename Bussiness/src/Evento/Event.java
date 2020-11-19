package Evento;

import com.calendarfx.model.Entry;
import java.time.ZoneId;
import vos.Evento;

public class Event {
    public Event() {
    }

    private Entry createEntry(Evento event) {
        Entry entry = new Entry();
        entry.setTitle(event.getTitulo());
        entry.setId(Integer.toString(event.getId()));
        entry.setFullDay(false);
        entry.changeStartDate(event.getStartDate());
        entry.changeStartTime(event.getStartTime());
        entry.changeEndDate(event.getEndDate());
        entry.changeEndTime(event.getEndTime());
        entry.setZoneId(ZoneId.of(event.getZoneId()));
        return entry;
    }
}