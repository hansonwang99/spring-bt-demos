package cn.codesheep.springbt_brace.scripts;


import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.strcat;
import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.str;

// 监控方法的耗时情况
@BTrace
public class BtraceTest2 {

    @OnMethod(clazz = "cn.codesheep.springbt_brace.controller.UserController", method = "getUsersByName", location = @Location(Kind.RETURN))
    public static void getFuncRunTime( @ProbeMethodName String pmn, @Duration long duration) {
        println( "接口 " + pmn + strcat("的执行时间(ms)为: ", str(duration / 1000000)) ); //单位是纳秒，要转为毫秒
    }

}
