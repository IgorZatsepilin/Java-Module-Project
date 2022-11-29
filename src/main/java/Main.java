import static java.lang.Math.floor;

import java.math.BigDecimal;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String name; //название продукта
        double price;// стоимость продукта
        int peopleAmount; //кол-во человек


        Product newProduct = new Product(); //создаем экземпляр класса Продукт для записи данных


        peopleAmount = peopleAmountInputCheck(); //ввод и проверка формата ввода кол-ва человек
        do {
            name = inputNameFormatCheck(); //ввод и проерка формата ввода названия продукта
            newProduct.nameList = newProduct.nameList + name + "\n"; //добавляем введенный товар в список товаров

            price = inputPriceFormatCheck(); //ввод и проверка формата ввода Стоимости продукта

            newProduct.sum = newProduct.sum + price; //добавляем стоимость текущего товара к суммарной стоимости товаров
            System.out.println("Товар добавлен в калькулятор. Хотите ли добавить еще один товар? Введите любой символ (если хотите) или Завершить (если не хотите)");
            Scanner scanner1 = new Scanner(System.in);
            String addNewProduct = scanner1.nextLine();
            String strLower = addNewProduct.toLowerCase(); //приводим введенное строковое значение к нижнему регистру
            if (strLower.equals("завершить")) { //проверяем "завершить"
                break;
            }

        } while (true); // закончен полный цикл ввода товаров и стоимости

        System.out.println("Добавленные товары:");
        System.out.println(newProduct.nameList); //выводим список товаров
        newProduct.sumPerPerson = newProduct.sum / peopleAmount; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);

    }

    static int peopleAmountInputCheck() {
        String question = "На скольких человек необходимо разделить счёт?";
        String request = "Введите корректное число. ";
        System.out.println(question);
        do {
            Scanner scanner = new Scanner(System.in);

            while (!scanner.hasNextInt()) { //проверка ввода корректного значения Int
                System.out.println("Введенное значение некорректно.\n" + question);
                scanner.next();
            }
            int peopleAmountInput = scanner.nextInt();
            if (peopleAmountInput == 1) {
                System.out.println("Ошибка! Количество человек не может быть равно 1, так как в этом случае нет смысла ничего считать и делить.\n" + request + " " + question);
            }
            if (peopleAmountInput < 1) {
                System.out.println("Ошибка! Количество человек не может быть отрицательным или нулевым.\n" + request + " " + question);
            }
            if (peopleAmountInput > 1) {
                System.out.println("Отлично! Число человек = " + peopleAmountInput);
                return peopleAmountInput;
            }

        } while (true);
    }

    static String inputNameFormatCheck() {
        String request = "Введите название товара:";
        System.out.println(request);
        Scanner scanner = new Scanner(System.in);
        String InputName = scanner.nextLine();
        while (InputName.trim().equals("")) { //проверка пустого ввода
            System.out.println("Вы ничего не ввели. " + request);
            InputName = scanner.nextLine();
        }
        return InputName;
    }

    static Double inputPriceFormatCheck() {
        String question = "Введите стоимость товара в формате 'рубли.копейки', например, '10.45':";
        do {
            System.out.println(question);
            Scanner scannerPrice = new Scanner(System.in);
            while (!scannerPrice.hasNextDouble()) { //проверка на корректность введенного формата Дробное значение
                System.out.println("Введенное значение некорректно.\n" + question);
                scannerPrice.next();
            }
            Double priceInput = scannerPrice.nextDouble();

            int digitsAfterDot = BigDecimal.valueOf(priceInput).scale(); //определяем кол-во знаков после запятой
            if (digitsAfterDot == 2) { //проверка на кол-во знаков после запятой в соответствии с требуемым форматом (два знака после запятой)
                return priceInput;

            }
            System.out.println("Введенное значение некорректно. Кол-во знаков после запятой не равно 2.");
        } while (true); // окончание цикл ввода стоимости и проверки формата ввода


    }
}


class Product {
    int bezDrobnoyInt, twoLastDigits, oneLastDigit; //сумма на человека интовая, две последних цифры суммы на человека, одна последняя цифра суммы на человека
    String nameList = ""; //список товаров
    double sum, sumPerPerson; //общая сумма товаров, сумма на человека
    String padezh; //падеж написания слова "рубль"


    static String definePadezh(double sumPerPerson, int twoLastDigits, int oneLastDigit, int bezDrobnoyInt) {
        int padezhVar = 1;  //вариант падежа. Если 1 то рубль, если 2 то рубля, если 3 то рублей
        double bezDrobnoy = floor(sumPerPerson); //отбрасываем дробную часть
        bezDrobnoyInt = (int) bezDrobnoy; //приводим к целочисленному типу
        oneLastDigit = bezDrobnoyInt % 10; //определяем последнию цифру
        twoLastDigits = bezDrobnoyInt % 100; //определяем две последних цифры

        if (oneLastDigit == 0) {
            padezhVar = 3;
        }
        if (oneLastDigit == 1) {
            padezhVar = 1;
        }
        if (((oneLastDigit > 1) & (oneLastDigit < 5))) {
            padezhVar = 2;
        }
        if ((oneLastDigit > 4) & (oneLastDigit <= 9)) {
            padezhVar = 3;
            ;
        }

        if ((twoLastDigits > 4) & (twoLastDigits < 21)) {
            padezhVar = 3;
        }
        switch (padezhVar) {
            case (1):
                return " рубль.";

            case (2):
                return " рубля.";

            case (3):
                return " рублей.";

            default:
                return " error.";
        }


    }


}












