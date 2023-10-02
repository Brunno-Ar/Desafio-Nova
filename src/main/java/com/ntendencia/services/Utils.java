package com.ntendencia.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ntendencia.services.exceptions.ErroAoCarregarJsonException;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Utils {
    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getMensagemValidacao(final String chaveMensagem, final Object... params) {
        ResourceBundle bundle = ResourceBundle.getBundle("Validation", Locale.getDefault());
        String pattern = bundle.getString(chaveMensagem);

        return MessageFormat.format(pattern, params);
    }

    private static <T, E extends Collection<?>> T doGetMockObject(String mockFolder, String fileName, Class<T> targetClazz,
                                                                  @Nullable Class<E> collectionClazz) {
        String filePath = mockFolder + "/" + fileName;
        try (InputStream is = Utils.class.getResourceAsStream(filePath)) {
            if (collectionClazz != null) {
                CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(collectionClazz, targetClazz);
                return mapper.readValue(is, collectionType);
            } else {
                return mapper.readValue(is, targetClazz);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ErroAoCarregarJsonException("Um erro ocorreu ao carregar o JSON de teste: " + filePath);
        }
    }

    public static <T> T getMockObject(String mockFolder, String fileName, Class<T> targetClazz) {
        return doGetMockObject(mockFolder, fileName, targetClazz, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getMockObjectList(String mockFolder, String fileName, Class<T> targetClazz) {
        return (List<T>) doGetMockObject(mockFolder, fileName, targetClazz, List.class);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}