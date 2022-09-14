package com.company;

/**
 * Created by Сергей on 12.09.2022.
 */
 enum Operate {
    DIV('/'), MULT('*'), ADD('+'), SUB('-');
    private char sig;
    Operate(char sig){
       this.sig = sig;
    }

    char getSig() {
        return sig;
    }
}
