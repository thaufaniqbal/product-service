package com.banyuijo.foundation.repository;

import com.banyuijo.foundation.entity.SiteBaseProductParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SiteBaseProductParentRepository extends JpaRepository <SiteBaseProductParent, UUID> {

}
