package com.ems.springboot.utils;

import com.ems.springboot.model.Designation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Custom converter which converts String from JSP forms to Designation.
 * Expects the string to be in the following format -> [id: 1, name: HR]
 */
@Component
public class DesignationConverter implements Converter<String, Designation> {
    /**
     * @return {@link Designation}
     * <p>
     * which is used by Spring under the hood whenever any entry is
     * submitted on the frontend.
     */
    @Override
    public Designation convert(String source) {
        ObjectMapper mapper = new ObjectMapper();
        Designation designation;
        try {
            designation = mapper.readValue(source, Designation.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Matcher matcher = Pattern.compile("(\\w{0,3} \\S{1,2})[,|\\]]").matcher(designation.toString()); //old regex for future reference [ ](\\S{1,2})[,|\\]]")
        List<String> result = new ArrayList<>();                                         //didn't consider first group of sequence in "SDE I"
        while (matcher.find()) result.add(matcher.group(1).trim());
        return new Designation(Integer.parseInt(result.get(0)), result.get(1));
    }
}
