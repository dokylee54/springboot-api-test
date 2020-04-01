package com.dokylee.api_test2.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends CommonResult {
    // T는 타입이 아직 정해져 있지않은 Genric 변수
    private T data;
}
