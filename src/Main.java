import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Basket basket;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        File txtFile = new File("basket.json");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("shop.xml");

        XPath xPath = XPathFactory.newInstance().newXPath();
        boolean doLoad = Boolean.parseBoolean(xPath
                .compile("/config/load/enabled")
                .evaluate(doc));
        String loadFileName = xPath
                .compile("/config/load/fileName")
                .evaluate(doc);
        String loadFormat = xPath
                .compile("/config/load/format")
                .evaluate(doc);

        if (doLoad) {
            try {
                File loadFile = new File(loadFileName);
                if ("json".equals(loadFormat)) {
                    basket = Basket.loadFromJSON(loadFile);
                }
            } catch (FileNotFoundException e) {
                basket = new Basket(new String[]{"1. Хлеб", "2. Яблоки", "3. Молоко"}, new int[]{100, 200, 300});
            }
        }

        basket.printPurchases();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите товар и количество или введите end");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                break;
            }

            String[] amount = input.split(" ");
            int productNumber = Integer.parseInt(amount[0]) - 1;
            int productCount = Integer.parseInt(amount[1]);
            basket.addToCart(productNumber, productCount);
            ClientLog clientLog = new ClientLog(productNumber + 1, productCount);
            clientLog.log();
            basket.seveJSON(txtFile);
        }

        basket.printCart();
    }
}
