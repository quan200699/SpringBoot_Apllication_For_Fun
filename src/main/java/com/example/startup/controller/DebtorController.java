package com.example.startup.controller;

import com.example.startup.exception.EmailUniqueException;
import com.example.startup.exception.UsernameUniqueException;
import com.example.startup.model.dto.DebtorDto;
import com.example.startup.model.entity.Debtor;
import com.example.startup.service.debtor.IDebtorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/debtors")
public class DebtorController {
    @Autowired
    private IDebtorService debtorService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<DebtorDto>> getAllDebtors(@RequestParam int page, @RequestParam int size) {
        List<Debtor> debtors = (List<Debtor>) debtorService.findAll(page, size);
        List<DebtorDto> debtorDtoList = debtors.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(debtorDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebtorDto> getDebtorById(@PathVariable Long id) {
        Optional<Debtor> debtorOptional = debtorService.findById(id);
        return debtorOptional.map(debtor -> new ResponseEntity<>(convertToDto(debtor), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Debtor> createNewDebtor(@Valid @RequestBody DebtorDto debtorDto) throws UsernameUniqueException, EmailUniqueException {
        Optional<Debtor> debtorOptional = debtorService.findByUsername(debtorDto.getUsername());
        if (debtorOptional.isPresent()) {
            throw new UsernameUniqueException();
        }
        debtorOptional = debtorService.findByEmail(debtorDto.getEmail());
        if (debtorOptional.isPresent()) {
            throw new EmailUniqueException();
        }
        Debtor debtor = convertToEntity(debtorDto);
        debtor.setPassword(passwordEncoder.encode(debtor.getPassword()));
        return new ResponseEntity<>(debtorService.save(debtor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DebtorDto> updateDebtorById(@PathVariable Long id, @Valid @RequestBody DebtorDto debtorDto) {
        Optional<Debtor> debtorOptional = debtorService.findById(id);
        if (!debtorOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return debtorOptional.map(debtor -> {
            Debtor newDebtor = convertToEntity(debtorDto);
            newDebtor.setId(debtor.getId());
            return new ResponseEntity<>(convertToDto(debtorService.save(newDebtor)), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DebtorDto> deleteDebtor(@PathVariable Long id) {
        Optional<Debtor> debtorOptional = debtorService.findById(id);
        if (!debtorOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return debtorOptional.map(debtor -> {
            debtorService.remove(id);
            return new ResponseEntity<>(convertToDto(debtor), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameUniqueException.class)
    public Map<String, String> handleUsernameUniqueException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "username đã tồn tại");
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailUniqueException.class)
    public Map<String, String> handleEmailUniqueException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "email đã tồn tại");
        return errors;
    }

    private DebtorDto convertToDto(Debtor debtor) {
        return modelMapper.map(debtor, DebtorDto.class);
    }

    private Debtor convertToEntity(DebtorDto debtorDto) {
        return modelMapper.map(debtorDto, Debtor.class);
    }
}
