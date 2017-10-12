package validation;

import entity.User;
import service.UserService;
import util.MD5;

/**
 * Created by mihail on 11.10.17.
 */
public class LoginValidation implements IValidation {
    @Override
    public String check(Object o) throws ClassCastException {

        //return "test message";
        User user = (User) o;

        UserService service = new UserService();
        System.out.println(user.getName() + " for LV1");//todo FT
        User userFromBase = service.findUserByEmail(user.getEmail());
        System.out.println("lv 4");//todo FT
        if (userFromBase == null)
            userFromBase = service.findUserByName(user.getName());

        if (userFromBase == null) {
            return "Жителя с именем или имейлом: " + user.getName() + " нет в тирети.";
        }
        try {
            if (!userFromBase.getPassword().equals(MD5.getMD5(user.getPassword()))) {
                return "Неверный пароль.";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return null;
    }
}
