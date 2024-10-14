package ru.lexender.ifmo.web3.core;

import javax.servlet.ServletRequestListener;

public class ContourService {
    public boolean check(double x, double y, double r) {
        if (x >= 0 && y <= 0) {
            return x <= r && y >= -r / 2;
        } else if (x <= 0 && y <= 0) {
            return y >= -x - r / 2;
        } else if (x <= 0 && y >= 0) {
            return x * x + y * y <= r * r / 4;
        } else {
            return false;
        }
    }
}
