import static java.lang.Math.floor;

import java.math.BigDecimal;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        String name; //название продукта
        double price;// стоимость продукта
        int peopleAmount; //кол-во человек

        Product newProduct = new Product(); //создаем экземпляр класса Продукт для записи данных
        System.out.println("На скольких человек необходимо разделить счёт?");


        do { //цикл ввода и проверки кол-ва человек
            Scanner scanner = new Scanner(System.in);

            while (!scanner.hasNextInt()) { //проверка ввода корректного значения Int
                System.out.println("Введенное значение некорректно.");
                System.out.println("На скольких человек необходимо разделить счёт ?");
                scanner.next();
            }
            peopleAmount = scanner.nextInt();
            if (peopleAmount == 1) {
                System.out.println("Ошибка! Количество человек не может быть равно 1, так как в этом случае нет смысла ничего считать и делить.");
                System.out.println("Введите корректное число. На скольких человек необходимо разделить счёт?");
            }
            if (peopleAmount < 1) {
                System.out.println("Ошибка! Количество человек не может быть отрицательным или нулевым.");
                System.out.println("Введите корректное число. На скольких человек необходимо разделить счёт?");
            }
            if (peopleAmount > 1) {
                System.out.println("Отлично! Число человек = " + peopleAmount);
            }

        } while (peopleAmount <= 1); // окончание цикла ввода и проверки кол-ва человек
        do { // полный цикл ввода названия и стоимости товара
            System.out.println("Введите название товара:");
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine();
            while (name.trim().equals("")) { //проверка пустого ввода
                System.out.println("Вы ничего не ввели. Введите название товара:");
                name = scanner.nextLine();
            }
                     newProduct.nameList = newProduct.nameList + name + "\n"; //добавляем введенный товар в список товаров

            do { //  цикл ввода стоимости и проверки формата ввода
                System.out.println("Введите стоимость товара в формате 'рубли.копейки', например, '10.45':");
                Scanner scannerPrice = new Scanner(System.in);
                while (!scannerPrice.hasNextDouble()) { //проверка на корректность введенного формата Дробное значение
                    System.out.println("Введенное значение некорректно.");
                    System.out.println("Введите стоимость товара в формате 'рубли.копейки', например, '10.45':");
                    scannerPrice.next();
                }
                price = scannerPrice.nextDouble();

                int digitsAfterDot = BigDecimal.valueOf(price).scale(); //определяем кол-во знаков после запятой
                if (digitsAfterDot == 2) { //проверка на кол-во знаков после запятой в соответствии с требуемым форматом (два знака после запятой)
                    break;
                }
                System.out.println("Введенное значение некорректно. Кол-во знаков после запятой не равно 2.");
            } while (true); // окончание цикл ввода стоимости и проверки формата ввода

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
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh); //+ "флор: " + newProduct.z + ", numDigits: " + newProduct.numDigits + ", один знак: " + newProduct.y + ", два знака: " + newProduct.x);
 //ТеСТ
        newProduct.sumPerPerson = 0; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);

        newProduct.sumPerPerson = 1; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 2; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 3; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 4; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 5; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 10; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 11; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 12; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);

        newProduct.sumPerPerson = 19; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 20; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 21; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 22; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 25; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 30; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 100; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 101; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 102; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 105; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 110; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 111; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 112; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 115; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 120; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 121; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 122; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 125; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);
        newProduct.sumPerPerson = 130; // вычисляем сумму на человека
        //определяем падеж слова "рубль":
        newProduct.padezh = Product.definePadezh(newProduct.sumPerPerson, newProduct.twoLastDigits, newProduct.oneLastDigit, newProduct.bezDrobnoyInt);
        System.out.println("Сумма, которую должен заплатить каждый человек, равна: " + String.format("%.2f", newProduct.sumPerPerson) + newProduct.padezh);

    }
    }


    class Product {
        int bezDrobnoyInt, twoLastDigits, oneLastDigit; //сумма на человека интовая, две последних цифры суммы на человека, одна последняя цифра суммы на человека
        String nameList = ""; //список товаров
        double sum, sumPerPerson; //общая сумма товаров, сумма на человека
        String padezh; //падеж написания слова "рубль"


        static String definePadezh(double sumPerPerson, int twoLastDigits, int oneLastDigit, int bezDrobnoyInt) {
            int padezhVar=1;  //вариант падежа. Если 1 то рубль, если 2 то рубля, если 3 то рублей
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
                    padezhVar = 3;;
                }

                if ((twoLastDigits > 4) & (twoLastDigits < 21)) {
                    padezhVar = 3;
                }
                switch (padezhVar){
                    case(1):
                        return " рубль.";

                    case(2):
                        return " рубля.";

                    case(3):
                        return " рублей.";


                }


            return " error.";
        }


        }












