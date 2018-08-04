package com.company.Converter;

import com.company.ConverDBase.DBase;
import com.company.ConverDBase.ListUnits;
import com.company.ConverDBase.Unit;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

 public class ConverEng {
     private DBase dBase;
     public ConverEng(DBase dbase){
         this.dBase =dbase;
     }
     public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }
     public void parserInpData(List<String> inpData){
        for(String str : inpData){
            String words[] = str.split(" ");

            Unit un1 = new Unit();
            Unit un2 = new Unit();
            un1.setName(words[1]);
            un2.setName(words[4]);

            double v1 = Double.valueOf(words[0]);

            if(words[3]=="?"){
                // outputCompute(un1,un2,dBase);
            }
            else {
                double v2 = Double.valueOf(words[3]);
                if (v1 < v2) {
                    un2.setVal(v2 / v1);
                } else
                    un1.setVal(v1 / v2);

                inputToBase(un1,un2);

            }
        }

    }

     private  void inputToBase(Unit un1, Unit un2) {
         for (ListUnits lu : dBase.listsUn) {
             int ind1 = lu.units.indexOf(un1);
             int ind2 = lu.units.indexOf(un2);

             if (lu.units.contains(un1) & lu.units.contains(un2)) {
                lu.units.set(ind1,un1);
                lu.units.set(ind2,un2);
                 return;
             }
             else if (ind2 > -1) {

                 if (ind2 != 0)
                 {
                     un2.setVal(lu.units.get(ind2).getVal()/un2.getVal());
                     un1.setVal(un2.getVal());
                 }
                 lu.units.set(ind2,un2);
                 lu.units.add(ind2,un1);

                 return;
             }

             else if (ind1 > -1) {
                   lu.units.add(un2);

                 return;
             }
         }
            //Both units are unical, creatig new list units
                ListUnits lstUn = new ListUnits();
                if (un1.getVal() < un2.getVal()) {
                    lstUn.units.add(un1);
                    lstUn.units.add(un2);
                } else {
                    lstUn.units.add(un2);
                    lstUn.units.add(un1);
                }
                dBase.listsUn.add(lstUn);

            }

      }

