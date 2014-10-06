package cn.mobiledaily.p2plite.web;

import cn.mobiledaily.p2plite.common.RestError;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by johnson on 14-10-6.
 */
@ControllerAdvice
public class RestExceptionProcessor {
    private static final int ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_CODE = 1001;

    @Inject
    private MessageSource messageSource;
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestError illegalArgumentException(HttpServletRequest req, IllegalArgumentException ex) {
        String errorMessage = localizeErrorMessage(ex.getMessage());
        String errorURL = req.getRequestURL().toString();
        return new RestError(HttpStatus.BAD_REQUEST, ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE_CODE, errorMessage, null, errorURL, ex);
    }

//    @ExceptionHandler(MemberNotFoundException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public RestError memberNotFoundException(HttpServletRequest req, MemberNotFoundException ex) {
//        String errorMessage = localizeErrorMessage("error.no.member.id") + ex.getMemberId();
//        String errorURL = req.getRequestURL().toString();
//        return new RestError(HttpStatus.NOT_FOUND, MEMBER_NOT_FOUND_MESSAGE_CODE, errorMessage, null, errorURL, ex);
//    }
    public String localizeErrorMessage(String errorCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(errorCode, null, locale);
    }
}
