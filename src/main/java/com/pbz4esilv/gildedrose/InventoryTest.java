package com.pbz4esilv.gildedrose;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by MERIEM on 02-Oct-15.
 */


public class InventoryTest {


    /*
     * Test si la qualité augmente ou diminue selon l'item après un update.
     * et aucun changement pour l'objet "Sulfuras"
     */
    @Test
    public void updateQuality_Test() throws Exception{

        //Initialisation
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };

        Inventory inventory=new Inventory(items);

        //Test the quality and the sellin before updating.
        Assert.assertEquals(6,items.length);

        Assert.assertEquals(20, items[0].getQuality());
        Assert.assertEquals(0, items[1].getQuality());
        Assert.assertEquals(7, items[2].getQuality());
        Assert.assertEquals(80, items[3].getQuality());
        Assert.assertEquals(20, items[4].getQuality());
        Assert.assertEquals(6, items[5].getQuality());

        Assert.assertEquals(10, items[0].getSellIn());
        Assert.assertEquals(2, items[1].getSellIn());
        Assert.assertEquals(5, items[2].getSellIn());
        Assert.assertEquals(0, items[3].getSellIn());
        Assert.assertEquals(15, items[4].getSellIn());
        Assert.assertEquals(3, items[5].getSellIn());

        //Compute
        inventory.updateQuality();

        //Assert
        //Checking the quality and the sellin after updating.
        Assert.assertEquals(6,items.length);

        Assert.assertEquals(19,items[0].getQuality());
        Assert.assertEquals(1,items[1].getQuality());
        Assert.assertEquals(6, items[2].getQuality());
        Assert.assertEquals(80,items[3].getQuality());
        Assert.assertEquals(21,items[4].getQuality());
        Assert.assertEquals(4, items[5].getQuality());

