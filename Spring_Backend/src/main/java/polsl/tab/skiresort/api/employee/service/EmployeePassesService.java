package polsl.tab.skiresort.api.employee.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.employee.response.PassesResponse;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.repository.PassRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeePassesService {

    private final PassRepository passRepository;

    public EmployeePassesService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    private PassesResponse mapPassResponse(Pass pass) {
        return new PassesResponse(
                pass.getUnitPrice(),
                pass.getInvoicesIdInvoice().getUserIdUser().getEmail(),
                pass.getStartDate(),
                pass.getEndDate(),
                pass.getFirstName(),
                pass.getLastName(),
                pass.getBirthDate(),
                pass.getUsesTotal(),
                pass.getUsesLeft()
        );
    }

    public List<PassesResponse> getActivePasses() {
        return passRepository
                .getAllActivePasses()
                .stream()
                .map(this::mapPassResponse)
                .collect(Collectors.toList());
    }

    public List<PassesResponse> getAllPasses() {
        return passRepository
                .findAll()
                .stream()
                .map(this::mapPassResponse)
                .collect(Collectors.toList());
    }

    public PassesResponse setPassBlock(Integer passId) {
        Pass pass=passRepository.findById(passId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Pass not found"));
        pass.setBlocked(!pass.getBlocked());
        passRepository.save(pass);
        return mapPassResponse(pass);
    }

}
