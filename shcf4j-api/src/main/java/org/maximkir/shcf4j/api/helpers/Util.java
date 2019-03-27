package org.maximkir.shcf4j.api.helpers;


public final class Util {

	
    private Util() {
    }


    static final public void report(String msg, Throwable t) {
        System.err.println(msg);
        System.err.println("Reported exception:");
        t.printStackTrace();
    }

    static final public void report(String msg) {
        System.err.println("SHCF4J: " + msg);
    }
    
	

}
