package polsl.tab.skiresort.api.passes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.passes.request.PassRequest;
import polsl.tab.skiresort.api.passes.response.PassResponse;
import polsl.tab.skiresort.api.passes.service.PassService;

import java.util.List;

@RestController
@RequestMapping("/api/passes")
public class PassesApi {

    private final PassService passService;

    public PassesApi(PassService passService) {
        this.passService = passService;
    }

    @GetMapping
    public ResponseEntity<List<PassResponse>> getAllUserPasses(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(passService.getAllUserPasses(token));
    }

    @GetMapping("/active")
    public ResponseEntity<List<PassResponse>> getAllUserActivePasses(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(passService.getAllUserActivePasses(token));
    }

    @GetMapping("/{passId}")
    public ResponseEntity<PassResponse> getPassById(@RequestHeader("Authorization") String token,
                                                    @PathVariable("passId") Integer passId
    ) {
        return ResponseEntity.ok(passService.getPassById(token, passId));
    }

    @GetMapping(value = "/qr/{passId}", produces = "image/jpg")
    public @ResponseBody byte[] generateQr(@RequestHeader("Authorization") String token,
                                           @PathVariable("passId") Integer passId
    ) {
        return passService.generateQr(token, passId);
    }

    @PostMapping("/{invoiceId}")
    public ResponseEntity<List<PassResponse>> addPassToUserInvoice(@RequestHeader("Authorization") String token,
                                                            @PathVariable("invoiceId") Integer invoiceId,
                                                            @RequestBody PassRequest request
    ) {
        return ResponseEntity.ok(passService.addPassToUserInvoice(token, invoiceId, request));
    }

    @PutMapping("/{passId}")
    public ResponseEntity<PassResponse> editSinglePass(@RequestHeader("Authorization") String token,
                                                @PathVariable("passId") Integer passId,
                                                @RequestBody PassRequest request
    ) {
        return ResponseEntity.ok(passService.editSinglePass(token, passId, request));
    }

    @DeleteMapping("/{invoiceId}/{passId}")
    public ResponseEntity<PassResponse> deletePassFromInvoice(@RequestHeader("Authorization") String token,
                                                              @PathVariable("invoiceId") Integer invoiceId,
                                                              @PathVariable("passId") Integer passId
    ) {
        return ResponseEntity.ok(passService.deletePassFromInvoice(token, invoiceId, passId));
    }

}
