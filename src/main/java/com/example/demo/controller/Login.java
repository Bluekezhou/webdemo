package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class Login {
    @RequestMapping(value = "/fastjson", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject test(@RequestBody String data) {
        JSONObject obj = JSON.parseObject(data);
        // JSONObject obj = JSON.parseObject(data, Feature.SupportNonPublicField); // 当使用 TemplatesImpl的时候用这个
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("message", "success");
        result.put("data", "Hello " + obj.get("name"));
        return result;
    }

    @RequestMapping(value = "/command", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject run(@RequestBody String data) {
        // JSONObject obj = JSON.parseObject(data, Feature.SupportNonPublicField); // 当使用 TemplatesImpl的时候用这个
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("message", "success");
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(new String[]{"cmd", "/c", data});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
