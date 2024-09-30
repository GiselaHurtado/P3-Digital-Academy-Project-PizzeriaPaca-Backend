package org.factoriaf5.pizzeriapaca.uploadimage.firebase.repository;

import org.factoriaf5.pizzeriapaca.uploadimage.firebase.models.FileRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirebaseRepository extends JpaRepository<FileRecord, Long> {

}
