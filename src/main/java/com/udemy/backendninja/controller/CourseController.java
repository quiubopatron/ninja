package com.udemy.backendninja.controller;

import com.udemy.backendninja.entity.Course;
import com.udemy.backendninja.service.CourseServiceImp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/course")
public class CourseController {

    private static final String COURSES = "courses";
    private static final Log LOG = LogFactory.getLog(CourseController.class);

    @Autowired
    @Qualifier("CourseServiceImp")
    private CourseServiceImp courseServiceImp;


    @GetMapping("/listcourses")
    public ModelAndView listAllCourses(){

        LOG.info("Call:" + "listAllCourses()");
        ModelAndView modelAndView = new ModelAndView(COURSES);
        modelAndView.addObject("courses", courseServiceImp.listAllCourses());
        modelAndView.addObject("course", new Course());

        return modelAndView;

    }

    @PostMapping("/addcourse")
    public String addCourse(@ModelAttribute("course")Course course) {

        LOG.info("Call:" + "addCourse()" + "-- Para,: " + course.toString());

        courseServiceImp.addCourse(course);

        return "redirect:/course/listcourses";
    }

    // TODO Continuar
    @PostMapping("/updatecourse")
    public String updateCourse (@ModelAttribute("course") Course course) {

        LOG.info("Call:" + "updateCourse()" + "-- Para,: " + course.toString());

        courseServiceImp.updateCourse(course);

        return "redirect:/course/listcourses";

    }

}
