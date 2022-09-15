package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите строку:");
        String inputExpr = reader.readLine();
        try {
            System.out.println(calc(inputExpr));
        }
        catch (Exception e) {
            System.out.println("Без паники! Все под контролем!");
            System.out.println(e.getMessage());
        }
    }
    public static String calc(String inputExpr)  throws Exception{

        int maxNumberOperands = 2; // максимальное количество операндов в выражении
        Operate[] listOperate = Operate.values();
        Operate[] opers = new Operate[maxNumberOperands];
        Operate inputOper = Operate.ADD;
        Operand [] operands = new Operand[maxNumberOperands];
        int i = 0, j = -1, findedIndex = 0, minIndex = 0, startIndex = 0;
        boolean fFinded = true;
        int result = 0;
        String sResult = "", sRresult= "";
        String d0 = "";

        System.out.println("Строка на входе: " + inputExpr);
        // пройдемся по строке inputOrder найдем все операнды и запишем их в массив operands
        //&& startIndex < inputExpr.length()
        while (fFinded && j < maxNumberOperands - 1) {
            fFinded = false;
            minIndex = inputExpr.length();
            // пробежимся по списку валидных операций и найдем первую операцию во входной строке
            for (Operate mO : listOperate) {
                findedIndex = inputExpr.indexOf(mO.getSig(), startIndex);
                if (findedIndex > -1) {
                    if (findedIndex < minIndex) {
                        fFinded = true;
                        minIndex = findedIndex;
                        inputOper = mO;
                    }
                }
 //               System.out.print(mOper.getSig());
 //               System.out.print(inputExpr.indexOf(mOper.getSig(), i) + "  ");
            }
 //           System.out.println();
            // ------------------
  //          if (j >= maxNumberOperands-1) {
  //              throw new Exception("Слишком много операндов/операторов!");
  //          }
            if (fFinded) {
                j++;
                operands[j] = new Operand(inputExpr.substring(startIndex, minIndex));
//                System.out.println(j +" операнд: " + operands[j].name);
                opers[j] = inputOper;
                operands[j].setTypeOperand();
//                System.out.println("Тип : " + operands[j].type);
                startIndex = minIndex + 1;
            }
            else {
                if (j > -1) { //значит операнды есть в начале строки и они уже занесены в массив, остался последний в конце строки
                    j++;
                    operands[j] = new Operand(inputExpr.substring(startIndex));
//                    System.out.println(j +"Последний операнд: " + operands[j].name);
                    operands[j].setTypeOperand();
//                    System.out.println("Тип: " + operands[j].type);
                }
                else { // нет операторов
                    throw new Exception("Нет оператора!");
                }
            }

        }
// конец цикла

        // проверим все ли операторы одного типа и определим значение каждого
        char t = operands[0].type;
        for (Operand operand : operands) {
            if (!(operand.type == t))  throw new Exception("Разнотипные операнды!");
            operand.setNumber();
            if (operand.number > 10 | operand.number < 1) throw new Exception("Операнд вне диапазона 1-10 !");
        }
        // для первых двух операндов вычислим выражение поскольку в условии задачи должно быть всего два операнда
        switch (opers[0]) {
            case DIV:
//                System.out.println("Делим");
                result = operands[0].number / operands[1].number;
                break;
            case MULT:
//                System.out.println("Умножаем");
                result = operands[0].number * operands[1].number;
                break;
            case ADD:
//                System.out.println("Складываем");
                result = operands[0].number + operands[1].number;
                break;
            case SUB:
//                System.out.println("Вычитаем");
                result = operands[0].number - operands[1].number;
                break;
        }
        sResult = Integer.toString(result).trim();
        if (operands[0].type == 'r') {
            if (result < 1) {
                throw new Exception("Результат меньше 1 не может быть записан римским числом.");
            }
            if (result == 100) {sRresult = "C";}
            else {
                if (sResult.length() == 2) {
                    for (RomDigit1 dr1 : RomDigit1.values()) {
                        if (sResult.substring(0, 1).equals(Integer.toString(dr1.getDigit()).trim())) {
//                            System.out.println("Десятки" + dr1.name());
                            sRresult = dr1.name();
                            break;
                        }
                    }
                    d0 = sResult.substring(1, 2);
                } else {
                    d0 = sResult.substring(0, 1);
                }

                for (RomDigit dr0 : RomDigit.values()) {
                    if (d0.equals(Integer.toString(dr0.getDigit()).trim())) {
//                        System.out.println("Еденицы" + dr0.name());
                        sRresult = sRresult + dr0.name();
                        break;
                    }
                }
            }
        }
//        System.out.println("Hello World from OldSchoolMan! Calculated: " + result);
        if (sRresult.isEmpty()) return sResult;
        else return sRresult;
    }
}
