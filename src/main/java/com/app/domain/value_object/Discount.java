package com.app.domain.value_object;

import com.app.domain.exception.DiscountException;

import java.math.BigDecimal;
import java.util.Objects;

public final class Discount {
    private BigDecimal value;

    public Discount() {
        this.value = BigDecimal.ZERO;
    }

    public Discount(String value) {
        this.value = initStr(value);
    }

    private Discount(BigDecimal value) {
        this.value = value;
    }

    public Discount reverse() {
        return new Discount(BigDecimal.ONE.subtract(value));
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    private BigDecimal initStr(String value) {
        if (Objects.isNull(value) || !value.matches("\\d\\.\\d+")) {
            throw new DiscountException("discount value is not correct");
        }

        var decimal = new BigDecimal(value);

        if (decimal.compareTo(BigDecimal.ZERO) < 0 || decimal.compareTo(BigDecimal.ONE) > 0) {
            throw new DiscountException("discount value is out of range");
        }

        return decimal;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(value, discount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
