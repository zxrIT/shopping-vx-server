package com.ZengXiangRui.Shopping.controller;

import com.ZengXiangRui.Shopping.entity.ai.AIRequestParam;
import com.ZengXiangRui.Shopping.entity.ai.AIResult;
import com.ZengXiangRui.Shopping.entity.response.BaseResponse;
import com.ZengXiangRui.Shopping.util.JsonSerialization;
import com.ZengXiangRui.Shopping.util.JsonUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.StringReader;

@RestController
@CrossOrigin
@SuppressWarnings("all")
@RequestMapping("/ai")
public class AIController {
    private final static String aiModel = "deepseek-r1:8b";
    private final static String aiUrl = "http://localhost:11434/api/generate";

    @PostMapping("/deepSeek/R1")
    public String getAIResultDeepSeekR18B(@RequestBody AIRequestParam requestParam) throws Exception {
        requestParam.setModel(aiModel);
        System.out.println(requestParam.getPrompt());
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(aiUrl);
        String requestJson = JsonSerialization.toJson(requestParam);
        httpPost.setEntity(new StringEntity(requestJson));
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        String responseBody = EntityUtils.toString(response.getEntity());
        BufferedReader bufferedReader = new BufferedReader(new StringReader(responseBody));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            AIResult aiResult = JsonUtil.jsonToObject(line, AIResult.class);
            stringBuilder.append(aiResult.getResponse());
        }
        return JsonSerialization.toJson(new BaseResponse<String>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, stringBuilder.toString()
        ));
    }
}
