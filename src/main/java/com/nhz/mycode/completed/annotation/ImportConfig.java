package com.nhz.mycode.completed.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Student.class)
public class ImportConfig {

}
