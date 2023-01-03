import java.io.File;
import java.util.Scanner;

public class Main {

    static Basket basket;

    public static String[] setProduct() {
        String[] products = new String[] {"1. Хлеб", "2. Яблоки", "3. Молоко"};
        return products;
    }

    public static int[] setPrices() {
        int[] prices = new int[] {100, 200, 300};
        return prices;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File textFile = new File("basket.bin");
        if (textFile.exists()) {
            System.out.println("Файл найден");
            basket = Basket.loadFromBinFile(textFile);
        } else {
            System.out.println("Файл создан");
            basket = new Basket(setProduct(), setPrices());
        }

        basket.printPurchases();

        while (true) {
            System.out.println("Выберите товар и количество или введите end");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                break;
            }

            String[] amount = input.split(" ");
            long productNumber = Integer.parseInt(amount[0]) - 1;
            long productCount = Integer.parseInt(amount[1]);
            basket.addToCart(productNumber, productCount);
        }
        basket.printCart();
    }
}
