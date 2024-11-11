package ru.lexender.ifmo.web3.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import ru.lexender.ifmo.web3.core.ContourService;
import ru.lexender.ifmo.web3.core.DataRow;
import ru.lexender.ifmo.web3.database.DatabaseConnection;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FormBean {
    Double x;
    Double y;
    Double r;
    Long time;
    String result;

    @ManagedProperty(value = "#{tableBean}")
    TableBean tableBean;

    @ManagedProperty(value = "#{contourService}")
    ContourService contourService;

    @ManagedProperty(value = "#{databaseConnection}")
    DatabaseConnection databaseConnection;


    public void submit() {
        long start = System.nanoTime();

        var realResult = contourService.check(x, y, r);
        result = realResult ? "Пробитие" : "Не пробил";
        time = System.nanoTime() - start;

        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        databaseConnection.saveShot(x, y, r, time, realResult, session.getId());
        tableBean.getData().add(new DataRow(x, y, r, realResult, time, session.getId()));

        System.out.println("Submitted: x=" + x + ", y=" + y + ", r=" + r + ", result=" + result + ", time=" + time);
    }
}
