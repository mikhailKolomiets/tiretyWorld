package util;

/**
 * Created by mihail on 23.10.17.
 */
public class ProfessionLevel {

    public static int getLevelOfAgronomExpirience(int exp) {
        if (exp < 100)
            return 0;
        if (exp < 300)
            return 1;
        if (exp < 600)
            return 2;
        if (exp < 1000)
            return 3;
        return 4;
    }

    public static int getLevelOfWoodmanExpirience(int exp) {
        if (exp < 120)
            return 0;
        if (exp < 350)
            return 1;
        if (exp < 700)
            return 2;
        if (exp < 1200)
            return 3;
        return 4;
    }
}
