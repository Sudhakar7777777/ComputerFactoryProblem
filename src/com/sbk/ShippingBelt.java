package com.sbk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ShippingBelt
{
    BlockingQueue<Computer> items;

    public static final int MAX_SIZE = 100;

    private ShippingBelt() { items = new LinkedBlockingQueue<>();}

    private static class LazyLoader
    {
        static final ShippingBelt myInstance = new ShippingBelt();
    }

    public static ShippingBelt getMyInstance()
    {
        return LazyLoader.myInstance;
    }

    public boolean loadItems(List<Computer> _items, int _batch_size) throws InterruptedException
    {
        if (items.size() == MAX_SIZE)
        {
            System.out.println("ShippingBelt::loadItems() - Has " + items.size() + " which exceeds max size " + MAX_SIZE);
            return false;
        }

        for (int i = 0; i < _batch_size; i++)
        {
            items.put(_items.get(i));
        }
        return true;
    }

    public List<Computer> unloadItems(int _batch_size) throws InterruptedException
    {
        List<Computer> result = new ArrayList<>();

        if (items.size() < _batch_size)
        {
            System.out.println("ShippingBelt::unloadItems() Has " + items.size() + " which is less than the demand " + _batch_size);
            return null;
        }
        System.out.println("ShippingBelt::unloadItems() Belt Size = " + items.size() + "; Demand = " + _batch_size);
        for (int i = 0; i < _batch_size; i++)
        {
            result.add(items.take());
        }
        return result;
    }
}