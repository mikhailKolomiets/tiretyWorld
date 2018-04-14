package controller;


import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.GameTimeUtil;
import util.Variable;

/**
 * Created by mihail on 13.04.18.
 */

@RestController
public class TestSpringController{

    @RequestMapping("/annny")
    public String getAny() {
        return "{\"testSpring\":\" its ok\"}";
    }

}
