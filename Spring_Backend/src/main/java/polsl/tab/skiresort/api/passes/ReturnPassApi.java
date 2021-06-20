package polsl.tab.skiresort.api.passes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.passes.response.PassResponse;
import polsl.tab.skiresort.api.passes.service.ReturnPassService;

@RestController
@RequestMapping("/api/return_passes")
public class ReturnPassApi {

    private final ReturnPassService returnPassService;

    public ReturnPassApi(ReturnPassService returnPassService) {this.returnPassService = returnPassService;}

    @DeleteMapping("/{passId}")
    public ResponseEntity<PassResponse> returnUnusedPass(
            @RequestHeader("Authorization") String token,
            @PathVariable("passId") Integer passId) {
        return ResponseEntity.ok(returnPassService.returnPass(token, passId));
    }
}
