package fr.huiitre.tools.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LogController {
    
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .enable(SerializationFeature.INDENT_OUTPUT);

    private String moduleName;

    private Logger getCallerLogger() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        // stack[0] = Thread.getStackTrace
        // stack[1] = getCallerLogger()
        // stack[2] = info/debug dans LogController
        // stack[3] = la méthode appelante (ex : AuthController.me)
        String callerClassName = stack[3].getClassName();

        String[] parts = callerClassName.split("\\.");
        String module = parts[3];

        moduleName = module;

        return LoggerFactory.getLogger(callerClassName);
    }

    private String convert(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "Erreur lors de la sérialisation de l'objet: " + obj;
        }
    }

    public void info(Object message, Object... additional) {
        ch.qos.logback.classic.Logger callerLogger = (ch.qos.logback.classic.Logger) getCallerLogger();
        initFileAppender(callerLogger, moduleName);

        if (additional == null || additional.length == 0) {
            callerLogger.info(convert(message));
        } else {
            callerLogger.info("{} {}", convert(message), convert(additional[0]));
        }
    }
    
    public void debug(Object message, Object... additional) {
        ch.qos.logback.classic.Logger callerLogger = (ch.qos.logback.classic.Logger) getCallerLogger();
        initFileAppender(callerLogger, moduleName);

        if (additional == null || additional.length == 0) {
            callerLogger.debug(convert(message));
        } else {
            callerLogger.debug("{} {}", convert(message), convert(additional[0]));
        }
    }

    public void error(Object message, Object... additional) {
        ch.qos.logback.classic.Logger callerLogger = (ch.qos.logback.classic.Logger) getCallerLogger();
        initFileAppender(callerLogger, moduleName);

        if (additional == null || additional.length == 0) {
            callerLogger.error(convert(message));
        } else {
            callerLogger.error("{} {}", convert(message), convert(additional[0]));
        }
    }

    private void initFileAppender(ch.qos.logback.classic.Logger callerLogger, String moduleName) {
        if (callerLogger.getAppender("FILE-" + moduleName) != null) {
            return;
        }
        
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    
        String folderPath = "logs/" + moduleName;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".log";
        String filePath = folderPath + "/" + fileName;
    
        RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
        fileAppender.setContext(context);
        fileAppender.setName("FILE-" + moduleName);
        fileAppender.setFile(filePath);

        // Ajout d'un filtre pour n'accepter que les logs de niveau INFO et supérieur (DEBUG sera filtré)
        ch.qos.logback.classic.filter.ThresholdFilter filter = new ch.qos.logback.classic.filter.ThresholdFilter();
        filter.setLevel("INFO"); // Par défaut, DEBUG ne sera pas écrit
        filter.start();
        fileAppender.addFilter(filter);
    
        TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<>();
        rollingPolicy.setContext(context);
        rollingPolicy.setParent(fileAppender);
        rollingPolicy.setFileNamePattern(folderPath + "/%d{yyyy-MM-dd}.log");
        rollingPolicy.setMaxHistory(30);
        rollingPolicy.start();
    
        fileAppender.setRollingPolicy(rollingPolicy);
    
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n%n");
        encoder.start();
    
        fileAppender.setEncoder(encoder);
        fileAppender.start();
    
        callerLogger.addAppender(fileAppender);
    }    
}
