package com.company;

/**
 * Created by Сергей on 14.09.2022.
 * коллекция едениц
 */
 enum RomDigit {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);
    private int number;

    RomDigit(int number){
        this.number = number;
    }

    int getDigit() {
        return number;
    }


    }

