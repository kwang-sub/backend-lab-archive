package config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.*;

@Configuration
/*@ComponentScan(basePackages = {"spring"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "spring.*Dao"))*/
@ComponentScan(basePackages = {"spring"})
public class AppCtx {

    @Bean
    @Qualifier("printer")
    public MemberPrinter printer1(){
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("printer2")
    public MemberSummaryPrinter memberPrinter2(){
        return new MemberSummaryPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter(){

        VersionPrinter printer = new VersionPrinter();

        printer.setMajorVersion(5);
        printer.setMinorVersion(0);

        return printer;
    }
}
