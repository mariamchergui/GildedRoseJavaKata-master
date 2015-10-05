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
