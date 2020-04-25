package com.analuciabolico.assembly.v1.core.validation;

import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class MessageValidationProperties {
    private static final String PROPERTIES = "ValidationMessages";
    private static final ResourceBundle bundle = getBundle(PROPERTIES);

    public static String getMensagem(GenericMessagesValidationEnum enumProperties) {
        return bundle.getString(enumProperties.getKey());
    }
}
