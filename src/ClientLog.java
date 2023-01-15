import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class ClientLog {
    private final int productNumber;
    private final int productCount;

    @Override
    public String toString() {
        return productNumber + "," + productCount;
    }

    public ClientLog(int productNumber, int productCount) {
        this.productNumber = productNumber;
        this.productCount = productCount;
    }

    public void log() throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter("client.csv", true))) {
            writer.writeNext(toString().split(","));
        }
    }
}
