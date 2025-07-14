import java.net.*;
import java.util.Scanner;
import java.io.*;
import org.json.JSONObject;

public class CurrencyConverter {

    private static final String API_URL = "https://open.er-api.com/v6/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🌍 Welcome to the Currency Converter!");

        System.out.print("Enter base currency (e.g., USD, INR): ");
        String baseCurrency = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter target currency (e.g., EUR, JPY): ");
        String targetCurrency = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = getValidAmount(scanner);

        try {
            double convertedAmount = convertCurrency(baseCurrency, targetCurrency, amount);
            System.out.printf("💱 %.2f %s = %.2f %s\n",
                amount, baseCurrency, convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("❌ Failed to fetch exchange rate. " + e.getMessage());
        }

        scanner.close();
    }

    private static double getValidAmount(Scanner scanner) {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount > 0) return amount;
                else System.out.print("⚠️ Amount must be greater than 0. Try again: ");
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Invalid number. Enter amount again: ");
            }
        }
    }

    private static double convertCurrency(String base, String target, double amount) throws Exception {
        String urlStr = API_URL + base;

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder jsonResponse = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            jsonResponse.append(inputLine);
        }
        in.close();

        JSONObject obj = new JSONObject(jsonResponse.toString());

        if (!obj.has("rates") || !obj.getJSONObject("rates").has(target)) {
            throw new RuntimeException("Invalid currency code or unsupported currency.");
        }

        double rate = obj.getJSONObject("rates").getDouble(target);
        return amount * rate;
    }
}
