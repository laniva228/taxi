package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaxiDAO dao = new TaxiDAO();

        while (true) {

            System.out.println("\n ТАКСИ ");
            System.out.println("1. Показать все поездки");
            System.out.println("2. Добавить поездку");
            System.out.println("3. Найти поездку");
            System.out.println("4. Изменить поездку");
            System.out.println("5. Удалить поездку");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    for (String trip : dao.getAllTrips()) {
                        System.out.println(trip);
                    }
                    break;

                case 2:
                    System.out.print("ID водителя: ");
                    int driverId = scanner.nextInt();
                    System.out.print("ID пассажира: ");
                    int passengerId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Откуда: ");
                    String from = scanner.nextLine();

                    System.out.print("Куда: ");
                    String to = scanner.nextLine();

                    System.out.print("Цена: ");
                    double price = scanner.nextDouble();

                    dao.addTrip(driverId, passengerId, from, to, price);
                    break;

                case 3:
                    System.out.print("Введите ID поездки: ");
                    int searchId = scanner.nextInt();
                    dao.findTrip(searchId);
                    break;

                case 4:
                    System.out.print("Введите ID поездки: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Новый адрес отправления: ");
                    String newFrom = scanner.nextLine();

                    System.out.print("Новый адрес назначения: ");
                    String newTo = scanner.nextLine();

                    System.out.print("Новая цена: ");
                    double newPrice = scanner.nextDouble();

                    dao.updateTrip(updateId, newFrom, newTo, newPrice);
                    break;

                case 5:
                    System.out.print("Введите ID поездки: ");
                    int deleteId = scanner.nextInt();

                    dao.deleteTrip(deleteId);
                    break;

                case 0:
                    System.out.println("Программа завершена.");
                    return;

                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}