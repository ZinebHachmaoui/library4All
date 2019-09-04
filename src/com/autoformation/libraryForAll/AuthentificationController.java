package com.autoformation.libraryForAll;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthentificationController {
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword(Locale locale, Model model, 
	  @RequestParam("id") int id, @RequestParam("token") String token) {
		String result = LoginDAO.validatePasswordResetToken(id, token);
	    if (result != null) {
	        model.addAttribute("message", 
	          "Erreur");
	        return "login";
	    }
	    return "redirect:/faces/updatePassword.xhtml?id="+id;
	}
}
