import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] products = {"1. Хлеб ", "2. Яблоки ", "3. Молоко "};

        int[] prices = {100, 200, 300};

        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + prices[i] + " руб/шт");
        }

        int[] basket = {0, 0, 0};

        int sumProducts = 0;

        while (true) {
            System.out.println("Выберите товар и количество или введите end");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] amount = input.split(" ");
            int productNumber = Integer.parseInt(amount[0]) - 1;
            int productCount = Integer.parseInt(amount[1]);

            if (productNumber == 0) {
                basket[0] += productCount;
            } else if (productNumber == 1) {
                basket[1] += productCount;
            } else {
                basket[2] += productCount;
            }

            if (sumProducts == 0) {
                sumProducts += ((basket[0] * prices[0]) + (basket[1] * prices[1]) + (basket[2] * prices[2]));
            } else {
                sumProducts = 0;
                sumProducts += ((basket[0] * prices[0]) + (basket[1] * prices[1]) + (basket[2] * prices[2]));
            }
        }
        System.out.println("Ваша корзина:");
        System.out.println(products[0] + basket[0] + " шт " + (basket[0] * prices[0]) + " руб в сумме");
        System.out.println(products[1] + basket[1] + " шт " + (basket[1] * prices[1]) + " руб в сумме");
        System.out.println(products[2] + basket[2] + " шт " + (basket[2] * prices[2]) + " руб в сумме");
        System.out.println("Итого: " + sumProducts + " руб");
    }
}
