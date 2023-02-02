import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import static jdk.internal.icu.text.UTF16.charAt;

public class Main {


    public static void main(String[] args) throws IOException {

        String expression = "expression";
        Scanner in = new Scanner(System.in);
        System.out.println("print expression ") ; expression = in.nextLine();
        String result = calc(expression);
        System.out.println(result);
        in.close();
    }
        public static String calc(String input) throws IOException {

            // Result calculation.
            int result = 0;
            String resultStr = "resultStr";
            int[] nums = parsing(input);
            switch (nums[2]){
                case(0):{
                    result = nums[0] + nums[1];
                    break;
                }
                case(1):{
                    result = nums[0] - nums[1];
                    break;
                }
                case(2):{
                    result = nums[0] * nums[1];
                    break;
                }
                case(3):{
                    result = nums[0] / nums[1];
                    break;
                }
            }

            resultStr = Integer.toString(result);
            if(nums[3] == 1){
                if(result <= 0){
                    try {
                        throw new FormatException("Результат меньше или равен нулю. Результатом работы калькулятора с римскими числами могут быть только положительные числа");
                    } catch (FormatException e) {
                        throw new RuntimeException(e);
                    }
                }

                resultStr = makeRoman(result);

            }

            return resultStr;

        }

    public static int[] parsing(String input)  {

        // Reading numbers and mathematical operation.
        int[] nums = new int[] { 0, 0, 0, 0};// a, b, operation, roman
        String[] str1 = input.split(" ");
        if(str1.length != 3 ){
            try {
                throw new FormatException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *).\nВведите другое выражение");
            } catch (FormatException e) {
                throw new RuntimeException(e);
            }
        }

        String[] romanNumbers = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        if((str1[0].indexOf(46) == 1 || str1[0].indexOf(44) == 1) || (str1[2].indexOf(46) == 1 || str1[2].indexOf(44) == 1)){
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Калькулятор умеет работать только с целыми числами.\nВведите целое число");
            }
        }

        for (int i = 0; i < 10; i++){
            if(str1[0].equals(romanNumbers[i])){ nums[0] = i + 1; nums[3] = 1;}

            if(str1[2].equals(romanNumbers[i])){ nums[1] = i + 1; nums[3] = 1;}
        }
        if ((nums[0] == 0 && nums[1] != 0) || (nums[0] != 0 && nums[1] == 0) ){
            try {
                throw new FormatException("используются одновременно разные системы счисления");
            } catch (FormatException e) {
                throw new RuntimeException(e);
            }
        }

        if(nums[0] == 0 || nums[1] == 0){
            nums[0] = Integer.parseInt(str1[0]);
            nums[1]= Integer.parseInt(str1[2]);
        }

        if ((nums[0] < 1 || nums[0] > 10) || (nums[1] < 1 || nums[1] > 10)){
            try {
                throw new FormatException("Калькулятор умеет работать только с числами от 1 до 10 включительно.\nВведите другое число");

        } catch (FormatException e) {
                throw new RuntimeException(e);
            }
        }

        nums[2] = 4;
        String[] chars = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++){
            if(str1[1].equals(chars[i])){ nums[2] = i;}
        }

        if(nums[2] == 4){
            try {
                throw new FormatException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *).\nВведите другое выражение");
            } catch (FormatException e) {
                throw new RuntimeException(e);
            }
        }

        return nums;
    }
    public static String makeRoman(Integer input){
        String[] romanNumbers = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV",
                "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV",
                "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
                "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        return romanNumbers[input];
    }

}