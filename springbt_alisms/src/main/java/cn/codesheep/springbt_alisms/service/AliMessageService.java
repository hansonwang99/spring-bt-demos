package cn.codesheep.springbt_alisms.service;

import cn.codesheep.springbt_alisms.utils.SmsSender;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Service;

@Service
public class AliMessageService {

    public String sendCodeMessage( String code, String phoneNum ) throws ClientException {
        return SmsSender.sendSms( code, phoneNum );
    }
}
