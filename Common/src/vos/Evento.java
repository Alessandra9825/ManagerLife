
package vos;

import Annotations.CampoNoBanco;
import Basis.Entidade;
import Enums.enumCalendarsType;
import java.time.LocalDate;
import java.time.LocalTime;

public class Evento extends Entidade {
    @CampoNoBanco(nome = "usuario_id", chave = true)
    private int usuario_id;

    @CampoNoBanco(nome = "titulo", chave = false)
    private String titulo;

    @CampoNoBanco(nome = "local", chave = false)
    private String local;

    @CampoNoBanco(nome = "fullday", chave = false)
    private Boolean fullday;

    @CampoNoBanco(nome = "startDate", chave = false)
    private LocalDate startDate;

    @CampoNoBanco(nome = "endDate", chave = false)
    private LocalDate endDate;

    @CampoNoBanco(nome = "startTime", chave = false)
    private LocalTime startTime;

    @CampoNoBanco(nome = "endTime", chave = false)
    private LocalTime endTime;

    @CampoNoBanco(nome = "zoneId", chave = false)
    private String zoneId;

    @CampoNoBanco(nome = "calendarName", chave = false)
    private enumCalendarsType calendarName;

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getFullday() {
        return this.fullday;
    }

    public void setFullday(Boolean fullday) {
        this.fullday = fullday;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getZoneId() {
        return this.zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public enumCalendarsType getCalendarName() {
        return this.calendarName;
    }

    public void setCalendarName(enumCalendarsType calendarName) {
        this.calendarName = calendarName;
    }

    public int getUsuario_id() {
        return this.usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
