package cn.codesheep.springbt_alisms.controller;

import cn.codesheep.springbt_alisms.model.dto.VerificationCode;
import cn.codesheep.springbt_alisms.model.result.ResponseMessage;
import cn.codesheep.springbt_alisms.service.AliMessageService;
import cn.codesheep.springbt_alisms.service.VerificationCodeService;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private AliMessageService aliMessageService;

    @RequestMapping(value = "/GetVerificationCode", method = RequestMethod.POST)
    public ResponseMessage getVerificationCode(@RequestBody VerificationCode model ) throws ClientException {

        String verificationCode = verificationCodeService.createVerificationCode();
        String phoneNum = model.getMobile();
        String result = aliMessageService.sendCodeMessage( verificationCode, phoneNum );
        if( "OK".equals(result) ) {
            verificationCodeService.add( phoneNum, verificationCode );
            return new ResponseMessage();
        } else
            return new ResponseMessage("验证码短信发送失败！错误详情：" + result);
    }


    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public ResponseMessage login( @RequestBody VerificationCode model ) {

        if( verificationCodeService.isVerificationCodeCorrect( model.getMobile(), model.getCode() ) ) {

            // 可在此处加入正常登陆逻辑

            return null;
        } else
            return new ResponseMessage("验证码错误！");
    }

}
