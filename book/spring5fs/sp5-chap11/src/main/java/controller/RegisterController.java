package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/register/*")
public class RegisterController {

    private MemberRegisterService service;

    public RegisterController(@Autowired MemberRegisterService service) {
        this.service = service;
    }

    @GetMapping("step1")
    public String handleStep1() {
        return "register/step1";
    }

    @GetMapping("step2")
    public String handleStep2Get() {
        return "redirect:/register/step1";
    }

    @PostMapping("step2")
    public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
                              Model model) {
        if(!agree){
            return "register/step1";
        }
        model.addAttribute("formData", new RegisterRequest());
        return "register/step2";
    }

    @PostMapping("step3")
    public String handleStep3(@Valid @ModelAttribute("formData") RegisterRequest regReq, Errors errors) {
        if (errors.hasErrors()) {
            return "register/step2";
        }
        try {
            service.regist(regReq);
            return "register/step3";
        } catch (DuplicateMemberException e){
            errors.rejectValue("email", "duplicate");
            return "register/step2";
        }
    }

//컨트롤범위 validation
  /*  @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new RegisterRequestValidator());
    }*/
}
