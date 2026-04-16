package models.saucedemo;

import java.util.Objects;

public class Item {
    private final String name;
    private final String desc;
    private final String price;
    private final String imageSrc;

    public Item(String name, String desc, String price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.imageSrc = null;
    }

    public Item(String name, String desc, String price, String image) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.imageSrc = image;
    }

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public String getPrice() { return price; }
    public String getImage() { return imageSrc; }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price='" + price + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(desc, item.desc) &&
                Objects.equals(price, item.price) &&
                Objects.equals(imageSrc, item.imageSrc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, price, imageSrc);
    }
}
