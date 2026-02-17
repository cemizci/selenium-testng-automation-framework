package Models.SauceDemo;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(desc, item.desc) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, price);
    }
}
