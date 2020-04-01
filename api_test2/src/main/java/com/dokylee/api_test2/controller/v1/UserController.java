package com.dokylee.api_test2.controller.v1;

import com.dokylee.api_test2.entity.User;
import com.dokylee.api_test2.model.response.CommonResult;
import com.dokylee.api_test2.model.response.ListResult;
import com.dokylee.api_test2.model.response.SingleResult;
import com.dokylee.api_test2.repo.UserJpaRepo;
import com.dokylee.api_test2.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// UserController를 대표하는 최상단 타이틀 영역에 표시될 값을 세팅
@Api(tags = {"1. User"})
// class내부에 final로 선언된 객체에 대해서 Constructor Injection을 수행합니다. 해당 어노테이션을 사용하지 않고 선언된 객체에 @Autowired를 사용해도 됨
@RequiredArgsConstructor
// 결과값을 JSON으로 출력
@RestController
// api resource를 버전별로 관리하기 위해 /v1 을 모든 리소스 주소에 적용되도록 처리
@RequestMapping(value = "/v1")
public class UserController {
    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService;

    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회합니다")
    @GetMapping(value = "/user")
    public ListResult<User> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다")
    @GetMapping(value = "/user/{msrl}")
    public SingleResult<User> findUserById(@ApiParam(value = "회원ID", required = true) @PathVariable long msrl) {
        // 결과데이터가 단일건인경우 getBasicResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findById(msrl).orElse(null));
    }

    // 각각의 resource에 제목과 설명을 표시하기 위해 세팅
    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다")
    // user객체를 전달하면 다음과 같이 내부적으로 insert문을 실행시켜 줌
    @PostMapping(value = "/user")
    // @ApiParam: 파라미터에 대한 설명을 보여주기 위해 세팅
    public SingleResult<User> save(@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
                                   @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam long msrl,
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .msrl(msrl)
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable long msrl) {
        userJpaRepo.deleteById(msrl);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }

}
