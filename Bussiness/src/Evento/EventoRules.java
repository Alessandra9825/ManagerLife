package Evento;

import Auditoria.GerenciadorAuditoria;
import Enums.enumCalendarsType;
import MSSQL.EventoMSSQL;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.Entry;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vos.Evento;
import vos.Usuario;

public class EventoRules {

    private final String meth =  "EventoRules - ";
    private List<String> errosPopUp = new ArrayList();

    public boolean saveUpdateEntry(List<Calendar> calendars, List<Entry> entryListDB, List<Entry> entryListUpdate, List<Entry> entryListDelete){
        Usuario user = null;

        clearLists(entryListDB, entryListUpdate, entryListDelete);

        boolean save = saveAllEntry(calendars, entryListDB, 1);
        boolean delete = deleteAllEntry(entryListDelete);
        boolean update = updateAllEntry(entryListUpdate, 1, entryListDB);

        return save && update && delete;
    }

    private void clearLists(List<Entry> entryListDB, List<Entry> entryListUpdate, List<Entry> entryListDelete){
        clearInsertUpdate(entryListDB, entryListUpdate);
        clearDeleteUpdate(entryListDelete, entryListUpdate);
        clearDeleteDabatase(entryListDelete, entryListDB);
    }
    private void clearInsertUpdate(List<Entry> entryListDB, List<Entry> entryListUpdate){
        boolean delete;

        List<Entry> backup = new ArrayList<>();
        for (Entry entryUpdate : entryListUpdate){
            delete = true;
            for(Entry entryDB : entryListDB){
                if(entryDB.getId().equals(entryUpdate.getId()))
                    delete = false;
            }

            if(delete)
                backup.add(entryUpdate);
        }

        for (Entry entry : backup)
            entryListUpdate.remove(entry);
    }

    private void clearDeleteUpdate(List<Entry> entryListDelete, List<Entry> entryListUpdate){

        List<Entry> backup = new ArrayList<>();
        for (Entry entryValida : entryListDelete){
            for(Entry entryExclui : entryListUpdate){
                if(entryExclui.getId().equals(entryValida.getId()))
                    backup.add(entryExclui);
            }
        }

        for (Entry entry : backup)
            entryListUpdate.remove(entry);
    }

    private void clearDeleteDabatase(List<Entry> entryListDelete, List<Entry> entryListDB){
        boolean delete;

        List<Entry> backup = new ArrayList<>();
        for (Entry entryValida : entryListDelete){
            delete = true;
            for(Entry entryExclui : entryListDB){
                if(entryExclui.getId().equals(entryValida.getId()))
                    delete = false;
            }
            if(delete)
                backup.add(entryValida);
        }

        for (Entry entry : backup)
            entryListDelete.remove(entry);
    }

