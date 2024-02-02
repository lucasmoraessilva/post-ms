package com.lucasmoraessilva.postms.business.client;

import com.lucasmoraessilva.postms.business.client.dto.UserDTOIn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="userClient", url = "http://localhost:8080")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/user/{redactorId}")
    public UserDTOIn getRedactor(@PathVariable("redactorId") Integer redactorId);
}
