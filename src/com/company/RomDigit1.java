package com.company;

/**
 * Created by Сергей on 14.09.2022.
 *  запись десятков
 */
enum RomDigit1 {
    X(1), XX(2), XXX(3), XL(4), L(5), LX(6), LXX(7), LXXX(8), XC(9);
    private int number;

    RomDigit1(int number){
        this.number = number;
    }

    int getDigit() {
        return number;
    }
}
