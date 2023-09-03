package ToyStore;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ToyStore {
    private List<Toy> toys = new ArrayList<>();
    private List<Toy> prizeToys = new ArrayList<>();

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateToyWeight(int id, double weight) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                return;
            }
        }
    }

    public Toy pickPrizeToy() {
        if (toys.isEmpty()) {
            System.out.println("Магазин пуст, нет игрушек для розыгрыша.");
            return null;
        }

        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        Random rand = new Random();
        double randomWeight = rand.nextDouble() * totalWeight;

        double currentWeight = 0;
        Iterator<Toy> iterator = toys.iterator();
        while (iterator.hasNext()) {
            Toy toy = iterator.next();
            currentWeight += toy.getWeight();
            if (randomWeight <= currentWeight) {
                int remainingQuantity = toy.getQuantity();
                if (remainingQuantity > 0) {
                    Toy prizeToy = new Toy(toy.getId(), toy.getName(), 1, toy.getWeight());
                    toy.decreaseQuantity(1);
                    prizeToys.add(prizeToy);
                    return prizeToy;
                }
            }
        }

        return null;
    }


    public void savePrizeToyToFile(Toy prizeToy, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Выигрыш: " + prizeToy.getName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Toy getPrizeToy() {
        if (!prizeToys.isEmpty()) {
            Toy prizeToy = prizeToys.remove(0);
            return prizeToy;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Игрушки в магазине:\n");
        for (Toy toy : toys) {
            sb.append(toy.toString()).append("\n");
        }
        return sb.toString();
    }
}





