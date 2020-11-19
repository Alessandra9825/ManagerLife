package TelaEvento.src;

import Enums.enumCalendarsType;
import Repositorio.MSSQL.RepositorioMSSQL;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Calendar.Style;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import vos.Evento;

public class EventoController {
    private List<String> errosPopUp = new ArrayList();

    public EventoController() {
    }

    public boolean saveAllEntry(List<Calendar> calendars, List<Entry> entryListDB) {
        boolean ok = true;

        try {
            List<Entry> entrySave = new ArrayList();
            boolean save = false;
            Iterator var6 = calendars.iterator();

            while(var6.hasNext()) {
                Calendar calendar = (Calendar)var6.next();
                Iterator var8 = calendar.findEntries("").iterator();

                while(var8.hasNext()) {
                    Entry entry = (Entry)var8.next();
                    save = true;
                    Iterator var10 = entryListDB.iterator();

                    while(var10.hasNext()) {
                        Entry entryDB = (Entry)var10.next();
                        if (entry.getId().equals(entryDB.getId())) {
                            save = false;
                            break;
                        }
                    }

                    if (save) {
                        entrySave.add(entry);
                    }
                }
            }

            if (entrySave.size() > 0) {
            }
        } catch (Exception var12) {
            ok = false;
        }

        return ok;
    }

    public List<Entry> findAllEntry() {
        new RepositorioMSSQL();
        List<Entry> entryDB = new ArrayList();
        return entryDB;
    }

    public List<Calendar> updateCalendarEntry(CalendarEvent evt, List<Calendar> calendarList) {
        calendarList.forEach((calendar) -> {
            if (calendar == evt.getOldCalendar()) {
                calendar.removeEntry(evt.getEntry());
            } else if (calendar == evt.getCalendar()) {
                calendar.addEntry(evt.getEntry());
            }

        });
        return calendarList;
    }

    public List<Calendar> calendarsWithEntrys(List<Entry> entrys, List<Calendar> calendars) {
        boolean error = false;
        Calendar calendarErro = new Calendar("Erro - Calendar not Found");
        calendarErro.setReadOnly(true);
        calendarErro.setStyle(Style.STYLE7);
        Iterator var5 = entrys.iterator();

        while(var5.hasNext()) {
            Entry entry = (Entry)var5.next();
            error = true;
            Iterator var7 = calendars.iterator();

            while(var7.hasNext()) {
                Calendar calendar = (Calendar)var7.next();
                if (entry.getCalendar() == calendar) {
                    calendar.addEntry(entry);
                    error = false;
                }
            }

            if (error) {
                calendarErro.addEntry(entry);
            }
        }

        if (calendarErro.findEntries("").size() > 0) {
            calendars.add(calendarErro);
        }

        return calendars;
    }

    public List<Entry> updateListEntrys(CalendarEvent evt, List<Entry> entryList) {
        Iterator var3 = entryList.iterator();

        while(var3.hasNext()) {
            Entry entry = (Entry)var3.next();
            if (entry.getId().equals(evt.getEntry().getId())) {
                entryList.remove(entry);
                entryList.add(evt.getEntry());
            }
        }

        return entryList;
    }

    private Evento entry2Evento(Entry entry, int usuarioId) {
        Evento evt = new Evento();
        evt.setId(Integer.getInteger(entry.getId()));
        evt.setUsuario_id(usuarioId);
        evt.setTitulo(entry.getTitle());
        evt.setFullday(entry.isFullDay());
        evt.setStartDate(entry.getStartDate());
        evt.setEndDate(entry.getEndDate());
        evt.setStartTime(entry.getStartTime());
        evt.setEndTime(entry.getEndTime());
        evt.setZoneId(entry.getZoneId().toString());
        evt.setCalendarName(enumCalendarsType.valueOf(entry.getCalendar().getName()));
        return evt;
    }

    public void showError() {
        System.out.println("Error");
    }
}
