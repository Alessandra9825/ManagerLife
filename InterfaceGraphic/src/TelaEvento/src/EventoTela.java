package TelaEvento.src;

import Evento.EventoRules;
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
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class EventoTela extends Application {

    private EventoRules bussiness = new EventoRules();

    //lista para atualizacao
    private List<Entry> calendarsEntryUpdate = new ArrayList();

    //lista para delete
    private List<Entry> calendarsEntryDelete = new ArrayList();

    //lista nativa do banco
    private List<Entry> calendarsEntryDB = new ArrayList();

    private List<Calendar> calendarList = new ArrayList();

    public void start(Stage primaryStage) throws Exception {
        final CalendarView calendarView = new CalendarView();

        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowSourceTrayButton(false);

        calendarView.getCalendarSources().addAll(setCalendars());

        //atualiza o calendar padrao para Lazer
        for (Calendar calendar : calendarView.getCalendars()){
            if (calendar.getName().equals("Padrão")) {
                calendar.setName("Lazer");
                calendarList.add(calendar);
            }
        }

        calendarsEntryDB = bussiness.findAllEntry(calendarList);

        //configura os listeners apos a implementacao
        configCalendar();

        calendarView.setRequestedTime(LocalTime.now());

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            public void run() {
                while(true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        sleep(10000);
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
            if (!bussiness.saveUpdateEntry(calendarView.getCalendars(), calendarsEntryDB, calendarsEntryUpdate, calendarsEntryDelete)) {
                List<String> erros = bussiness.getErrosPopUp();
                showError(erros);
                exit.consume();
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
        Calendar calendarErro = new Calendar("Error - Calendar not Found");

        google.setReadOnly(true);

        aniversario.setStyle(Style.STYLE2);
        reuniao.setStyle(Style.STYLE3);
        folga.setStyle(Style.STYLE4);
        google.setStyle(Style.STYLE5);
        calendarErro.setStyle(Style.STYLE7);

        calendarList.add(aniversario);
        calendarList.add(reuniao);
        calendarList.add(folga);
        calendarList.add(google);
        calendarList.add(calendarErro);

        calendarList.forEach((c) -> {
            myCalendarSource.getCalendars().add(c);
        });

        return myCalendarSource;
    }

    private void configCalendar(){
        EventHandler<CalendarEvent> handler = (evt) -> {
            if(evt.isEntryRemoved())
                calendarsEntryDelete.add(evt.getEntry());
            else
                bussiness.updateListEntrys(evt, calendarsEntryUpdate);
        };

        calendarList.forEach((c) -> {
            c.addEventHandler(handler);
        });
    }

    public void showError(List<String> erros) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Ops, Ocorreu um erro!");

        String text = "";

        for (int i = 0; i < erros.size(); i++){
            text += erros.get(i) + "\n";
        }

        alert.setContentText(text);

        erros.clear();

        alert.showAndWait();
    }
}