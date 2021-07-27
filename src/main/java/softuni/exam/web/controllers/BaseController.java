package softuni.exam.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    public ModelAndView view(String view) {
        return setView(view);
    }

    public ModelAndView view(String view, String objectName, Object object) {
        ModelAndView modelAndView = setView(view);
        modelAndView.addObject(objectName, object);

        return modelAndView;
    }

    private ModelAndView setView(String view) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", view);
        return modelAndView;
    }

    public ModelAndView redirect(String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + url);

        return modelAndView;
    }
}
