package ru.lexender.ifmo.web3.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.lexender.ifmo.web3.core.ContourService;
import ru.lexender.ifmo.web3.database.DatabaseConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormBean {
    Double x;
    Double y;
    Double r;
    Long time;
    Boolean result;

    ContourService contourService = new ContourService();
    DatabaseConnection databaseConnection = new DatabaseConnection();


    public void submit() {
        long start = System.nanoTime();

        result = contourService.check(x, y, r);
        time = System.nanoTime() - start;

        databaseConnection.saveShot(x, y, r, time, result);
    }
}
