package com.dokylee.api_test2.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 클라이언트 요청의 진입점이 되는 Class
public class HelloController {
    @GetMapping(value = "/helloworld/string")
    @ResponseBody   // @ResonseBody를 지정하지 않으면 return에 지정된 “helloworld” 이름으로 된 파일을 프로젝트 경로에서 찾아 화면에 출력
    public String helloworldString() {
        return "helloworld";
    }

    @GetMapping(value = "/helloworld/json")
    @ResponseBody
    public Hello helloworldJson() {
        Hello hello = new Hello();
        hello.message = "helloworld";
        return hello;
    }

    /* ResponseBody를 지정하지 않으면 Spring에서는 해당 결과를 표시해주기 위한 페이지를 찾는데 해당 페이지가 없으므로 404에러가 발생 */
    @GetMapping(value = "/helloworld/page")
    public String helloworld() {
        return "helloworld";
    }

    @Getter
    @Setter
    public static class Hello {
        private String message;
    }

}
