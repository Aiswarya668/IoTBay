/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author shara
 */
public class TestRunner {

    public static void main(String[] args) {
        System.out.println("--- Testing DB Order Manager -----");

        Result result = JUnitCore.runClasses(DBOrderTest.class);

        // If there is failure, log that
        for (Failure f : result.getFailures()) {
            System.out.println(f.toString());
        }

        // for the status of the test
        String status = result.wasSuccessful() ? "Passed" : "Failed";
        System.out.println("Test Result = " + status);
        System.out.println("Number of test passed = " + result.getRunCount());
        System.out.println("Number of test failed = " + result.getFailureCount());
        System.out.println("Number of test ignored = " + result.getIgnoreCount());

        System.out.println("Time = "+ result.getRunTime()/1000.0 + "s");

    }

}
