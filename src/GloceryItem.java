import java.io.Serializable;

public class GloceryItem implements Serializable {
    private String item_code;
    private String item_name;
    private String weightPerSize;
    private String MFD;
    private String EXP;
    private String manufacturer;
    private Double unitPrice;
    private int quantity;
    private double discount;

    GloceryItem(String itemCode) throws ItemCodeNotFound {
        // Currency c = Currency.getInstance("LKR");
        switch (itemCode) {
            case "0001" -> {
                this.item_code = "0001";
                this.item_name = "Gold Marie";
                this.unitPrice = 100.00;
                this.weightPerSize = "40g";
                this.manufacturer = "Maliban";
                this.discount = 0.1;
                this.EXP = "2024-10-20";
                this.MFD = "2024-02-23";
            }
            case "0002" -> {
                this.item_code = "0002";
                this.item_name = "HappyCow";
                this.unitPrice = 260.00;
                this.weightPerSize = "120g";
                this.manufacturer = "WOERLE";
                this.discount = 0.15;
                this.EXP = "2024-08-15";
                this.MFD = "2024-01-15";
            }
            case "0003" -> {
                this.item_code = "0003";
                this.item_name = "Lemon SunCrush";
                this.unitPrice = 490.00;
                this.weightPerSize = "300g";
                this.manufacturer = "SunCrush";
                this.discount = 0.05;
                this.EXP = "2024-12-07";
                this.MFD = "2024-01-08";
            }
            case "0004" -> {
                this.item_code = "0004";
                this.item_name = "Mix Fruit Jam";
                this.unitPrice = 340.00;
                this.weightPerSize = "55g";
                this.manufacturer = "MD";
                this.discount = 0.13;
                this.EXP = "2024-10-12";
                this.MFD = "2023-09-11";
            }
            case "0005" -> {
                this.item_code = "0005";
                this.item_name = "Mango Drink";
                this.unitPrice = 540.00;
                this.weightPerSize = "200g";
                this.manufacturer = "SMAK";
                this.discount = 0.15;
                this.EXP = "2024-08-05";
                this.MFD = "2023-12-05";
            }
            default -> throw new ItemCodeNotFound("Item Code Not Found: " + itemCode);
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getItem_name(){
        return this.item_name;
    }
    public Double getUnitPrice(){
        return this.unitPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getDiscount() {
        return discount;
    }
}