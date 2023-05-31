package com.bbatista.konex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbatista.konex.model.entity.MedicamentoEntity;

@Repository("medicamentoRepository")
public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Long>{
	
	@Modifying
	@Query(value = "UPDATE K_MEDICAMENTOS SET M_STOCK = M_STOCK - ?2 WHERE M_ID = ?1",  nativeQuery = true)
	void updateInventory(Long id, Integer cantidad);

}
