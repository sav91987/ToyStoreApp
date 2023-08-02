package Model;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import View.Printer;

public class Toy {
    long id;
    String title;
    int quantity;
    double winChance;

    /**
     * @param id        - ID игрушки
     * @param title     - название игрушки
     * @param quantity  - количество игрушек
     * @param winChance - шанс выигрыша данной игрушки
     */
    public Toy(long id, String title, int quantity, double winChance) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.winChance = winChance;
    }

    /**
     * Метод добавления новой игрушки
     * 
     * @param list    - список игрушек
     * @param printer - объект класса Printer (для вывода информации в кончоль или
     *                файл)
     * @return - обновленный список игрушек после добавления новой
     */
    public List<Toy> addNewToy(List<Toy> list, Printer printer) {
        Toy newToy = new Toy(id, title, quantity, winChance);
        // Поиск максимального id сдеди игрушек, чтобы позже внести неповторяющееся
        // значение для новой игрушки
        long maxId = 0;
        for (Toy elem : list) {
            if (elem.id > maxId) {
                maxId = elem.id;
            }
        }
        Scanner scanner = new Scanner(System.in);
        newToy.id = maxId + 1;

        String titleTest;
        boolean flag = true;

        while (flag) {
            printer.printMessage("Введите название игрушки: ");
            // Предварительное название новой игрушки
            titleTest = scanner.next();
            // Ищем совпадение по названию игрушки (title и titleTest)
            for (Toy elem : list) {
                if (elem.title.toLowerCase().equals(titleTest.toLowerCase())) {
                    printer.printMessage("Указанная игрушка уже существует!");
                    flag = true;
                    break;
                } else {
                    newToy.title = titleTest;
                    flag = false;
                }
            }
        }

        printer.printMessage("Введите количество игрушек: ");
        newToy.quantity = scanner.nextInt();
        newToy.winChance = 100 * Math.random();

        list.add(newToy);
        return list;
    }

    /**
     * Метод для проведения розыгрыша игрушек
     * 
     * @param list    - список игрушек
     * @param printer - объект класса Printer (для вывода информации в кончоль или
     *                файл)
     * @param count   - счетчик для подсчета количества проведенных розыгрышей
     * @return обновленный список (после каждого розыгрыша количество игрушек
     *         уменьшается)
     * @throws IOException - возможные ошибки при записи в файл
     */
    public List<Toy> drawing(List<Toy> list, Printer printer, int count) throws IOException {
        double maxWinChance = 0;

        int index = 0;
        Toy winnerToy;
        // Счетчик для проверки, что все игрушки разыграны
        int toyCount = 0;

        for (Toy elem : list) {

            // Проверяем по количеству есть ли вообще игрушки такого типа
            if (elem.getQuantity() != 0) {

                // Если есть, то рандомно задаем для данной игрушки шанс быть выиграной
                elem.setWinChance(100 * Math.random());
                // Находим выигрышную игрушку по максимальному winChance
                if (elem.getWinChance() > maxWinChance) {
                    //Запоминаем индекс будущего выигрышного элемента
                    index = list.indexOf(elem);
                    maxWinChance = elem.getWinChance();
                }
            } else
                toyCount++;
        }
        printer.printList(list);

        // Может случиться, что пройдя по циклу выше (96 строка) окажется, что нет ни одной игрушки для розыгрыша.
        //Тогда счетчик toyCount будет равен list.size(). В этом случае сообщим пользователю о том, что игрушек нет.
        if (toyCount != list.size()) {
            //Если доступные игрушки есть, то создаем новый новый объект класса Toy. Для заполния полей используем значения 
            //исходного объекта класса Toy в списке list (индекс этого объекта - index - определяли ранее) за исключением количества 
            //quantity, которое уменьшаем на единицу, т.к. одна игрушка разыграна
            winnerToy = new Toy(list.get(index).getId(), list.get(index).getTitle(), list.get(index).getQuantity() - 1,
                    list.get(index).getWinChance());
            //Записываем выигрышную игрушку в файл winner.txt и номер розыгрыша count
            printer.fileWriter(winnerToy, count);

            printer.printMessage("Вы выиграли Игрушку ***" + winnerToy.getTitle()
                    + "***\nВыигрышный шанс составил " + winnerToy.getWinChance());
            //Удаляем из исходного списка игрушку которая выиграла
            list.remove(index);
            //Добавляем игрушку, но с усеньшаным количеством
            list.add(winnerToy);
        } else
            printer.printMessage("Доступные для розыгрыша игрушки закончились :(");

        return list;
    }

   
    @Override
    public String toString() {
        return "Toy [id=" + id + ", title=" + title + ", quantity=" + quantity + ", winChance=" + winChance + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Toy other = (Toy) obj;
        if (id != other.id)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (quantity != other.quantity)
            return false;
        if (Double.doubleToLongBits(winChance) != Double.doubleToLongBits(other.winChance))
            return false;
        return true;
    }

    
    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getId() {
        return id;
    }

    public double getWinChance() {
        return winChance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setWinChance(double winChance) {
        this.winChance = winChance;
    }
}
