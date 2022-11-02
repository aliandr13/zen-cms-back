package xyz.zen.cms.admin.model.converter;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DateConverter implements Converter<Date, LocalDate> {
    @Override
    public LocalDate convert(Date date) {
        if(date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
