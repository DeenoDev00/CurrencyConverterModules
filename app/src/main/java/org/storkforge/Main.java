package org.storkforge;

import org.storkforge.CurrencyConverter;
import org.storkforge.ConverterName;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ServiceLoader<CurrencyConverter> loader = ServiceLoader.load(CurrencyConverter.class);
        List<CurrencyConverter> converters = new ArrayList<>();
        List<String> names = new ArrayList<>();

        for (CurrencyConverter converter : loader) {
            converters.add(converter);
            ConverterName annotation = converter.getClass().getAnnotation(ConverterName.class);
            names.add(annotation != null ? annotation.value() : converter.getClass().getSimpleName());
        }

        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose converter: ");
        int choice = scanner.nextInt();

        CurrencyConverter selected = converters.get(choice - 1);
        System.out.print("Amount in SEK: ");
        double amount = scanner.nextDouble();
        System.out.println("Converted to " + selected.getTargetCurrency() + ": " + selected.convert(amount));
    }
}
