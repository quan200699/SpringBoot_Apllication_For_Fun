package com.example.startup.controller;

import com.example.startup.model.dto.DebtDto;
import com.example.startup.model.entity.Debt;
import com.example.startup.service.debt.IDebtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/debts")
public class DebtController {
    @Autowired
    private IDebtService debtService;

    @Autowired
    private ModelMapper modelMapper;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping
    public ResponseEntity<List<DebtDto>> getAllDebts(@RequestParam int page, @RequestParam int size) {
        List<Debt> debtors = debtService.findAll(page, size).getContent();
        List<DebtDto> debtDtos = debtors.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(debtDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebtDto> getDebtById(@PathVariable Long id) {
        Optional<Debt> debtOptional = debtService.findById(id);
        return debtOptional.map(debt -> new ResponseEntity<>(convertToDto(debt), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<DebtDto> createNewDebt(@Valid @RequestBody DebtDto debtDto) {
        Debt debt = convertToEntity(debtDto);
        debtService.save(debt);
        return new ResponseEntity<>(debtDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DebtDto> updateDebtById(@PathVariable Long id, @Valid @RequestBody DebtDto debtDto) {
        Optional<Debt> debtOptional = debtService.findById(id);
        if (!debtOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return debtOptional.map(debt -> {
            Debt newDebt = convertToEntity(debtDto);
            newDebt.setId(debt.getId());
            return new ResponseEntity<>(convertToDto(debtService.save(newDebt)), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DebtDto> deleteDebtor(@PathVariable Long id) {
        Optional<Debt> debtOptional = debtService.findById(id);
        if (!debtOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return debtOptional.map(debt -> {
            debtService.remove(id);
            return new ResponseEntity<>(convertToDto(debt), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private DebtDto convertToDto(Debt debt) {
        return modelMapper.map(debt, DebtDto.class);
    }

    private Debt convertToEntity(DebtDto debtDto) {
        return modelMapper.map(debtDto, Debt.class);
    }
}
