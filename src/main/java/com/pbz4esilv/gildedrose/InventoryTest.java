package com.pbz4esilv.gildedrose;
import org.junit.Assert;
import org.junit.Test;


public class InventoryTest {


    /*
     * Test si la qualité augmente ou diminue selon l'item après un update.
     * et aucun changement pour l'objet "Sulfuras"
     */
    @Test
    public void updateQuality_Test() throws Exception {
        //Initialisation
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };

    }