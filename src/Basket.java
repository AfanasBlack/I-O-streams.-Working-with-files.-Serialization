import java.io.*;

public class Basket {

    protected String[] products;
    protected int[] prices;
    protected int[] basket;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        basket = new int[products.length];
    }

    public void addToCart(long productNum, long amount) {
        for (int i = 0; i < basket.length; i++) {
            if (productNum == i) basket[i] += amount;
        }
        try {
            System.out.println("Новая запись в файл произведена");
            saveTxt(new File("basket.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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

    public void saveTxt(File textFile) throws IOException {

        try (PrintWriter out = new PrintWriter(textFile);) {
            for (int i = 0; i < basket.length; i++) {
                out.print(products[i] + "," + basket[i] + "," + prices[i] + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            String line = reader.readLine();
            String[] newProduct = new String[0];
            int[] newBasket = new int[0];
            int[] newPrice = new int[0];
            while (line != null) {
                String[] lineValues = line.split(",");
                for (int value = 0; value < lineValues.length; value++) {
                    if (value == 0) newProduct = addArrayString(newProduct.length, newProduct, String.valueOf(lineValues[value]));
                    if (value == 1) newBasket = addArrayInt(newBasket.length, newBasket, Integer.parseInt(lineValues[value]));
                    if (value == 2) newPrice = addArrayInt(newPrice.length, newPrice, Integer.parseInt(lineValues[value]));
                }
                line = reader.readLine();
            }
            Basket basket = new Basket(newProduct, newPrice);
            basket.basket = newBasket;
            return basket;
        } catch (IOException e) {
            System.out.println("Загрузить корзину не удалось!");
        }
        return null;
    }

    public static int[] addArrayInt(int num, int[] arr, int x) {
        int[] newarr = new int[num + 1];
        if (num >= 0) System.arraycopy(arr, 0, newarr, 0, num);
        newarr[num] = x;
        return newarr;
    }

    public static String[] addArrayString(int num, String[] arr, String x) {
        String[] newarr = new String[num + 1];
        if (num >= 0) System.arraycopy(arr, 0, newarr, 0, num);
        newarr[num] = x;
        return newarr;
    }
}

