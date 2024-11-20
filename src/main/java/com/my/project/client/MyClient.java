package com.my.project.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.my.project.model.User;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.my.project.utils.SignUtil.getSign;

/**
 * @Description
 * @Author
 * @Create 17:43
 */
@Data
public class MyClient {


    private String accessKey;

    private String secretKey;

    public MyClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getName(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result1 = HttpUtil.get("http://localhost:7343/api/name/?", paramMap);
        System.out.println(result1);
        return result1;
    }

    public String postName(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result2 = HttpUtil.post("http://localhost:7343/api/name/", paramMap);
        System.out.println(result2);
        return  result2;
    }

    private Map<String,String> getHeaderMap(String headers){
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("accessKey",accessKey);
        hashMap.put("headers",headers);
        //用户参数+秘钥+加密算法=sign
        hashMap.put("sign",getSign(headers,secretKey));
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));

        return hashMap;
    }


    public String postNameByBody(User user){
        String jsonStr = JSONUtil.toJsonStr(user);
        HttpResponse execute = HttpRequest.post("http://localhost:7343/api/name/user")
                .addHeaders(getHeaderMap(jsonStr))
                .body(jsonStr)
                .execute();
        System.out.println(execute.getStatus());
        String result3 = execute.body();
        System.out.println(result3);
        return result3;
    }



}
