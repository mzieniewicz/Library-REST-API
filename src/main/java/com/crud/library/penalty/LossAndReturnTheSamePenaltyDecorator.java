package com.crud.library.penalty;

import java.math.BigDecimal;

public class LossAndReturnTheSamePenaltyDecorator extends AbstractPenaltyDecorator{

    protected LossAndReturnTheSamePenaltyDecorator(Penalty penalty) {
        super(penalty);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(10));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "the fee for losing a book and returning the same book, ";
    }
}