        Assert.assertEquals(9, items[0].getSellIn());
        Assert.assertEquals(1, items[1].getSellIn());
        Assert.assertEquals(4, items[2].getSellIn());
        Assert.assertEquals(0, items[3].getSellIn());
        Assert.assertEquals(14, items[4].getSellIn());
        Assert.assertEquals(2, items[5].getSellIn());


    }


    /*
     * Test de l'objet legendaire "Sulfuras" => Aucun changement doit avoir lieu pour cet objet.
     */
    @Test
    public void sulfuras_Test(){
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 0, 80)};

        Inventory inventory=new Inventory(items);

        //Test the quality and the sellin before updating.
        Assert.assertEquals(80, items[0].getQuality());
        Assert.assertEquals(0, items[0].getSellIn());

        //update
        inventory.updateQuality();

        //Checking the quality and the sellin after updating.
        Assert.assertEquals(80, items[0].getQuality());
        Assert.assertEquals(0, items[0].getSellIn());

    }


    /*
     * Test si les autres objets normaux ont leur SellIn et leur Quality qui baisse par 1
     * avec le SellIn > 0.
     */
    @Test
    public void lowerTheSellInByOneOthersItems_Test() throws Exception {
        Item[] item = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Elixir of the Mongoose", 5, 7)
        };

        Inventory inventory = new Inventory(item);

        //Test the quality and the sellin before updating.
        Assert.assertEquals(10, item[0].getSellIn());
        Assert.assertEquals(20, item[0].getQuality());
        Assert.assertEquals(5, item[1].getSellIn());
        Assert.assertEquals(7, item[1].getQuality());

        //update
        inventory.updateQuality();

        //Test the quality and the sellin before updating.
        Assert.assertEquals(9, item[0].getSellIn());
        Assert.assertEquals(19, item[0].getQuality());
        Assert.assertEquals(4, item[1].getSellIn());
        Assert.assertEquals(6, item[1].getQuality());
    }


    /*
     * Test que la qualité des items ne soient jamais négatives.
     */
    @Test
    public void theQualityNeverNegative_Test() throws Exception {
        //INIT
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 0),
                new Item("Elixir of the Mongoose", 5, 0),
                new Item("Sulfuras, Hand of Ragnaros", 0, 0),
                new Item("Conjured Mana Cake", 3, 0)
        };

        Inventory inventory=new Inventory(items);

        //Test the quality before updating.
        Assert.assertEquals(4,items.length);

        Assert.assertEquals(0, items[0].getQuality());
        Assert.assertEquals(0, items[1].getQuality());
        Assert.assertEquals(0, items[2].getQuality());
        Assert.assertEquals(0, items[3].getQuality());


        //Compute
        inventory.updateQuality();

        //Assert
        //Test the quality before updating.
        Assert.assertEquals(4,items.length);

        Assert.assertEquals(0,items[0].getQuality());
        Assert.assertEquals(0,items[1].getQuality());
        Assert.assertEquals(0, items[2].getQuality());
        Assert.assertEquals(0,items[3].getQuality());



    }


    /*
     * Test que la qualité des items se dégradent deux fois plus vite une fois la date de vente passée.
     */
    @Test
    public void theQualityDegratesTwiceAsFastOnceTheSellInDateHasPassed_Test() throws Exception {
        //INIT
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", -2, 10),
                new Item("Elixir of the Mongoose", -2, 20),
                new Item("Conjured Mana Cake", -2, 6)

        };

        Inventory inventory=new Inventory(items);

        //Test the quality before updating.
        Assert.assertEquals(10, items[0].getQuality());
        Assert.assertEquals(20, items[1].getQuality());
        Assert.assertEquals(6, items[2].getQuality());

        //Compute
        inventory.updateQuality();

        //Assert
        //Check the quality after updating.
        Assert.assertEquals(8, items[0].getQuality());
        Assert.assertEquals(18, items[1].getQuality());
        Assert.assertEquals(2, items[2].getQuality());


    }


    /*
     * Test sur l'item "Backstage passes to a TAFKAL80ETC concert".
     */
    @Test
    public void backstage_Test() {

        Item[] items= new Item[]
                { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};

        Inventory inventory=new Inventory(items);

		/*
		 * increase backstage passes quality by 1 when the concert is more than 10 days away
		 */
        Assert.assertEquals(20, items[0].getQuality());
        Assert.assertEquals(10, items[0].getSellIn());
        inventory.updateQuality();
        Assert.assertEquals(22, items[0].getQuality());
        Assert.assertEquals(9, items[0].getSellIn());

		/*
		 *increase backstage passes quality by 2 when the concert is 10 days or less away
		 *increase backstage passes quality by 3 when the concert is 5 days or less away
		*/

        //for 4 update the quality increase by 2 when there are 10 days at less and 3 for 5 day or less
        //and 3 for 5 day or less
        //22+4*2+3=33
        for(int i=0;i<5;i++)
        {
            inventory.updateQuality();

        }

        Assert.assertEquals(33, items[0].getQuality());
        Assert.assertEquals(4, items[0].getSellIn());

        for(int i=0;i<5;i++)
        {
            inventory.updateQuality();
        }

		/*
		 * lower backstage passes to Zero Quality once the concert has happened
		 */
        Assert.assertEquals(0, items[0].getQuality());
        Assert.assertEquals(-1, items[0].getSellIn());
    }


    /*
     * Test sur l'item "Backstage passes to a TAFKAL80ETC concert"
     * si la date de vente est passé => la qualité de cet item vaudra 0.
     */
    @Test
    public void lowerBackstagePassesToZeroQualityOnceConcertHasHappened_Test() throws Exception {
        Item[] items= new Item[]
                { new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20)};

        Inventory inventory=new Inventory(items);

        //Test the quality and the sellin before updating.
        Assert.assertEquals(20, items[0].getQuality());
        Assert.assertEquals(-1, items[0].getSellIn());

        inventory.updateQuality();//update

        //Checking the quality and the sellin after updating.
        Assert.assertEquals(0, items[0].getQuality());
        Assert.assertEquals(-2, items[0].getSellIn());
    }


    /*
     *La qualité de "Backstage passes to a TAFKAL80ETC concert" ne doit pas dépasser 50.
     */
    @Test
    public void BackstagePassesNotOverQualityof50() throws Exception {

        Item[] items= new Item[]
                {       new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50),
                        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48),
                };

        Inventory inventory=new Inventory(items);

        //test the quality and the sellin before updating.
        Assert.assertEquals(50, items[0].getQuality());
        Assert.assertEquals(49, items[1].getQuality());
        Assert.assertEquals(48, items[2].getQuality());

        //update
        inventory.updateQuality();

        //check the quality and the sellin after updating.
        Assert.assertEquals(50, items[0].getQuality());
        Assert.assertEquals(50, items[1].getQuality());
        Assert.assertEquals(50, items[2].getQuality());


    }


    /*
     * Test sur l'item "Aged Brie".
     * la qualité de cet item augmente.
     */
    @Test
    public void agedBrie_Test() throws Exception {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 0)};

        Inventory inventory=new Inventory(items);

        //Test the quality and the sellin before updating.
        Assert.assertEquals(0, items[0].getQuality());
        Assert.assertEquals(2, items[0].getSellIn());

        inventory.updateQuality();//update

        //Checking the quality and the sellin after updating.
        Assert.assertEquals(1, items[0].getQuality());
        Assert.assertEquals(1, items[0].getSellIn());
    }


    /*
     * La qualité de "Aged Brie" ne peut pas dépasser 50
     */
    @Test
    public void qualityAgedBrieNotOver50_Test() throws Exception {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 50)};

        Inventory inventory=new Inventory(items);

        //Test the quality and the sellin before updating.
        Assert.assertEquals(50, items[0].getQuality());
        Assert.assertEquals(2, items[0].getSellIn());

        //update
        inventory.updateQuality();

        //Checking the quality and the sellin after updating.
        Assert.assertEquals(50, items[0].getQuality());
        Assert.assertEquals(1, items[0].getSellIn());
    }


    /*
     * Test sur l'item "Conjured Mana Cake"
     * "Conjured" items degrade in Quality twice as fast as normal items
     */
    @Test
    public void conjured_Test() throws Exception {

        Item[] items= new Item[]
                { new Item("Conjured Mana Cake", 3, 6)};

        Inventory inventory=new Inventory(items);

        //test the quality and the sellin before updating.
        Assert.assertEquals(6, items[0].getQuality());
        Assert.assertEquals(3, items[0].getSellIn());

        //update
        inventory.updateQuality();

        //Checking the quality and the sellin of Conjured Mana Cake
        Assert.assertEquals(4,items[0].getQuality());
        Assert.assertEquals(2, items[0].getSellIn());

    }









}







