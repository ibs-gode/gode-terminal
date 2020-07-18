package org.ibs.cds.gode.terminal.util;

public class ThreadManager {

    public static void start(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
