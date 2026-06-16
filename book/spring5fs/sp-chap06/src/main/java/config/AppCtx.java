package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppCtx {

    @Bean
    public Client client(){
        Client client = new Client();
        client.setHost("host");
        return client;
    }

    @Bean(initMethod = "connect", destroyMethod = "close")
    public Client2 client2(){
        Client2 client2 = new Client2();
        client2.setHost("host2");
        return client2;
    }


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
