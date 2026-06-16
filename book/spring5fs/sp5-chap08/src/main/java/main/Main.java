package main;

import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static AnnotationConfigApplicationContext ctx = null;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            ctx = new AnnotationConfigApplicationContext(AppCtx.class);
            System.out.println("명령어를 입력하세요");
            String command = sc.nextLine();
            if(command.equalsIgnoreCase("exit")){
                System.out.println("종료합니다.");
                break;
            } else if (command.startsWith("new ")) {
                processNewCommand(command.split(" "));
            } else if (command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
            } else if (command.startsWith("info ")) {
                processInfoCommand(command.split(" "));
            } else if (command.equals("list")) {
                processListCommand();
            } else {
                printHelp();
            }
            ctx.close();
        }
    }
    private static void processVersionCommand() {

        VersionPrinter printer = ctx.getBean(VersionPrinter.class);

        printer.print();
    }

    private static void processInfoCommand(String[] arg) {
        if(arg.length != 2){
            printHelp();
            return;
        }

        MemberInfoPrinter printer = ctx.getBean(MemberInfoPrinter.class);
        printer.printMemberInfo(arg[1]);
    }

    private static void processListCommand() {

        MemberListPrinter printer = ctx.getBean(MemberListPrinter.class);
        printer.printAll();

    }

    private static void processNewCommand(String[] arg){
        if(arg.length != 5){
            printHelp();
            return;
        }

        MemberRegisterService regSvc = ctx.getBean(MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if(!req.isPasswordEqualToConfirmPassword()){
            System.out.println("암호와 확인이 일치하지 않습니다.");
            return;
        }

        try {
            regSvc.regist(req);
            System.out.println("등록했습니다.");
        }catch (DuplicateMemberException e){
            System.out.println("이미 존재하는 이메일입니다.");
        }
    }

    private static void processChangeCommand(String[] arg){
        if(arg.length != 4){
            printHelp();
            return;
        }

        ChangePasswordService changePwdSvc = ctx.getBean(ChangePasswordService.class);

        try {
            changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호를 변경했습니다.");
        }catch (MemberNotFoundException e){
            System.out.println("존재하지 않는 이메일입니다.");
        }catch (WrongIdPasswordException e){
            System.out.println("이메일과 암호가 일치하지 않습니다.");
        }

    }

    private static void printHelp(){
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요");
        System.out.println("명령어 사용법");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println();
    }

}
