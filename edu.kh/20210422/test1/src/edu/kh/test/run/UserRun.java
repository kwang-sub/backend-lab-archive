package edu.kh.test.run;

import edu.kh.test.model.service.UserService;

public class UserRun {

	public static void main(String[] args) {
		
		UserService service = new UserService();
		new UserService().displayMenu();
	
	}

}
