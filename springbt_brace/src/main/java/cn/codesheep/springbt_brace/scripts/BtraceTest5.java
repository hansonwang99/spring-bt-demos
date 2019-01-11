package cn.codesheep.springbt_brace.scripts;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.println;


// 监控指定函数中所有外部调用的耗时情况
@BTrace
public class BtraceTest5 {

    @OnMethod (clazz = "cn.codesheep.springbt_brace.service.UserService",method = "getUsersByName",
    location=@Location(value= Kind.CALL, clazz="/.*/", method="/.*/", where = Where.AFTER) )
    public static void printMethodRunTime(@Self Object self,@TargetInstance Object instance,@TargetMethodOrField String method, @Duration long duration) {

        if( duration > 5000000 ){  //如果耗时大于 5ms 则打印出来 这个条件建议加 否则打印的调用函数太多 具体数值可以自己调控
            println( "self: " + self );
            println( "instance: " + instance );
            println( method + ",cost:" + duration/1000000 + " ms" );
        }
    }

}
