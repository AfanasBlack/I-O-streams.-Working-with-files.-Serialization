import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Basket {

    protected String[] products;
    protected int[] prices;
    protected int[] basket;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        basket = new int[products.length];
    }

    public void addToCart(int productNum, int amount) {
        basket[productNum] += amount;
    }

    public void printCart() {
        int sumProducts = 0;

        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + " " + basket[i] + " шт " + (basket[i] * prices[i]) + " руб в сумме");
            sumProducts += (basket[i] * prices[i]);
        }
        System.out.println("Итого: " + sumProducts + " руб");
    }

    public void printPurchases() {
        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + " " + prices[i] + " руб/шт");
        }
    }

    public void seveJSON(File file) throws IOException {
        try (PrintWriter out = new PrintWriter(file)) {
            Gson gson = new Gson();
            String json = gson.toJson(this);
            out.println(json);
        }
    }

    public static Basket loadFromJSON(File file) throws IOException{
        try (Scanner scanner = new Scanner(file)) {
            Gson gson = new Gson();
            String json = scanner.nextLine();
            return gson.fromJson(json, Basket.class);
        }
    }
}

