package cn.codesheep.springbt_alisms.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VerificationCodeService {

    private static Map<String,String> codeList = new HashMap<>();

    public boolean isVerificationCodeCorrect( String mobile, String verificationCode ) {

        if( codeList.containsKey(mobile) && verificationCode.equals(codeList.get(mobile)) )
            return true;
        else
            return false;
    }

    public String createVerificationCode() {

        String verificationCode = "";
        char[] pattern = new char[]
                {
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
                };

        for( int i=0; i<6; ++i ) {
            int randomIndex = (int) (Math.random()*10);
            verificationCode += pattern[randomIndex];
        }
        System.out.println( "后台生成的验证码为：" + verificationCode );
        return verificationCode;
    }

    public void add( String mobile, String verificationCode ) {
        codeList.put( mobile, verificationCode );
    }
}
