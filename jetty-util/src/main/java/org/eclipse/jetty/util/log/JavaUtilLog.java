package org.eclipse.jetty.util.log;

import java.util.logging.Level;

/**
 * <p>
 * Implementation of Jetty {@link Logger} based on {@link java.util.logging.Logger}.
 * </p>
 * 
 * <p>
 * Honors the standard jetty system property <code>"org.eclipse.jetty.util.log.DEBUG"</code> to set logger into debug
 * mode (defaults to false, set to "true" to enable)
 * </p>
 * 
 * <p>
 * You can also set the logger level using <a href="http://java.sun.com/j2se/1.5.0/docs/guide/logging/overview.html">
 * standard java.util.logging configuration</a> against the name <code>"org.eclipse.jetty.util.log"</code>.
 * </p>
 */
public class JavaUtilLog implements Logger
{
    private java.util.logging.Logger logger;

    public JavaUtilLog()
    {
        this("org.eclipse.jetty.util.log");
    }

    public JavaUtilLog(String name)
    {
        logger = java.util.logging.Logger.getLogger(name);
        if (Boolean.getBoolean("org.eclipse.jetty.util.log.DEBUG"))
        {
            logger.setLevel(Level.FINE);
        }
    }

    public void debug(String msg)
    {
        logger.log(Level.FINE,msg);
    }

    public void debug(String msg, Throwable th)
    {
        logger.log(Level.FINE,msg,th);
    }

    public void debug(String msg, Object arg0, Object arg1)
    {
        logger.log(Level.FINE,format(msg,arg0,arg1));
    }

    public Logger getLogger(String name)
    {
        return new JavaUtilLog(name);
    }

    public void info(String msg)
    {
        logger.log(Level.INFO,msg);
    }

    public void info(String msg, Object arg0, Object arg1)
    {
        logger.log(Level.INFO,format(msg,arg0,arg1));
    }

    public boolean isDebugEnabled()
    {
        return logger.isLoggable(Level.FINE);
    }

    public void setDebugEnabled(boolean enabled)
    {
        logger.setLevel(Level.FINE);
    }

    public void warn(String msg)
    {
        logger.log(Level.WARNING,msg);
    }

    public void warn(String msg, Object arg0, Object arg1)
    {
        logger.log(Level.WARNING,format(msg,arg0,arg1));
    }

    public void warn(String msg, Throwable th)
    {
        logger.log(Level.WARNING,msg,th);
    }

    private String format(String msg, Object arg0, Object arg1)
    {
        int i0 = msg.indexOf("{}");
        int i1 = i0 < 0?-1:msg.indexOf("{}",i0 + 2);

        if (arg1 != null && i1 >= 0)
            msg = msg.substring(0,i1) + arg1 + msg.substring(i1 + 2);
        if (arg0 != null && i0 >= 0)
            msg = msg.substring(0,i0) + arg0 + msg.substring(i0 + 2);
        return msg;
    }
}
