package util;

/**
 * Created by mihail on 13.10.17.
 */
public class UserRunUtil {
    public static int getY(int position) {
        return position % 1000;
    }

    public static int getX(int position) {
        return position / 10000;
    }

    public static int countDistance(int from, int to) {
        double fromX = getX(from), fromY = getY(from);
        double toX = getX(to), toY = getY(to);

        return (int) (Math.sqrt(Math.pow(toX - fromX, 2) + Math.pow(toY - fromY, 2)));
    }


}
