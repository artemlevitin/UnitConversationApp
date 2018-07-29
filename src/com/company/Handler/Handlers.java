package com.company.Handler;

import com.company.ConverDBase.DBase;
import com.company.ConverDBase.ListUnits;
import com.company.ConverDBase.Unit;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

 public class Handlers {
    static public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }
    static public void inputData(List<String> inpData, DBase dBase){
        for(String str : inpData){
            String words[] = str.split(" ");

            Unit un1 = new Unit();
            Unit un2 = new Unit();
            un1.setName(words[1]);
            un2.setName(words[4]);
            double v1 = Double.valueOf(words[0]);
            if(words[3]=="?"){
                // outputCompute();
            }
            else {
                //  inputToBase();
                double v2 = Double.valueOf(words[3]);
                if (v1 < v2) {
                    un2.setVal(v2 / v1);
                } else
                    un1.setVal(v1 / v2);

                ListUnits cnvLst = new ListUnits();
                if (un1.getVal() < un2.getVal()) {
                    cnvLst.units.add(un1);
                    cnvLst.units.add(un2);
                } else {
                    cnvLst.units.add(un2);
                    cnvLst.units.add(un1);
                }
                dBase.base.add(cnvLst);
            }
        }




    }
}
