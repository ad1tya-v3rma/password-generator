package com.password_generator.api.datasets;

import lombok.Getter;
import org.springframework.stereotype.Component;


@Component
@Getter
public class CharacterSet {

    private String[] upper = {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z"
    };

    private String[] lower = {
            "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z"
    };

    private String[] numbers = {
            "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9"
    };

    private String[] special = {
            "!", "@", "#", "$", "%",
            "^", "&", "*", "(", ")", "-", "+", "=",
            "[", "]", "{", "}", "|", ";", ":", "\"",
            "<", ">", ",", ".", "?", "/"
    };

    /*public String getCharacter(int index, String type) {
        switch (type) {

            case "upper": return upper[index];break;
            case "lower": return getLower()[index];

        }
    }*/

}
