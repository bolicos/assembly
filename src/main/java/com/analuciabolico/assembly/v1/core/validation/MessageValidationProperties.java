package com.analuciabolico.assembly.v1.core.validation;

import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

@NoArgsConstructor
public class MessageValidationProperties {
    private static final String PROPERTIES = "ValidationMessages";
    private static final ResourceBundle bundle = getBundle(PROPERTIES);

    public static String getMessage(GenericMessagesValidationEnum enumProperties) {
        return bundle.getString(enumProperties.getKey());
    }
}
