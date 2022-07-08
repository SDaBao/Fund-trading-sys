package com.demo.server;

import com.demo.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.StringWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CloudComponent
public class SystemServiceImpl implements SystemService {


    @Autowired(required = false)
    private SystemDAOMapper systemDAO;
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ObjectMapper mapper = new ObjectMapper();
    private Map<Integer, User> userMap = new HashMap<Integer, User>();

    private LocalDate systemDate = LocalDate.now();

    public SystemServiceImpl(){
        if(systemDate.getDayOfWeek() == DayOfWeek.SATURDAY){
            systemDate = systemDate.plusDays(2);
        }else if(systemDate.getDayOfWeek() == DayOfWeek.SUNDAY){
            systemDate = systemDate.plusDays(1);
        }
    }

    public void initMarket(Product product){
        LocalDate earliestDay = systemDate.minusYears(2);
    }

    public static String toJson(Object obj){
        String json = null;
        try {
            StringWriter writer = new StringWriter();
            JsonGenerator generator = mapper.getFactory().createGenerator(writer);
            mapper.writeValue(generator, obj);
            json = writer.toString();
            generator.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public LocalDate increaseDate(LocalDate date) {
        if(systemDate.getDayOfWeek() == DayOfWeek.FRIDAY){
            return systemDate.plusDays(3);
        }else{
            return systemDate.plusDays(1);
        }
    }

    public LocalDate decreaseDate(LocalDate date){
        if(systemDate.getDayOfWeek() == DayOfWeek.MONDAY){
            return systemDate.minusDays(3);
        }else{
            return systemDate.minusDays(1);
        }
    }

    public String setDate(String date){
        System.out.println(date);
        systemDate = LocalDate.parse(date, fmt);
        return "Successfully set system date to " + date;
    }

    public String getDate(){
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("date", systemDate.format(fmt));
            return toJson(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("date", "error");
        return toJson(map);
    }

    public void importMarket() {
        try {
            systemDAO.importMarket(systemDate.format(fmt), decreaseDate(systemDate).format(fmt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String systemClear() {
        importMarket();
        Map<String, Object> map = new HashMap<>();
        try {
            systemDAO.systemClear(systemDate.format(fmt));
            map.put("error_code", 200);
            map.put("result", "System clear successfully executed.");
            return toJson(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("error_code", 500);
        map.put("result", "Failed to clear.");
        return toJson(map);
    }

    public String systemRollback() {
        Map<String, Object> map = new HashMap<>();
        try {
            systemDAO.systemRollback(systemDate.format(fmt));
            map.put("error_code", 200);
            map.put("result", "System rollback successfully executed.");
            return toJson(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("error_code", 500);
        map.put("result", "Failed to rollback.");
        return toJson(map);
    }
}
