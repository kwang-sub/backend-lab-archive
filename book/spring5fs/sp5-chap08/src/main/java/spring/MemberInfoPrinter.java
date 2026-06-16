package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberInfoPrinter {

    private MemberDao memberDao;
    private MemberPrinter printer;

    public MemberInfoPrinter(@Autowired MemberDao memberDao, @Autowired MemberPrinter printer) {
        this.memberDao = memberDao;
        this.printer = printer;
    }

    public void printMemberInfo(String email){

        Member member = memberDao.selectByEmail(email);

        if(member == null){
            System.out.println("데이터 없음");
            return;
        }
        printer.print(member);
        System.out.println();

    }
}
