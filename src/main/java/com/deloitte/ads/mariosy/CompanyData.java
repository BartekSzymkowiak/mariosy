package com.deloitte.ads.mariosy;

import com.deloitte.ads.mariosy.repository.User;

import java.util.Arrays;
import java.util.List;

public class CompanyData {
        private static final List<User> employees =   Arrays.asList(new User("John","Doe", "john.d@email.com"),
                new User("Johny","Doe","johny.d@email.com"),
                new User("Johan","Doe","johan.d@email.com"),
                new User("Jan","Doe","jan.d@email.com"));

        public static List<User> getEmployees(){
                return employees;
        }
}
