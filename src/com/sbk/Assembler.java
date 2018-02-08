package com.sbk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Assembler extends Thread
{
    private String name;
    private ShippingBelt belt;

    public static final long PRODUCTION_TIME = 10000;   // 1 seconds
    public static final long RETRY_TIME = 2000;         // 2 seconds
    public static final int MAX_PRODUCTION_CAPACITY = 6;
    public static final int BATCH_SIZE = 1;

    public Assembler(String _name)
    {
        name = _name;
        belt = ShippingBelt.getMyInstance();
        System.out.println("Assembler[" + name + "]: Is created and ready for production...");
    }

    public void build() throws InterruptedException
    {
        // Assemble the product
        Random rand = new Random();
        long newSerial = Math.abs((rand.nextLong() % 10000000000L));  // 10 digit random number

        Computer item = new Computer(newSerial);
        List<Computer> items = new ArrayList<>();
        items.add(item);

        // pause during the assembling time
        Thread.sleep(PRODUCTION_TIME);

        // Load items to belt when Assembly is done
        boolean status = false;
        do
        {
            status = belt.loadItems(items, BATCH_SIZE);

            if(status == true)
            {
                System.out.println("Assembler[" + name + "]: Built and loaded Computer [" + newSerial + "]");
            }
            else
            {
                System.out.println("Assembler[" + name + "]: Loading failed... Pause for a while before retry to Load again.");
                Thread.sleep(RETRY_TIME);
            }
        }
        while(status == false);
    }

    public void run()
    {
        System.out.println("Assembler[" + name + "]: Production Begins...");
        for (int i = 0; i < MAX_PRODUCTION_CAPACITY; i++)
        {
            try
            {
                build();
            }
            catch (InterruptedException e)
            {
                System.out.println("Assembler[" + name + "]: Interrupted...");
            }
        }
        System.out.println("Assembler[" + name + "]: Production Ends...");
    }
}
