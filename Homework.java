import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*
 * Реализовать консольное приложение - телефонный справочник.
У одной фамилии может быть несколько номеров.
Пользователь может вводить команды:
1. ADD Ivanov 88005553535 - добавить в справочник новое значение.
Первый аргумент - фамилия, второй - номер телефона
2. GET Ivanov - получить список всех номеров по фамилии
3. REMOVE Ivanov - удалить все номера по фамилии
4. LIST - Посмотреть все записи
5. EXIT - Завершить программу
Если при GET или REMOVE нужной фамилии нет, вывести информацию об этом


Пример взаимодействия (=> - это вывод на консоль):
ADD Ivanov 8 800 555 35 35
ADD Ivanov 8 800 100 10 10
GET Ivanov => [8 800 555 35 35, 8 800 100 10 10]
ADD Petrov 8 555 12 34
LIST => Ivanov = [8 800 5553535, 8 800 100 10 10], Petrov = [8 555 12 34]
REMOVE Ivanov
LIST => Petrov = [8 555 12 34]
GET NoName => Не найдена запись с фамилией "NoName"
REMOVE NoName => Не найдена запись с фамилией "NoName"
 */

public class Homework {
    static List<String> commandList = Arrays.asList(
            "add", "get", "list", "remove", "exit");
    static HashMap<String, List<String>> phonebook = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] entryValues = scanner.nextLine().split(" ");
            if (!checkCommands(entryValues[0])) {
                System.out.println("There's no such command. Try again.");
                continue;
            }
            if (entryValues[0].equals("exit")) {
                System.exit(0);
            }
            try {
                if ("add".equals(entryValues[0])) {
                    addSubscribers(entryValues);
                }
                if ("get".equals(entryValues[0])) {
                    getInfo(entryValues);
                }
                if ("remove".equals(entryValues[0])) {
                    removeSubscriber(entryValues);
                }
            } catch (Exception e) {
                System.out.println("Incorrect input. Try again.");
            }
            if ("list".equals(entryValues[0])) {
                listAllSubscribers();
            }
        }
    }

    public static void addSubscribers(String[] entryValues) {
        List<String> a = new ArrayList<>();
        if (!phonebook.containsKey(entryValues[1])) {
            a.add(entryValues[2]);
            phonebook.put(entryValues[1], a);
        } else {
            a = phonebook.get(entryValues[1]);
            a.add(entryValues[2]);
        }
    }

    public static void getInfo(String[] entryValues) {
        if (phonebook.containsKey(entryValues[1])) {
            System.out.println(
                    entryValues[1] + " = " + phonebook.get(entryValues[1]));
        } else {
            System.out.println(
                    "The last name " + entryValues[1] + " was not found.");
        }
    }

    public static void listAllSubscribers() {
        for (HashMap.Entry<String, List<String>> entry : phonebook.entrySet()) {
            System.out.println(
                    entry.getKey() + " = " + entry.getValue());
        }
    }

    public static void removeSubscriber(String[] entryValues) {
        if (phonebook.containsKey(entryValues[1])) {
            phonebook.remove(entryValues[1]);
        } else {
            System.out.println(
                    "The last name " + entryValues[1] + " was not found.");
        }
    }

    public static boolean checkCommands(String string) {
        if (commandList.contains(string)) {
            return true;
        }
        return false;
    }
}