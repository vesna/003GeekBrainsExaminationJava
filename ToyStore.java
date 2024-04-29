import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyStore {
    private List<Toy> toys;
    // private List<Toy> prizeToys;

    public ToyStore() {
        toys = new ArrayList<>();
        // prizeToys = new ArrayList<>();
    }

    public void addToy(int id, String name, int quantity, double weight) {
        Toy toy = new Toy(id, name, quantity, weight);
        toys.add(toy);
    }

    public void setToyWeight(int id, double weight) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                break;
            }
        }
    }

    public void drawToys() {
        if (toys.isEmpty()) {
            System.out.println("В магазиненет игрушек");
        }
        double totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }
        Random rand = new Random();
        double randomNumber = rand.nextDouble() * totalWeight;

        double currentWeight = 0;
        Toy choosenToy = null;
        for (Toy toy : toys) {
            currentWeight += toy.getWeight();
            if (randomNumber < currentWeight) {
                choosenToy = toy;
                break;
            }
        }

        if (choosenToy != null) {
            System.out.println("Поздравляем, вы выиграли игрушку: " + choosenToy.getName());
            if (choosenToy.getQuantity() > 0) {
                choosenToy.setQuantity(choosenToy.getQuantity() - 1);
            } else {
                System.out.println("К сожалению, данная игрушка закончилась.");
            }
        } else {
            System.out.println("Ничего не выиграли.");
        }
    }

    public void saveToysToFile(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(toys);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Список игрушек успешно сохранен в файл " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении списка игрушек в файл " + filename);
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadToysFromFile(String filename){
        try{
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            toys = (List<Toy>) (objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Список игрушек успешно загружен из файла " + filename);
        } catch(IOException |ClassNotFoundException e){
            System.out.println("Ошибка при загрузке списка игрушек из файла " + filename);
            e.printStackTrace();
        }
    }
}