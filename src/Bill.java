import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Bill implements Serializable {

    private static int billCount = 0;
    private int billNumber;
    private String cashier_name;
    private String branch;
    private String customer_name = null;
    private List<GloceryItem> itemlist = new ArrayList<>();
    private double total_Price;
    private double total_discount;
    public boolean isPending = false;

    public Bill(String cashier_name,String branch){
        this.cashier_name = cashier_name;
        this.branch = branch;
        this.billNumber= ++billCount;
    }
    public Bill(String cashier_name,String branch,String
            customer_name){
        this.cashier_name = cashier_name;
        this.branch = branch;
        this.customer_name = customer_name;
        this.billNumber= ++billCount;
    }

    public void addItem(GloceryItem g){
        itemlist.add(g);
    }

    public void printBill(){
        if(!itemlist.isEmpty()){
            System.out.println("--------------------------------------------------------");
            System.out.println("|            Super Saving Super Market                   |");
                    System.out.println("--------------------------------------------------------");

            System.out.println("Cashier Name  : "+ this.cashier_name );
            System.out.println("Branch        : "+ this.branch);
            if(customer_name != null){
                System.out.println("Customer Name : "+
                        this.customer_name );
            }
            System.out.println();
            System.out.printf("   %-20s | %10s | %10s | %10s | %10s |%n","Item Name","Unit Price","Quantity","Discount","Net Price");

            int i = 1;
            for (GloceryItem g : itemlist){
                double netPrice = g.getQuantity() * g.getUnitPrice();
                double discount = g.getDiscount() * netPrice;
                System.out.printf("%d  %-20s | %10.2f | %10d | %10.2f | %10.2f |%n",i,g.getItem_name(),g.getUnitPrice(),g.getQuantity(),discount,netPrice);
                i++;
                this.total_Price += netPrice;
                this.total_discount += discount;
            }

            System.out.println();
            double netAmount = this.total_Price-this.total_discount;
            System.out.println("Total Price    : " + this.total_Price);
            System.out.println("Total Discount : " +
                    this.total_discount);
            System.out.println("Net Amount     : " + netAmount);

            System.out.println();

            DateTimeFormatter myFormat =
                    DateTimeFormatter.ofPattern("HH:mm:ss");

            System.out.println("Time : "+
                    java.time.LocalTime.now().format(myFormat));
            System.out.println("Date : "+ java.time.LocalDate.now());

            System.out.println();

        }
    }

    public int getBillNumber() {
        return billNumber;
    }
}