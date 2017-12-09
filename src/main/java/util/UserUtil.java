package util;

import entity.User;

import java.time.LocalDateTime;

/**
 * Created by mihail on 08.12.17.
 */
public class UserUtil {

    /**
     * @param user
     * @return game old of player by game
     */
    public static int convertUserOld(User user) {

        LocalDateTime registrationTime = LocalDateTime.parse(user.getRegistrationTime());
        LocalDateTime serverTime = GameTimeUtil.createGameTimeFromLDT(Variable.STARTTIME);

        return 18 + GameTimeUtil.getYearsBetweenLDT(registrationTime, serverTime);
    }

}
