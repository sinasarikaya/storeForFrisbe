package com.sina.store.component;

import com.sina.store.exception.StoreBulkException;
import com.sina.store.exception.StoreException;
import com.sina.store.model.dto.ErrorWrapperDto;
import com.sina.store.property.BasePropertyConfig;
import com.sina.store.property.ErrorMessageDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
@AllArgsConstructor
@Slf4j
public class ExceptionUtil {

    private static final String DEFAULT_LANGUAGE = "tr";
    private static final String NO_DEFINITION_MESSAGE = "9000";

    private static final Map<String, BasePropertyConfig> ALL_MESSAGES = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static {
        Yaml yaml = new Yaml();
        try {
            InputStream inputStreamTr = ExceptionUtil.class.getClassLoader().getResourceAsStream("rest_messages_tr.yaml");
            BasePropertyConfig turkishConfig = yaml.loadAs(inputStreamTr, BasePropertyConfig.class);
            ALL_MESSAGES.put("tr", turkishConfig);
            ALL_MESSAGES.put("tr-tr,tr;q=0.9,en-us;q=0.8,en;q=0.7", turkishConfig);
            ALL_MESSAGES.put("tr, tr", turkishConfig);
            ALL_MESSAGES.put("tr, tr-TR", turkishConfig);
            ALL_MESSAGES.put("tr-TR", turkishConfig);
            ALL_MESSAGES.put("en-us", turkishConfig);
        } catch (Exception e) {
            log.error("HATA MESAJLARI YUKLENEMEDI. RESOURCES ALTINDA rest_messages_tr.yaml OLMAYABİLİR");
        }
        try {
            InputStream inputStreamTr = ExceptionUtil.class.getClassLoader().getResourceAsStream("rest_messages_en.yaml");
            BasePropertyConfig englishConfig = yaml.loadAs(inputStreamTr, BasePropertyConfig.class);
            ALL_MESSAGES.put("en", englishConfig);
        } catch (Exception e) {
            log.error("HATA MESAJLARI YUKLENEMEDI. RESOURCES ALTINDA rest_messages_en.yaml OLMAYABİLİR");
        }
    }

    public String getMessage(final int errorCode, final String languageKey) {
        return getErrorMessageDefinition(errorCode, languageKey).getMessage();
    }

    public String getDescription(final int errorCode, final String languageKey) {
        return getErrorMessageDefinition(errorCode, languageKey).getDescription();
    }

    public String[] getFields(final int errorCode, final String languageKey) {
        String fields = getErrorMessageDefinition(errorCode, languageKey).getFields();
        return fields == null ? null : fields.split(",");
    }

    private ErrorMessageDefinition getErrorMessageDefinition(int errorCode, String languageKey) {
        ErrorMessageDefinition errorMessageDefinition = ALL_MESSAGES.getOrDefault(languageKey, ALL_MESSAGES.get("tr")).getMessages().get(String.valueOf(errorCode));
        if (errorMessageDefinition == null) {
            errorMessageDefinition = ALL_MESSAGES.get(languageKey).getMessages().get(NO_DEFINITION_MESSAGE);
        }
        return errorMessageDefinition;
    }

    public String getMessage(final int errorCode) {
        return getMessage(errorCode, DEFAULT_LANGUAGE);
    }

    public String getDescription(final int errorCode) {
        return getDescription(errorCode, DEFAULT_LANGUAGE);
    }

    public String[] getFields(final int errorCode) {
        return getFields(errorCode, DEFAULT_LANGUAGE);
    }

    public String checkLanguage(String language) {
        String lang = StringUtils.isEmpty(language) ? "tr" : language.toLowerCase();
        if (lang.equalsIgnoreCase("tr-tr")) {
            return "tr";
        }
        return lang;
    }

    public ErrorWrapperDto convert(StoreException exception, String language, String applicationName) {
        ErrorWrapperDto dto = convert(exception, language);
        dto.setApplicationName(applicationName);
        return dto;
    }

    public ErrorWrapperDto convert(StoreException exception, String language) {
        ErrorWrapperDto errorWrapperDto = new ErrorWrapperDto();
        errorWrapperDto.setCode(exception.getCode());
        language = checkLanguage(language);
        String description = getDescription(exception.getCode(), language);
        if (description != null && !description.trim().isEmpty()) {
            if (exception.getPlaceHolders() != null) {
                errorWrapperDto.setDescription(String.format(description, (Object[]) exception.getPlaceHolders()));
            } else {
                errorWrapperDto.setDescription(description);
            }
        } else {
            errorWrapperDto.setDescription(exception.getDescription());
        }

        String[] field = getFields(exception.getCode(), language);
        if (field != null) {
            errorWrapperDto.setFields(field);
        } else {
            errorWrapperDto.setFields(exception.getFields());
        }

        String message = getMessage(exception.getCode(), language);
        if (message != null && !message.trim().isEmpty()) {
            if (exception.getPlaceHolders() != null) {
                errorWrapperDto.setMessage(String.format(message, (Object[]) exception.getPlaceHolders()));
            } else {
                errorWrapperDto.setMessage(message);
            }
        } else {
            errorWrapperDto.setMessage(exception.getMessage());
        }
        return errorWrapperDto;
    }


    public ErrorWrapperDto convert(StoreBulkException bulkException, String language, String applicationName) {
        ErrorWrapperDto dto = convert(bulkException, language);
        dto.setApplicationName(applicationName);
        return dto;
    }

    public ErrorWrapperDto convert(StoreBulkException bulkException, String language) {
        List<ErrorWrapperDto> errorWrapperDtos = new ArrayList<>();
        for (StoreException exception : bulkException.getExceptions()) {
            errorWrapperDtos.add(convert(exception, language));
        }
        return new ErrorWrapperDto(errorWrapperDtos);
    }
}
