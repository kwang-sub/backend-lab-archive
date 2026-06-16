package org.example.ex07.canexecute;

public class CompanyFactory {
    public static Company create(Object[] data) {
        Precondition.requires(data.length >= 2);

        String domainName = (String) data[0];
        int numberOfEmployees = (int) data[1];

        return new Company(domainName, numberOfEmployees);
    }
}
