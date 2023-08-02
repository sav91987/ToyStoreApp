package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Toy;
import View.Printer;

public class ToyStore {

    /**
     * Конструктор класса ToyStore(магазин игрушек)
     */
    public ToyStore() {

    }

    public static void main(String[] args) throws IOException {

        // Создание объектов класса. Создаем изначально, чтобы было с чем работать
        Toy toy1 = new Toy(1, "cat", 4, 100 * Math.random());
        Toy toy2 = new Toy(2, "dog", 3, 100 * Math.random());
        Toy toy3 = new Toy(3, "bike", 2, 100 * Math.random());
        Toy toy4 = new Toy(4, "doll", 5, 100 * Math.random());
        Toy toy5 = new Toy(5, "ball", 3, 100 * Math.random());

        // Заполняем список созданными игрушками
        List<Toy> toyList = new ArrayList<Toy>();
        toyList.add(toy1);
        toyList.add(toy2);
        toyList.add(toy3);
        toyList.add(toy4);
        toyList.add(toy5);

        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        Toy toy = new Toy(0, null, 0, 0);

        printer.printMessage(
                "Добро пожаловать в магазин игрушек!");

        int key = 0;
        boolean flag = true;
        
        // Счетчик нужен будет для подсчета проведенных розыгрышей
        int count = 0;
        while (flag) {
            printer.printMessage(
                    "Выберите пункт меню: \n\t1. Показать список доступных игрушек и количество.\n\t2. Добавить новую игрушку.\n\t3. Провести розыгрыш игрушки\n\t0. Выход.");
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
            } else {
                printer.printMessage("Введено не число!");
                scanner.next();
            }

            switch (key) {
                case 1:
                    printer.printList(toyList);
                    break;
                case 2:
                    toyList = toy.addNewToy(toyList, printer);
                    break;
                case 3:
                    count++;
                    toyList = toy.drawing(toyList, printer, count);
                    ;
                    break;
                case 0:
                    flag = false;
            }
        }
    }
}
