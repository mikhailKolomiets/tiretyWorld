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

        return null;
    }
}
