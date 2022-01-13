package com.esport;

public class ProcessC implements Process {
    private final Process process;

    public ProcessC(Process process) {
        this.process = process;
    }

    public String run() {
        System.out.println("Reading processBValue: " + process.run());
        System.out.println("Processing C ...");
        return "Process C";
    }
}
