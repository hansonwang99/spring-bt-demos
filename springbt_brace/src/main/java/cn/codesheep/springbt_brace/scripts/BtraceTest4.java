package cn.codesheep.springbt_brace.scripts;

import cn.codesheep.springbt_brace.entity.User;
import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import java.lang.reflect.Field;
import java.util.List;

import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.str;


// 拦截方法的 参数/返回值
@BTrace
public class BtraceTest4 {

//    @OnMethod(
//            clazz = "cn.codesheep.springbt_brace.controller.UserController",
//            method = "getUsersByName",
//            location = @Location(Kind.ENTRY)
//    )
//    public static void getFuncEntry(@ProbeClassName String pcn, @ProbeMethodName String pmn, User user ) {
//
//        println("类名: " + pcn);
//        println("方法名: " + pmn);
//
//        BTraceUtils.print("入参实体为: ");
//        BTraceUtils.printFields(user);
//
//        // print one field
//        Field oneFiled = BTraceUtils.field("cn.codesheep.springbt_brace.entity.User", "userName");
//        println("userName字段为: " + BTraceUtils.get(oneFiled, user));
//
//        oneFiled = BTraceUtils.field("cn.codesheep.springbt_brace.entity.User", "userAge");
//        println("userAge字段为: " + BTraceUtils.get(oneFiled, user));
//
//    }


    @OnMethod(
            clazz = "cn.codesheep.springbt_brace.controller.UserController",
            method = "getUsersByName",
            location = @Location(Kind.RETURN)  //函数返回的时候执行，如果不填，则在函数开始的时候执行
    )
    public static void getFuncReturn( @Return List<User> users ) {
        println("返回值为: ");
        println(str(users));
    }

}
