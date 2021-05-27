package com.harrydrummond.dxfdetailer.webapp.repository;

import com.harrydrummond.dxfdetailer.webapp.model.DXFFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DXFDbRepository extends JpaRepository<DXFFile, Long> {
}