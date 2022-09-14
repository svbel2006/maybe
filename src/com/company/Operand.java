package com.company;

/**
 * Created by Сергей on 13.09.2022.
 * допустимые операнды
 */
 class Operand {
    String name; // изначальная строка
    int number; // соответствующее число
    char type; // a - arabic r - rim

   Operand (String name) {
      this.name = name.trim();
  }

    void setTypeOperand() throws Exception {
        String validArabicCharacter = "0123456789";
        String validRimCharacter = "IVX";
        char type = '?';

        if  (!name.isEmpty()) {
            for (int i = 0; i < name.length(); i++) {
                if (!(validArabicCharacter.indexOf(name.charAt(i)) == -1)) {
                    if (type == 'r') throw new Exception("Неопределенный операнд! Ожидаются только римские цифры.");
                    else type = 'a';
                }
                else {
                    if (!(validRimCharacter.indexOf(name.charAt(i)) == -1)) {
                        if (type == 'a') throw new Exception("Неопределенный операнд! Ожидаются только арабские цифры.");
                        else type = 'r';
                    }
                    else {
                        type = '?';
                        throw new Exception("Неопределенный операнд! Недопустимый символ.");
                    }
                }

            }
        }
        else {
            throw new Exception("Пустой/отсутствует операнд!");
        }
        this.type = type;
    }

    void setNumber() throws Exception {
       RomDigit[] listRomDigit = RomDigit.values();
       boolean fFinded = false;
       if (type == 'a') this.number = Integer.parseInt(name);
       if (type == 'r') {
           for ( RomDigit digit : listRomDigit){
               if (name.equals(digit.name())) {
                   fFinded = true;
                   this.number = digit.getDigit();
                   System.out.print(digit.name());
                   System.out.println(" - " + this.number);
                   break;
               }
           }
           if (!fFinded) {
               throw new Exception("Неизвестная римская цифра: " + name);
           }
       }
    }

}
