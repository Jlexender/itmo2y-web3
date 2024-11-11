package ru.lexender.ifmo.web3.bean;

import lombok.Getter;
import lombok.Setter;
import ru.lexender.ifmo.web3.core.DataRow;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class TableBean {
    List<DataRow> data = new LinkedList<>();
}
