

public class Item {
    private int itemId;
    private String name;
   // private String description;
    private double price;
    private int quantity;

    public Item(int itemId, String name, double price, int quantity) {
        this.itemId = itemId;
        this.name = name;
      //  this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
