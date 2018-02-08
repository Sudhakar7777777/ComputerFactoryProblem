package com.sbk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Truck extends Thread
{
    private String name;
    private ShippingBelt belt;
    private List<Computer> items;

    public static final long LOADING_TIME = 5000;       // 5 seconds
    public static final long RETRY_TIME = 10000;        // 10 seconds
    public static final int MAX_LOAD_CAPACITY = 2;
    public static final int BATCH_SIZE = 5;

    public Truck(String _name)
    {
        name = _name;
        items = new ArrayList<>();
        belt = ShippingBelt.getMyInstance();
        System.out.println("Truck[" + name + "]: Is created and ready for production...");
    }

    public void load() throws InterruptedException
    {

        // Load the products to truck
        List<Computer> batchItems;
        do
        {
            batchItems = belt.unloadItems(BATCH_SIZE);

            if(null != batchItems)
            {
                for (int i = 0; i < batchItems.size() ; i++)
                {
                    items.add(batchItems.get(i));
                }
                //Pause to load items
                Thread.sleep(LOADING_TIME);

                System.out.println("Truck[" + name + "]: Loaded " + batchItems.size() + "items to Truck...");
            }
            else
            {
                System.out.println("Truck[" + name + "]: Loading failed... Pause for a while before retry to Load again.");
                Thread.sleep(RETRY_TIME);
            }
        }
        while(null == batchItems);
    }

    public void printTruckLoad()
    {
        System.out.println("Truck[" + name + "]: printTruckLoad");
        Iterator it = items.iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }

    public void run()
    {
        System.out.println("Truck[" + name + "]: Production Begins...");
        for (int i = 0; i < MAX_LOAD_CAPACITY; i++)
        {
            try
            {
                load();
            }
            catch (InterruptedException e)
            {
                System.out.println("Truck[" + name + "]: Interrupted...");
            }
        }
        System.out.println("Truck[" + name + "]: Production Ends...");
        printTruckLoad();
    }
}