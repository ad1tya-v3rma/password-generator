package com.password_generator.api.service.impl;

import com.password_generator.api.datasets.CharacterSet;
import com.password_generator.api.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final CharacterSet characterSet;
    private int num;
    private Set<String> passwordSet = new HashSet<>();

    @Override
    public Optional<String> getPassword() {

        String password = "";
        String letter = "";
        while (password.length() < 19) {
             num = getRandomNumber(0, characterSet.getUpper().length - 1);
            letter = characterSet.getUpper()[num];
            //this.password = this.password + (password.contains(letter) ? continue : letter);
            if (password.contains(letter)) {
                continue;
            }
            else {
                password += letter;
            }
            num = getRandomNumber(0, characterSet.getSpecial().length - 1);
            letter = characterSet.getSpecial()[num];
            if (password.contains(letter)) {
                continue;
            }
            else {
                password += letter;
            }
            num = getRandomNumber(0, characterSet.getLower().length - 1);
            letter = characterSet.getLower()[num];
            if (password.contains(letter)) {
                continue;
            }
            else {
                password += letter;
            }
            num = getRandomNumber(0, characterSet.getNumbers().length - 1);
            
                while (num > characterSet.getNumbers().length - 1) {
                    num = this.getRandomNumber(0,characterSet.getNumbers().length);
                }
                letter = characterSet.getNumbers()[num];
                if (password.contains(letter)) {
                    continue;
                }
                else {
                    password += letter;
                }
            }
        
        if (passwordSet.contains(password)) {
            this.getPassword();
        }
        else {
            passwordSet.add(password);
        }
        return Optional.ofNullable(password);
    }

    private int getRandomNumber(int min, int max)
    {
        return (int) Math.floor(min + (Math.random() * (max - min + 1)));
    }

}
