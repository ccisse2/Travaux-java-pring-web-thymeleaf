package fr.eni.demo.controller.converter;

import fr.eni.demo.bll.CoursService;
import fr.eni.demo.bo.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCoursConverter implements Converter<String, Cours> {

    private CoursService service;

    @Autowired
    public void setService(CoursService service) {
        this.service = service;
    }

    @Override
    public Cours convert(String id) {
        Integer idInt = Integer.parseInt(id);
        return service.findById(idInt);
    }

}
