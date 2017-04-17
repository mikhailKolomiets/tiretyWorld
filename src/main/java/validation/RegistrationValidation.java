package validation;

import entity.User;

import java.util.ArrayList;

/**
 * Created by mihail on 16.04.17.
 */
public class RegistrationValidation implements IValidation{

    @Override
    public String check(Object o) {

        User user = (User)o;

        if (user.getName().length() == 0) {
            return "У всех есть имя";
        }

        if (user.getName().length() > 25) {
            return "Имя слишком длинное";
        }

        if (user.getPassword().length() < 6 || user.getPassword().length() > 50) {
            return "Пароль от 6 до 50 символов";
        }

        String[] partEmail = user.getEmail().split("@| ");
        if (partEmail.length != 2 || partEmail[1].split("\\.").length != 2) {
            return "Неправильно введен адрес";
        }

        return null;
    }
}
