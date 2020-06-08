package com.app.domain.value_object;

import com.app.domain.exception.DiscountException;

import java.math.BigDecimal;
import java.util.Objects;

// value objects pochodza z klas ktore sa immutable
// maja za zadanie reprezentowac pewne zagadnienia takie jak pieniadze,
// daty, znizki, procenty
public final class Money {
    private BigDecimal value;

    public Money() {
        this.value = BigDecimal.ZERO;
    }

    public Money(String value) {
        this.value = initStr(value);
    }

    private Money(BigDecimal value) {
        this.value = value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Money add(Money money) {
        return new Money(this.value.add(money.value));
    }

    public Money add(String value) {
        return new Money(this.value.add(initStr(value)));
    }

    public Money add(int value) {
        return new Money(this.value.add(BigDecimal.valueOf(value)));
    }

    public Money multiply(String value) {
        return new Money(this.value.multiply(initStr(value)));
    }

    public Money multiply(int value) {
        return new Money(this.value.multiply(BigDecimal.valueOf(value)));
    }

    private BigDecimal initStr(String value) {
        if (Objects.isNull(value) || !value.matches("\\d+(\\.\\d+)?")) {
            throw new DiscountException("money value is not correct");
        }

        return new BigDecimal(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
