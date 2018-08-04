package com.company.ConverDBase;

import java.util.ArrayList;

public class ListUnits {
   public ArrayList<Unit> units;

    public ListUnits(){

        units = new ArrayList<Unit>();
    }
    @Override
    public String toString()
    {
        String res = "";
        for (Unit un : units) {
            res += ( un + ";" );
        }
        return res ;
    }
}
