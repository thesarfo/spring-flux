package dev.thesarfo.springflux.controller;

import dev.thesarfo.springflux.dto.EmployeeDto;
import dev.thesarfo.springflux.repository.EmployeeRepository;
import dev.thesarfo.springflux.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.saveEmployee(employeeDto);
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<EmployeeDto> getEmployee(@PathVariable("id") String id){
        return employeeService.getEmployee(id);
    }

    @GetMapping
    public Flux<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PutMapping("{id}")
    public Mono<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto,
                                            @PathVariable("id") String employeeId){
        return employeeService.updateEmployee(employeeDto, employeeId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteEmployee(@PathVariable("id") String employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

}

