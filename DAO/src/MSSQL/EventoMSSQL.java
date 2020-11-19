package MSSQL;

import Basis.Entidade;
import Basis.MSSQLDAO;
import Enums.enumCalendarsType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vos.Evento;

public class EventoMSSQL<E extends Entidade> extends MSSQLDAO {
    public EventoMSSQL() {
        super(Evento.class);
        this.setTabela("tbEvento");
    }

    protected Entidade preencheEntidade(ResultSet rs) {
        Evento evt = new Evento();

        try {
            evt.setId(rs.getInt("id"));
            evt.setUsuario_id(rs.getInt("usuario_id"));
            evt.setTitulo(rs.getString("titulo"));
            evt.setFullday(rs.getBoolean("fullday"));
            evt.setStartDate(LocalDate.parse(rs.getString("startDate")));
            evt.setEndDate(LocalDate.parse(rs.getString("endDate")));
            evt.setStartTime(LocalTime.parse(rs.getString("startTime")));
            evt.setEndTime(LocalTime.parse(rs.getString("endTime")));
            evt.setZoneId(rs.getString("zoneId"));
            evt.setCalendarName(enumCalendarsType.valueOf(rs.getString("calendarName")));
        } catch (SQLException var4) {
            Logger.getLogger(UsuarioMSSQL.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

        return evt;
    }

    protected String getInsertCommand(Entidade entidade) {
        String SQL = "INSERT INTO " + this.tabela +
                " (id, usuario_id, titulo, fullday, startDate, endDate, startTime, endTime, zoneId, calendarName) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return SQL;
    }

    protected PreparedStatement getInsertStatement(Entidade entidade, PreparedStatement stmt) throws SQLException {
        Evento evt = (Evento)entidade;
        stmt.setInt(1, evt.getId());
        stmt.setInt(2, evt.getUsuario_id());
        stmt.setString(3, evt.getTitulo());
        stmt.setBoolean(4, evt.getFullday());
        stmt.setString(5, evt.getStartDate().toString());
        stmt.setString(6, evt.getEndDate().toString());
        stmt.setString(7, evt.getStartTime().toString());
        stmt.setString(8, evt.getEndTime().toString());
        stmt.setString(9, evt.getZoneId());
        stmt.setString(10, evt.getCalendarName().getDescricao());
        return stmt;
    }

    public ArrayList lista(int usuarioId) throws SQLException {
        ArrayList<Evento> evts = new ArrayList();
        Connection conexao = this.getConnection();
        Throwable var4 = null;

        try {
            String SQL = this.getListaCommand(usuarioId);
            PreparedStatement stmt = this.getStatement(SQL, conexao);
            Throwable var7 = null;

            try {
                ResultSet rs = stmt.executeQuery();
                Throwable var9 = null;

                try {
                    while(rs.next()) {
                        evts.add((Evento)this.preencheEntidade(rs));
                    }
                } catch (Throwable var53) {
                    var9 = var53;
                    throw var53;
                } finally {
                    if (rs != null) {
                        if (var9 != null) {
                            try {
                                rs.close();
                            } catch (Throwable var52) {
                                var9.addSuppressed(var52);
                            }
                        } else {
                            rs.close();
                        }
                    }

                }
            } catch (Throwable var55) {
                var7 = var55;
                throw var55;
            } finally {
                if (stmt != null) {
                    if (var7 != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var51) {
                            var7.addSuppressed(var51);
                        }
                    } else {
                        stmt.close();
                    }
                }

            }
        } catch (Throwable var57) {
            var4 = var57;
            throw var57;
        } finally {
            if (conexao != null) {
                if (var4 != null) {
                    try {
                        conexao.close();
                    } catch (Throwable var50) {
                        var4.addSuppressed(var50);
                    }
                } else {
                    conexao.close();
                }
            }

        }

        return evts;
    }

    protected String getListaCommand(int usuarioId) {
        return "SELECT * FROM " + this.tabela + " WHERE usuario_id = " + usuarioId;
    }
}
