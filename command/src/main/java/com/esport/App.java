package com.esport;

public class App {

    public static void main(String[] args) {
        Process processA = FactoryProcess.createA();
        Process processB = FactoryProcess.createB(processA);
        Process processC = FactoryProcess.createC(processB);
        String output = processC.run();
        System.out.println(output);
    }
}
