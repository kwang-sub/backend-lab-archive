package edu.kh.objarray.run;

import edu.kh.objarray.model.service.ObjectArrayService;

public class ObjectArrayRun {

	public static void main(String[] args) {
		ObjectArrayService service = new ObjectArrayService();
		service.init1();
//		service.priceCut();
		service.printBooks();
//		System.out.println(service.searchAuthor());
		service.displayMenu();
	}
	

}
