package com.esport;

public class ProcessB implements Process {
    private final Process process;

    public ProcessB(Process process) {
        this.process = process;
    }

    public String run() {
        System.out.println("Reading processAValue: " + process.run());
        System.out.println("Processing B ...");
        return "Process B";
    }
}
