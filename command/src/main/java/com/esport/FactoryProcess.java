package com.esport;

import java.util.function.Supplier;

public class FactoryProcess {

    public static ProcessA createA() {
        return new ProcessA();
    }

    public static ProcessB createB(Process process) {
        return new ProcessB(process);
    }

    public static ProcessC createC(Process process) {
        return new ProcessC(process);
    }
}
