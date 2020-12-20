package com.crud.library.penalty;

import java.math.BigDecimal;

public class OneMonthPenaltyDecorator extends AbstractPenaltyDecorator {

    protected OneMonthPenaltyDecorator(Penalty penalty) {
        super(penalty);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(3));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "the fee for one month of keeping the book, ";
    }
}
