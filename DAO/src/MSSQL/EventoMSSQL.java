package MSSQL;

import Basis.Entidade;
import Basis.MSSQLDAO;
import Enums.enumCalendarsType;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vos.Evento;

public class EventoMSSQL<E extends Entidade> extends MSSQLDAO {
    public EventoMSSQL() {
        super(Evento.class);
        this.setTabela("Evento");
    }

    protected Entidade preencheEntidade(ResultSet rs) {
        Evento evt = new Evento();

        try {
            evt.setId(rs.getInt("id"));
            evt.setUsuario_id(rs.getInt("usuario_id"));
            evt.setTitulo(rs.getString("titulo"));
            evt.setLocal(rs.getString("local"));
            evt.setFullday(rs.getBoolean("fullday"));
            evt.setStartDate(LocalDate.parse(rs.getString("startDate")));
            evt.setEndDate(LocalDate.parse(rs.getString("endDate")));
            evt.setStartTime(LocalTime.parse(rs.getString("startTime")));
            evt.setEndTime(LocalTime.parse(rs.getString("endTime")));
            evt.setZoneId(rs.getString("zoneId"));

            String calendarDB = rs.getString("calendarName");
            for (enumCalendarsType calendar: enumCalendarsType.values())
                if(calendar.getDescricao().equals(calendarDB))
                    evt.setCalendarName(calendar);

        } catch (SQLException var4) {
            Logger.getLogger(UsuarioMSSQL.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

        return evt;
    }

    protected String getInsertCommand(Entidade entidade) {
        String SQL = "INSERT INTO " + this.tabela +
                " (usuario_id, titulo, local, fullday, startDate, endDate, startTime, endTime, zoneId, calendarName) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return SQL;
    }

    protected PreparedStatement getInsertStatement(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Evento evt = (Evento)entidade;

        //stmt.setInt(1, evt.getId());
        stmt.setInt(1, evt.getUsuario_id());
        stmt.setString(2, evt.getTitulo());
        stmt.setString(3, evt.getLocal());
        stmt.setBoolean(4, evt.getFullday());
        stmt.setDate(5, Date.valueOf(evt.getStartDate()));
        stmt.setDate(6, Date.valueOf(evt.getEndDate()));
        stmt.setTime(7, Time.valueOf(evt.getStartTime()));
        stmt.setTime(8, Time.valueOf(evt.getEndTime()));
        stmt.setString(9, evt.getZoneId());
        stmt.setString(10, evt.getCalendarName().getDescricao());
        return stmt;
    }

    public List<Evento> lista(int usuarioId) throws SQLException {
        List<Evento> evts = new ArrayList();
        Evento evt;

        try(Connection conexao = getConnection()){
            String SQL = getListaCommand(usuarioId);
            try(PreparedStatement stmt = getStatement(SQL, conexao)){
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        evt = (Evento) preencheEntidade(rs);
                        evts.add(evt);
                    }
                }
            }
        }

        return evts;
    }

    protected String getListaCommand(int usuarioId) {
        return "SELECT * FROM " + this.tabela + " WHERE usuario_id = " + usuarioId;
    }

    public boolean atualiza(Entidade entidade) throws SQLException {
        try(Connection conexao = getConnection()){
            String SQL = getAtualizaCommand();
            try(PreparedStatement stmt = getUpadateStatement(entidade, conexao.prepareStatement(SQL))){
                return stmt.executeUpdate() > 0;
            }
        }
    }

    private String getAtualizaCommand() {
        return "UPDATE " + tabela + " SET " +
                "titulo = ?, " +
                "local = ?, " +
                "fullday = ?, " +
                "startDate = ?, " +
                "endDate = ?, " +
                "startTime = ?, " +
                "endTime = ?, " +
                "zoneId = ? ," +
                "calendarName = ? " +
                "WHERE id = ?";
    }

    private PreparedStatement getUpadateStatement(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Evento evt = (Evento)entidade;

        stmt.setString(1, evt.getTitulo());
        stmt.setString(2, evt.getLocal());
        stmt.setBoolean(3, evt.getFullday());
        stmt.setDate(4, Date.valueOf(evt.getStartDate()));
        stmt.setDate(5, Date.valueOf(evt.getEndDate()));
        stmt.setTime(6, Time.valueOf(evt.getStartTime()));
        stmt.setTime(7, Time.valueOf(evt.getEndTime()));
        stmt.setString(8, evt.getZoneId());
        stmt.setString(9, evt.getCalendarName().getDescricao());
        stmt.setInt(10, evt.getId());

        return stmt;
    }

    public boolean delete(int id) throws SQLException {
        try(Connection conexao = getConnection()){
            String SQL = getDeleteCommand(id);
            try(PreparedStatement stmt = conexao.prepareStatement(SQL)){
                return stmt.executeUpdate() > 0;
            }
        }
    }

    private String getDeleteCommand(int id) {
        return "DELETE FROM " + tabela + " WHERE id = " + id;
    }
}
