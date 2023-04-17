package com.ntendencia.services;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Utils {
    public static String getMensagemValidacao(final String chaveMensagem, final Object... params) {
        ResourceBundle bundle = ResourceBundle.getBundle("Validation", Locale.getDefault());
        String pattern = bundle.getString(chaveMensagem);

        return MessageFormat.format(pattern, params);
    }
}