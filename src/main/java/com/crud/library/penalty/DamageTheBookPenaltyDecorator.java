package com.crud.library.penalty;

import java.math.BigDecimal;

public class DamageTheBookPenaltyDecorator extends AbstractPenaltyDecorator{

    protected DamageTheBookPenaltyDecorator(Penalty penalty) {
        super(penalty);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(15));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "the fee for damage to the book, ";
    }
}
