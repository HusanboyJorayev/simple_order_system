package org.example.simple_order_sytem.exam;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String gettext(String text) {
        return "UserService = > text " + text;
    }

    public Integer summa(Integer a, Integer b) {
        return a + b;
    }
}
