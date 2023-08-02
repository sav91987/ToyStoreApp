package View;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import Model.Toy;

public class Printer {

    /**
     * Метод для вывода в консоль элементов списка игрушек
     * 
     * @param list - список игрушек
     */
    public void printList(List<Toy> list) {
        for (Toy elem : list) {
            System.out.println("Игрушка: " + elem.getTitle() + " | Количество: " + elem.getQuantity() + " | Шанс "
                    + elem.getWinChance());
        }
    }

    /**
     * Метод вывода различный сообщений, информирующих пользователя
     * 
     * @param message - любое входное соощение
     */
    public void printMessage(String message) {
        System.out.println("*****************************************************\n");
        System.out.println("\t" + message);
        System.out.println("\n*****************************************************");
    }

    /**
     * Метод записи выигранной игрушки и номера розыгрыша в файл
     * 
     * @param toy   - игрушка
     * @param count - номер розыгрыша
     * @throws IOException
     */
    public void fileWriter(Toy toy, int count) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("winner.txt", true), "UTF-8"))) {
            bw.write("Поздравляем!\nВы выиграли Игрушку ***" + toy.getTitle()
                    + "***!\nВыигрышный шанс составил " + toy.getWinChance() + ".\nНомер розыгрыша: " + count + "\n");
            bw.write("\n");
            bw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
