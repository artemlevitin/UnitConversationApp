package com.company;

import com.company.ConverDBase.DBase;
import com.company.Handler.Handlers;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DBase dBase = new DBase();
        List<String> lst =Handlers.readFile("inpData.txt");
        if(lst!=null)
            Handlers.parserInpData(lst,dBase);

    }





}