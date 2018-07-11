package log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

public class UserLog {
    private static Logger log = Logger.getLogger(UserLog.class.getName());

    @AroundInvoke
    public Object logInfo(InvocationContext context) throws Exception {
        log.info("Create User");
        return context.proceed();
    }
}
