package org.storkforge;

import org.storkforge.CurrencyConverter;
import org.storkforge.ConverterName;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        // Ladda alla CurrencyConverter-implementationer via SPI
        ServiceLoader<CurrencyConverter> loader = ServiceLoader.load(CurrencyConverter.class);

        // Skapa en map för att koppla konverterarnamn till implementationer
        Map<String, CurrencyConverter> converterMap = new HashMap<>();

        // Iterera igenom varje implementation och lagra den med namnet från annotationen
        for (CurrencyConverter converter : loader) {
            System.out.println("Discovered: " + converter.getClass().getName()); // Lägg till denna rad
            ConverterName nameAnnotation = converter.getClass().getAnnotation(ConverterName.class);
            if (nameAnnotation != null) {
                converterMap.put(nameAnnotation.value(), converter);
            }
        }

        //....

        // Om inga converters hittas, visa ett meddelande och avsluta
        if (converterMap.isEmpty()) {
            System.out.println("No currency converters found!");
            return;
        }

        // Visa alla tillgängliga converters till användaren
        System.out.println("Choose Converter:");
        for (String name : converterMap.keySet()) {
            System.out.println("- " + name);
        }

        // Läs in användarens val
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        // Hämta rätt converter baserat på användarens val
        CurrencyConverter chosen = converterMap.get(choice.toUpperCase());

        // Kontrollera om användaren valt en giltig converter och utför konvertering
        if (chosen != null) {
            System.out.print("Enter amount in SEK: ");
            double amount = scanner.nextDouble();
            System.out.println(amount + " SEK = " + chosen.convert(amount) + " " + chosen.getTargetCurrency());

        } else {
            System.out.println("Invalid choice.");
        }
    }
}

