package Models.SauceDemo;

public class Item {
    private final String name;
    private final String desc;
    private final String price;

    public Item(String name, String desc, String price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public String getPrice() { return price; }
}
