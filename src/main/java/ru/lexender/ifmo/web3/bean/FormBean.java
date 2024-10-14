package ru.lexender.ifmo.web3.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.lexender.ifmo.web3.core.ContourService;

import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

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


    public void submit() {
        long start = System.nanoTime();

        result = contourService.check(x, y, r);
        time = System.nanoTime() - start;
    }
}
