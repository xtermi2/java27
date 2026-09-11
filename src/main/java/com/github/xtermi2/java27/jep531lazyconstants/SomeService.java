package com.github.xtermi2.java27.jep531lazyconstants;

public class SomeService {
    private final String requestor;

    public SomeService(final String requestor) {
        this.requestor = requestor;
        System.out.println("SomeService init from " + requestor + "...");
        // some expensive work
        try {
            Thread.sleep(100);
        } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
        }
    }

    public void doSomething() {
        System.out.println("Doing something from " + requestor + "...");
    }
}
