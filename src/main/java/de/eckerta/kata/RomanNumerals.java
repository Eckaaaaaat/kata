package de.eckerta.kata;

import org.jetbrains.annotations.NotNull;

public class RomanNumerals {
    public static String translate(int number) {
        if (number >= 1000)
            return "M" + translate(number - 1000);

        return translateLarge(number, "IVXXLCCDM");
    }



    @NotNull
    private static String translateLarge(int number, String letters) {
        if (number == 0)
            return "";

        String prefix = translateLarge(number / 10, letters.substring(3));
        return prefix + translateSingleDigit(number % 10, letters);
    }

    @NotNull
    private static String translateSingleDigit(int number, String letters) {
        if (number == 0)
            return "";

        if (number == 4)
            return letters.substring(0, 2);

        if (number == 9)
            return letters.substring(0, 1) + letters.substring(2,3);

        if (number >= 5)
            return letters.substring(1,2) + translateLarge(number - 5, letters);

        return letters.substring(0, 1) + translateLarge(number - 1, letters);
    }
}
