/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class OrganizationChart {

    private Map<String, List<Employee>> graph;

    public static void main(String[] args) {

        OrganizationChart soln = new OrganizationChart();
        ArrayList<Employee> org = new ArrayList<>();
        Employee emp1 = new Employee("Alex", null, "CEO");
        Employee emp2 = new Employee("Dana", "Trey", "Software Engineer");
        Employee emp3 = new Employee("David", "Jesse", "Customer Support");
        Employee emp4 = new Employee("Erin", "Trey", "Designer");
        Employee emp5 = new Employee("Jesse", "Alex", "Operations");
        Employee emp6 = new Employee("Paul", "Trey", "Software Engineer");
        Employee emp8 = new Employee("John", "Paul", "Software Engineer");
        Employee emp7 = new Employee("Trey", null, "CTO");
        org.add(emp1);
        org.add(emp2);
        org.add(emp3);
        org.add(emp4);
        org.add(emp5);
        org.add(emp6);
        org.add(emp7);
        org.add(emp8);
        soln.traverseOrgEmployees(org);
    }

    private void traverseOrgEmployees(List<Employee> list) {
        graph = new HashMap<>();
        List<Employee> topMgrs = new ArrayList<>();

        for(Employee each:list) {
            if(each.mgr != null) {
                graph.putIfAbsent(each.mgr, new ArrayList<>());
                graph.get(each.mgr).add(each);
            } else
                topMgrs.add(each);
        }

      for(Employee each: topMgrs)
            traverseIsland(each, 0);
    }

    private void traverseIsland(Employee emp, int level) {
        printEmployee(emp, level);

        if(!graph.containsKey(emp.name)) return;

        for(Employee each: graph.get(emp.name))
            traverseIsland(each, level+1);
    }

    private void printEmployee(Employee emp, int level) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< level; i++) sb.append("  ");
        System.out.println(sb + emp.name + " (" + emp.title +")");
    }
}

class Employee{
    public String name;
    public String mgr;
    public String title;

    public Employee(String name, String mgr, String title) {
        this.name = name;
        this.mgr = mgr;
        this.title = title;
    }
}

