package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppCtx {

    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegSvc(){
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePwdSvc(){
        ChangePasswordService service = new ChangePasswordService();
        service.setMemberDao(memberDao());
        return service;
    }

    @Bean
    public MemberPrinter printer(){
        return new MemberPrinter();
    }

    @Bean
    public MemberListPrinter listPrinter(){
        return new MemberListPrinter(memberDao(), printer());
    }

    @Bean
    public MemberInfoPrinter infoPrinter(){
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setPrinter(printer());
        infoPrinter.setMemberDao(memberDao());
        return infoPrinter;
    }

    @Bean
    public VersionPrinter versionPrinter(){

        VersionPrinter printer = new VersionPrinter();

        printer.setMajorVersion(5);
        printer.setMinorVersion(0);

        return printer;
    }
}
