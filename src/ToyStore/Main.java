package ToyStore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        Toy doll = new Toy(1, "Кукла", 10, 20);
        Toy car = new Toy(2, "Машинка", 15, 15);
        Toy ball = new Toy(3, "Мяч", 20, 10);

        toyStore.addToy(doll);
        toyStore.addToy(car);
        toyStore.addToy(ball);

        toyStore.updateToyWeight(1, 25);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ждем вашего розыгрыша. Для начала розыгрыша нажмите Enter.");
            scanner.nextLine();

            Toy prizeToy = toyStore.pickPrizeToy();
            if (prizeToy != null) {
                System.out.println("Поздравляем! Вы выиграли " + prizeToy.getName());
                toyStore.savePrizeToyToFile(prizeToy, "winners.txt");
            } else {
                System.out.println("Ничего не выиграно.");
            }

            // Получаем призовую игрушку из списка призовых игрушек
            Toy receivedPrizeToy = toyStore.getPrizeToy();
            if (receivedPrizeToy != null) {
                System.out.println("Получена призовая игрушка: " + receivedPrizeToy.getName());
            } else {
                System.out.println("Список призовых игрушек пуст.");
            }

            System.out.println("Хотите продолжить розыгрыш? (да/нет): ");
            String answer = scanner.nextLine().toLowerCase();
            if (!answer.equals("да")) {
                break;
            }
        }
    }
}


