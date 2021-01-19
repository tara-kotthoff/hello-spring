package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("hello")
public class HelloController {

    //Handles requests at /hello/goodbye
    @GetMapping("goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    //Handles request of the form /hello?name=LaunchCode
    @RequestMapping(method={RequestMethod.GET, RequestMethod.POST})
    public String hello(@RequestParam String name, Model model) {
        String greeting = "Hello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "hello";
    }

    //Handles requests of the form /hello/LaunchCode
    @GetMapping("{name}")
    public String helloAgain(@PathVariable String name, Model model) {
        String greeting = "Hello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "hello";
    }

    //Lives at /hello/form
    @GetMapping("form")
    public String helloForm() {
        return "form";
    }

    //Lives at /hello/language
    @GetMapping("language")
    @ResponseBody
    public String createMessage() {
        return "<html>" +
                "<body>" +
                "<form method='post'>" + //submits request to /hello/language
                "<input type='text' name='name'>" +
                "<select name='languages'>" +
                "<option value='french'>French</option>" +
                "<option value='german'>German</option>" +
                "<option value='spanish'>Spanish</option>" +
                "<option value='english'>English</option>" +
                "</select>" +
                "<input type='submit' value='Greet me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    @PostMapping("language")
    @ResponseBody
    public String languageReply(@RequestParam String name, @RequestParam String languages) {
        String greeting;
        switch (languages) {
            case "german":
                greeting = "Hallo";
                break;
            case "french":
                greeting = "Bonjour";
                break;
            case "spanish":
                greeting = "Hola";
                break;
            default:
                greeting = "Hello";
        }
        return greeting + ", " + name + "!";
    }

    @GetMapping("hello-names")
    public String helloNames(Model model) {
        List<String> names = new ArrayList<>();
        names.add("LaunchCode");
        names.add("Java");
        names.add("Javascript");
        model.addAttribute("names", names);
        return "hello-list";
    }
    
}

