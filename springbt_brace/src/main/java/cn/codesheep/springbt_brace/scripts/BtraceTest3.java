package cn.codesheep.springbt_brace.scripts;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

// 监控代码是否到达了某类的某一行
@BTrace
public class BtraceTest3 {

    @OnMethod(
            clazz="cn.codesheep.springbt_brace.service.UserService",
            method="getUsersByName",
            location=@Location(value= Kind.LINE, line=28)  // 拦截第28行, 28行是从数据库取数据操作
    )
    public static void lineTest( @ProbeClassName String pcn, @ProbeMethodName String pmn, int line ) {
        BTraceUtils.println("ClassName: " + pcn);
        BTraceUtils.println("MethodName: " + pmn);
        BTraceUtils.println("执行到的line行数: " + line);
    }
}
