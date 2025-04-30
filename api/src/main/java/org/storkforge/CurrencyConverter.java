package org.storkforge;

public interface CurrencyConverter {
    double convert(double amount);
    String getTargetCurrency();
}
