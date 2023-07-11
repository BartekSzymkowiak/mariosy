package com.deloitte.ads.mariosy;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CompanyData {
        private static final List<User> employees =   Arrays.asList(new User("John","Doe"),
                new User("Johny","Doe"),
                new User("Johan","Doe"),
                new User("Jan","Doe"));

        public static List<User> getEmployees(){
                return employees;
        }
}
