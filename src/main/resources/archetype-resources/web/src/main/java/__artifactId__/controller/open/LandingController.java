#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.${artifactId}.bind.annotation.GetMapping;
import org.springframework.${artifactId}.bind.annotation.RequestMapping;
import org.springframework.${artifactId}.servlet.ModelAndView;

@Controller
@RequestMapping
public class LandingController {

    @GetMapping("")
    public ModelAndView showLandingPage() {
//        return new ModelAndView("index");
        return new ModelAndView("home");
    }
}
