package com.udemy.backendninja.controller;

import com.udemy.backendninja.component.ExampleComponent;
import com.udemy.backendninja.model.Person;
import com.udemy.backendninja.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("example")
public class ExampleController {

    // Ejemplo inyeccion de un bean de tipo Component
    @Autowired
    @Qualifier("exampleComponent")
    private ExampleComponent exampleComponent;

    // Ejemplo inyeccion de un servicio, se importa la interfaz no la implementacion
    @Autowired
    @Qualifier("exampleService")
    private ExampleService exampleService;

    public static final String EXAMPLE_VIEW = "example";

    // Primeraforma
    @RequestMapping(value = "/exampleString", method = RequestMethod.GET)
    public String exampleString(Model model) {
        model.addAttribute("personas", getPersonas());
        return EXAMPLE_VIEW;

    }

    // Segunda forma
    @GetMapping("/examplemav")
    public ModelAndView exampleMAV(){

        ModelAndView modelAndView = new ModelAndView(EXAMPLE_VIEW);
        modelAndView.addObject("personas", exampleService.getListPeople());
        return modelAndView;

//        return new ModelAndView(EXAMPLE_VIEW);
    }

    private List<Person> getPersonas() {

        exampleComponent.sayHello();// Ejemplo de la inyeccion del bean
        List<Person> personas = new ArrayList<>();
        Person person1 = new Person("Tomas", 37);
        Person person2 = new Person("Vanessa", 31);
        Person person3 = new Person("Alicia", 3);

        personas.add(person1);
        personas.add(person2);
        personas.add(person3);

        return personas;

    }
}
