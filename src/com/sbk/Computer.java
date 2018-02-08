package com.sbk;

public class Computer
{
    long serialNumber;

    public Computer(long serial_number)
    {
        serialNumber = serial_number;
    }

    public long getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(long serial_number)
    {
        this.serialNumber = serial_number;
    }

    public String toString()
    {
        return "Computer [" + serialNumber + "]";
    }
}
