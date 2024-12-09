package com.multiversa.SistAcad.Repository;

import com.multiversa.SistAcad.model.DisciplinaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<DisciplinaModel, Long> {

}
