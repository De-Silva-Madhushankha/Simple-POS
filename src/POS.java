import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class POS {
    private String cashier;
    private String branch;

    POS(String cashier, String branch){
        this.cashier = cashier;
        this.branch = branch;
    }

    public GloceryItem getItemDetails() {
        GloceryItem item = null;
        try {
            InputStreamReader r = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(r);
            System.out.print("Enter item code : ");
            String item_code = br.readLine();

            item = new GloceryItem(item_code);
            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(br.readLine());
            item.setQuantity(quantity);

//            br.close();
//            r.close();

        } catch (ItemCodeNotFound e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (IOException e) {
            System.out.println("Invalid item code.");
            return null;
        }
        return item;
    }
}