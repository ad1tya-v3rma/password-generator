package com.password_generator.api.service.impl;

import com.password_generator.api.datasets.CharacterSet;
import com.password_generator.api.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService, Serializable {

    private final transient Logger LOG = LoggerFactory.getLogger(PasswordServiceImpl.class);
    private final CharacterSet characterSet;
    private final Random randomGenerator = new Random();
    private Set<StringBuilder> passwordSet = new HashSet<>();
    @Override
    public Optional<String> getPassword() {

        StringBuilder password = new StringBuilder();
        String letter;
        int num;
        while (password.length() < 19) {
             num = getRandomNumber(0, characterSet.getUpper().length - 1);
            letter = characterSet.getUpper()[num];
            //this.password = this.password + (password.toString().contains(letter) ? continue : letter);
            if (password.toString().contains(letter)) {
                continue;
            }
            else {
                password.append(letter);
            }
            num = getRandomNumber(0, characterSet.getSpecial().length - 1);
            letter = characterSet.getSpecial()[num];
            if (password.toString().contains(letter)) {
                continue;
            }
            else {
                password.append(letter);
            }
            num = getRandomNumber(0, characterSet.getLower().length - 1);
            letter = characterSet.getLower()[num];
            if (password.toString().contains(letter)) {
                continue;
            }
            else {
                password.append(letter);
            }
            num = getRandomNumber(0, characterSet.getNumbers().length - 1);
            
                while (num > characterSet.getNumbers().length - 1) {
                    num = this.getRandomNumber(0,characterSet.getNumbers().length);
                }
                letter = characterSet.getNumbers()[num];
                if (!password.toString().contains(letter)) {
                    password.append(letter);
                }
            }
        
        if (passwordSet.contains(password)) {
            this.getPassword();
        }
        else {
            passwordSet.add(password);
        }
        LOG.info("without encoding "+password);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        LOG.info("with encoding "+encoder.encode(password));
        return Optional.ofNullable(encoder.encode(password));

    }

    public int getRandomNumber(int min, int max)
    {
        //return (int) Math.floor(min + (Math.random() * (max - min + 1)));
        return randomGenerator.nextInt(min,max);
    }

}
