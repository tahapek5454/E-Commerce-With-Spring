package com.ecommerce.secondhand.user.controller;

import com.ecommerce.secondhand.user.model.dto.AdvertisementDTO;
import com.ecommerce.secondhand.user.model.dto.CreateAdvertisementRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateAdvertisementRequest;
import com.ecommerce.secondhand.user.model.entity.Advertisement;
import com.ecommerce.secondhand.user.service.AdvertisementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/advertisement")
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping
    public ResponseEntity<AdvertisementDTO> addAdvertisement(@RequestBody CreateAdvertisementRequest createAdvertisementRequest){
        return ResponseEntity.ok(this.advertisementService.addAdvertisement(createAdvertisementRequest));
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<AdvertisementDTO>> getAllAdvertisementBySearch(@PathVariable String search){
        return ResponseEntity.ok(this.advertisementService.getAllAdvertisementBySearch(search));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvertisementDTO> updateAdvertisementById(
            @PathVariable String id, @RequestBody UpdateAdvertisementRequest updateAdvertisementRequest){
        return ResponseEntity.ok(this.advertisementService.updateAdvertisementById(id, updateAdvertisementRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvertisementById(@PathVariable String id){
        this.advertisementService.deleteAdvertisementById(id);
        return ResponseEntity.ok().build();
    }


}
