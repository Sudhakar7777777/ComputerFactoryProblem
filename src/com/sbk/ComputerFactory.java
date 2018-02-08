package com.sbk;

public class ComputerFactory
{
    public static void runProduction()
    {
        Assembler m1 = new Assembler("Assembler-1");
        m1.start();

        Assembler m2 = new Assembler("Assembler-2");
        m2.start();

        Assembler m3 = new Assembler("Assembler-3");
        m3.start();

        Assembler m4 = new Assembler("Assembler-4");
        m4.start();

        Assembler m5 = new Assembler("Assembler-5");
        m5.start();

        Truck t1 = new Truck("Truck-1");
        t1.start();

        Truck t2 = new Truck("Truck-2");
        t2.start();

        Truck t3 = new Truck("Truck-3");
        t3.start();
    }
}
