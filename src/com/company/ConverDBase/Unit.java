package com.company.ConverDBase;

public class Unit {
    private String name;
    private double val =1;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Unit)
        {
            sameSame = this.name.equals(((Unit) object).name);
        }

        return sameSame;
    }
    @Override
    public String toString()
    {

        return val + " " + name ;
    }
}