    //salva todas as entradas novas
    private boolean saveAllEntry(List<Calendar> calendars, List<Entry> entryListDB, int usuarioId) {
        EventoMSSQL dao =  new EventoMSSQL();
        String erro;
        boolean ok = true;

        try {
            List<Entry> entrySaveList = new ArrayList();
            boolean save = true;

            //pega toda os novos eventos
            for (Calendar calendar : calendars){
                for (Entry entry : calendar.findEntries("")){
                    save = true;
                    for (Entry entryDB : entryListDB){
                        if(entry.getId().equals(entryDB.getId())){
                            save = false;
                            break;
                        }
                    }

                    if(save)
                        entrySaveList.add(entry);
                }
            }

            Evento evt;
            if(entrySaveList.size() > 0){
                for(Entry entry: entrySaveList){
                    evt = entry2Evento(entry, usuarioId);
                    if(!dao.salvar(evt)){
                        erro = "Erro - saveAllEntry - Evento: " + evt.getTitulo() + "Data: " + evt.getStartDate().toString() + "Calendario: " + evt.getCalendarName();
                        errosPopUp.add(erro);
                        GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + erro);
                    }
                    else{
                        entryListDB.add(entry);
                        GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Sucesso - saveAllEntry - Evento: " + evt.getTitulo());
                    }

                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
            ok = false;
        }

        return ok;
    }

    //atualiza todos os eventos necessarios
    private boolean updateAllEntry(List<Entry> entryListUpdate, int usuarioId, List<Entry> entryListDB) {
        EventoMSSQL dao = new EventoMSSQL();
        String erro;
        boolean certo = true;
        Evento evt;

        for(Entry entry : entryListUpdate){
            evt = entry2Evento(entry, usuarioId);
            try {
                if(!dao.atualiza(evt)){
                    certo = false;
                    erro = "Erro - updateAllEntry - Evento: " + evt.getTitulo() + "Data: " + evt.getStartDate().toString() + "Calendario: " + evt.getCalendarName();
                    errosPopUp.add(erro);
                    GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + erro);
                }
                else{
                    GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + " Sucesso - updateAllEntry - Evento " +  evt.getTitulo());
                }
            } catch (SQLException ex) {
                erro = "Erro - updateAllEntry - Evento: " + evt.getTitulo() + "Data: " + evt.getStartDate().toString() + "Calendario: " + evt.getCalendarName();
                errosPopUp.add(erro);
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + erro);
                ex.printStackTrace();
            }
        }

        return certo;
    }

    //atualiza todos os eventos necessarios
    private boolean deleteAllEntry(List<Entry> entryListDelete) {
        EventoMSSQL dao = new EventoMSSQL();
        String erro;
        boolean deletado = true;

        for(Entry entry : entryListDelete){
            try {
                if(!dao.delete(Integer.parseInt(entry.getId()))){
                    deletado = false;
                    erro = "Erro - deleteAllEntry - Evento: " + entry.getTitle() + "Data: " + entry.getStartDate() + "Calendario: Sem calendario";
                    errosPopUp.add(erro);
                    GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + erro);
                }
                else{
                    GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + " Sucesso - deleteAllEntry - Evento " +  entry.getTitle());
                }
            } catch (SQLException ex) {
                erro = "Erro - deleteAllEntry - Evento: " + entry.getTitle() + "Data: " + entry.getStartDate() + "Calendario: Sem calendario";
                errosPopUp.add(erro);
                GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + erro);
                ex.printStackTrace();
            }
        }

        return deletado;
    }

    //Faz a busca de todos os eventos no banco
    public List<Entry> findAllEntry(int usuarioId, List<Calendar> calendars) {
        EventoMSSQL evento =  new EventoMSSQL();
        List<Evento> eventoDB = new ArrayList<>();
        List<Entry> entryDB = new ArrayList<>();
        Entry entry;

        try{
            eventoDB = evento.lista(usuarioId);

            if(eventoDB.size() > 0)
                for (Evento evt: eventoDB){
                    entry = evento2Entry(evt, calendars);
                    entryDB.add(entry);
                }

        } catch (SQLException ex) {
            errosPopUp.add(meth + "Error - findAllEntry - Erro ao acessar o banco");
            GerenciadorAuditoria.getInstancia().adicionaMsgAuditoria(meth + "Error - findAllEntry - Erro ao acessar o banco" + Arrays.toString(ex.getStackTrace()));
            ex.printStackTrace();
        }

        return entryDB;
    }

    //atualiza a lista de evento para posterior atualizacao
    public void updateListEntrys(CalendarEvent evt, List<Entry> entryList) {
        boolean contains = false;

        for(Entry entry: entryList){
            if(entry.getId().equals(evt.getEntry().getId())){
                entryList.remove(entry);
                entryList.add(evt.getEntry());
                contains = true;
            }
        }

        if(!contains)
            entryList.add(evt.getEntry());
    }

    //Converte um Entry em evento
    private Evento entry2Evento(Entry entry, int usuarioId) {
        Evento evt = new Evento();

        evt.setId(Integer.parseInt(entry.getId()));
        evt.setUsuario_id(usuarioId);
        evt.setTitulo(entry.getTitle());
        evt.setLocal(entry.getLocation());
        evt.setFullday(entry.isFullDay());
        evt.setStartDate(entry.getStartDate());
        evt.setEndDate(entry.getEndDate());
        evt.setStartTime(entry.getStartTime());
        evt.setEndTime(entry.getEndTime());
        evt.setZoneId(entry.getZoneId().toString());

        String calendar = entry.getCalendar().getName();
        for (enumCalendarsType calendarType: enumCalendarsType.values())
            if(calendarType.getDescricao().equals(calendar))
                evt.setCalendarName(calendarType);

        return evt;
    }

    private Entry evento2Entry(Evento evt, List<Calendar> calendars) {
        Entry entry = new Entry();

        entry.setId(Integer.toString(evt.getId()));
        entry.setTitle(evt.getTitulo());
        entry.setLocation(evt.getLocal());
        entry.setFullDay(evt.getFullday());
        entry.changeStartDate(evt.getStartDate());
        entry.changeEndDate(evt.getEndDate());
        entry.changeStartTime(evt.getStartTime());
        entry.changeEndTime(evt.getEndTime());
        entry.setZoneId(ZoneId.of(evt.getZoneId()));

        for(Calendar calendar: calendars){
            if(evt.getCalendarName() != null && calendar.getName().equals(evt.getCalendarName().getDescricao()))
                entry.setCalendar(calendar);
        }

        if(entry.getCalendar() == null)
            for (Calendar calendar: calendars)
                if(calendar.getName().equals(enumCalendarsType.ERROR.getDescricao()))
                    entry.setCalendar(calendar);

        return entry;
    }

    public List<String> getErrosPopUp(){
        return errosPopUp;
    }
}