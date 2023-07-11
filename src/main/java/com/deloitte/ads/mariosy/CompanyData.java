package com.deloitte.ads.mariosy;

import com.deloitte.ads.mariosy.repository.User;

import java.util.Arrays;
import java.util.List;

public class CompanyData {
        private static final List<User> employees =   Arrays.asList(new User("John","Doe"),
                new User("Johny","Doe"),
                new User("Johan","Doe"),
                new User("Jan","Doe"));

        public static List<User> getEmployees(){
                return employees;
        }
}
