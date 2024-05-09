package aljwright.myproject.url.shortening.service.api.repository;

import aljwright.myproject.url.shortening.service.api.entity.URLRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLShorteningAPIRepository extends CrudRepository<URLRecord,Long> {

}
