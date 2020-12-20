package com.crud.library.penalty;

import java.math.BigDecimal;

public class BasicPenalty implements Penalty {

    @Override
    public BigDecimal getCost() {
        return new BigDecimal(15);
    }

    @Override
    public String getDescription() {
        return "Basic fee for breaking the library regulations, ";
    }
}
