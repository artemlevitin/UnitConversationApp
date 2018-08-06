package com.company.Converter;

import com.company.ConverDBase.DBase;
import com.company.ConverDBase.ListUnits;
import com.company.ConverDBase.Unit;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Formatter;

 public class ConverEng {
     private DBase dBase;
     private ArrayList<String> resultLst;

     public ConverEng(DBase dbase) {
         this.setdBase(dbase);
         resultLst = new ArrayList<String>();
     }

     public DBase getdBase() {
         return dBase;
     }

     public void setdBase(DBase dBase) {
         this.dBase = dBase;
     }

     public List<String> readFile(String filePath) {
         try {
             return Files.readAllLines(Paths.get(filePath));
         } catch (Exception exc) {
             System.out.println(exc.getMessage());
             return null;
         }
     }

     public void safeResltToFile() {

         try {
             PrintWriter fileWrt = new PrintWriter("CoversResult.txt", "UTF-8");
             for (String resLn : resultLst) {
                 if (resLn == null)
                     resLn = "No conversion is possible";
                 fileWrt.println(resLn);
             }
             fileWrt.close();
         } catch (Exception exc) {
             System.out.println(exc.getMessage());
         }
     }

     public void parserInpData(List<String> inpData) {
         for (String str : inpData) {
             String words[] = str.split(" ");

             Unit un1 = new Unit();
             Unit un2 = new Unit();
             un1.setName(words[1]);
             un2.setName(words[4]);

             double v1 = Double.valueOf(words[0]);

             if ( words[3].equals("?") ) {
                 un1.setVal(v1);
                 resultLst.add(outputCompute(un1, un2));
             }
             else {
                 double v2 = Double.valueOf(words[3]);
                 if (v1 < v2) {
                     un2.setVal(v2 / v1);
                 } else
                     un1.setVal(v1 / v2);

                 //un1 should be val = 1
                 if (un1.getVal() != 1) {
                     Unit tUn = un1;
                     un1 = un2;
                     un2 = tUn;
                 }

                 inputToBase(un1, un2);

             }
         }

     }

     private void inputToBase(Unit un1, Unit un2) {
         for (ListUnits lu : getdBase().listsUn) {
             int ind1 = lu.units.indexOf(un1);
             int ind2 = lu.units.indexOf(un2);

             if (ind1 != -1 & ind2 != -1) {
                 lu.units.set(ind1, un1);
                 lu.units.set(ind2, un2);
                 return;
             }

             //Shoud to insert un1, it is bigest un2 wich has been in list
             else if (ind2 == 0) {
                 lu.units.set(ind2, un2);
                 lu.units.add(ind2, un1);

                 return;
             }
             else if (ind2 > 0) {

                 int indInst = ind2;// index for install unit
                 Unit unLess = lu.units.get(indInst);
                 //going in list to bigest unit with minimal index (ex. from second to year)
                    while(indInst > 0) {
                     un1.setVal(un2.getVal());
                     unLess = lu.units.get(indInst);
                     if(un1.getVal() > unLess.getVal()) {
                         un1.setVal(un1.getVal() / unLess.getVal());
                         --indInst;
                     }
                     else
                     {
                         double val = un1.getVal();
                         un1.setVal(unLess.getVal()/un1.getVal());
                         unLess.setVal(val);
                         break;
                     }
                 }
                 lu.units.set(indInst, unLess);
                 lu.units.add(indInst, un1);

                 return;
             }

             // If should add  Unit2(un1 has been in list yet)
             else if (ind1 > -1) {
                 int indInst= ind1+1;

                 while(indInst <= lu.units.size() - 1) {
                     Unit unless = lu.units.get(indInst);
                     if(un2.getVal() < unless.getVal())
                         break;
                     un2.setVal(un2.getVal()/unless.getVal());
                     ++indInst;
                 }

            lu.units.add(indInst,un2);

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
         getdBase().listsUn.add(lstUn);

     }

     private String outputCompute(Unit un1, Unit un2) {
         for (ListUnits lu : getdBase().listsUn) {
             int ind1 = lu.units.indexOf(un1);
             int ind2 = lu.units.indexOf(un2);
        String rez= String.format("%.6f",un1.getVal()) + " " + un1.getName() + " = ";
        double valRez=un1.getVal();


             if (ind1 != -1 & ind2 != -1) {
                 if(ind1 < ind2)
                     for(int i = ind1 + 1; i <=  ind2; ++i){
                         valRez *= lu.units.get(i).getVal();
                     }
                 else
                     for(int i = ind1 ; i >  ind2; --i){
                         valRez /= lu.units.get(i).getVal();
                     }

                     rez += String.format("%.6e",valRez) + " " + un2.getName();

                 return rez;
             }
         }
         return null;
     }


 }
