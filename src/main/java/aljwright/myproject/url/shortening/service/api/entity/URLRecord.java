package aljwright.myproject.url.shortening.service.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class URLRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long urlId;
    private String longUrl;
    private String shortUrl;

}
