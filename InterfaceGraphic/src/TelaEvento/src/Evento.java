
package TelaEvento.src;

import Enums.enumCalendarsType;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.view.CalendarView;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Evento extends Application {
    private EventoController controller = new EventoController();
    private List<Entry> calendarsEntryUpdate = new ArrayList();
    private List<Entry> calendarsEntryDB = new ArrayList();
    private List<Calendar> calendarList = new ArrayList();

    public Evento() {
    }

    public void start(Stage primaryStage) throws Exception {
        final CalendarView calendarView = new CalendarView();
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowSourceTrayButton(false);
        calendarView.getCalendarSources().addAll(new CalendarSource[]{this.setCalendars()});
        calendarView.getCalendars().forEach((c) -> {
            if (c.getName().equals("Padrão")) {
                c.setName(enumCalendarsType.LAZER.getDescricao());
            }

        });
        calendarView.setRequestedTime(LocalTime.now());
        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            public void run() {
                while(true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        sleep(10000L);
                    } catch (InterruptedException var2) {
                        var2.printStackTrace();
                    }
                }
            }
        };
        updateTimeThread.setPriority(1);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
        Scene scene = new Scene(calendarView);
        primaryStage.setTitle("Calendar");
        primaryStage.setScene(scene);
        primaryStage.setWidth(900.0D);
        primaryStage.setHeight(700.0D);
        primaryStage.centerOnScreen();
        primaryStage.show();
        primaryStage.setOnCloseRequest((exit) -> {
            if (!this.controller.saveAllEntry(calendarView.getCalendars(), this.calendarsEntryDB)) {
                this.controller.showError();
            } else {
                primaryStage.close();
            }

        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private CalendarSource setCalendars() {
        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        Calendar aniversario = new Calendar("Aniversário");
        Calendar reuniao = new Calendar("Reunião");
        Calendar folga = new Calendar("Folga");
        Calendar google = new Calendar("Google");
        google.setReadOnly(true);
        aniversario.setStyle(Style.STYLE2);
        reuniao.setStyle(Style.STYLE3);
        folga.setStyle(Style.STYLE4);
        google.setStyle(Style.STYLE5);
        this.calendarList.add(aniversario);
        this.calendarList.add(reuniao);
        this.calendarList.add(folga);
        this.calendarList.add(google);
        this.calendarsEntryDB = this.controller.findAllEntry();
        this.calendarList = this.controller.calendarsWithEntrys(this.calendarsEntryDB, this.calendarList);
        EventHandler<CalendarEvent> handler = (evt) -> {
            if (evt.getOldCalendar() != evt.getCalendar()) {
                this.calendarList = this.controller.updateCalendarEntry(evt, this.calendarList);
            }

            this.calendarsEntryUpdate = this.controller.updateListEntrys(evt, this.calendarsEntryUpdate);
        };
        this.calendarList.forEach((c) -> {
            c.addEventHandler(handler);
            myCalendarSource.getCalendars().add(c);
        });
        return myCalendarSource;
    }
}