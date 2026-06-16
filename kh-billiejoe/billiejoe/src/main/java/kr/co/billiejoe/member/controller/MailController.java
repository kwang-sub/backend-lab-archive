package kr.co.billiejoe.member.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.billiejoe.member.model.service.MailService;
import kr.co.billiejoe.member.model.vo.Member;

@Controller
@RequestMapping("/mail/*")
public class MailController {

	@Autowired
	private MailService service;
	
	@Autowired
	private JavaMailSender mailSender; // 메일 (root-context)
	
	@RequestMapping("findPwd")
	public String findPwd() {
		return null;
	}
	
	@RequestMapping("sendMail")
	public String sendMail(Member member, RedirectAttributes ra) {
		  String setfrom = "zxcvzvv55@gmail.com";
	      String tomail = "HyeJ@student.kenvale.edu.au"; // 받는 사람 이메일
	      //String tomail = member.getMemberEmail(); // 받는 사람 이메일
	      String title = "[Billie Joe] 임시비밀번호 발송"; //제목 
	      String content = "임시비밀번호 : "; // 내용
	      String key = ""; 
	    
	      
	      System.out.println(member.getMemberEmail());
	      System.out.println(member.getMemberName());
	      
	      // 1) 입력받은 이름과 이메일이 실제로 DB에 있는 회원인지 확인
	      int memberDetail = service.selectmemberDetail(member);
	      
	      System.out.println(memberDetail);
	      
	     
	      
	      // System.out.println(tomail);
	      
	      // 2) 있다면 임시비밀번호 생성 및 DB에 저장 + (Service에서 암호화진행)
	      if(memberDetail > 0) {
	    	  
		      try {
		    	 
		  		
		  		// 임시 비밀번호 생성 
				Random random = new Random();
				for (int i = 0; i < 3; i++) {
					int index = random.nextInt(25) + 65; // A~Z까지 랜덤 알파벳 생성
					key += (char) index;
				}
				int numIndex = random.nextInt(8999) + 1000; // 4자리 정수를 생성
				key += numIndex;

				for (int i = 0; i < 3; i++) {
					int index = random.nextInt(25) + 65; // A~Z까지 랜덤 알파벳 생성
					key += (char) index;
				}
				numIndex = random.nextInt(8999) + 1000; // 4자리 정수를 생성
				key += numIndex;
			  		
		    	  member.setMemberPw(key);
		           	
		         int result = service.updateTempPw(member);   
		         
		         
		         // 3) 있다면 임시 비밀번호 메일 보내기 구문 실행 
		         if(result > 0) {
		        	 MimeMessage message = mailSender.createMimeMessage();
		        	 MimeMessageHelper messageHelper = new MimeMessageHelper(message,true, "UTF-8");
		        	 
		        	 messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
		        	 messageHelper.setTo(tomail); // 받는사람 이메일
		        	 messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
		        	 messageHelper.setText(content + key); // 메일 내용
		        	 
		        	 mailSender.send(message);
		        	 
		        	 swalSetMessage(ra, "success", "임시비밀번호가 발송되었습니다.",null);
		         }
		         
		         
		         
		      } catch (Exception e) {
		         System.out.println(e);
		      }
		      
	      }else { // 이메일, 이름이 일치하는 회원이 없을 경우
	    	  swalSetMessage(ra, "error", "이름 혹은 이메일이 일치하지 않습니다.",null);
	    	  return "redirect:/mail/findPwd";
	      }

	      return "redirect:/";
	   }

	private static void swalSetMessage(RedirectAttributes ra, String icon, String title, String text) {
		ra.addFlashAttribute("icon", icon);
		ra.addFlashAttribute("title", title);
		ra.addFlashAttribute("text", text);
	}
	
	}

