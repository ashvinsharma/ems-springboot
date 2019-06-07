package com.ems.springboot.utils;

import com.ems.springboot.model.Designation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Custom converter required by Spring under the hood to convert
 * String (usually data from some kind of form) to models which are used inside other models
 * implements {@link Converter} to Override convert method
 */
@Component
public class DesignationConverter implements Converter<String, Designation> {
    /**
     * @return {@link Designation}
     * <p>
     * which is used by Spring under the hood whenever any entry is
     * submitted on the frontend.
     * converts String from JSP forms to Designation.
     *
     * Expects the string to be in the following format -> [id: 1, name: HR]
     * which corresponds to the result of <code>Designation.toString()</code>
     */
    @Override
    public Designation convert(String source) {
        final Logger LOGGER = Logger.getLogger(DesignationConverter.class.getName());
        ObjectMapper mapper = new ObjectMapper();
        Designation designation;
        try {
            designation = mapper.readValue(source, Designation.class);
        } catch (JsonMappingException | JsonParseException e) {
            LOGGER.severe(e.getOriginalMessage());
            return null;
        } catch (IOException e) {
            return null;
        }

        Matcher matcher = Pattern.compile("(\\w{0,3} \\S{1,2})[,|\\]]").matcher(designation.toString()); //old regex for future reference [ ](\\S{1,2})[,|\\]]")
        List<String> result = new ArrayList<>();                                         //didn't consider first group of sequence in "SDE I"
        while (matcher.find()) result.add(matcher.group(1).trim());
        return new Designation(Integer.parseInt(result.get(0)), result.get(1));
    }
}
