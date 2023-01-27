package com.ecommerce.secondhand.user.service;

import com.ecommerce.secondhand.user.exception.AdvertisementNotFoundException;
import com.ecommerce.secondhand.user.model.dto.AdvertisementDTO;
import com.ecommerce.secondhand.user.model.dto.AdvertisementDTOConverter;
import com.ecommerce.secondhand.user.model.dto.CreateAdvertisementRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateAdvertisementRequest;
import com.ecommerce.secondhand.user.model.entity.Advertisement;
import com.ecommerce.secondhand.user.repository.AdvertisementElasticSearchRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class AdvertisementService {
    private final AdvertisementElasticSearchRepository advertisementElasticSearchRepository;
    private final AdvertisementDTOConverter advertisementDTOConverter;

    public AdvertisementService(AdvertisementElasticSearchRepository advertisementElasticSearchRepository, AdvertisementDTOConverter advertisementDTOConverter) {
        this.advertisementElasticSearchRepository = advertisementElasticSearchRepository;
        this.advertisementDTOConverter = advertisementDTOConverter;
    }

    public List<AdvertisementDTO> getAllAdvertisementBySearch(String search){
        return advertisementDTOConverter.convert(advertisementElasticSearchRepository
                .findByTitleLike(search));
    }
    public AdvertisementDTO addAdvertisement(CreateAdvertisementRequest createAdvertisementRequest){
        Advertisement addedAdvertisement = new Advertisement(
                createAdvertisementRequest.getTitle(),
                createAdvertisementRequest.getDescription(),
                createAdvertisementRequest.getPrice()
        );
        return advertisementDTOConverter.convert(advertisementElasticSearchRepository.save(addedAdvertisement));
    }

    public AdvertisementDTO updateAdvertisementById(String id,UpdateAdvertisementRequest updateAdvertisementRequest){
        Advertisement updatedAdvertisement = getAdvertisementById(id);

        updatedAdvertisement.setLastModifiedDate(Calendar.getInstance().getTime());
        updatedAdvertisement.setDescription(updateAdvertisementRequest.getDescription());
        updatedAdvertisement.setTitle(updateAdvertisementRequest.getTitle());
        updatedAdvertisement.setPrice(updateAdvertisementRequest.getPrice());

        return this.advertisementDTOConverter
                .convert(this.advertisementElasticSearchRepository.save(updatedAdvertisement));
    }

    public void deleteAdvertisementById(String id){
        getAdvertisementById(id);
        this.advertisementElasticSearchRepository.deleteById(id);
    }

    protected Advertisement getAdvertisementById(String id){
        return this.advertisementElasticSearchRepository
                .findById(id)
                .orElseThrow(()-> new AdvertisementNotFoundException("Advertisement Couldn't be found by following id : "+id));
    }

}
