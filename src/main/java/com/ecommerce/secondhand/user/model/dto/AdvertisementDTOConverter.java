package com.ecommerce.secondhand.user.model.dto;

import com.ecommerce.secondhand.user.model.entity.Advertisement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdvertisementDTOConverter {

    public AdvertisementDTO convert(Advertisement from){
        return new AdvertisementDTO(
                from.getId(),
                from.getTitle(),
                from.getDescription(),
                from.getPrice(),
                from.getUserId(),
                from.getCreationDate(),
                from.getLastModifiedDate()
        );
    }
    public List<AdvertisementDTO> convert (List<Advertisement> fromList){
        return fromList.stream().map(from ->
                new AdvertisementDTO(
                        from.getId(),
                        from.getTitle(),
                        from.getDescription(),
                        from.getPrice(),
                        from.getUserId(),
                        from.getCreationDate(),
                        from.getLastModifiedDate()
                )).collect(Collectors.toList());
    }
}
