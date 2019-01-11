package cn.codesheep.springbt_brace.scripts;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;

import static com.sun.btrace.BTraceUtils.jstack;
import static com.sun.btrace.BTraceUtils.println;

@BTrace
public class BtraceTest {

    @OnMethod(clazz = "java.lang.System", method = "gc")
    public static void onSystemGC() {
        println("entered System.gc()");
        jstack();
    }

}

