package ru.lexender.ifmo.web3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.LinkedList;
import java.util.List;

@ManagedBean
@ViewScoped
public class TestBean {
    private List<Test> tests;

    public TestBean() {
        tests = new LinkedList<>();
        tests.add(new Test(1, "data1"));
        tests.add(new Test(2, "data2"));
        tests.add(new Test(3, "goool"));
    }

    public List<Test> getTests() {
        return tests;
    }
}
