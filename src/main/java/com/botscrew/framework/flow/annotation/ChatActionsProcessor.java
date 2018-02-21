package com.botscrew.framework.flow.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Component
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ChatActionsProcessor {
}