package com.password_generator.api.datasets;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter
public class CharacterSet implements Serializable {

    private final String[] upper = {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z"
    };

    private final String[] lower = {
            "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z"
    };

    private final String[] numbers = {
            "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9"
    };

    private final String[] special = {
            "!", "@", "#", "$", "%",
            "^", "&", "*", "(", ")", "-", "+", "=",
            "[", "]", "{", "}", "|", ";", ":", "\"",
            "<", ">", ",", ".", "?", "/"
    };

}
