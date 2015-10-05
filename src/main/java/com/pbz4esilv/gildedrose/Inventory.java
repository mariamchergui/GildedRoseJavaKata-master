package com.pbz4esilv.gildedrose;
import java.util.stream.Stream;

public class Inventory {

    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };

    }


    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    //Reusable method for updating Backstage and Aged Brie as nearly same constraints apply
    public void updateBSAB(Item item) {
        int itemSellin = item.getSellIn();
        int itemQuality = item.getQuality();
        if (itemSellin <= 10 && itemSellin > 5)
            item.setQuality(itemQuality + 2 <= 50 ? itemQuality + 2 : 50);
        else if (itemSellin <= 5)
            item.setQuality(itemQuality + 3 <= 50 ? itemQuality + 3 : 50);
    }

    //Reusable method for updating conjured and other items
    public void updateConjDefault(Item item, int beforeSellin){
        int itemQualityConjured = item.getQuality();
        item.setQuality(item.getSellIn() <= 0 ? (itemQualityConjured - beforeSellin*2 >= 0 ? itemQualityConjured - beforeSellin*2 : 0) :
                        (itemQualityConjured - beforeSellin >= 0 ? itemQualityConjured - beforeSellin : 0)
        );
    }

    public void updateQuality() {

        Stream<Item> itemStream = Stream.of(items).filter(item -> !item.getName().equals("Sulfuras"));
        itemStream.forEach(item -> item.setSellIn(item.getSellIn()-1));

        for (Item item : items) {
            switch (item.getName()) {

                case "Backstage passes":
                    int itemSellinBackstage = item.getSellIn();
                    if (itemSellinBackstage <= 0)
                        item.setQuality(0);
                    else {
                        updateBSAB(item);
                    }
                    break;

                case "Aged Brie":
                    updateBSAB(item);
                    break;

                case "Conjured":
                    updateConjDefault(item,2);
                    break;

                case "Sulfuras":
                    break;

                default:
                    updateConjDefault(item,1);
                    break;
            }
        }
    }
}
