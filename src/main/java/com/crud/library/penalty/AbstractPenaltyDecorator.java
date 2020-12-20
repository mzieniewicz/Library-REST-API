package com.crud.library.penalty;

import java.math.BigDecimal;

public  abstract class AbstractPenaltyDecorator implements Penalty {

    private final Penalty penalty;

    protected AbstractPenaltyDecorator(Penalty penalty) {
        this.penalty = penalty;
    }

    @Override
    public BigDecimal getCost() {
        return penalty.getCost();
    }

    @Override
    public String getDescription() {
        return penalty.getDescription();
    }
}


