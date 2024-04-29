import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToyStore store = new ToyStore();

        store.addToy(1, "Toy 1", 10, 20);
        store.addToy(2, "Toy 2", 5, 10);
        store.addToy(3, "Toy 3", 20, 70);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Выберите действие:");
            System.out.println("1. Розыгрыш игрушки");
            System.out.println("2. Добавление новой игрушки");
            System.out.println("3. Изменение веса (частоты выпадания) игрушки");
            System.out.println("4. Сохранить сипоск игрушек в файл");
            System.out.println("5. Загрузить список игрушек из файла");
            System.out.println("6. Выйти");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    store.drawToys();
                    break;
                case 2:
                    System.out.println("Введите id новой игрушки:");
                    int id = scanner.nextInt();
                    System.out.println("Введите называние новой игрушки:");
                    String name = scanner.next();
                    System.out.println("Введите количество новой игрушки:");
                    int quantity = scanner.nextInt();
                    System.out.println("Введите вес новой игрушки:");
                    double weight = scanner.nextDouble();
                    store.addToy(id, name, quantity, weight);
                    break;
                case 3:
                    System.out.println("Введите id игрушки у которой необходимо изменить вес:");
                    int idToy = scanner.nextInt();
                    System.out.println("Введите новый вес:");
                    double newWeight = scanner.nextDouble();
                    store.setToyWeight(idToy, newWeight);
                    break;
                case 4:
                    System.out.println("Введите имя файла для сохранения списка игрушек:");
                    String saveFileName = scanner.next();
                    store.saveToysToFile(saveFileName);
                    break;
                case 5:
                    System.out.println("Введите имя файла для загрузки списка игрушек:");
                    String loadFileName = scanner.next();
                    store.loadToysFromFile(loadFileName);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Не верный выбор");
                    break;
            }

        }
    }
}