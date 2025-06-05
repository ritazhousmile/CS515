/**
 * CS515 Assignment 5L
 *
 * Name: Huan Zhou(Rita)
 *
 * Section: 01
 *
 * Date: 06/05/2025
 *
 * Collaboration Declaration: 
 * Collaboration: None
 */

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Arrays.asList;

import java.util.HashMap;

public class LambdaFun {

      //--------Set-oriented methods----------
      public static void print(String label, Set<?> s) {
            System.out.print(label + ": ");
            s.forEach(val -> {
                  System.out.print( " " +val);
            });
            System.out.println();
      }

      public static Set<Integer> returnNegative(Set<Integer> s) {
            //Task 1 Step 2
            Set<Integer> result = new TreeSet<>();
            s.forEach(val -> {
                  if(val <0){
                        result.add(val);
                  }
            });
            return result;
      }

      public static Set<Integer> multiplyCombos(Set<Integer> s1, Set<Integer> s2) {
            //Task 1 Step 3
            Set<Integer> resultSet = new TreeSet<>();
            s1.forEach(val1 -> {
                  s2.forEach(val2 -> {
                        resultSet.add(val1 * val2);
                  });
            });
            return resultSet;
      }

      public static void giveHolidayHours(Set<EmployeeRecord> workers, double hours) {
            //Task 1 Step 4
            workers.forEach(worker -> {
                  worker.hoursWorked += hours;
                  worker.workedThisWeek = true;
            });
      }

      public static Set<EmployeeRecord> collectWorkingWorkers(Set<EmployeeRecord> workers) {
            //Task 1 Step 5
            Set<EmployeeRecord> result = new TreeSet<>();
            workers.forEach(worker -> {
                  if(worker.workedThisWeek){
                        result.add(worker);
                  }
            });
            return result;
      }

      public static Map<String, Double> createPayroll(Set<EmployeeRecord> workers) {
            //Task 1 Step 6
            Map<String, Double> result = new HashMap<>();
            workers.forEach(worker -> {
                  if(worker.workedThisWeek){
                  result.put(worker.name, worker.payRate * worker.hoursWorked);
                  }
            });
            return result;
      }
      //--------Map-oriented methods----------
      public static void print(String label, Map<?, ?> m) {
            System.out.print(label + ": ");
            //Task 2 Step 1
            m.forEach((Key,value) -> {
                  System.out.print(" (" +Key + ", " + value + ")");
            });
            System.out.println();
      }

      public static Set<Integer> addKeysAndValuesIntoSet(Map<Integer, Integer> m) {
            Set <Integer> result = new TreeSet<>();
            m.forEach((key, value) -> {
                  result.add(key + value);
            });
            //Task 2 Step 2
            return result;
      }

      public static void addKeysToValues(Map<Integer, Integer> m) {
            //Task 2 Step 3
            m.replaceAll((key, value) -> {
                  value = value + key;
                  return value;
            });
            
      }

      public static void updateDepartmentCount(Map<String, Integer> headCounts,
                                               Map<String, Set<EmployeeRecord>> newEmployees) {
            headCounts.forEach((key,value) -> {
                  if(newEmployees.containsKey(key)){
                        newEmployees.get(key).forEach(employee -> {
                              headCounts.put(key, headCounts.get(key) + 1);
                        });
                  }
            });
                                          
            //Task 2 Step 4
      }

      public static void updateBudget(Map<String, Double> budget,
                                      Map<String, Set<EmployeeRecord>> departmentEmployees) {

            budget.forEach((key, value) -> {
                  if (departmentEmployees.containsKey(key)) {
                        double newValue = value;
                        for (EmployeeRecord employee : departmentEmployees.get(key)) {
                              newValue -= employee.payRate * employee.hoursWorked;
                        }
                        budget.put(key, newValue);
                  }
            });
            //Task 2 Step 5
      }
}
