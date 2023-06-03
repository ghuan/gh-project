package com.gh.common.web.config;

import com.gh.common.web.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({GlobalExceptionHandler.class})
public class WebConfig {
}
