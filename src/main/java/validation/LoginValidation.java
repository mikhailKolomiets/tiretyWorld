package validation;

import entity.User;
import service.UserService;
import util.GameTimeUtil;
import util.MD5;
import util.Variable;

/**
 * Created by mihail on 11.10.17.
 */
public class LoginValidation implements IValidation {
    @Override
    public String check(Object o) throws ClassCastException {

        User user = (User) o;

        if (user.getEmail().length() == 0) {
            return "Введите пожалуйста имя или email";
        }

        if (user.getPassword().length() == 0) {
            return "Введите пароль.";
        }

        UserService service = new UserService();

        User userFromBase = service.findUserByEmail(user.getEmail());

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

        if (userFromBase.getHealth() == null) {
            String gameTimeAtNow = GameTimeUtil.createGameTimeFromLDT(Variable.STARTTIME).toString();

            userFromBase.setRegistrationTime(gameTimeAtNow);
            userFromBase.setHealth(gameTimeAtNow);
            userFromBase.setTire(gameTimeAtNow);
            userFromBase.setHunger(gameTimeAtNow);

            service.updateUser(userFromBase);
            return "Обновились новые данные ... перезайдите";
        }

        return null;
    }
}
