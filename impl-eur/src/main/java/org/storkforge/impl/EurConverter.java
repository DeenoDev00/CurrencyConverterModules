package org.storkforge.impl;


import org.storkforge.CurrencyConverter;
import org.storkforge.ConverterName;

@ConverterName("EUR")
public class EurConverter implements CurrencyConverter {
    public double convert(double amount) {
        return amount * 0.1;
    }

    public String getTargetCurrency() {
        return "EUR";
    }
}
