package de.eckerta.kata;

public class RomanNumerals {
    private static final Number[] numbers = new Number[] {
            new Number(1, "I"),
            new Number(4, "IV"),
            new Number(5, "V"),
            new Number(9, "IX"),
            new Number(10, "X"),
            new Number(40, "XL"),
            new Number(50, "L"),
            new Number(90, "XC"),
            new Number(100, "C"),
            new Number(400, "CD"),
            new Number(500, "D"),
            new Number(900, "CM"),
            new Number(1000, "M"),
    };

    public static String translate(int number) {
        Number n = findLargestNotLargerThan(number);
        if (n != null) {
            return n.letters + translate(number - n.value);
        } else {
            return "";
        }
    }

    public static Number findLargestNotLargerThan(int number) {
        Number result = null;
        for (Number n : numbers) {
            if (n.value <= number)
                result  = n;
        }
        return result;
    }

    private static class Number {
        final int value;
        final String letters;

        public Number(int value, String letters) {
            this.value = value;
            this.letters = letters;
        }
    }
}
