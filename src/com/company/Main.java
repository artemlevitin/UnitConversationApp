package com.company;

import com.company.ConverDBase.DBase;
import com.company.Converter.ConverEng;

public class Main {

    public static void main(String[] args) {

        ConverEng convEng =new ConverEng(new DBase());

        convEng.parserInpData (convEng.readFile("inpData.txt"));


    }





}