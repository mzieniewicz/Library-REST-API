package com.crud.library.penalty;

import java.math.BigDecimal;

public class LosingABookPenaltyDecorator extends AbstractPenaltyDecorator {

    protected LosingABookPenaltyDecorator(Penalty penalty) {
        super(penalty);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(50));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "the fee for losing a book, ";
    }
}
