package com.crud.library.penalty;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BasicPenaltyTestSuite {

    @Test
    public void testBasicPenaltyGetCost() {
        //Given
        Penalty penalty = new BasicPenalty();
        //When
        BigDecimal theCost = penalty.getCost();
        //Then
        assertEquals(new BigDecimal(15), theCost);
    }

    @Test
    public void testBasicPenaltyGetDescription() {
        //Given
        Penalty penalty = new BasicPenalty();
        //When
        String theDescription = penalty.getDescription();
        //Then
        assertEquals("Basic fee for breaking the library regulations, ", theDescription);
    }
    @Test
    public void testDamageTheBookAndTwoMonthsPenalty() {
        //Given
        Penalty penalty = new BasicPenalty();
        penalty = new DamageTheBookPenaltyDecorator(penalty);
        penalty = new OneMonthPenaltyDecorator(penalty);
        penalty = new OneMonthPenaltyDecorator(penalty);
        System.out.println(penalty.getCost());
        //When
        BigDecimal theCost = penalty.getCost();
        String theDescription = penalty.getDescription();
        //Then
        assertEquals(new BigDecimal(36), theCost);
        assertEquals("Basic fee for breaking the library regulations, the fee for damage to the book, " +
                "the fee for one month of keeping the book, the fee for one month of keeping the book, ", theDescription);
    }

    @Test
    public void testLoosingABookAndLossAndReturnTheSamePenalty() {
        //Given
        Penalty penalty = new BasicPenalty();
        penalty = new LosingABookPenaltyDecorator(penalty);
        penalty = new LossAndReturnTheSamePenaltyDecorator(penalty);
        System.out.println(penalty.getCost());
        //When
        BigDecimal theCost = penalty.getCost();
        String theDescription = penalty.getDescription();
        //Then
        assertEquals(new BigDecimal(75), theCost);
        assertEquals("Basic fee for breaking the library regulations, the fee for losing a book, " +
                "the fee for losing a book and returning the same book, ", theDescription);
    }

}