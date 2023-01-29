package com.ecommerce.secondhand.user.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Calendar;
import java.util.Date;

@Document(indexName = "advertisement")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Advertisement {
    @Id
    String id;
    @NonNull
    @Field(type = FieldType.Keyword)
    String title;
    @NonNull
    @Field(type = FieldType.Keyword)
    String description;
    @NonNull
    Double price;

    @NonNull
    Long userId;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    Date creationDate = Calendar.getInstance().getTime();
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    Date lastModifiedDate = Calendar.getInstance().getTime();


}
