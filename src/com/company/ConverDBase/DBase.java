package com.company.ConverDBase;

import com.company.ConverDBase.ListUnits;

import java.util.ArrayList;

public class DBase {
  static public ArrayList<ListUnits> listsUn ;
  public DBase(){
    listsUn = new ArrayList<ListUnits>();

  }
    @Override
    public String toString()
    {
        String res = "";
        for (ListUnits lUns : listsUn) {
            res += ( lUns + "\n" );
        }
        return res ;
    }
}
