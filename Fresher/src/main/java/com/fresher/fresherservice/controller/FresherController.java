package com.lthdv.authservice.controller;

import com.lthdv.authservice.model.Fresher;
import com.lthdv.authservice.service.FresherService;
import com.lthdv.authservice.vo.dto.SearchFresherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lthdv.authservice.vo.response.ResponseEntity.responseToClient;


@RestController
@RequestMapping("/api/freshers")
@RequiredArgsConstructor
public class FresherController {

    private final FresherService fresherService;

    @GetMapping
//    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN', 'ROLE_MANAGER')")
    public Object getAllFreshers(SearchFresherDTO dataParams) {
        Object resultObj = fresherService.getAllFreshers(dataParams);
        return new ResponseEntity<>(responseToClient(resultObj), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Fresher getFresherById(@PathVariable Long id) {
        return fresherService.getFresherById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Fresher createFresher(@RequestBody Fresher fresher) {
        return fresherService.saveFresher(fresher);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Fresher updateFresher(@PathVariable Long id, @RequestBody Fresher fresher) {
        fresher.setId(id);
        return fresherService.saveFresher(fresher);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteFresher(@PathVariable Long id) {
        fresherService.deleteFresher(id);
    }

}
